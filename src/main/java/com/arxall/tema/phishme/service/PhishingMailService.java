package com.arxall.tema.phishme.service;

import com.arxall.tema.phishme.exception.ResourceNotFoundException;
import com.arxall.tema.phishme.model.Employee;
import com.arxall.tema.phishme.model.PhishingMailTemplate;
import com.arxall.tema.phishme.repository.PhishingMailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhishingMailService {
    private static final Logger log = LoggerFactory.getLogger(PhishingMailService.class);
    @Value( "${spring.application.baseUrl}" )
    private String applicationBaseUrl;

    private PhishingMailRepository phishingMailRepository;
    private MailService mailService;
    private EmployeeService employeeService;

    @Autowired
    public PhishingMailService(PhishingMailRepository phishingMailRepository, MailService mailService, EmployeeService employeeService) {
        this.phishingMailRepository = phishingMailRepository;
        this.mailService = mailService;
        this.employeeService = employeeService;
    }

    public List<PhishingMailTemplate> getAllPhishingEmailTemplates() {
        return phishingMailRepository.findAll();
    }

    public PhishingMailTemplate addPhishingEmailTemplate(PhishingMailTemplate phishingMailTemplate) {
        log.info("Saving phishing email template");
        return phishingMailRepository.save(phishingMailTemplate);
    }

    public void sendPhishingMailByTemplateId(String phishingMailTemplateId) {
        Optional<PhishingMailTemplate> phishingMailTemplateOptional = phishingMailRepository.findById(phishingMailTemplateId);

        if (phishingMailTemplateOptional.isPresent()) {
            log.info("Get phishing mail template with id " + phishingMailTemplateId);

            PhishingMailTemplate phishingMailTemplate = phishingMailTemplateOptional.get();
            List<Employee> employees = employeeService.getAllEmployees();
            String originalBody = phishingMailTemplate.getBody();

            employees.stream().forEach(employee -> {
                PhishingMailTemplate modifiedTemplate = phishingMailTemplate;
                modifiedTemplate.setBody(originalBody);
                modifiedTemplate.setRecipient(employee.getEmail());
                modifiedTemplate.setBody(
                        modifiedTemplate.getBody().replaceAll("<employee_name>", employee.getName()));
                modifiedTemplate.setBody(
                        modifiedTemplate.getBody().replaceAll("<phish_link>", applicationBaseUrl +
                                "/phishing-mail/record/" + employee.getId()));
                mailService.sendMail(modifiedTemplate);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        } else {
            log.error("Cannot get phishing mail template with id " + phishingMailTemplateId);
            throw new ResourceNotFoundException("Record not found with id : " + phishingMailTemplateId);
        }

    }

    public PhishingMailTemplate deletePhishingMailTemplate(String phishingMailTemplateId) {
        Optional<PhishingMailTemplate> deletedMailTemplateOptional = phishingMailRepository.findById(phishingMailTemplateId);

        if (deletedMailTemplateOptional.isPresent()) {
            PhishingMailTemplate deletedMailTemplate = deletedMailTemplateOptional.get();
            log.info("Deleting phishing email template with id " + phishingMailTemplateId);
            phishingMailRepository.delete(deletedMailTemplate);
            return deletedMailTemplate;
        } else {
            log.error("Cannot delete template - Does not exists in database. ");
            throw new ResourceNotFoundException("Record not found with id : " + phishingMailTemplateId);
        }


    }

    public PhishingMailTemplate updatePhishingMailTemplate(PhishingMailTemplate mailTemplate) {
        Optional < PhishingMailTemplate > mailTemplateOptional = phishingMailRepository.findById(mailTemplate.getId());

        if (mailTemplateOptional.isPresent()) {
            PhishingMailTemplate mailTemplateUpdate = mailTemplateOptional.get();
            mailTemplateUpdate.setId(mailTemplate.getId());
            mailTemplateUpdate.setSubject(mailTemplate.getSubject());
            mailTemplateUpdate.setSender(mailTemplate.getSender());
            mailTemplateUpdate.setBody(mailTemplate.getBody());

            log.info("Update phishing email template " + mailTemplate);
            phishingMailRepository.save(mailTemplateUpdate);
            return mailTemplateUpdate;
        } else {
            log.error("Cannot update phishing email template - Does not exists in database. ");
            throw new ResourceNotFoundException("Record not found with id : " + mailTemplate.getId());
        }
    }
}

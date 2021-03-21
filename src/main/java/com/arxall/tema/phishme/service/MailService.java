package com.arxall.tema.phishme.service;

import com.arxall.tema.phishme.exception.EmailException;
import com.arxall.tema.phishme.model.PhishingMailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private static final Logger log = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendMail(PhishingMailTemplate phishingMailTemplate) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(phishingMailTemplate.getSender());
            messageHelper.setTo(phishingMailTemplate.getRecipient());
            messageHelper.setSubject(phishingMailTemplate.getSubject());
            messageHelper.setText(phishingMailTemplate.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Phishing email sent to " + phishingMailTemplate.getRecipient());
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new EmailException("Exception occurred when sending mail to " + phishingMailTemplate.getRecipient(), e);
        }
    }

}

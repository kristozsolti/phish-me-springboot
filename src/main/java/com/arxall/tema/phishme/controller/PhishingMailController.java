package com.arxall.tema.phishme.controller;

import com.arxall.tema.phishme.model.Employee;
import com.arxall.tema.phishme.model.PhishingMailTemplate;
import com.arxall.tema.phishme.service.EmployeeService;
import com.arxall.tema.phishme.service.PhishingMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("phishing-mail")
public class PhishingMailController {

    private PhishingMailService phishingMailService;
    private EmployeeService employeeService;

    @Autowired
    public PhishingMailController(PhishingMailService phishingMailService, EmployeeService employeeService) {
        this.phishingMailService = phishingMailService;
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhishingMailTemplate>> getAllPhishingMailTemplates() {
        List<PhishingMailTemplate> phishingMailTemplates = phishingMailService.getAllPhishingEmailTemplates();
        return new ResponseEntity<>(phishingMailTemplates, HttpStatus.OK);
    }

    @PostMapping("/add-template")
    public ResponseEntity<PhishingMailTemplate> addPhishingMailTemplate(@RequestBody PhishingMailTemplate phishingMailTemplate) {
        PhishingMailTemplate newPhishingMailTemplate = phishingMailService.addPhishingEmailTemplate(phishingMailTemplate);
        return new ResponseEntity<>(newPhishingMailTemplate, HttpStatus.CREATED);
    }

    @PostMapping("/send")
    public HttpStatus sendPhishingMailByTemplateId(@RequestBody String phishingMailTemplateId) {
        phishingMailService.sendPhishingMailByTemplateId(phishingMailTemplateId);
        return HttpStatus.OK;
    }

    @GetMapping("/record/{id}")
    public HttpStatus recordPhishedEmployee(@PathVariable("id") String id) {
        employeeService.recordPhishedEmployee(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public ResponseEntity<PhishingMailTemplate> updatePhishingMailTemplate(@RequestBody PhishingMailTemplate mailTemplate) {
        PhishingMailTemplate phishingMailTemplate = phishingMailService.updatePhishingMailTemplate(mailTemplate);
        return new ResponseEntity<>(phishingMailTemplate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PhishingMailTemplate> deletePhishingMailTemplate(@PathVariable("id") String  id) {
        PhishingMailTemplate deletedPhishingMailTemplate = phishingMailService.deletePhishingMailTemplate(id);
        return new ResponseEntity<>(deletedPhishingMailTemplate, HttpStatus.OK);
    }
}

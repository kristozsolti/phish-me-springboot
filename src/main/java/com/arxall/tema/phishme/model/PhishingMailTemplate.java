package com.arxall.tema.phishme.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "phishing_email_templates")
public class PhishingMailTemplate {
    @Id
    private String id;
    private String subject;
    private String sender;
    private String recipient;
    private String body;

    public PhishingMailTemplate() {}

    public PhishingMailTemplate(String id, String subject, String sender, String recipient, String body) {
        this.id = id;
        this.subject = subject;
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "PhishingMailTemplate{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", sender='" + sender + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

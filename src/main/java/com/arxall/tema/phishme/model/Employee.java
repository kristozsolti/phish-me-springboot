package com.arxall.tema.phishme.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    private String name;
    private String email;
    private String jobTitle;
    private String imageUrl;
    private Integer phishingCount = 0;

    public Employee() {}

    public Employee(String id, String name, String email, String jobTitle, String imageUrl, Integer phishingCount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.imageUrl = imageUrl;
        this.phishingCount = phishingCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Integer getPhishingCount() {
        return phishingCount;
    }

    public void setPhishingCount(Integer phishingCount) {
        this.phishingCount = phishingCount;
    }

    public void incrementPhishingCount() {
        this.phishingCount++;
    }
}

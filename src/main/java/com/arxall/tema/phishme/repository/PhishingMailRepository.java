package com.arxall.tema.phishme.repository;

import com.arxall.tema.phishme.model.PhishingMailTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhishingMailRepository extends MongoRepository<PhishingMailTemplate, String> {
}

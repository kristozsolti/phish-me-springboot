package com.arxall.tema.phishme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class PhishmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhishmeApplication.class, args);
	}

}

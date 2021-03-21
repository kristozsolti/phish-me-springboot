package com.arxall.tema.phishme.service;

import com.arxall.tema.phishme.model.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Value( "${admin.username}" )
    private String adminUsername;
    @Value( "${admin.password}" )
    private String adminPassword;

    /**
     * Simple function to check if the username and password fields coming from front-end
     * corresponds to the one declared in application.properties file.
     * This function should be changed to another that does real authentication from
     * a database with encrypted password.
     * @param credentials : Credentioals that hold username and password fields.
     * @return : true, if the credentials corresponds, false otherwise.
     */
    public boolean authenticate(Credentials credentials) {
        if (credentials == null ||
        credentials.getUsername() == null ||
        credentials.getPassword() == null) {
            return false;
        }

        String username = credentials.getUsername();
        String password = credentials.getPassword();
        boolean areCredentialsValid = username.equals(adminUsername) &&
                password.equals(adminPassword);
        return areCredentialsValid;
    }
}

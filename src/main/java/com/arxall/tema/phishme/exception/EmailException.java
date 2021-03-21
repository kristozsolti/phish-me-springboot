package com.arxall.tema.phishme.exception;

public class EmailException extends RuntimeException{
    public EmailException(String message) {
        super(message);
    }

    public EmailException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
}




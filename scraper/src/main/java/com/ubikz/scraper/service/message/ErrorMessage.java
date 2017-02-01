package com.ubikz.scraper.service.message;

public class ErrorMessage extends AbstractMessage {
    private Object[] errors;

    public ErrorMessage() {
    }

    public Object[] getErrors() {
        return errors;
    }

    public void setErrors(Object[] errors) {
        this.errors = errors;
    }
}
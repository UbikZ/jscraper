package com.ubikz.scraper.service.message;

abstract public class AbstractMessage {
    private boolean isSuccess = false;
    private int code = 0;
    private int status = 0;

    public AbstractMessage() {
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
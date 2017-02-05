package com.ubikz.scraper.service.message;

public class BaseMessage extends AbstractMessage {
    private Object content;

    public BaseMessage() {
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
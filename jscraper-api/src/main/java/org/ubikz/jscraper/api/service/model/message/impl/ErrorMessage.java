package org.ubikz.jscraper.api.service.model.message.impl;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;

@JsonPropertyOrder({"code", "success", "title", "detail", "data"})
public class ErrorMessage extends BaseMessage<ErrorMessage, Exception> {
    private String title;
    private String detail;

    public ErrorMessage() {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
    }

    public String getTitle() {
        return title;
    }

    public ErrorMessage setTitle(String title) {
        this.title = title;

        return this;
    }

    public String getDetail() {
        return detail;
    }

    public ErrorMessage setDetail(String detail) {
        this.detail = detail;

        return this;
    }

    @Override
    public String toString() {
        return "ErrorMessage{"
                + "title='" + title + '\''
                + ", detail='" + detail + '\''
                + "} " + super.toString();
    }
}
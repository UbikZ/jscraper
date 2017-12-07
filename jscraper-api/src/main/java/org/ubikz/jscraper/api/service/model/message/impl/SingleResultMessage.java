package org.ubikz.jscraper.api.service.model.message.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;

@JsonPropertyOrder({"code", "success", "type", "data"})
public class SingleResultMessage extends BaseMessage<SingleResultMessage, Object> {
    private String type;

    public SingleResultMessage() {
        super();
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public SingleResultMessage setType(String type) {
        this.type = type;

        return this;
    }

    @Override
    public String toString() {
        return "SingleResultMessage{"
                + "type='" + type + '\''
                + "} " + super.toString();
    }
}
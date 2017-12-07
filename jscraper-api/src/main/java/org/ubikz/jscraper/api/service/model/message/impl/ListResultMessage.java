package org.ubikz.jscraper.api.service.model.message.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;

import java.util.List;

@JsonPropertyOrder({"code", "success", "total", "size", "data"})
public class ListResultMessage extends BaseMessage<ListResultMessage, List<Object>> {
    private int total = 0;
    private int size = 0;

    public ListResultMessage() {
        super();
    }

    @JsonProperty("size")
    public int getSize() {
        return size;
    }

    public ListResultMessage setSize(int size) {
        this.size = size;

        return this;
    }

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    public ListResultMessage setTotal(int total) {
        this.total = total;

        return this;
    }

    @Override
    public String toString() {
        return "ListResultMessage{"
                + "total=" + total
                + ", size=" + size
                + "} " + super.toString();
    }
}
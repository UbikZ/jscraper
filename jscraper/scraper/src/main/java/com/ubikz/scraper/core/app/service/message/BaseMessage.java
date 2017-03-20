package com.ubikz.scraper.core.app.service.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"code", "success", "size", "data"})
public class BaseMessage {
    private Object data;
    private int size = 0;
    private int code = 0;
    private int status = 0;
    private boolean isSuccess = false;

    public BaseMessage() {
    }

    @JsonProperty("success")
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @JsonProperty("code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @JsonIgnore
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonProperty("data")
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @JsonProperty("size")
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "data=" + data +
                ", size=" + size +
                ", code=" + code +
                ", status=" + status +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
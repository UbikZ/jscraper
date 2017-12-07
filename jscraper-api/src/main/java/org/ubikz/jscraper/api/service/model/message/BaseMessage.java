package org.ubikz.jscraper.api.service.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public abstract class BaseMessage<T extends BaseMessage, D> {
    protected D data;
    private int code = 0;
    private int status;
    private boolean success;

    public BaseMessage() {
        this(HttpStatus.OK.value(), true);
    }

    public BaseMessage(int status, boolean success) {
        this.status = status;
        this.success = success;
    }

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    @SuppressWarnings("unchecked")
    public T setSuccess(boolean success) {
        this.success = success;

        return (T) this;
    }

    @JsonProperty("code")
    public int getCode() {
        return code;
    }

    @SuppressWarnings("unchecked")
    public T setCode(int code) {
        this.code = code;

        return (T) this;
    }

    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    @SuppressWarnings("unchecked")
    public T setStatus(int status) {
        this.status = status;

        return (T) this;
    }

    @JsonProperty("data")
    public D getData() {
        return data;
    }

    @SuppressWarnings("unchecked")
    public T setData(D data) {
        this.data = data;

        return (T) this;
    }

    @Override
    public String toString() {
        return "BaseMessage{"
                + "data=" + data
                + ", code=" + code
                + ", status=" + status
                + ", success=" + success
                + '}';
    }
}
package org.ubikz.jscraper.api.controller.model.request;

import java.util.Date;

public abstract class BaseRequestBody {
    private Boolean isEnabled;
    private String label;
    private Date date;

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BaseRequestBody{"
                + "isEnabled=" + isEnabled
                + ", label='" + label + '\''
                + ", date=" + date
                + '}';
    }
}
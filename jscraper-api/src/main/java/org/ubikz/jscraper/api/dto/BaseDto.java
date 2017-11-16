package org.ubikz.jscraper.api.dto;

import java.util.Date;

public abstract class BaseDto {
    private Integer id;
    private String label;
    private Boolean isEnabled;
    private Date date;

    public BaseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BaseDto{"
                + "id=" + id
                + ", label='" + label + '\''
                + ", isEnabled=" + isEnabled
                + ", date=" + date
                + '}';
    }
}
package com.ubikz.scraper.core.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

abstract public class AbstractDto {
    private Integer id;
    private String label;
    private Boolean isEnabled;
    private Date date;

    public AbstractDto() {
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
        List<String> str = new ArrayList<>();
        str.add("id = " + this.id);
        str.add("label = " + this.label);
        str.add("date = " + this.date);
        str.add("isEnabled = " + this.isEnabled);
        return str.stream().collect(Collectors.joining(", "));
    }
}
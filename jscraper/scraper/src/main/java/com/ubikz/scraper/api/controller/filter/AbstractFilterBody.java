package com.ubikz.scraper.api.controller.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AbstractFilterBody {
    private Integer id;
    private Boolean isEnabled;
    private String label;
    private Date startDate;
    private Date endDate;
    private boolean isLazy = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    @JsonProperty("enabled")
    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Date getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isLazy() {
        return isLazy;
    }

    @JsonProperty("lazy")
    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    @Override
    public String toString() {
        return "AbstractFilterBody{" +
                "id=" + id +
                ", isEnabled=" + isEnabled +
                ", label='" + label + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isLazy=" + isLazy +
                '}';
    }
}
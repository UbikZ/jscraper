package com.ubikz.scraper.api.controller.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AbstractFilterBody {
    private Integer id = null;
    private Boolean isEnabled = null;
    private String label = null;
    private String startDate = null;
    private String endDate = null;
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

    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
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
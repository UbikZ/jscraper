package org.ubikz.jscraper.api.controller.model.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseFilterBody {
    private Integer id = null;
    private Boolean isEnabled = null;
    private String label = null;
    private String search = null;
    private String startDate = null;
    private String endDate = null;
    private Integer limit = null;
    private Integer offset = null;
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

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("search")
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "BaseFilterBody{"
                + "id=" + id
                + ", isEnabled=" + isEnabled
                + ", label='" + label + '\''
                + ", search='" + search + '\''
                + ", startDate='" + startDate + '\''
                + ", endDate='" + endDate + '\''
                + ", limit=" + limit
                + ", offset=" + offset
                + ", isLazy=" + isLazy
                + '}';
    }
}
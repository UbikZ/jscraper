package org.ubikz.jscraper.api.service.model.request;

public abstract class BaseServiceRequest {
    private Integer id;
    private String label;
    private Boolean isEnabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "BaseServiceRequest{"
                + "id=" + id
                + ", label='" + label + '\''
                + ", isEnabled=" + isEnabled
                + '}';
    }
}
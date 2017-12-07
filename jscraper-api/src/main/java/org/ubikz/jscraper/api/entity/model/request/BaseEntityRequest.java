package org.ubikz.jscraper.api.entity.model.request;

public class BaseEntityRequest {
    protected Integer id;
    protected String label;
    protected Boolean isEnabled;

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
        return "BaseEntityRequest{"
                + "id=" + id
                + ", label='" + label + '\''
                + ", isEnabled=" + isEnabled
                + '}';
    }
}

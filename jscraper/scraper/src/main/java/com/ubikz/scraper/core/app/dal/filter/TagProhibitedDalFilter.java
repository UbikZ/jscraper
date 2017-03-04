package com.ubikz.scraper.core.app.dal.filter;

import java.util.List;

public class TagProhibitedDalFilter extends AbstractDalFilter {
    private List<Integer> idsList;

    public List<Integer> getIdsList() {
        return idsList;
    }

    public void setIdsList(List<Integer> idsList) {
        this.idsList = idsList;
    }
}
package org.ubikz.jscraper.api.entity.model.filter.impl;

import org.ubikz.jscraper.api.entity.model.filter.BaseEntityFilter;

import java.util.List;

public class TagEntityFilter extends BaseEntityFilter {
    private List<String> nameList;

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    @Override
    public String toString() {
        return "TagEntityFilter{"
                + "nameList=" + nameList
                + "} " + super.toString();
    }
}
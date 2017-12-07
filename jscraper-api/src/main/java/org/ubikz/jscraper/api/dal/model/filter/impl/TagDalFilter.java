package org.ubikz.jscraper.api.dal.model.filter.impl;

import org.ubikz.jscraper.api.dal.model.filter.BaseDalFilter;

import java.util.List;

public class TagDalFilter extends BaseDalFilter {

    private List<String> nameList;

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    @Override
    public String toString() {
        return "TagDalFilter{"
                + "nameList=" + nameList
                + "} " + super.toString();
    }
}
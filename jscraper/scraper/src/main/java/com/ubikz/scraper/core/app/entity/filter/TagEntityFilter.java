package com.ubikz.scraper.core.app.entity.filter;

import java.util.List;

public class TagEntityFilter extends AbstractEntityFilter {
    private List<String> nameList;

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    @Override
    public String toString() {
        return super.toString() + "/ TagEntityFilter{" +
                "nameList=" + nameList +
                '}';
    }
}
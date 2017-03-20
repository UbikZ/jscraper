package com.ubikz.scraper.core.app.dal.filter;

import java.util.List;

public class TagDalFilter extends AbstractDalFilter {

    private List<String> nameList;

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    @Override
    public String toString() {
        return super.toString() + "/ TagDalFilter{" +
                "nameList=" + nameList +
                '}';
    }
}
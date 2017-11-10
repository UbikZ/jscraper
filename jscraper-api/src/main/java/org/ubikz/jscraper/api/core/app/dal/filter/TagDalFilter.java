package org.ubikz.jscraper.api.core.app.dal.filter;

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
        return "TagDalFilter{"
                + "nameList=" + nameList
                + "} " + super.toString();
    }
}
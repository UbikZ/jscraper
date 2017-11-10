package org.ubikz.jscraper.api.core.app.entity.filter;

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
        return "TagEntityFilter{"
                + "nameList=" + nameList
                + "} " + super.toString();
    }
}
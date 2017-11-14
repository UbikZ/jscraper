package org.ubikz.jscraper.dictionary.table.field;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum CommonDictionary implements IDictionary<CommonDictionary> {
    ID("id"),
    DATE("date"),
    ENABLED("enabled");
    
    private String name;

    CommonDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public CommonDictionary getFromValue(String value) {
        return new BaseDictionary<CommonDictionary>().getFromValue(values(), value);
    }
}
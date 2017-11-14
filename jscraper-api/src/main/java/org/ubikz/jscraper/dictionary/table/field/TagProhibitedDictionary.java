package org.ubikz.jscraper.dictionary.table.field;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum TagProhibitedDictionary implements IDictionary<TagProhibitedDictionary> {
    LABEL("label");
    
    private String name;

    TagProhibitedDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public TagProhibitedDictionary getFromValue(String value) {
        return new BaseDictionary<TagProhibitedDictionary>().getFromValue(values(), value);
    }
}

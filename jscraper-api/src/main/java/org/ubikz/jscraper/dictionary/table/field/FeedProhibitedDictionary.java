package org.ubikz.jscraper.dictionary.table.field;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum FeedProhibitedDictionary implements IDictionary<FeedProhibitedDictionary> {
    LABEL("label");
    
    private String name;

    FeedProhibitedDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public FeedProhibitedDictionary getFromValue(String value) {
        return new BaseDictionary<FeedProhibitedDictionary>().getFromValue(values(), value);
    }
}

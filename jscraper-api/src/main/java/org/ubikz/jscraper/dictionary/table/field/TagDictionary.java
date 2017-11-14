package org.ubikz.jscraper.dictionary.table.field;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum TagDictionary implements IDictionary<TagDictionary> {
    LABEL("label"),
    URL("url");

    private String name;

    TagDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public TagDictionary getFromValue(String value) {
        return new BaseDictionary<TagDictionary>().getFromValue(values(), value);
    }
}

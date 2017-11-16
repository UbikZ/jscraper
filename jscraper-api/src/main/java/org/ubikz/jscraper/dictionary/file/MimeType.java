package org.ubikz.jscraper.dictionary.file;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum MimeType implements IDictionary<MimeType> {
    JSON("application/Json");

    private String name;

    MimeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public MimeType getFromValue(String value) {
        return new BaseDictionary<MimeType>().getFromValue(values(), value);
    }
}

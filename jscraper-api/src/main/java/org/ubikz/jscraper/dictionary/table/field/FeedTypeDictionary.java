package org.ubikz.jscraper.dictionary.table.field;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum FeedTypeDictionary implements IDictionary<FeedTypeDictionary> {
    LABEL("label"),
    URL_REGEX("url_regex"),
    CONTENT_REGEX("content_regex");

    private String name;

    FeedTypeDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public FeedTypeDictionary getFromValue(String value) {
        return new BaseDictionary<FeedTypeDictionary>().getFromValue(values(), value);
    }
}

package org.ubikz.jscraper.dictionary.table.field;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum UserDictionary implements IDictionary<UserDictionary> {
    USERNAME("username"),
    FIRSTNAME("firstname"),
    LASTNAME("lastname"),
    PASSWORD("password"),
    EMAIL("email"),
    IS_ADMIN("is_admin");

    private String name;

    UserDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public UserDictionary getFromValue(String value) {
        return new BaseDictionary<UserDictionary>().getFromValue(values(), value);
    }
}

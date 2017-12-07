package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum UserReference implements IReference<UserReference> {
    USERNAME("username"),
    FIRSTNAME("firstname"),
    LASTNAME("lastname"),
    PASSWORD("password"),
    EMAIL("email"),
    IS_ADMIN("is_admin");

    private String name;

    UserReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }

    @Override
    public UserReference getFromValue(String value) {
        return new BaseReference<UserReference>().getFromValue(values(), value);
    }
}

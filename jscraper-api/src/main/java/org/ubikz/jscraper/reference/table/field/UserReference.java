package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.database.reference.IFieldReference;

public enum UserReference implements IFieldReference {
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
}

package org.ubikz.jscraper.reference.file;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum MimeTypeReference implements IReference<MimeTypeReference> {
    JSON("application/Json");

    private String name;

    MimeTypeReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public MimeTypeReference getFromValue(String value) {
        return new BaseReference<MimeTypeReference>().getFromValue(values(), value);
    }
}

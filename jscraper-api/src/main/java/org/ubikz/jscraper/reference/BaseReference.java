package org.ubikz.jscraper.reference;

import java.util.Arrays;

public final class BaseReference<T extends Enum> {
    public T getFromValue(T[] enumList, String value) {
        return Arrays.stream(enumList).filter(t -> t.name().equals(value)).findFirst().orElse(null);
    }
}

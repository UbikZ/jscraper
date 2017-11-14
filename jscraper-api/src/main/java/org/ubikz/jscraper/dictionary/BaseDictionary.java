package org.ubikz.jscraper.dictionary;

import java.util.Arrays;

public final class BaseDictionary<T extends Enum> {
    public T getFromValue(T[] enumList, String value) {
        return Arrays.asList(enumList).stream().filter(t -> t.name().equals(value)).findFirst().orElse(null);
    }
}

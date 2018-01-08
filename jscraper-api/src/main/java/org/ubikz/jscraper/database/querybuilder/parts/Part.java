package org.ubikz.jscraper.database.querybuilder.parts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Part implements IPart {
    protected final String buildListToString(List<String> elementList) {
        return this.buildListToString(elementList, true);
    }

    protected final String buildListToString(List<String> elementList, boolean forValue) {
        String result = "";

        if (elementList != null && elementList.size() > 0) {
            result = elementList.stream().collect(Collectors.joining(","));
            if (forValue) {
                result = "(" + result + ")";
            }
        }

        return result;
    }

    @Override
    public String toSQL() {
        checkIntegrity();

        return null;
    }

    @Override
    public List<Parameter> getParameters() {
        return new ArrayList<>();
    }

    @Override
    public abstract void checkIntegrity();
}

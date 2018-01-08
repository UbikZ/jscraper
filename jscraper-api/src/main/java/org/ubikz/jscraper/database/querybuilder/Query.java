package org.ubikz.jscraper.database.querybuilder;

import org.ubikz.jscraper.database.querybuilder.parts.IPart;
import org.ubikz.jscraper.database.querybuilder.parts.Parameter;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.querybuilder.parts.impl.IdentifierPart;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Query<T extends Query> {
    protected List<Class<? extends Part>> allowedParts;
    protected List<Part> parts;
    protected Map<String, Object> parameters;

    public Query() {
        this.parts = new ArrayList<>();
        this.parameters = new HashMap<>();
        this.allowedParts = new ArrayList<Class<? extends Part>>() {{
            add(IdentifierPart.class);
        }};
    }

    @SuppressWarnings("unchecked")
    protected <U extends Part> T consumePart(Consumer<U> consumer, U part) {
        return consumePart(consumer, (Class<U>) part.getClass());
    }

    @SuppressWarnings("unchecked")
    protected <U extends Part> T consumePart(Consumer<U> consumer, Class<U> clazz) {
        try {
            U part = clazz.newInstance();
            consumer.accept(part);
            parts.add(part);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ApplicativeException(e);
        }

        return (T) this;
    }

    private Stream<IPart> getValidParts() {
        return allowedParts
                .stream()
                .map(c -> parts.stream().filter(c::isInstance).collect(Collectors.toList()))
                .flatMap(Collection::stream);
    }

    public final String toSQL() {
        return getValidParts()
                .map(IPart::toSQL)
                .collect(Collectors.joining(" "));
    }

    public final Map<String, ?> getParameters() {
        return getValidParts()
                .map(IPart::getParameters)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Parameter::getName, Parameter::getValue));
    }
}

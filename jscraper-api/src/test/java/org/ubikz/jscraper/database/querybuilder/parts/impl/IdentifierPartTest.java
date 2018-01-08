package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.junit.Assert;
import org.junit.Test;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.database.reference.impl.IdentifierReference;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.util.Arrays;

public class IdentifierPartTest {
    @Test
    public void setIdentifierTest() {

        // Set

        IdentifierPart identifierPart = new IdentifierPart();

        Arrays.asList(IdentifierReference.SELECT, IdentifierReference.DELETE, IdentifierReference.INSERT, IdentifierReference.UPDATE).forEach(identifier -> {
            identifierPart.set(identifier);
            Assert.assertEquals(identifierPart.toSQL(), identifier.get());
        });
    }

    @Test
    public void setIdentifierDistinctTest() {

        // Set

        IdentifierPart identifierPart = new IdentifierPart();
        identifierPart.set(IdentifierReference.SELECT);
        identifierPart.distinct();

        Assert.assertEquals(
                identifierPart.toSQL(),
                String.format("%s %s", IdentifierReference.SELECT.get(), CommonReference.DISTINCT.get())
        );
    }

    @Test(expected = ApplicativeException.class)
    public void setNoIdentifierExceptionTest() {
        IdentifierPart identifierPart = new IdentifierPart();
        identifierPart.toSQL();
    }
}

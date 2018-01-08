package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.junit.Assert;
import org.junit.Test;
import org.ubikz.jscraper.exception.ApplicativeException;
import org.ubikz.jscraper.reference.table.TableReference;

public class FromPartTest {
    @Test
    public void setOneTableTest() {

        // Set

        FromPart fromPart = new FromPart();

        // Act

        fromPart.set(TableReference.FEED_TYPE);

        // Expected

        String sql = String.format(
                "%s %s",
                org.ubikz.jscraper.database.reference.impl.CommonReference.FROM.get(),
                TableReference.FEED_TYPE.get()
        );

        // Assert
        Assert.assertEquals(sql, fromPart.toSQL());
    }

    @Test
    public void setMultipleTablesTest() {

        // Set

        FromPart fromPart = new FromPart();

        // Act

        fromPart.set(TableReference.FEED_TYPE);
        fromPart.set(TableReference.FEED_ITEM);

        // Expected

        String sql = String.format(
                "%s %s, %s",
                org.ubikz.jscraper.database.reference.impl.CommonReference.FROM.get(),
                TableReference.FEED_TYPE.get(),
                TableReference.FEED_ITEM.get()
        );

        // Assert
        Assert.assertEquals(sql, fromPart.toSQL());
    }

    @Test
    public void setMultipleTablesWithAliasTest() {

        // Set

        FromPart fromPart = new FromPart();

        // Act

        fromPart.set(TableReference.FEED_TYPE, "ft");
        fromPart.set(TableReference.FEED_ITEM, "fi");

        // Expected

        String sql = String.format(
                "%s %s AS ft, %s AS fi",
                org.ubikz.jscraper.database.reference.impl.CommonReference.FROM.get(),
                TableReference.FEED_TYPE.get(),
                TableReference.FEED_ITEM.get()
        );

        // Assert
        Assert.assertEquals(sql, fromPart.toSQL());
    }

    @Test(expected = ApplicativeException.class)
    public void setNoTableExceptionTest() {
        TablePart tablePart = new TablePart();
        tablePart.toSQL();
    }
}

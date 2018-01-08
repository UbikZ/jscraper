package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.junit.Assert;
import org.junit.Test;
import org.ubikz.jscraper.exception.ApplicativeException;
import org.ubikz.jscraper.reference.table.TableReference;

public class TablePartTest {
    @Test
    public void setOneTableTest() {

        // Set

        TablePart tablePart = new TablePart();

        // Act

        tablePart.set(TableReference.USER);

        // Expected

        String sql = TableReference.USER.get();

        // Assert
        Assert.assertEquals(sql, tablePart.toSQL());
    }

    @Test
    public void setMultipleTablesTest() {

        // Set

        TablePart tablePart = new TablePart();

        // Act

        tablePart.set(TableReference.USER);
        tablePart.set(TableReference.TAG);

        // Expected

        String sql = String.format("%s, %s", TableReference.USER.get(), TableReference.TAG.get());

        // Assert
        Assert.assertEquals(sql, tablePart.toSQL());
    }

    @Test
    public void setMultipleTablesWithAliasTest() {

        // Set

        TablePart tablePart = new TablePart();

        // Act

        tablePart.set(TableReference.USER, "u");
        tablePart.set(TableReference.TAG, "t");

        // Expected

        String sql = String.format("%s AS u, %s AS t", TableReference.USER.get(), TableReference.TAG.get());

        // Assert
        Assert.assertEquals(sql, tablePart.toSQL());
    }

    @Test(expected = ApplicativeException.class)
    public void setNoTableExceptionTest() {
        TablePart tablePart = new TablePart();
        tablePart.toSQL();
    }
}

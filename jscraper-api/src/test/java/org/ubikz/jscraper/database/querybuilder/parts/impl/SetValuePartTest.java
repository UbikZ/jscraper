package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.junit.Assert;
import org.junit.Test;
import org.ubikz.jscraper.reference.table.field.CommonReference;
import org.ubikz.jscraper.reference.table.field.FeedReference;

public class SetValuePartTest {
    @Test
    public void setValueOK() {

        // Set

        SetValuePart setValuePart = new SetValuePart();

        // Act

        setValuePart.addValue(CommonReference.ID, 1234);
        setValuePart.addValue(CommonReference.ENABLED, true);
        setValuePart.addValue(FeedReference.LABEL, "Label Test");
//        setValuePart.addValue(CommonReference.DATE, new Date());

        // Expected

        String sql = "id=:id_1234, enabled=:enabled_true, label=:label_true";

        // Assert
        Assert.assertEquals(4, setValuePart.getParameters().size());
        Assert.assertEquals(sql, setValuePart.toSQL());
    }
}

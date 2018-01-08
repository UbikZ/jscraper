// @cs-: MultipleStringLiterals
package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.junit.Assert;
import org.junit.Test;
import org.ubikz.jscraper.database.querybuilder.parts.Parameter;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.reference.table.field.CommonReference;
import org.ubikz.jscraper.reference.table.field.FeedReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

public class SetValuePartTest {
    @Test
    public void addValueTest() throws ParseException {

        // Set

        SetValuePart setValuePart = new SetValuePart();

        // Act

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        setValuePart.addValue(CommonReference.ID, 1234);
        setValuePart.addValue(CommonReference.ENABLED, true);
        setValuePart.addValue(FeedReference.LABEL, "Label Test");
        setValuePart.addValue(CommonReference.DATE, sdf.parse("21/12/2012"));

        // Expected

        String sql = String.format(
                "id=:id_%s, enabled=:enabled_%s, label=:label_%s, date=:date_%s",
                Parameter.encode(1234),
                Parameter.encode(true),
                Parameter.encode("Label Test"),
                Parameter.encode("Fri Dec 21 00:00:00 CET 2012")
        );

        // Assert
        Assert.assertEquals(4, setValuePart.getParameters().size());
        Assert.assertEquals(sql, setValuePart.toSQL());
    }

    @Test
    public void setValuesTest() throws ParseException {

        // Set

        SetValuePart setValuePart = new SetValuePart();

        // Act

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        setValuePart.setValues(new LinkedHashMap<IFieldReference, Object>() {{
            put(CommonReference.ID, 5678);
            put(CommonReference.ENABLED, false);
            put(FeedReference.LABEL, "Label Test 2");
            put(CommonReference.DATE, sdf.parse("21/11/1988"));
        }});

        // Expected

        String sql = String.format(
                "id=:id_%s, enabled=:enabled_%s, label=:label_%s, date=:date_%s",
                Parameter.encode(5678),
                Parameter.encode(false),
                Parameter.encode("Label Test 2"),
                Parameter.encode("Mon Nov 21 00:00:00 CET 1988")
        );

        // Assert
        Assert.assertEquals(4, setValuePart.getParameters().size());
        Assert.assertEquals(sql, setValuePart.toSQL());
    }
}

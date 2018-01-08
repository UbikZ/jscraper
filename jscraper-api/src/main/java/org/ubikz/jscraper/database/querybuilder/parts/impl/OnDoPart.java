package org.ubikz.jscraper.database.querybuilder.parts.impl;

import com.google.common.base.Strings;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.exception.ApplicativeException;

public class OnDoPart extends Part {
    private String action;

    public void setAction(String action) {
        this.action = action;
    }

    public void nothing() {
        this.action = CommonReference.NOTHING.get();
    }

    @Override
    public String toSQL() {
        super.toSQL();

        return String.format("%s %s %s", CommonReference.ON.get(), CommonReference.DO.get(), action);
    }

    @Override
    public void checkIntegrity() {
        if (Strings.isNullOrEmpty(action)) {
            throw new ApplicativeException("Action should not be empty.");
        }
    }
}

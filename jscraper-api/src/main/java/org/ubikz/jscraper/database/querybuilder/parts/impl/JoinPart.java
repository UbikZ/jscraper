package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.assertj.core.util.Strings;
import org.ubikz.jscraper.database.querybuilder.parts.Parameter;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.ITableReference;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.database.reference.impl.JoinReference;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.util.ArrayList;
import java.util.List;

public class JoinPart extends Part {
    private ITableReference table;
    private String onColumnSrc;
    private String onColumnDest;
    private String alias;
    private JoinReference identifier;

    public void set(ITableReference table, IFieldReference onColumnSrc, IFieldReference onColumnDest) {
        set(table, onColumnSrc, onColumnDest, null);
    }

    public void set(ITableReference table, String onColumnSrc, String onColumnDest) {
        set(table, onColumnSrc, onColumnDest, null);
    }

    public void set(ITableReference table, IFieldReference onColumnSrc, IFieldReference onColumnDest, String alias) {
        set(table, onColumnSrc, onColumnDest, alias, JoinReference.INNER);
    }

    public void set(ITableReference table, String onColumnSrc, String onColumnDest, String alias) {
        set(table, onColumnSrc, onColumnDest, alias, JoinReference.INNER);
    }

    public void set(ITableReference table, IFieldReference onColumnSrc, IFieldReference onColumnDest, String alias, JoinReference identifier) {
        set(table, onColumnSrc.get(), onColumnDest.get(), alias, identifier);
    }

    public void set(ITableReference table, String onColumnSrc, String onColumnDest, String alias, JoinReference identifier) {
        this.table = table;
        this.onColumnSrc = onColumnSrc;
        this.onColumnDest = onColumnDest;
        this.alias = alias;
        this.identifier = identifier;
    }

    public JoinPart setIdentifier(JoinReference identifier) {
        this.identifier = identifier;

        return this;
    }

    @Override
    public String toSQL() {
        super.toSQL();

        return identifier + " "
                + table + " "
                + (Strings.isNullOrEmpty(alias) ? " " : CommonReference.AS.get() + alias + " ")
                + CommonReference.ON.get() + " " + onColumnSrc + " = " + onColumnDest + " ";
    }

    @Override
    public List<Parameter> getParameters() {
        return new ArrayList<>();
    }

    @Override
    public void checkIntegrity() {
        if (identifier == null) {
            throw new ApplicativeException("Identifier should not be null.");
        }

        if (table == null) {
            throw new ApplicativeException("Table should not be null.");
        }

        if (onColumnSrc == null) {
            throw new ApplicativeException("Column Src should not be null.");
        }

        if (onColumnDest == null) {
            throw new ApplicativeException("Column Dest should not be null.");
        }
    }
}

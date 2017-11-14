package org.ubikz.jscraper.database.querybuilder;

import org.ubikz.jscraper.database.querybuilder.impl.Delete;
import org.ubikz.jscraper.database.querybuilder.impl.Insert;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.database.querybuilder.impl.Update;

public class QueryBuilderService {
    public QueryBuilderService() {
    }

    public Select select(String... columns) {
        return new Select(columns);
    }

    public Insert insert(String table) {
        return new Insert(table);
    }

    public Update update(String table) {
        return new Update(table);
    }

    public Delete delete() {
        return new Delete();
    }
}
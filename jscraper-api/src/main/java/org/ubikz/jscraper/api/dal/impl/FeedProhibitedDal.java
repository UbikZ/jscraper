package org.ubikz.jscraper.api.dal.impl;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;

@Repository
public class FeedProhibitedDal extends BaseDal {
    /**
     * @param databaseService
     */
    public FeedProhibitedDal(DatabaseService databaseService) {
        super(databaseService);
        this.tableName = "feed_prohibited";
    }

    @Override
    protected Select getBaseSelect(AbstractDalFilter filter, boolean isCount) {
        Select select = super.getBaseSelect(filter, isCount);

        select.orderBy("label", true);

        return select;
    }
}
package org.ubikz.jscraper.api.dal.impl;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedProhibitedDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedProhibitedDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.reference.table.TableReference;
import org.ubikz.jscraper.reference.table.field.FeedProhibitedReference;

@Repository
public class FeedProhibitedDal extends BaseDal<FeedProhibitedDalRequest, FeedProhibitedDalFilter> {
    public FeedProhibitedDal(DatabaseService databaseService) {
        super(databaseService);
        this.table = TableReference.FEED_PROHIBITED;
    }

    @Override
    protected Select getBaseSelect(FeedProhibitedDalFilter filter, boolean isCount) {
        Select select = super.getBaseSelect(filter, isCount);

        select.orderBy(FeedProhibitedReference.LABEL);

        return select;
    }
}

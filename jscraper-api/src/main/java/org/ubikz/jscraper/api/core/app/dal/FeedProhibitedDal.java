package org.ubikz.jscraper.api.core.app.dal;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.core.app.dal.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.core.provider.db.DBWrapper;
import org.ubikz.jscraper.api.core.provider.db.qb.Select;

@Repository
public class FeedProhibitedDal extends AbstractDal {
    /**
     * @param dbWrapper
     */
    public FeedProhibitedDal(DBWrapper dbWrapper) {
        super(dbWrapper);
        this.tableName = "feed_prohibited";
    }

    @Override
    protected Select getBaseSelect(AbstractDalFilter filter, boolean isCount) {
        Select select = super.getBaseSelect(filter, isCount);

        select.orderBy("label", true);

        return select;
    }
}
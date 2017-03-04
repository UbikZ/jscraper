package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.provider.db.DBWrapper;
import com.ubikz.scraper.core.provider.db.qb.Select;
import org.springframework.stereotype.Repository;

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
    protected Select getBaseSelect(AbstractDalFilter filter) {
        Select select = super.getBaseSelect(filter);

        select.orderBy("label", true);

        return select;
    }
}
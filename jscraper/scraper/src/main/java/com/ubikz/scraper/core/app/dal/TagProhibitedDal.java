package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.provider.db.DBWrapper;
import com.ubikz.scraper.core.provider.db.qb.Select;
import org.springframework.stereotype.Repository;

@Repository
public class TagProhibitedDal extends AbstractDal {
    /**
     * @param dbWrapper
     */
    public TagProhibitedDal(DBWrapper dbWrapper) {
        super(dbWrapper);
        this.tableName = "tag_prohibited";
    }

    @Override
    protected Select getBaseSelect(AbstractDalFilter filter) {
        Select select = super.getBaseSelect(filter);

        select.orderBy("label", true);

        return select;
    }
}
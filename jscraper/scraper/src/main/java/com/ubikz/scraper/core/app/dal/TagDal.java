package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.TagDalFilter;
import com.ubikz.scraper.core.provider.db.DBWrapper;
import com.ubikz.scraper.core.provider.db.qb.AbstractQuery;
import org.springframework.stereotype.Repository;

@Repository
public class TagDal extends AbstractDal {
    /**
     * @param dbWrapper
     */
    public TagDal(DBWrapper dbWrapper) {
        super(dbWrapper);
        this.tableName = "tag";
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select) {
        TagDalFilter tagDalFilter = (TagDalFilter) filter;
        super.parseFilter(tagDalFilter, select);

        if (tagDalFilter.getNameList() != null && tagDalFilter.getNameList().size() > 0) {
            select.where("label", "in", tagDalFilter.getNameList());
        }
    }
}
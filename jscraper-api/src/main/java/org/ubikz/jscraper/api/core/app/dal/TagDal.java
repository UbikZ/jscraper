package org.ubikz.jscraper.api.core.app.dal;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.core.app.dal.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.core.app.dal.filter.TagDalFilter;
import org.ubikz.jscraper.api.core.provider.db.DBWrapper;
import org.ubikz.jscraper.api.core.provider.db.qb.AbstractQuery;

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
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select, boolean isCount) {
        TagDalFilter tagDalFilter = (TagDalFilter) filter;
        super.parseFilter(tagDalFilter, select, isCount);

        if (tagDalFilter.getNameList() != null && tagDalFilter.getNameList().size() > 0) {
            select.where("label", "in", tagDalFilter.getNameList());
        }
    }
}
package org.ubikz.jscraper.api.dal.impl;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.TagDalFilter;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.AbstractQuery;

@Repository
public class TagDal extends BaseDal {
    /**
     * @param databaseService
     */
    public TagDal(DatabaseService databaseService) {
        super(databaseService);
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
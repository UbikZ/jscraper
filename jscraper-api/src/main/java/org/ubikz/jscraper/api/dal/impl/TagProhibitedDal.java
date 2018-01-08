package org.ubikz.jscraper.api.dal.impl;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.TagProhibitedDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.TagProhibitedDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.reference.table.TableReference;
import org.ubikz.jscraper.reference.table.field.TagProhibitedReference;

@Repository
public class TagProhibitedDal extends BaseDal<TagProhibitedDalRequest, TagProhibitedDalFilter> {
    public TagProhibitedDal(DatabaseService databaseService) {
        super(databaseService);
        this.table = TableReference.TAG_PROHIBITED;
    }

    @Override
    protected Select getBaseSelect(TagProhibitedDalFilter filter, boolean isCount) {
        Select select = super.getBaseSelect(filter, isCount);

        select.orderBy(TagProhibitedReference.LABEL);

        return select;
    }
}

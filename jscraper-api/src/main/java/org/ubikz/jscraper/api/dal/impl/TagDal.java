package org.ubikz.jscraper.api.dal.impl;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.TagDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.TagDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.database.reference.impl.OperatorReference;
import org.ubikz.jscraper.reference.table.TableReference;
import org.ubikz.jscraper.reference.table.field.TagReference;

@Repository
public class TagDal extends BaseDal<TagDalRequest, TagDalFilter> {
    public TagDal(DatabaseService databaseService) {
        super(databaseService);
        this.table = TableReference.TAG;
    }

    @Override
    protected void parseFilter(TagDalFilter filter, Select select, boolean isCount) {
        super.parseFilter(filter, select, isCount);

        if (filter.getNameList() != null && filter.getNameList().size() > 0) {
            select.where(w -> w.and(p -> p.set(TagReference.LABEL, OperatorReference.IN, filter.getNameList())));
        }
    }
}

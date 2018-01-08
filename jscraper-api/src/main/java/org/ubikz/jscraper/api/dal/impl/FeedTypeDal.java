package org.ubikz.jscraper.api.dal.impl;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedTypeDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedTypeDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.impl.OperatorReference;
import org.ubikz.jscraper.reference.table.TableReference;
import org.ubikz.jscraper.reference.table.field.CommonReference;
import org.ubikz.jscraper.reference.table.field.FeedTypeReference;

import java.util.Map;

@Repository
public class FeedTypeDal extends BaseDal<FeedTypeDalRequest, FeedTypeDalFilter> {
    public FeedTypeDal(DatabaseService databaseService) {
        super(databaseService);
        this.table = TableReference.FEED_TYPE;
    }

    @Override
    protected Map<IFieldReference, Object> parseRequest(FeedTypeDalRequest request) {
        Map<IFieldReference, Object> values = super.parseRequest(request);

        if (request.getUrlRegex() != null) {
            values.put(FeedTypeReference.URL_REGEX, request.getUrlRegex());
        }

        if (request.getContentRegex() != null) {
            values.put(FeedTypeReference.CONTENT_REGEX, request.getContentRegex());
        }

        return values;
    }

    @Override
    protected void parseFilter(FeedTypeDalFilter filter, Select select, boolean isCount) {
        super.parseFilter(filter, select, isCount);

        if (filter.getIdList() != null && filter.getIdList().size() > 0) {
            select.where(w -> w.and(p -> p.set(CommonReference.ID, OperatorReference.IN, filter.getIdList())));
        }
    }
}

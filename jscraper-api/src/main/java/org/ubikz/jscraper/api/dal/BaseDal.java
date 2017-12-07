package org.ubikz.jscraper.api.dal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.ubikz.jscraper.api.dal.model.filter.BaseDalFilter;
import org.ubikz.jscraper.api.dal.model.request.BaseDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.AbstractQuery;
import org.ubikz.jscraper.database.querybuilder.QueryBuilderService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.reference.IReference;
import org.ubikz.jscraper.reference.table.field.CommonReference;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public abstract class BaseDal {
    protected final Logger logger = LoggerFactory.getLogger(BaseDal.class);
    protected DatabaseService databaseService;
    protected String tableName;
    protected String tableIdentifier = "id";

    @Autowired
    public BaseDal(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }


    public <R extends BaseDalRequest> int create(R request) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery insert = qb.insert(this.tableName).values(this.parseRequest(request)).returning(this.tableIdentifier);

        return databaseService.insert(insert);
    }

    public <R extends BaseDalRequest> int edit(R request) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery update = qb.update(this.tableName).set(this.parseRequest(request)).where(this.tableIdentifier, request.getId());

        return databaseService.update(update);
    }

    public int delete(BaseDalFilter filter) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery delete = qb.delete().from(this.tableName).where(this.tableIdentifier, filter.getId());

        return databaseService.delete(delete);
    }

    protected Select getBaseSelect(BaseDalFilter filter) {
        return this.getBaseSelect(filter, false);
    }

    protected Select getBaseSelect(BaseDalFilter filter, boolean isCount) {
        QueryBuilderService qb = new QueryBuilderService();
        Select select = qb.select().from(this.tableName);

        if (isCount) {
            select.columns("COUNT(DISTINCT id)");
        }

        this.parseFilter(filter, select, isCount);

        return select;
    }

    public List<Object> createAll(List<BaseDalRequest> requestList) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery insert = qb.insert(this.tableName).values(this.parseRequestList(requestList, true)).onConflict().onDo("NOTHING").returning("id");

        return this.insertMultiple(insert);
    }

    public List<Map<String, Object>> getAll(BaseDalFilter filter) {
        return this.find(this.getBaseSelect(filter));
    }

    public Map<String, Object> getOne(BaseDalFilter filter) {
        return this.findOne(this.getBaseSelect(filter));
    }

    protected <T extends BaseDalRequest> List<Map<IReference, Object>> parseRequestList(List<T> requestList, boolean created) {
        return requestList.stream().map(r -> parseRequest(r, created)).collect(Collectors.toList());
    }

    protected <T extends BaseDalRequest> Map<IReference, Object> parseRequest(T request) {
        return parseRequest(request, true);
    }

    protected <T extends BaseDalRequest> Map<IReference, Object> parseRequest(T request, boolean created) {
        Map<IReference, Object> values = new HashMap<>();

        if (!created && request.getId() != null) {
            values.put(CommonReference.ID, request.getId());
        }

        if (request.getEnabled() != null) {
            values.put(CommonReference.ENABLED, request.getEnabled());
        }

        return values;
    }

    protected <T extends BaseDalFilter> void parseFilter(T filter, AbstractQuery select) {
        this.parseFilter(filter, select, false);
    }

    protected <T extends BaseDalFilter> void parseFilter(T filter, AbstractQuery select, boolean isCount) {
        if (filter.getId() != null) {
            select.where(CommonReference.ID, filter.getId());
        }

        if (filter.getIdList() != null && filter.getIdList().size() > 0) {
            select.where(COLUMN_ID, "in", filter.getIdList());
        }

        if (filter.getLabel() != null) {
            select.where(COLUMN_LABEL, filter.getLabel());
        }

        if (filter.getSearch() != null) {
            select.where(COLUMN_LABEL, "LIKE", filter.getSearch() + "%");
        }

        if (filter.getEnabled() != null) {
            select.where(COLUMN_ENABLED, filter.getEnabled());
        }

        if (filter.getStartDate() != null) {
            select.where(COLUMN_DATE, ">=", new Timestamp(filter.getStartDate().getTime()));
        }

        if (filter.getEndDate() != null) {
            select.where(COLUMN_DATE, "<=", new Timestamp(
                    filter.getEndDate().getTime() + (59 + 59 * 60 + 23 * 3600) * 1000
            ));
        }

        if (!isCount && select instanceof Select) {
            if (filter.getLimit() != null) {
                ((Select) select).limit(filter.getLimit());
            }

            if (filter.getOffset() != null) {
                ((Select) select).offset(filter.getOffset());
            }
        }
    }

    public int count(BaseDalFilter filter) {
        return this.count(this.getBaseSelect(filter, true));
    }
}

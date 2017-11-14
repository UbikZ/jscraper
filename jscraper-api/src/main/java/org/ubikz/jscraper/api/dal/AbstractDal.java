package org.ubikz.jscraper.api.dal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.AbstractQuery;
import org.ubikz.jscraper.database.querybuilder.QueryBuilderService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public abstract class AbstractDal {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LABEL = "label";
    private static final String COLUMN_ENABLED = "enabled";
    private static final String COLUMN_DATE = "date";
    protected final Logger logger = LoggerFactory.getLogger(AbstractDal.class);
    protected DatabaseService databaseService;
    protected String tableName;
    protected String tableIdentifier = "id";

    @Autowired
    public AbstractDal(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    /**
     * @param request
     * @return
     */
    public int create(AbstractDalRequest request) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery insert = qb.insert(this.tableName).values(this.parseRequest(request)).returning(this.tableIdentifier);

        return this.insert(insert);
    }

    /**
     * @param request
     * @return
     */
    public int edit(AbstractDalRequest request) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery update = qb.update(this.tableName).set(this.parseRequest(request)).where(this.tableIdentifier, request.getId());

        return this.update(update);
    }
    
    /**
     * @param request
     * @return
     */
    protected int delete(AbstractQuery request) {
        request.build();

        this.logger.debug("# Delete SQL > " + request.getSQL());
        this.logger.debug("# Delete Params > " + request.getParameters());

        return this.databaseService.jdbcTemplate.update(request.getSQL(), request.getParameters());
    }

    /**
     * @param filter
     * @return
     */
    public int delete(AbstractDalFilter filter) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery delete = qb.delete().from(this.tableName).where(this.tableIdentifier, filter.getId());

        return this.delete(delete);
    }

    /**
     * @param filter
     * @return
     */
    protected Select getBaseSelect(AbstractDalFilter filter) {
        return this.getBaseSelect(filter, false);
    }

    /**
     * @param filter
     * @return
     */
    protected Select getBaseSelect(AbstractDalFilter filter, boolean isCount) {
        QueryBuilderService qb = new QueryBuilderService();
        Select select = qb.select().from(this.tableName);

        if (isCount) {
            select.columns("COUNT(DISTINCT id)");
        }

        this.parseFilter(filter, select, isCount);

        return select;
    }

    /**
     * @param requestList
     * @param created
     * @return
     */
    protected List<Map<String, Object>> parseRequestList(List<AbstractDalRequest> requestList, boolean created) {
        List<Map<String, Object>> values = new ArrayList<>();

        for (AbstractDalRequest dalRequest : requestList) {
            values.add(this.parseRequest(dalRequest, created));
        }

        return values;
    }

    /**
     * @param requestList
     * @return
     */
    public List<Object> createAll(List<AbstractDalRequest> requestList) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery insert = qb.insert(this.tableName).values(this.parseRequestList(requestList, true)).onConflict().onDo("NOTHING").returning("id");

        return this.insertMultiple(insert);
    }

    /**
     * @param filter
     * @return
     */
    public List<Map<String, Object>> getAll(AbstractDalFilter filter) {
        return this.find(this.getBaseSelect(filter));
    }

    /**
     * @param filter
     * @return
     */
    public Map<String, Object> getOne(AbstractDalFilter filter) {
        return this.findOne(this.getBaseSelect(filter));
    }

    /**
     * @param request
     * @return
     */
    protected Map<String, Object> parseRequest(AbstractDalRequest request) {
        return this.parseRequest(request, true);
    }

    /**
     * @param request
     * @param created
     * @return
     */
    protected Map<String, Object> parseRequest(AbstractDalRequest request, boolean created) {
        Map<String, Object> values = new HashMap<>();

        if (!created && request.getId() != null) {
            values.put("id", request.getId());
        }

        if (request.getLabel() != null) {
            values.put(COLUMN_LABEL, request.getLabel());
        }

        if (request.getEnabled() != null) {
            values.put(COLUMN_ENABLED, request.getEnabled());
        }

        return values;
    }

    /**
     * @param filter
     * @param select
     */
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select) {
        this.parseFilter(filter, select, false);
    }

    /**
     * @param filter
     * @return
     */
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select, boolean isCount) {
        if (filter.getId() != null) {
            select.where(COLUMN_ID, filter.getId());
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

    /**
     * @param request
     * @return
     */
    protected int insert(AbstractQuery request) {
        request.build();

        this.logger.debug("# Insert SQL > " + request.getSQL());
        this.logger.debug("# Insert Params > " + request.getParameters());

        return this.databaseService.jdbcTemplate.queryForObject(request.getSQL(), request.getParameters(), Integer.class);
    }

    /**
     * @param request
     * @return
     */
    protected List<Object> insertMultiple(AbstractQuery request) {
        request.build();

        this.logger.debug("# Insert Multiple SQL > " + request.getSQL());
        this.logger.debug("# Insert Multiple Params > " + request.getParameters());

        return this.databaseService.jdbcTemplate.queryForList(request.getSQL(), request.getParameters(), Object.class);
    }

    /**
     * @param request
     * @return
     */
    protected int update(AbstractQuery request) {
        request.build();

        this.logger.debug("# Update SQL > " + request.getSQL());
        this.logger.debug("# Update Params > " + request.getParameters());

        return this.databaseService.jdbcTemplate.update(request.getSQL(), request.getParameters());
    }

    /**
     * @param query
     * @return
     */
    protected List<Map<String, Object>> find(AbstractQuery query) {
        query.build();

        this.logger.debug("# Select All SQL > " + query.getSQL());
        this.logger.debug("# Select All Params > " + query.getParameters());

        return this.databaseService.jdbcTemplate.queryForList(query.getSQL(), query.getParameters());
    }

    /**
     * @param query
     * @return
     */
    protected Map<String, Object> findOne(AbstractQuery query) {
        query.build();

        this.logger.debug("# Select SQL > " + query.getSQL());
        this.logger.debug("# Select Params > " + query.getParameters());

        return this.databaseService.jdbcTemplate.queryForMap(query.getSQL(), query.getParameters());
    }


    /**
     * @return
     */
    public int count(AbstractDalFilter filter) {
        return this.count(this.getBaseSelect(filter, true));
    }

    /**
     * @param query
     * @return
     */
    protected int count(AbstractQuery query) {
        query.build();

        this.logger.debug("# Count SQL > " + query.getSQL());
        this.logger.debug("# Count Params > " + query.getParameters());

        return this.databaseService.jdbcTemplate.queryForObject(query.getSQL(), query.getParameters(), Integer.class);
    }
}
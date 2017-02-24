package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.lib.db.DBWrapper;
import com.ubikz.scraper.core.lib.db.qb.AbstractQuery;
import com.ubikz.scraper.core.lib.db.qb.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
abstract public class AbstractDal {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDal.class);
    protected DBWrapper dbWrapper;
    protected String tableName;
    protected String tableIdentifier = "id";

    @Autowired
    public AbstractDal(DBWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }

    /**
     * @param request
     * @return
     */
    public int create(AbstractDalRequest request) {
        QueryBuilder qb = new QueryBuilder();
        AbstractQuery insert = qb
                .insert(this.tableName)
                .values(this.parseRequest(request))
                .returning(this.tableIdentifier);

        return this.insert(insert);
    }


    /**
     * @param request
     * @return
     */
    public int edit(AbstractDalRequest request) {
        QueryBuilder qb = new QueryBuilder();
        AbstractQuery update = qb
                .update(this.tableName)
                .set(this.parseRequest(request))
                .where(this.tableIdentifier, request.getId());

        return this.update(update);
    }

    /**
     * @param filter
     * @return
     */
    public List<Map<String, Object>> get(FeedDalFilter filter) {
        QueryBuilder qb = new QueryBuilder();
        AbstractQuery select = qb.select().from(this.tableName);

        this.parseFilter(filter, select);

        return this.find(select);
    }

    /**
     * @param filter
     * @return
     */
    public Map<String, Object> getOne(FeedDalFilter filter) {
        QueryBuilder qb = new QueryBuilder();
        AbstractQuery select = qb.select().from(this.tableName);

        this.parseFilter(filter, select);

        return this.findOne(select);
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
            values.put("label", request.getLabel());
        }

        if (request.isEnabled() != null) {
            values.put("enabled", request.isEnabled());
        }

        return values;
    }

    /**
     * @param filter
     * @return
     */
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select) {
        if (filter.getId() != null) {
            select.where("id", filter.getId());
        }

        if (filter.getLabel() != null) {
            select.where("label", filter.getLabel());
        }

        if (filter.isEnabled() != null) {
            select.where("enabled", filter.isEnabled());
        }
    }

    /**
     * @param request
     * @return
     */
    protected int insert(AbstractQuery request) {
        request.build();

        logger.debug("# Insert SQL > " + request.getSQL());
        logger.debug("# Insert Params > " + request.getParameters());

        return this.dbWrapper.jdbcTemplate.queryForObject(request.getSQL(), request.getParameters(), Integer.class);
    }

    /**
     * @param request
     * @return
     */
    protected int update(AbstractQuery request) {
        request.build();

        logger.debug("# Update SQL > " + request.getSQL());
        logger.debug("# Update Params > " + request.getParameters());

        return this.dbWrapper.jdbcTemplate.update(request.getSQL(), request.getParameters());
    }

    /**
     * @param query
     * @return
     */
    protected List<Map<String, Object>> find(AbstractQuery query) {
        query.build();

        logger.debug("# Select All SQL > " + query.getSQL());
        logger.debug("# Select All Params > " + query.getParameters());

        return this.dbWrapper.jdbcTemplate.queryForList(query.getSQL(), query.getParameters());
    }

    /**
     * @param query
     * @return
     */
    protected Map<String, Object> findOne(AbstractQuery query) {
        query.build();

        logger.debug("# Select SQL > " + query.getSQL());
        logger.debug("# Select Params > " + query.getParameters());

        return this.dbWrapper.jdbcTemplate.queryForMap(query.getSQL(), query.getParameters());
    }
}
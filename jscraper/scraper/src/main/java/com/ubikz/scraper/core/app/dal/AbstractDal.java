package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.lib.db.DBWrapper;
import com.ubikz.scraper.core.lib.db.qb.AbstractQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 *
 */
abstract class AbstractDal {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDal.class);
    protected DBWrapper dbWrapper;

    @Autowired
    public AbstractDal(DBWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }

    /**
     * @param request
     * @return
     */
    public int insert(AbstractQuery request) {
        request.build();

        logger.debug("# Insert SQL > " + request.getSQL());
        logger.debug("# Insert Params > " + request.getParameters());

        return this.dbWrapper.jdbcTemplate.queryForObject(request.getSQL(), request.getParameters(), Integer.class);
    }

    /**
     * @param request
     * @return
     */
    public int update(AbstractQuery request) {
        request.build();

        logger.debug("# Update SQL > " + request.getSQL());
        logger.debug("# Update Params > " + request.getParameters());

        return this.dbWrapper.jdbcTemplate.update(request.getSQL(), request.getParameters());
    }

    /**
     * @param query
     * @return
     */
    public List<Map<String, Object>> find(AbstractQuery query) {
        query.build();

        logger.debug("# Select SQL > " + query.getSQL());
        logger.debug("# Select Params > " + query.getParameters());

        return this.dbWrapper.jdbcTemplate.queryForList(query.getSQL(), query.getParameters());
    }
}
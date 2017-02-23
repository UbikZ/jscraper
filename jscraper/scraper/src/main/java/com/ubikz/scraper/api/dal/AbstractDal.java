package com.ubikz.scraper.api.dal;

import com.ubikz.scraper.lib.db.DBWrapper;
import com.ubikz.scraper.lib.db.qb.AbstractQuery;
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
    public int request(AbstractQuery request) {
        request.build();

        logger.debug("# Request SQL > " + request.getSQL());
        logger.debug("# Request Params > " + request.getParameters());

        return this.dbWrapper.jdbcTemplate.queryForObject(request.getSQL(), request.getParameters(), Integer.class);
    }

    /**
     * @param query
     * @return
     */
    public List<Map<String, Object>> query(AbstractQuery query) {
        query.build();

        logger.debug("# Query SQL > " + query.getSQL());
        logger.debug("# Query Params > " + query.getParameters());

        return this.dbWrapper.jdbcTemplate.queryForList(query.getSQL(), query.getParameters());
    }
}
package com.ubikz.scraper.api.dal;

import com.ubikz.scraper.lib.db.DBWrapper;
import com.ubikz.scraper.lib.db.qb.AbstractQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

abstract class AbstractDal {
    protected DBWrapper dbWrapper;

    @Autowired
    public AbstractDal(DBWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }

    public int request(AbstractQuery request) {
        request.build();

        System.out.println("# Request SQL > " + request.getSQL());
        System.out.println("# Request Params > " + request.getParameters());

        return this.dbWrapper.jdbcTemplate.update(request.getSQL(), request.getParameters());
    }

    public List<Map<String, Object>> query(AbstractQuery query) {
        query.build();

        System.out.println("# Query SQL > " + query.getSQL());
        System.out.println("# Query Params > " + query.getParameters());

        return this.dbWrapper.jdbcTemplate.queryForList(query.getSQL(), query.getParameters());
    }
}
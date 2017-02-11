package com.ubikz.scraper.lib.db;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DBWrapper {
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public DBWrapper(DBProperties dbProperties) {
        this.jdbcTemplate = new JdbcTemplate(this.buildDataSource(dbProperties));
    }

    private DataSource buildDataSource(DBProperties dbProperties) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(dbProperties.getHost());
        dataSource.setPortNumber(dbProperties.getPort());
        dataSource.setDatabaseName(dbProperties.getDbName());
        dataSource.setUser(dbProperties.getUsername());
        dataSource.setPassword(dbProperties.getPassword());
        return dataSource;
    }
}
package org.ubikz.jscraper.database;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.database.model.DatabaseProperties;

import javax.sql.DataSource;

@Component
public class DatabaseService {
    public NamedParameterJdbcTemplate jdbcTemplate;
    private DataSourceTransactionManager transactionManager;
    private DataSource dataSource;

    @Autowired
    public DatabaseService(DatabaseProperties databaseProperties) {
        this.dataSource = this.buildDataSource(databaseProperties);
        this.jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource);
    }

    private DataSource buildDataSource(DatabaseProperties databaseProperties) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(databaseProperties.getHost());
        dataSource.setPortNumber(databaseProperties.getPort());
        dataSource.setDatabaseName(databaseProperties.getDbName());
        dataSource.setUser(databaseProperties.getUsername());
        dataSource.setPassword(databaseProperties.getPassword());
        return dataSource;
    }
}
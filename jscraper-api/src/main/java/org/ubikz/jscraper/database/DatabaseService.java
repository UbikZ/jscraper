package org.ubikz.jscraper.database;

import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.database.model.DatabaseProperties;
import org.ubikz.jscraper.database.querybuilder.AbstractQuery;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class DatabaseService {
    private final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    public NamedParameterJdbcTemplate jdbcTemplate;
    private DataSourceTransactionManager transactionManager;
    private DataSource dataSource;

    @Autowired
    public DatabaseService(DatabaseProperties databaseProperties) {
        this.dataSource = buildDataSource(databaseProperties);
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
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

    public int insert(AbstractQuery request) {
        request.build();

        logger.debug("# Insert SQL > " + request.getSQL());
        logger.debug("# Insert Params > " + request.getParameters());

        return jdbcTemplate.queryForObject(request.getSQL(), request.getParameters(), Integer.class);
    }

    public List<Object> insertMultiple(AbstractQuery request) {
        request.build();

        logger.debug("# Insert Multiple SQL > " + request.getSQL());
        logger.debug("# Insert Multiple Params > " + request.getParameters());

        return jdbcTemplate.queryForList(request.getSQL(), request.getParameters(), Object.class);
    }

    public int update(AbstractQuery request) {
        request.build();

        logger.debug("# Update SQL > " + request.getSQL());
        logger.debug("# Update Params > " + request.getParameters());

        return jdbcTemplate.update(request.getSQL(), request.getParameters());
    }

    public List<Map<String, Object>> find(AbstractQuery query) {
        query.build();

        logger.debug("# Select All SQL > " + query.getSQL());
        logger.debug("# Select All Params > " + query.getParameters());

        return jdbcTemplate.queryForList(query.getSQL(), query.getParameters());
    }

    public Map<String, Object> findOne(AbstractQuery query) {
        query.build();

        logger.debug("# Select SQL > " + query.getSQL());
        logger.debug("# Select Params > " + query.getParameters());

        return jdbcTemplate.queryForMap(query.getSQL(), query.getParameters());
    }

    public int delete(AbstractQuery request) {
        request.build();

        logger.debug("# Delete SQL > " + request.getSQL());
        logger.debug("# Delete Params > " + request.getParameters());

        return jdbcTemplate.update(request.getSQL(), request.getParameters());
    }

    public int count(AbstractQuery query) {
        query.build();

        logger.debug("# Count SQL > " + query.getSQL());
        logger.debug("# Count Params > " + query.getParameters());

        return jdbcTemplate.queryForObject(query.getSQL(), query.getParameters(), Integer.class);
    }
}

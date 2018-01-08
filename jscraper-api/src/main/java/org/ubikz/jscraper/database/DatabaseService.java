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
import org.ubikz.jscraper.database.querybuilder.Query;
import org.ubikz.jscraper.database.querybuilder.impl.Delete;
import org.ubikz.jscraper.database.querybuilder.impl.Insert;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.database.querybuilder.impl.Update;
import org.ubikz.jscraper.exception.ApplicativeException;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

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

    @SuppressWarnings("unchecked")
    private <T extends Query, R> R consume(Consumer<T> queryConsumer, BiFunction<String, Map<String, ?>, R> paramFunction, Class<T> clazz) {
        T query;

        try {
            query = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ApplicativeException(e);
        }

        queryConsumer.accept(query);

        String sql = query.toSQL();
        Map<String, ?> parameters = query.getParameters();

        logger.debug(String.format("# %s SQL > %s", clazz.getName(), sql));
        logger.debug(String.format("# %s Params > %s", clazz.getName(), parameters));

        return paramFunction.apply(sql, parameters);
    }

    public int insert(Consumer<Insert> consumer) {
        return consume(consumer, (sql, param) -> jdbcTemplate.queryForObject(sql, param, Integer.class), Insert.class);
    }

    public List<Object> insertMultiple(Consumer<Insert> consumer) {
        return consume(consumer, (sql, param) -> jdbcTemplate.queryForList(sql, param, Object.class), Insert.class);
    }

    public int update(Consumer<Update> consumer) {
        return consume(consumer, (sql, param) -> jdbcTemplate.update(sql, param), Update.class);
    }

    public List<Map<String, Object>> find(Consumer<Select> consumer) {
        return consume(consumer, (sql, param) -> jdbcTemplate.queryForList(sql, param), Select.class);

    }

    public Map<String, Object> findOne(Consumer<Select> consumer) {
        return consume(consumer, (sql, param) -> jdbcTemplate.queryForMap(sql, param), Select.class);

    }

    public int delete(Consumer<Delete> consumer) {
        return consume(consumer, (sql, param) -> jdbcTemplate.update(sql, param), Delete.class);
    }

    public int count(Consumer<Select> consumer) {
        return consume(consumer, (sql, param) -> jdbcTemplate.queryForObject(sql, param, Integer.class), Select.class);
    }
}

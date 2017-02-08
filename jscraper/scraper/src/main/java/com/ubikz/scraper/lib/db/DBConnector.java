package com.ubikz.scraper.lib.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

@EnableAutoConfiguration
public class DBConnector {
    @Autowired
    private DBProperties dbProperties;
    private String connectionString;

    public DBConnector() {
        this.connectionString = "jdbc:postgresql://" + dbProperties.getHost() + ":" + dbProperties.getPort() + "/" +
                dbProperties.getDbName();
    }

    private Connection open() throws SQLException {
        return DriverManager.getConnection(
                this.connectionString,
                dbProperties.getUsername(),
                dbProperties.getPassword()
        );
    }

    public void execute(Callable callable) throws Exception {
        Connection connection = null;

        try {
            connection = this.open();
            connection.setAutoCommit(false);

            callable.call();

            connection.commit();
            connection.close();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
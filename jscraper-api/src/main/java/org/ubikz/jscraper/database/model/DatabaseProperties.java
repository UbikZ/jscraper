package org.ubikz.jscraper.database.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jdbc")
public class DatabaseProperties {
    private String host;
    private int port;
    private String dbName;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DBProperties{"
                + "host='" + host + '\''
                + ", port=" + port
                + ", dbName='" + dbName + '\''
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
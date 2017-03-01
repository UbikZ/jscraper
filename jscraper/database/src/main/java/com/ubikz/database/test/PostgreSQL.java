package com.ubikz.database.test;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQL {
    public static void main(String[] args) throws Exception {
        if (args.length != 6) {
            throw new Exception("Wrong number of parameters.");
        }

        String mode = args[0];
        String host = args[1];
        String port = args[2];
        String dbName = args[3];
        String username = args[4];
        String password = args[5];

        String connexionString = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

        switch (mode) {
            case "check":
                PostgreSQL.checkDatabase(connexionString, username, password);
                break;
            case "migrate":
                PostgreSQL.migrate(connexionString, username, password);
                break;
            default:
                throw new Exception("Wrong mode provided.");
        }
    }

    private static void migrate(String connexionString, String username, String password) throws Exception {
        Flyway flyway = new Flyway();
        flyway.setDataSource(connexionString, username, password);
        flyway.migrate();
    }

    private static void checkDatabase(String connexionString, String username, String password) throws Exception {
        int maxTries = 60;
        Connection connection = null;

        while (true) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(connexionString, username, password);
                break;
            } catch (Exception e) {
                --maxTries;
                Thread.sleep(1000);
                if (maxTries == 0) {
                    System.err.println(e.getClass().getName() + " : Max tried connection reached. There is an issue.");
                    System.err.println(e.getMessage());
                    System.exit(0);
                }
            }
        }

        System.out.println("Opened database successfully");
    }
}
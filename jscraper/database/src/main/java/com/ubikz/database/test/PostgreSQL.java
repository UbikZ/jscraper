package com.ubikz.database.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQL {
    public static void main(String[] args) throws Exception {
        if (args.length != 5) {
            throw new Exception("Wrong number of parameters.");
        }

        int maxTries = 60;
        String host = args[0];
        String port = args[1];
        String dbName = args[2];
        String username = args[3];
        String password = args[4];

        Connection connection = null;

        while (true) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://" + host + ":" + port + "/" + dbName,
                        username,
                        password
                );
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
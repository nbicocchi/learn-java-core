package com.nbicocchi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

final class Database {
    private Database() {
    }

    static Connection openConnection() throws SQLException {
        String url = System.getenv().getOrDefault("ADDRESSBOOK_DB_URL",
                "jdbc:postgresql://localhost:5432/addressbook");
        Properties properties = new Properties();
        properties.setProperty("user", System.getenv().getOrDefault("ADDRESSBOOK_DB_USER", "addressbook"));
        properties.setProperty("password", System.getenv().getOrDefault("ADDRESSBOOK_DB_PASSWORD", "local-course-password"));
        properties.setProperty("connectTimeout", "5");
        properties.setProperty("socketTimeout", "10");
        return DriverManager.getConnection(url, properties);
    }
}

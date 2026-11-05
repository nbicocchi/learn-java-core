package com.nbicocchi.addressbook.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConfig {
    private final String url;
    private final String user;
    private final String password;

    public DatabaseConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DatabaseConfig fromEnvironment() {
        return new DatabaseConfig(
                System.getenv().getOrDefault("ADDRESSBOOK_DB_URL", "jdbc:postgresql://localhost:5432/addressbook"),
                System.getenv().getOrDefault("ADDRESSBOOK_DB_USER", "addressbook"),
                System.getenv().getOrDefault("ADDRESSBOOK_DB_PASSWORD", "local-course-password"));
    }

    public Connection openConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        properties.setProperty("connectTimeout", "5");
        properties.setProperty("socketTimeout", "10");
        return DriverManager.getConnection(url, properties);
    }
}

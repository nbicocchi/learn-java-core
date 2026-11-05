package com.nbicocchi.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadNames {
    public static void main(String[] args) throws SQLException {
        String sql = "SELECT id, name FROM names ORDER BY id";
        try (Connection connection = Database.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            while (rows.next()) {
                IO.println(rows.getLong("id") + " | " + rows.getString("name"));
            }
        }
    }
}

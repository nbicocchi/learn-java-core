package com.nbicocchi.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ImportNames {
    public static void main(String[] args) throws IOException, SQLException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: ImportNames <UTF-8 file>");
        }

        try (BufferedReader reader = Files.newBufferedReader(Path.of(args[0]), StandardCharsets.UTF_8);
             Connection connection = Database.openConnection()) {
            connection.setAutoCommit(false);
            try {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("""
                            CREATE TABLE IF NOT EXISTS names (
                                id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                name VARCHAR(80) NOT NULL UNIQUE CHECK (btrim(name) <> '')
                            )
                            """);
                }

                int inserted = 0;
                String sql = "INSERT INTO names (name) VALUES (?) ON CONFLICT (name) DO NOTHING";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String name = line.strip();
                        if (!name.isEmpty()) {
                            statement.setString(1, name);
                            inserted += statement.executeUpdate();
                        }
                    }
                }
                connection.commit();
                IO.println("Inserted " + inserted + " new names.");
            } catch (IOException | SQLException | RuntimeException failure) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackFailure) {
                    failure.addSuppressed(rollbackFailure);
                }
                throw failure;
            }
        }
    }
}

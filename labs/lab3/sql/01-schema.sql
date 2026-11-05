-- Safe to rerun: creates the table if absent; never drops existing data.
-- This is not a migration system: it does not modify an existing table definition.
CREATE TABLE IF NOT EXISTS persons (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(80) NOT NULL CHECK (btrim(first_name) <> ''),
    last_name VARCHAR(80) NOT NULL CHECK (btrim(last_name) <> ''),
    street VARCHAR(160) NOT NULL CHECK (btrim(street) <> ''),
    postal_code VARCHAR(20) NOT NULL CHECK (btrim(postal_code) <> ''),
    city VARCHAR(100) NOT NULL CHECK (btrim(city) <> ''),
    birthday DATE NOT NULL
);

-- A repeatable exercise: all changes are rolled back, including after an error
-- when psql is used with ON_ERROR_STOP and the connection closes.
BEGIN;

INSERT INTO persons (first_name, last_name, street, postal_code, city, birthday)
VALUES ('CLI', 'Practice', 'Temporary address', '00118', 'Roma', DATE '2000-01-01')
RETURNING id AS practice_id \gset

SELECT id, first_name, last_name, postal_code, city
FROM persons WHERE id = :practice_id;

UPDATE persons SET city = 'Modena' WHERE id = :practice_id;
SELECT id, city FROM persons WHERE id = :practice_id;

DELETE FROM persons WHERE id = :practice_id;
SELECT count(*) AS remaining_practice_rows FROM persons WHERE id = :practice_id;

ROLLBACK;

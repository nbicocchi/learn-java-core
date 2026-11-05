-- Optional demo data. Rerunning sequentially does not duplicate these contacts.
INSERT INTO persons (first_name, last_name, street, postal_code, city, birthday)
SELECT sample.*
FROM (VALUES
    ('Anna', 'Rossi', 'Via Emilia 12', '41121', 'Modena', DATE '1998-03-14'),
    ('Luca', 'Bianchi', 'Via Roma 8', '00118', 'Roma', DATE '1995-07-22'),
    ('Sara', 'Verdi', 'Via del Porto 5', '40122', 'Bologna', DATE '2001-11-06'),
    ('Marco', 'Conti', 'Corso Italia 27', '56125', 'Pisa', DATE '1992-01-30'),
    ('Giulia', 'Romano', 'Via Mazzini 16', '10123', 'Torino', DATE '2000-05-09'),
    ('Paolo', 'Ferrari', 'Via Garibaldi 3', '16124', 'Genova', DATE '1988-09-18')
) AS sample(first_name, last_name, street, postal_code, city, birthday)
WHERE NOT EXISTS (
    SELECT 1 FROM persons p
    WHERE p.first_name = sample.first_name
      AND p.last_name = sample.last_name
      AND p.street = sample.street
);

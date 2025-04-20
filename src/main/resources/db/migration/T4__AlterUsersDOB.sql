ALTER TABLE users
    ADD COLUMN date_of_birth DATE DEFAULT (CURRENT_DATE- INTERVAL '12 years');

UPDATE users
    SET date_of_birth = CURRENT_DATE - INTERVAL '12 years'
    WHERE date_of_birth IS NULL;

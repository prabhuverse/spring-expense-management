-- ========================
-- USER TABLE
-- ========================
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INTEGER,
    CONSTRAINT uc_users_email UNIQUE (email)
);

-- ========================
-- EXPENSE GROUP TABLE
-- ========================
CREATE TABLE IF NOT EXISTS expense_group (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INTEGER
);

-- ========================
-- EXPENSE FILE TABLE
-- ========================
CREATE TABLE IF NOT EXISTS expense_file (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    info JSON NOT NULL,
    version INTEGER,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ========================
-- EXPENSES TABLE
-- ========================
CREATE TABLE IF NOT EXISTS expenses (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    category VARCHAR(255) NOT NULL,
    created_on DATE DEFAULT CURRENT_DATE NOT NULL,
    description VARCHAR(100) NOT NULL,
    amount NUMERIC(10, 3) NOT NULL,
    user_id BIGINT,
    file_id BIGINT,
    group_id BIGINT,
    version INTEGER,
    CONSTRAINT fk_expenses_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_expenses_file FOREIGN KEY (file_id) REFERENCES expense_file(id),
    CONSTRAINT fk_expenses_group FOREIGN KEY (group_id) REFERENCES expense_group(id)
);

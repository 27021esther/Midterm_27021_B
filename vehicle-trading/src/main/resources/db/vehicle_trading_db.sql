-- ============================================================
-- vehicle_trading_db PostgreSQL Schema
-- Run this script in psql or pgAdmin after creating the DB
-- ============================================================

-- 1. Create the database (run this as superuser outside the DB)
-- CREATE DATABASE vehicle_trading_db;
-- \c vehicle_trading_db

-- ============================================================
-- TABLE: provinces
-- ============================================================
CREATE TABLE IF NOT EXISTS provinces (
    id            BIGSERIAL PRIMARY KEY,
    province_code VARCHAR(10)  NOT NULL UNIQUE,
    province_name VARCHAR(100) NOT NULL UNIQUE
);

-- ============================================================
-- TABLE: locations
-- ============================================================
CREATE TABLE IF NOT EXISTS locations (
    id          BIGSERIAL PRIMARY KEY,
    district    VARCHAR(100) NOT NULL,
    sector      VARCHAR(100) NOT NULL,
    street      VARCHAR(200),
    province_id BIGINT       NOT NULL,
    CONSTRAINT fk_location_province FOREIGN KEY (province_id)
        REFERENCES provinces (id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE: districts (Rwanda administrative hierarchy - level 2)
-- ============================================================
CREATE TABLE IF NOT EXISTS districts (
    id              BIGSERIAL PRIMARY KEY,
    district_code   VARCHAR(20)  NOT NULL UNIQUE,
    district_name   VARCHAR(100) NOT NULL,
    province_id     BIGINT       NOT NULL,
    CONSTRAINT fk_district_province FOREIGN KEY (province_id)
        REFERENCES provinces (id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE: sectors (Rwanda administrative hierarchy - level 3)
-- ============================================================
CREATE TABLE IF NOT EXISTS sectors (
    id            BIGSERIAL PRIMARY KEY,
    sector_code   VARCHAR(20)  NOT NULL UNIQUE,
    sector_name   VARCHAR(100) NOT NULL,
    district_id   BIGINT       NOT NULL,
    CONSTRAINT fk_sector_district FOREIGN KEY (district_id)
        REFERENCES districts (id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE: cells (Rwanda administrative hierarchy - level 4)
-- ============================================================
CREATE TABLE IF NOT EXISTS cells (
    id          BIGSERIAL PRIMARY KEY,
    cell_code   VARCHAR(20)  NOT NULL UNIQUE,
    cell_name   VARCHAR(100) NOT NULL,
    sector_id   BIGINT       NOT NULL,
    CONSTRAINT fk_cell_sector FOREIGN KEY (sector_id)
        REFERENCES sectors (id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE: villages (Rwanda administrative hierarchy - level 5)
-- ============================================================
CREATE TABLE IF NOT EXISTS villages (
    id           BIGSERIAL PRIMARY KEY,
    village_code VARCHAR(20)  NOT NULL UNIQUE,
    village_name VARCHAR(100) NOT NULL,
    cell_id      BIGINT       NOT NULL,
    CONSTRAINT fk_village_cell FOREIGN KEY (cell_id)
        REFERENCES cells (id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE: customers
-- ============================================================
CREATE TABLE IF NOT EXISTS customers (
    id          BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    phone       VARCHAR(20),
    location_id BIGINT UNIQUE,
    village_id  BIGINT NOT NULL,
    CONSTRAINT fk_customer_location FOREIGN KEY (location_id)
        REFERENCES locations (id) ON DELETE SET NULL,
    CONSTRAINT fk_customer_village FOREIGN KEY (village_id)
        REFERENCES villages (id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE: features
-- ============================================================
CREATE TABLE IF NOT EXISTS features (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

-- ============================================================
-- TABLE: vehicles
-- ============================================================
CREATE TABLE IF NOT EXISTS vehicles (
    id               BIGSERIAL PRIMARY KEY,
    make             VARCHAR(100)   NOT NULL,
    model            VARCHAR(100)   NOT NULL,
    manufacture_year INTEGER        NOT NULL,
    price            NUMERIC(15, 2) NOT NULL,
    vin              VARCHAR(50)    NOT NULL UNIQUE,
    status           VARCHAR(20)    NOT NULL CHECK (status IN ('AVAILABLE', 'SOLD', 'RESERVED'))
);

-- ============================================================
-- TABLE: vehicle_features  (Many-to-Many join table)
-- ============================================================
CREATE TABLE IF NOT EXISTS vehicle_features (
    vehicle_id BIGINT NOT NULL,
    feature_id BIGINT NOT NULL,
    PRIMARY KEY (vehicle_id, feature_id),
    CONSTRAINT fk_vf_vehicle FOREIGN KEY (vehicle_id)
        REFERENCES vehicles (id) ON DELETE CASCADE,
    CONSTRAINT fk_vf_feature FOREIGN KEY (feature_id)
        REFERENCES features (id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE: transactions
-- ============================================================
CREATE TABLE IF NOT EXISTS transactions (
    id               BIGSERIAL PRIMARY KEY,
    transaction_date TIMESTAMP      NOT NULL,
    sale_price       NUMERIC(15, 2) NOT NULL,
    transaction_type VARCHAR(10)    NOT NULL CHECK (transaction_type IN ('BUY', 'SELL')),
    customer_id      BIGINT         NOT NULL,
    vehicle_id       BIGINT         NOT NULL,
    CONSTRAINT fk_transaction_customer FOREIGN KEY (customer_id)
        REFERENCES customers (id) ON DELETE CASCADE,
    CONSTRAINT fk_transaction_vehicle  FOREIGN KEY (vehicle_id)
        REFERENCES vehicles (id) ON DELETE CASCADE
);

-- ============================================================
-- SAMPLE DATA (optional)
-- ============================================================

-- Provinces
INSERT INTO provinces (province_code, province_name) VALUES
    ('KIG', 'Kigali City'),
    ('S',   'Southern Province'),
    ('N',   'Northern Province'),
    ('E',   'Eastern Province'),
    ('W',   'Western Province')
ON CONFLICT DO NOTHING;

-- Districts (sample data for Rwanda - Kigali City)
INSERT INTO districts (district_code, district_name, province_id) VALUES
    ('GAS', 'Gasabo', 1),
    ('KIC', 'Kicukiro', 1),
    ('NDO', 'Nyarugenge', 1)
ON CONFLICT DO NOTHING;

-- Sectors (sample data)
INSERT INTO sectors (sector_code, sector_name, district_id) VALUES
    ('KIM', 'Kimironko', 1),
    ('NIB', 'Niboye', 2),
    ('NYA', 'Nyarugenge', 3)
ON CONFLICT DO NOTHING;

-- Cells (sample data)
INSERT INTO cells (cell_code, cell_name, sector_id) VALUES
    ('GIK', 'Gikomero', 1),
    ('KAG', 'Kagarama', 2),
    ('GSA', 'Gatsata', 3)
ON CONFLICT DO NOTHING;

-- Villages (sample data)
INSERT INTO villages (village_code, village_name, cell_id) VALUES
    ('V01', 'Bibare', 1),
    ('V02', 'Kabeza', 2),
    ('V03', 'Rugarama', 3)
ON CONFLICT DO NOTHING;

-- Locations
INSERT INTO locations (district, sector, street, province_id) VALUES
    ('Gasabo',   'Kimironko', 'KG 123 St', 1),
    ('Kicukiro', 'Niboye',    'KK 45 Ave', 1),
    ('Huye',     'Ngoma',     'SH 7 Rd',   2)
ON CONFLICT DO NOTHING;

-- Features
INSERT INTO features (name, description) VALUES
    ('Sunroof',       'Panoramic sunroof'),
    ('GPS',           'Built-in navigation system'),
    ('Leather Seats', 'Premium leather interior'),
    ('Backup Camera', 'Rear-view camera'),
    ('Bluetooth',     'Wireless audio connectivity')
ON CONFLICT DO NOTHING;

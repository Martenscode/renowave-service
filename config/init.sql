CREATE TABLE maintainer_account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(500) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO maintainer_account (username, password_hash)
VALUES ('admin', '$argon2id$v=19$m=10240,t=2,p=1$GfXakgxfqz3CJJOzezfzlg$mpAgXxmDPdBy7Q+9GeJxNYXEeqB7BtqvJen1B0ThgrM'); --pw: changeme

CREATE TABLE renovation (
  id SERIAL PRIMARY KEY,
  year INTEGER NOT NULL,
  antsla_vald INTEGER NOT NULL,
  rouge_vald INTEGER NOT NULL,
  setomaa_Vald INTEGER NOT NULL,
  voru_linn INTEGER NOT NULL,
  voru_vald INTEGER NOT NULL,
  county_total INTEGER NOT NULL
);

CREATE TABLE monthly_building_count (
    id BIGSERIAL PRIMARY KEY,
    year INT NOT NULL,
    municipality VARCHAR(100) NOT NULL,
    apt_count INT NOT NULL,
    month DATE NOT NULL,
    date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (year, municipality, month)
);

CREATE TABLE monthly_building_energy_class_count (
    id BIGSERIAL PRIMARY KEY,
    year INT NOT NULL,
    municipality VARCHAR(100) NOT NULL,
    energy_class VARCHAR(10) NOT NULL,
    count INT NOT NULL,
    month DATE NOT NULL,
    date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (year, municipality, energy_class, month)
);

CREATE TABLE apartment_statistics (
    id SERIAL PRIMARY KEY,
    kov_name VARCHAR(100) NOT NULL,
    kahe_kolme INTEGER DEFAULT 0,
    kuni_12 INTEGER DEFAULT 0,
    rohkem_kui_12 INTEGER DEFAULT 0,
    eriliselt_suur INTEGER DEFAULT 0,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO apartment_statistics (
    kov_name, kahe_kolme, kuni_12, rohkem_kui_12, eriliselt_suur
) VALUES
    ('ANTSLA_VALD', 0, 0, 0, 0),
    ('ROUGE_VALD', 0, 0, 0, 0),
    ('SETOMAA_VALD', 0, 0, 0, 0),
    ('VORU_LINN', 0, 0, 0, 0),
    ('VORU_VALD', 0, 0, 0, 0),
    ('VORU_MAAKOND', 0, 0, 0, 0);
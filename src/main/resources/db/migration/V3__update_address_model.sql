
ALTER TABLE addresses
RENAME COLUMN street TO address_line;

ALTER TABLE addresses
ADD COLUMN address_type VARCHAR(30) NOT NULL DEFAULT 'HOME';
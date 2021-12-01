CREATE DATABASE ers_p1;

CREATE TABLE users (
user_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
user_name VARCHAR(32), 
password_hash TEXT UNIQUE,  
user_manager BOOLEAN,
user_removed BOOLEAN);

CREATE TABLE requests (
request_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
user_ref INTEGER NOT NULL, 
amount NUMERIC,
date_created TIMESTAMP, 
pending BOOLEAN, 
approved BOOLEAN);

INSERT INTO users (user_name, password_hash, user_manager, user_removed) 
VALUES ('admin', 'admin', true, false);

INSERT INTO users (user_name, password_hash, user_manager, user_removed) 
VALUES ('default', 'Revature', false, false);

INSERT INTO requests (user_ref, amount, pending, approved)
VALUES (2, 6.37, true, false);
INSERT INTO requests (user_ref, amount, pending, approved)
VALUES (2, 151.42, true, false);
INSERT INTO requests (user_ref, amount, pending, approved)
VALUES (2, 1001.17, true, false);

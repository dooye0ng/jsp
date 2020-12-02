CREATE DATABASE if NOT EXISTS myhomedb;
USE myhomedb;

CREATE TABLE if NOT EXISTS USER (
	`id` VARCHAR(40) PRIMARY KEY,
	`password` VARCHAR(40) NOT NULL,
	`email` VARCHAR(40) NOT NULL UNIQUE,
	`name` VARCHAR(40) NOT NULL,
	`regdate` DATETIME DEFAULT CURRENT_TIMESTAMP()
);

-- data.sql 
-- INSERT INTO user VALUES('admin', 'admin1234', 'admin@test.com', 'manager', DEFAULT);
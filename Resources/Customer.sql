DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer(
    `customer_id` INT NOT NULL AUTO_INCREMENT,
	`user_name` VARCHAR(50) NOT NULL UNIQUE ,
    `email` VARCHAR(100) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
    `wallet_balance` DOUBLE DEFAULT 0.00,
    `saving_balance` DOUBLE DEFAULT 0.00,
	PRIMARY KEY(`customer_id`)
)ENGINE=INNODB;
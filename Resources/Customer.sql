DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer(
    `customerId` INT NOT NULL AUTO_INCREMENT,
	`userName` VARCHAR(50) NOT NULL UNIQUE ,
    `email` VARCHAR(100) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
	`firstName` VARCHAR(50) NOT NULL,
	`lastName` VARCHAR(50) NOT NULL,
    `walletBalance` DOUBLE DEFAULT 0.00,
    `savingBalance` DOUBLE DEFAULT 0.00,
	PRIMARY KEY(`customerId`)
)ENGINE=INNODB;
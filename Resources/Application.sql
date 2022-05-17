DROP DATABASE IF EXISTS wallet_application;
Drop TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Transaction;

CREATE DATABASE wallet_application;

USE wallet_application;

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

CREATE TABLE Transaction(
    `transaction_id` INT NOT NULL AUTO_INCREMENT,
    `type` ENUM('Unknown', 'Add', 'Withdraw', 'Deposit', 'Transfer') DEFAULT 'Unknown',
    `date_of_transaction` DATETIME DEFAULT NOW(),
    `wallet_balance_before` DOUBLE DEFAULT 0.00,
    `saving_balance_before` DOUBLE DEFAULT 0.00,
    `wallet_balance_after` DOUBLE DEFAULT 0.00,
    `saving_balance_after` DOUBLE DEFAULT 0.00,
    `amount` DOUBLE DEFAULT 0.00,
    `source` VARCHAR(200) NOT NULL,
    `destination` VARCHAR(200) NOT NULL, 
    `customer_id_fk` INT DEFAULT NULL,
	PRIMARY KEY(`transaction_id`),
    CONSTRAINT `FKnbpjofb5abhjg5hiovi0t3k57` FOREIGN KEY(`customer_id_fk`)
    REFERENCES `Customer` (`customer_id`)
    ON DELETE CASCADE
)ENGINE=INNODB;

-- Dummy 
INSERT INTO Customer(customer_id, user_name, password, email, first_name, last_name, wallet_balance, saving_balance) VALUES
(1, "Admin", "D6!aeYic%De5J3", "admin@mail.com", "Christopher", "Bessette-Lee", 150.00, 72.00);

INSERT INTO Transaction(transaction_id, customer_id_fk, type, date_of_transaction, wallet_balance_before, saving_balance_before, wallet_balance_after, saving_balance_after, amount, source, destination) VALUES
(1, 1, 'Transfer', "2022-05-01 12:45:56", 180.00, 42.00, 150.00, 72.00, 30.00, "Admin-Wallet", "Admin-Saving"),
(2, 1, 'Withdraw', "2022-05-02 15:45:56", 150.00, 72.00, 110.00, 72.00, 40.00, "Admin-Withdraw", "Admin-Wallet"),
(3, 1, 'Add', "2022-05-02 12:30:56", 110.00, 72.00, 140.00, 72.00, 30.00, "Admin-Add", "Admin-Wallet"),
(4, 1, 'Deposit', "2022-05-05 5:45:56", 140.00, 72.00, 140.00, 112.00, 40.00, "Admin-Deposit", "Admin-Saving"),
(5, 1, 'Transfer', "2022-05-06 18:45:56", 140.00, 112.00, 90.00, 162.00, 50.00, "Admin-Wallet", "Admin-Saving"),
(6, 1, 'Withdraw', "2022-05-07 19:30:56", 90.00, 162.00, 90.0, 140.00, 22.00, "Admin-Withdraw", "Admin-Saving"),
(7, 1, 'Deposit', "2022-05-07 12:45:56", 90.00, 140.00, 90.0, 170.00, 30.00, "Admin-Deposit", "Admin-Saving"),
(8, 1, 'Add', "2022-05-07 2:45:56", 90.0, 170.00, 170.0, 170.00, 80.00, "Admin-Add", "Admin-Wallet"),
(9, 1, 'Transfer', "2022-05-09 20:45:56", 170.00, 170.00, 120.0, 170.00, 50.00, "Admin-Wallet", "Friend-155285642645"),
(10, 1, 'Transfer', "2022-05-10 8:20:56", 120.00, 170.00, 100.0, 170.00, 20.00, "Admin-Wallet", "Friend-155285642645"),
(11, 1, 'Transfer', "2022-05-12 12:45:56", 100.00, 170.00, 70.0, 170.00, 30.00, "Admin-Wallet", "Friend-255387428787");

UPDATE Customer SET wallet_balance=70.00, saving_balance=170.00 WHERE customer_id=1; 

COMMIT;
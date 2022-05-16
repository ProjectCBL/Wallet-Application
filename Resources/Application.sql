DROP DATABASE IF EXISTS wallet_application;
Drop TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Transaction;

CREATE DATABASE wallet_application;

USE wallet_application;

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

CREATE TABLE Transaction(
    `transactionId` INT NOT NULL AUTO_INCREMENT,
	`customerId` INT NOT NULL,
    `type` ENUM('Unknown', 'Add', 'Withdraw', 'Deposit', 'Transfer') DEFAULT 'Unknown',
    `dateOfTransaction` DATETIME DEFAULT NOW(),
    `walletBalanceBefore` DOUBLE DEFAULT 0.00,
    `savingBalanceBefore` DOUBLE DEFAULT 0.00,
    `walletBalanceAfter` DOUBLE DEFAULT 0.00,
    `savingBalanceAfter` DOUBLE DEFAULT 0.00,
    `amount` DOUBLE DEFAULT 0.00,
    `source` VARCHAR(200) NOT NULL,
    `destination` VARCHAR(200) NOT NULL, 
	PRIMARY KEY(`transactionId`),
    FOREIGN KEY(`customerId`)
    REFERENCES Customer(`customerId`)
    ON DELETE CASCADE
)ENGINE=INNODB;

-- Dummy 
INSERT INTO Customer(customerId, userName, password, email, firstName, lastName, walletBalance, savingBalance) VALUES(1, "Admin", "D6!aeYic%De5J3", "admin@mail.com", "Christopher", "Bessette-Lee", 150.00, 72.00);

INSERT INTO Transaction(transactionId, customerId, type, dateOfTransaction, walletBalanceBefore, savingBalanceBefore, walletBalanceAfter, savingBalanceAfter, amount, source, destination) VALUES
(1, 1, 'Transfer', "2022-05-01", 180.00, 42.00, 150.00, 72.00, 30.00, "Admin-Wallet", "Admin-Saving"),
(2, 1, 'Withdraw', "2022-05-02", 150.00, 72.00, 110.00, 72.00, 40.00, "Admin-Withdraw", "Admin-Wallet"),
(3, 1, 'Add', "2022-05-02", 110.00, 72.00, 140.00, 72.00, 30.00, "Admin-Add", "Admin-Wallet"),
(4, 1, 'Deposit', "2022-05-05", 140.00, 72.00, 140.00, 112.00, 40.00, "Admin-Deposit", "Admin-Saving"),
(5, 1, 'Transfer', "2022-05-06", 140.00, 112.00, 90.00, 162.00, 50.00, "Admin-Wallet", "Admin-Saving"),
(6, 1, 'Withdraw', "2022-05-07", 90.00, 162.00, 90.0, 140.00, 22.00, "Admin-Withdraw", "Admin-Saving"),
(7, 1, 'Deposit', "2022-05-07", 90.00, 140.00, 90.0, 170.00, 30.00, "Admin-Deposit", "Admin-Saving"),
(8, 1, 'Add', 90.00, "2022-05-07", 170.00, 170.0, 170.00, 80.00, "Admin-Add", "Admin-Wallet"),
(9, 1, 'Transfer', "2022-05-09", 170.00, 170.00, 120.0, 170.00, 50.00, "Admin-Wallet", "Friend-155285642645"),
(10, 1, 'Transfer', "2022-05-10", 120.00, 170.00, 100.0, 170.00, 20.00, "Admin-Wallet", "Friend-155285642645"),
(11, 1, 'Transfer', "2022-05-12", 100.00, 170.00, 70.0, 170.00, 30.00, "Admin-Wallet", "Friend-255387428787");

UPDATE Customer SET walletBalance=70.00, savingBalance=170.00 WHERE customerId=1; 

COMMIT;
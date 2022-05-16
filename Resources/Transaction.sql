DROP TABLE IF EXISTS Transaction;

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
	PRIMARY KEY(tID),
    FOREIGN KEY(`customerId`)
    REFERENCES Customer(`customerId`)
    ON DELETE CASCADE
)ENGINE=INNODB;
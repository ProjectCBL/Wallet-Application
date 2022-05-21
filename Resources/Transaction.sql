DROP TABLE IF EXISTS Transaction;

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
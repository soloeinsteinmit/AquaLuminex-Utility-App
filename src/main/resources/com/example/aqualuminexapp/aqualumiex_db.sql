CREATE SCHEMA `aqualuminex` ;

-- ==========================================
CREATE TABLE `aqualuminex`.`user_credentials` (
  `user_id` VARCHAR(15) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(64) NOT NULL,
  `phone_number` VARCHAR(25) NOT NULL,
  `gender` VARCHAR(2) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `picture` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`));
  
-- ============================================================
CREATE TABLE `aqualuminex`.`meters` (
  `meter_id` VARCHAR(15) NOT NULL,
  `meter_name` VARCHAR(45) NOT NULL,
  `meter_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`meter_id`));
  
  -- ============================================================
CREATE TABLE `aqualuminex`.`meter_info` (
  `meter_id` VARCHAR(15) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `daily_comsumption_estimate` INT NOT NULL,
  `meter_balance` INT NOT NULL,
  `bill_per_month` INT NOT NULL,
  `customer_id` VARCHAR(45) NOT NULL,
  `customer_name` VARCHAR(45) NOT NULL,
  `metering_system` VARCHAR(45) NOT NULL,
  `total_spent_kWh` INT NOT NULL,
  `total_spent_GHS` INT NOT NULL,
  `meter_info` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customer_name`),
  INDEX `meter_id_idx` (`meter_id` ASC) VISIBLE,
  CONSTRAINT `meter_id`
    FOREIGN KEY (`meter_id`)
    REFERENCES `aqualuminex`.`meters` (`meter_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    -- ============================================================
CREATE TABLE `aqualuminex`.`transaction` (
  `transaction _id` VARCHAR(45) NOT NULL,
  `meter_id` VARCHAR(15) NOT NULL,
  `top_up_amount` INT NOT NULL,
  `top_up_status` VARCHAR(45) NOT NULL,
  `date` VARCHAR(25) NOT NULL,
  `time` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`transaction _id`));
  
  -- ============================================================
ALTER TABLE `aqualuminex`.`transaction` 
ADD CONSTRAINT `meterId`
  FOREIGN KEY (`meter_id`)
  REFERENCES `aqualuminex`.`meters` (`meter_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  -- ============================================================
CREATE TABLE `aqualuminex`.`wallet` (
  `account_number` VARCHAR(45) NOT NULL,
  `account_name` VARCHAR(45) NOT NULL,
  `account_type` VARCHAR(45) NOT NULL,
  `account_balance` INT NOT NULL,
  PRIMARY KEY (`account_number`));
  
  -- ============================================================
ALTER TABLE `aqualuminex`.`wallet` 
DROP COLUMN `account_balance`,
CHANGE COLUMN `account_number` `account_id` VARCHAR(45) NOT NULL ,
CHANGE COLUMN `account_type` `phone_number` VARCHAR(25) NOT NULL , RENAME TO  `aqualuminex`.`wallet_account` ;

-- ============================================================
ALTER TABLE `aqualuminex`.`wallet_account` 
ADD COLUMN `wallet_type` VARCHAR(45) NOT NULL AFTER `phone_number`,
CHANGE COLUMN `account_id` `wallet_id` VARCHAR(45) NOT NULL ,
CHANGE COLUMN `account_name` `wallet_name` VARCHAR(45) NOT NULL ;

-- =============================================================== 
CREATE TABLE `aqualuminex`.`wallet_balance` (
  `wallet_id` VARCHAR(45) NOT NULL,
  `wallet_balance` INT NOT NULL,
  PRIMARY KEY (`wallet_id`),
  CONSTRAINT `wallet_id`
    FOREIGN KEY (`wallet_id`)
    REFERENCES `aqualuminex`.`wallet_account` (`wallet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

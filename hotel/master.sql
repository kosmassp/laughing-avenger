CREATE  TABLE `hotel`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_number` VARCHAR(50) NOT NULL ,
  `id_type` VARCHAR(45) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `address` VARCHAR(45) NULL ,
  `phone` VARCHAR(45) NULL ,
  `age` INT NULL ,
  `birthday` DATETIME NULL ,
  `zip_code` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `CUSTOMER_UQ` (`id_number` ASC, `id_type` ASC, `name` ASC) )
COMMENT = 'Table for customer data whom checkin checkout' ;

CREATE  TABLE `hotel`.`facility` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `default_price` DECIMAL(16,2) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `NAME_IDX` (`name` ASC) )
COMMENT = 'Master table for facility that exist' ;


CREATE  TABLE `hotel`.`purchase` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `default_price` DECIMAL(16,2) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `NAME_IDX` (`name` ASC) )
COMMENT = 'Master table for item available for purchase' ;


CREATE  TABLE `hotel`.`room_type` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(150) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `RT_NAME_IDX` (`name` ASC) );

CREATE  TABLE `hotel`.`room` (
  `id` INT NOT NULL ,
  `id_room_type` INT ,
  `room_number` INT NOT NULL ,
  `room_floor` INT NOT NULL ,
  `name` VARCHAR(15) NULL ,
  `status` INT NULL ,
  `description` VARCHAR(150) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `R_NUMBER_IDX` (`room_number` ASC) ,
  INDEX `R_FLOOR_IDX` (`room_floor` ASC) ,
  INDEX `FK_R_ROOM_TYPE` (`id_room_type` ASC) ,
  CONSTRAINT `FK_R_ROOM_TYPE`
    FOREIGN KEY (`id_room_type` )
    REFERENCES `hotel`.`room_type` (`id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
COMMENT = 'Table for facility transaction such as blanket, extra bed' /* comment truncated */ 
;

CREATE  TABLE `hotel`.`event` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
COMMENT = 'Event Table' ;


CREATE  TABLE `hotel`.`event_price` (
  `id` INT NOT NULL ,
  `id_event` INT NOT NULL ,
  `id_room_type` INT NOT NULL ,
  `price` DECIMAL(16,2) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `EP_EVENT_ROOM_UQ` (`id_room_type` ASC, `id_event` ASC) ,
  INDEX `FK_EP_EVENT` (`id_event` ASC) ,
  INDEX `FK_EP_ROOM_TYPE` (`id_room_type` ASC) ,
  CONSTRAINT `FK_EP_EVENT`
    FOREIGN KEY (`id_event` )
    REFERENCES `hotel`.`event` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_EP_ROOM_TYPE`
    FOREIGN KEY (`id_room_type` )
    REFERENCES `hotel`.`room_type` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
COMMENT = 'Price for the event that currently any' ;


CREATE  TABLE `hotel`.`transaction` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_customer` INT NOT NULL ,
  `status` INT NOT NULL COMMENT 'transaction status' ,
  `check_in_time` DATETIME NULL ,
  `check_in_by` INT NULL ,
  `check_out_time` DATETIME NULL ,
  `check_out_by` INT NULL ,
  `status_id_card` VARCHAR(45) NULL ,
  `id_room` INT NOT NULL ,
  `id_event` INT NOT NULL ,
  `room_price` DECIMAL(16,2) NOT NULL ,
  `total_fee` DECIMAL(16,2) NULL ,
  `current_fee` DECIMAL(16,2) NULL ,
  `paid_fee` DECIMAL(16,2) NULL ,
  `unpaid_fee` DECIMAL(16,2) NULL ,
  `created_date` DATETIME NOT NULL ,
  `created_by` INT NOT NULL ,
  `modified_date` DATETIME NOT NULL ,
  `modified_by` INT NOT NULL ,
  `data_customer` VARCHAR(250) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_T_CUSTOMER` (`id_customer` ASC) ,
  INDEX `FK_T_ROOM` (`id_room` ASC) ,
  INDEX `FK_T_EVENT` (`id_event` ASC) ,
  CONSTRAINT `FK_T_CUSTOMER`
    FOREIGN KEY (`id_customer` )
    REFERENCES `hotel`.`customer` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_T_ROOM`
    FOREIGN KEY (`id_room` )
    REFERENCES `hotel`.`room` (`id` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `FK_T_EVENT`
    FOREIGN KEY (`id_event` )
    REFERENCES `hotel`.`event` (`id` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
COMMENT = 'Transactional event' ;


ALTER TABLE `hotel`.`transaction` 
	ADD COLUMN `check_in_by_name` VARCHAR(45) NULL  AFTER `data_customer` , 
	ADD COLUMN `check_out_by_name` VARCHAR(45) NULL  AFTER `check_in_by_name` ;


CREATE  TABLE `hotel`.`transaction_facility` (
  `id` INT NOT NULL ,
  `id_transaction` BIGINT NOT NULL ,
  `id_facility` INT NOT NULL ,
  `quantity` INT NOT NULL ,
  `price` DECIMAL(16,2) NOT NULL ,
  `total_price` DECIMAL(16,2) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_FT_TRANSACTION` (`id_transaction` ASC) ,
  INDEX `FK_FT_FACILITY` (`id_facility` ASC) ,
  CONSTRAINT `FK_FT_TRANSACTION`
    FOREIGN KEY (`id_transaction` )
    REFERENCES `hotel`.`transaction` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_FT_FACILITY`
    FOREIGN KEY (`id_facility` )
    REFERENCES `hotel`.`facility` (`id` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

    
CREATE  TABLE `hotel`.`transaction_purchase` (
  `id` INT NOT NULL ,
  `id_transaction` BIGINT NOT NULL ,
  `id_purchase` INT NOT NULL ,
  `quantity` INT NOT NULL ,
  `price` DECIMAL(16,2) NOT NULL ,
  `total_price` DECIMAL(16,2) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_PT_TRANSACTION` (`id_transaction` ASC) ,
  INDEX `FK_PT_PURCHASE` (`id_purchase` ASC) ,
  CONSTRAINT `FK_PT_TRANSACTION`
    FOREIGN KEY (`id_transaction` )
    REFERENCES `hotel`.`transaction` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_PT_PURCHASE`
    FOREIGN KEY (`id_purchase` )
    REFERENCES `hotel`.`purchase` (`id` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE);


ALTER TABLE `hotel`.`customer` CHANGE COLUMN `birthday` `birthdate` DATETIME NULL DEFAULT NULL  ;




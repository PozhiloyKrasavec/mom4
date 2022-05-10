-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`clients` (
  `idclients` INT NOT NULL,
  `FIO` VARCHAR(45) NOT NULL,
  `platform` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idclients`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Goods` (
  `idGoods` INT NOT NULL,
  `Brand` VARCHAR(45) NOT NULL,
  `Model` VARCHAR(45) NOT NULL,
  `Quantity` INT NOT NULL,
  `SellPrice` INT NOT NULL,
  `7daysPrice` INT NOT NULL,
  `14daysPrice` INT NOT NULL,
  `MonthPrice` INT NOT NULL,
  `2monthPrice` INT NOT NULL,
  `3monthPrice` INT NOT NULL,
  PRIMARY KEY (`idGoods`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Deals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Deals` (
  `idDeals` INT NOT NULL,
  `clients_idclients` INT NOT NULL,
  `Goods_idGoods` INT NOT NULL,
  `Time` INT NOT NULL,
  `Delivery` INT NOT NULL,
  `FinalPrice` INT NOT NULL,
  `Deposit` INT NOT NULL,
  `GiveDate` DATE NOT NULL,
  `ReceiveDate` DATE NOT NULL,
  `Docs` TINYINT NOT NULL,
  `ContractNum` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDeals`, `clients_idclients`, `Goods_idGoods`),
  INDEX `fk_Deals_clients_idx` (`clients_idclients` ASC) VISIBLE,
  INDEX `fk_Deals_Goods1_idx` (`Goods_idGoods` ASC) VISIBLE,
  CONSTRAINT `fk_Deals_clients`
    FOREIGN KEY (`clients_idclients`)
    REFERENCES `mydb`.`clients` (`idclients`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Deals_Goods1`
    FOREIGN KEY (`Goods_idGoods`)
    REFERENCES `mydb`.`Goods` (`idGoods`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

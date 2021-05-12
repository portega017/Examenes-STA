-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Mayo13
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Mayo13` ;

-- -----------------------------------------------------
-- Schema Mayo13
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Mayo13` ;
USE `Mayo13` ;

-- -----------------------------------------------------
-- Table `Mayo13`.`CONTACTOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Mayo13`.`CONTACTOS` ;

CREATE TABLE IF NOT EXISTS `Mayo13`.`CONTACTOS` (
  `idCONTACTOS` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NULL,
  `Alias` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `Telefono` VARCHAR(9) NULL,
  PRIMARY KEY (`idCONTACTOS`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

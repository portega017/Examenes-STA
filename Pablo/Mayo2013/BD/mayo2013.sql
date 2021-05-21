-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mayo2013
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mayo2013` ;

-- -----------------------------------------------------
-- Schema mayo2013
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mayo2013` ;
USE `mayo2013` ;

-- -----------------------------------------------------
-- Table `mayo2013`.`Contactos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mayo2013`.`Contactos` ;

CREATE TABLE IF NOT EXISTS `mayo2013`.`Contactos` (
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NULL,
  `Alias` VARCHAR(45) NULL,
  `Correo_electronico` VARCHAR(45) NULL,
  `NumTelf` INT(9) NULL,
  UNIQUE INDEX `NumTelf_UNIQUE` (`NumTelf` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

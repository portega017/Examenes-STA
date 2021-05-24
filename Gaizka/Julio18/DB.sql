-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ExamenSta
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ExamenSta` ;

-- -----------------------------------------------------
-- Schema ExamenSta
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ExamenSta` DEFAULT CHARACTER SET utf8 ;
USE `ExamenSta` ;

-- -----------------------------------------------------
-- Table `ExamenSta`.`Author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ExamenSta`.`Author` ;

CREATE TABLE IF NOT EXISTS `ExamenSta`.`Author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ExamenSta`.`Journal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ExamenSta`.`Journal` ;

CREATE TABLE IF NOT EXISTS `ExamenSta`.`Journal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `abbr` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ExamenSta`.`Publication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ExamenSta`.`Publication` ;

CREATE TABLE IF NOT EXISTS `ExamenSta`.`Publication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `year` INT NOT NULL,
  `journal` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Publication_Journal_idx` (`journal` ASC) VISIBLE,
  CONSTRAINT `fk_Publication_Journal`
    FOREIGN KEY (`journal`)
    REFERENCES `ExamenSta`.`Journal` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ExamenSta`.`Relation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ExamenSta`.`Relation` ;

CREATE TABLE IF NOT EXISTS `ExamenSta`.`Relation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `publication` INT NOT NULL,
  `author` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Relation_Publication1_idx` (`publication` ASC) VISIBLE,
  INDEX `fk_Relation_Author1_idx` (`author` ASC) VISIBLE,
  CONSTRAINT `fk_Relation_Author1`
    FOREIGN KEY (`author`)
    REFERENCES `ExamenSta`.`Author` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Relation_Publication1`
    FOREIGN KEY (`publication`)
    REFERENCES `ExamenSta`.`Publication` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

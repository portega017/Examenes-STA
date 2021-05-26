-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ExamenSta
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ExamenSta` ;

-- -----------------------------------------------------
-- Schema ExamenSta
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ExamenSta` ;
USE `ExamenSta` ;

-- -----------------------------------------------------
-- Table `ExamenSta`.`Examenes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ExamenSta`.`Examenes` ;

CREATE TABLE IF NOT EXISTS `ExamenSta`.`Examenes` (
  `idExamen` INT NOT NULL AUTO_INCREMENT,
  `Asignatura` VARCHAR(45) NOT NULL,
  `Alumnos` INT NOT NULL,
  PRIMARY KEY (`idExamen`),
  UNIQUE INDEX `Asignatura_UNIQUE` (`Asignatura` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ExamenSta`.`Entregas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ExamenSta`.`Entregas` ;

CREATE TABLE IF NOT EXISTS `ExamenSta`.`Entregas` (
  `idEntregas` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Respuesta` VARCHAR(45) NOT NULL,
  `Examenes_idExamenes` INT NOT NULL,
  PRIMARY KEY (`idEntregas`),
  INDEX `fk_Entregas_Examenes_idx` (`Examenes_idExamenes` ASC) VISIBLE,
  CONSTRAINT `fk_Entregas_Examenes`
    FOREIGN KEY (`Examenes_idExamenes`)
    REFERENCES `ExamenSta`.`Examenes` (`idExamen`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ExamenSta`.`Reservas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ExamenSta`.`Reservas` ;

CREATE TABLE IF NOT EXISTS `ExamenSta`.`Reservas` (
  `idReservas` INT NOT NULL AUTO_INCREMENT,
  `Aula` VARCHAR(45) NOT NULL,
  `Examenes_idExamen` INT NOT NULL,
  PRIMARY KEY (`idReservas`),
  UNIQUE INDEX `Aula_UNIQUE` (`Aula` ASC) VISIBLE,
  INDEX `fk_Reservas_Examenes1_idx` (`Examenes_idExamen` ASC) VISIBLE,
  CONSTRAINT `fk_Reservas_Examenes1`
    FOREIGN KEY (`Examenes_idExamen`)
    REFERENCES `ExamenSta`.`Examenes` (`idExamen`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

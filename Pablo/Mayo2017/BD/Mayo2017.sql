-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Mayo2017
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Mayo2017` ;

-- -----------------------------------------------------
-- Schema Mayo2017
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Mayo2017` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `Mayo2017` ;

-- -----------------------------------------------------
-- Table `Mayo2017`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Mayo2017`.`Categoria` ;

CREATE TABLE IF NOT EXISTS `Mayo2017`.`Categoria` (
  `idCategorias` INT NOT NULL AUTO_INCREMENT,
  `nombreCategotria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategorias`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Mayo2017`.`Tarea`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Mayo2017`.`Tarea` ;

CREATE TABLE IF NOT EXISTS `Mayo2017`.`Tarea` (
  `idTareas` INT NOT NULL AUTO_INCREMENT,
  `nombreTareas` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTareas`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Mayo2017`.`Tiempo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Mayo2017`.`Tiempo` ;

CREATE TABLE IF NOT EXISTS `Mayo2017`.`Tiempo` (
  `idTiempo` INT NOT NULL AUTO_INCREMENT,
  `tiempo` BIGINT NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `Categorias_idCategorias` INT NOT NULL,
  `Tareas_idTareas` INT NOT NULL,
  PRIMARY KEY (`idTiempo`),
  INDEX `fk_Tiempo_Categorias_idx` (`Categorias_idCategorias` ASC) VISIBLE,
  INDEX `fk_Tiempo_Tareas1_idx` (`Tareas_idTareas` ASC) VISIBLE,
  CONSTRAINT `fk_Tiempo_Categorias`
    FOREIGN KEY (`Categorias_idCategorias`)
    REFERENCES `Mayo2017`.`Categoria` (`idCategorias`),
  CONSTRAINT `fk_Tiempo_Tareas1`
    FOREIGN KEY (`Tareas_idTareas`)
    REFERENCES `Mayo2017`.`Tarea` (`idTareas`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

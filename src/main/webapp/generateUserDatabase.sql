-- MySQL Script generated by MySQL Workbench
-- Sat Feb 22 15:37:05 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gestorDatabase
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestorDatabase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestorDatabase` DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci ;
USE `gestorDatabase` ;

-- -----------------------------------------------------
-- Table `gestorDatabase`.`parents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestorDatabase`.`parents` (
  `parentId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`parentId`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `gestorDatabase`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestorDatabase`.`students` (
  `studentId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `parents_parentId` INT NOT NULL,
  PRIMARY KEY (`studentId`, `parents_parentId`),
  UNIQUE INDEX `userId_UNIQUE` (`studentId` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_students_parents1_idx` (`parents_parentId` ASC) VISIBLE,
  CONSTRAINT `fk_students_parents1`
    FOREIGN KEY (`parents_parentId`)
    REFERENCES `gestorDatabase`.`parents` (`parentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `gestorDatabase`.`teachers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestorDatabase`.`teachers` (
  `teacherId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `passowrd` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`teacherId`),
  UNIQUE INDEX `teacherId_UNIQUE` (`teacherId` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `gestorDatabase`.`subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestorDatabase`.`subjects` (
  `subjectId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `students_studentId` INT NOT NULL,
  `teachers_teacherId` INT NOT NULL,
  PRIMARY KEY (`subjectId`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_subjects_students_idx` (`students_studentId` ASC) VISIBLE,
  INDEX `fk_subjects_teachers1_idx` (`teachers_teacherId` ASC) VISIBLE,
  CONSTRAINT `fk_subjects_students`
    FOREIGN KEY (`students_studentId`)
    REFERENCES `gestorDatabase`.`students` (`studentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subjects_teachers1`
    FOREIGN KEY (`teachers_teacherId`)
    REFERENCES `gestorDatabase`.`teachers` (`teacherId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `gestorDatabase`.`grades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestorDatabase`.`grades` (
  `gradeId` INT NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `subjects_subjectId` INT NOT NULL,
  PRIMARY KEY (`gradeId`, `subjects_subjectId`),
  INDEX `fk_grades_subjects1_idx` (`subjects_subjectId` ASC) VISIBLE,
  CONSTRAINT `fk_grades_subjects1`
    FOREIGN KEY (`subjects_subjectId`)
    REFERENCES `gestorDatabase`.`subjects` (`subjectId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
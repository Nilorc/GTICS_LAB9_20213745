SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema lab9
-- -----------------------------------------------------


-- -----------------------------------------------------
DROP DATABASE IF EXISTS `lab9`;
CREATE SCHEMA IF NOT EXISTS `lab9` DEFAULT CHARACTER SET utf8mb3 ;
USE `lab9` ;
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS favorites (
    id INT AUTO_INCREMENT PRIMARY KEY,
    meal_id VARCHAR(50) NOT NULL,
    meal_name VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    area VARCHAR(100),
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
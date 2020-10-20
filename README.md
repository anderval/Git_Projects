TCC

Ferramentas e Tecnologias Utilizadas:
JSP - 2.2 +
IDE - Eclipse Neon
JDK - 1.8 or later
Apache Tomcat - 8.5
JSTL - 1.2.1
Servlet API - 2.5
MySQL - mysql-connector-java-8.0.13.jar


Banco MYSQL:

CREATE DATABASE TCC;
USE TCC;

CREATE TABLE medico (
  `crm` INT NOT NULL,
  `nome_medico` VARCHAR(45) NOT NULL,
  `especialidade` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`crm`));

CREATE TABLE hospital (
  `idHospital` INT NOT NULL,
  `nome_hospital` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idHospital`));

CREATE TABLE farmaceutico (
  `crf` INT NOT NULL,
  `nome_farmaceutico` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`crf`));

CREATE TABLE receituario (
  `id_receituario` INT NOT NULL AUTO_INCREMENT,
  `cpf_paciente` VARCHAR(11) NOT NULL,
  `nome_paciente` VARCHAR(45) NOT NULL,
  `data_emissao` DATE NOT NULL,
  `itens_receita` VARCHAR(1000) NOT NULL,
  `status_receituario` TINYINT NOT NULL,
  `hash` VARCHAR(100) NOT NULL,
  `hospital_idHospital` INT NULL,
  `medico_crm` INT NOT NULL,
  `farmaceutico_crf` INT NULL,
  PRIMARY KEY (`id_receituario`, `medico_crm`),
  INDEX `fk_receituario_hospital_idx` (`hospital_idHospital` ASC) VISIBLE,
  INDEX `fk_receituario_medico1_idx` (`medico_crm` ASC) VISIBLE,
  INDEX `fk_receituario_farmaceutico1_idx` (`farmaceutico_crf` ASC) VISIBLE,
  CONSTRAINT `fk_receituario_hospital`
    FOREIGN KEY (`hospital_idHospital`)
    REFERENCES `TCC`.`hospital` (`idHospital`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receituario_medico1`
    FOREIGN KEY (`medico_crm`)
    REFERENCES `TCC`.`medico` (`crm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receituario_farmaceutico1`
    FOREIGN KEY (`farmaceutico_crf`)
    REFERENCES `TCC`.`farmaceutico` (`crf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

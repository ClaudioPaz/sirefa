-- MySQL Script generated by MySQL Workbench
-- 04/01/15 08:28:58
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sirefa
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sirefa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sirefa` DEFAULT CHARACTER SET latin1 ;
USE `sirefa` ;

-- -----------------------------------------------------
-- Table `sirefa`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`persona`;
CREATE TABLE IF NOT EXISTS `sirefa`.`persona` (
  `idPersona` INT(11) NOT NULL AUTO_INCREMENT,
  `primerNombre` VARCHAR(45) NOT NULL,
  `segundoNombre` VARCHAR(45) NULL DEFAULT NULL,
  `primerApellido` VARCHAR(45) NOT NULL,
  `segundoApellido` VARCHAR(45) NULL DEFAULT NULL,
  `fechaDeNacimiento` DATE NOT NULL,
  `direccion` VARCHAR(200) NULL DEFAULT NULL,
  `numeroTelefono` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`idPersona`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`tipo_empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`tipo_empleado`;
CREATE TABLE IF NOT EXISTS `sirefa`.`tipo_empleado` (
  `idTipo_Empleado` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idTipo_Empleado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`empleado`;
CREATE TABLE IF NOT EXISTS `sirefa`.`empleado` (
  `idPersona` INT(11) NOT NULL,
  `nombreDeUsuario` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(45) NOT NULL,
  `fechaIngreso` DATE NOT NULL,
  `idTipoEmpleado` INT(11) NOT NULL,
  PRIMARY KEY (`idPersona`),
  INDEX `FK_EMPLEADO_PERSONA` (`idPersona` ASC),
  INDEX `FK_TipoEmpleado_idx` (`idTipoEmpleado` ASC),
  UNIQUE INDEX `FK_NOMBREUSUARIO_UNIQUE` (`nombreDeUsuario` ASC),
  CONSTRAINT `FK_Persona`
    FOREIGN KEY (`idPersona`)
    REFERENCES `sirefa`.`persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_TipoEmpleado`
    FOREIGN KEY (`idTipoEmpleado`)
    REFERENCES `sirefa`.`tipo_empleado` (`idTipo_Empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`paciente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`paciente`;
CREATE TABLE IF NOT EXISTS `sirefa`.`paciente` (
  `idPersona` INT(11) NOT NULL,
  PRIMARY KEY (`idPersona`),
  INDEX `FK_Persona_idx` (`idPersona` ASC),
  CONSTRAINT `FK_PACIENTE_PERSONA`
    FOREIGN KEY (`idPersona`)
    REFERENCES `sirefa`.`persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`cita`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`cita`;
CREATE TABLE IF NOT EXISTS `sirefa`.`cita` (
  `idCita` INT(11) NOT NULL AUTO_INCREMENT,
  `idPersona` INT(11) NOT NULL,
  `idEmpleado` INT(11) NOT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idCita`),
  INDEX `FK_Paciente_idx` (`idPersona` ASC),
  INDEX `FK_Empleado_idx` (`idEmpleado` ASC),
  CONSTRAINT `FK_CITA_EMPLEADO`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `sirefa`.`empleado` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CITA_PACIENTE`
    FOREIGN KEY (`idPersona`)
    REFERENCES `sirefa`.`paciente` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`diagnostico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`diagnostico`;
CREATE TABLE IF NOT EXISTS `sirefa`.`diagnostico` (
  `idDiagnostico` INT(11) NOT NULL AUTO_INCREMENT,
  `idCita` INT(11) NOT NULL,
  PRIMARY KEY (`idDiagnostico`),
  INDEX `FK_Cita_idx` (`idCita` ASC),
  CONSTRAINT `FK_DIAGNOSTICO_CITA`
    FOREIGN KEY (`idCita`)
    REFERENCES `sirefa`.`cita` (`idCita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`enfermedades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`enfermedades`;
CREATE TABLE IF NOT EXISTS `sirefa`.`enfermedades` (
  ` idEnfermedad` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (` idEnfermedad`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`enfermedades_diagnostico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`enfermedades_diagnostico`;
CREATE TABLE IF NOT EXISTS `sirefa`.`enfermedades_diagnostico` (
  `idEnfermedad` INT(11) NOT NULL,
  `idDiagnostico` INT(11) NOT NULL,
  PRIMARY KEY (`idEnfermedad`, `idDiagnostico`),
  INDEX `FK_Enfermedad_idx` (`idEnfermedad` ASC),
  INDEX `FK_Diagnostico_idx` (`idDiagnostico` ASC),
  CONSTRAINT `FK_Diagnostico`
    FOREIGN KEY (`idDiagnostico`)
    REFERENCES `sirefa`.`diagnostico` (`idDiagnostico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Enfermedad`
    FOREIGN KEY (`idEnfermedad`)
    REFERENCES `sirefa`.`enfermedades` (` idEnfermedad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`info_preclinica`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`info_preclinica`;
CREATE TABLE IF NOT EXISTS `sirefa`.`info_preclinica` (
  `idCita` INT(11) NOT NULL,
  `idPreClinica` INT(11) NOT NULL AUTO_INCREMENT,
  `PA` VARCHAR(45) NULL DEFAULT NULL,
  `P_FC` VARCHAR(45) NULL DEFAULT NULL,
  `temperatura` VARCHAR(45) NULL DEFAULT NULL,
  `FR` VARCHAR(45) NULL DEFAULT NULL,
  `peso` FLOAT NULL DEFAULT NULL,
  `talla` FLOAT NULL DEFAULT NULL,
  `cabeza` VARCHAR(45) NULL DEFAULT NULL,
  `ojos` VARCHAR(45) NULL DEFAULT NULL,
  `nariz` VARCHAR(45) NULL DEFAULT NULL,
  `boca` VARCHAR(45) NULL DEFAULT NULL,
  `oidos` VARCHAR(45) NULL DEFAULT NULL,
  `garganta` VARCHAR(45) NULL DEFAULT NULL,
  `cuello` VARCHAR(45) NULL DEFAULT NULL,
  `torax` VARCHAR(45) NULL DEFAULT NULL,
  `mamas` VARCHAR(45) NULL DEFAULT NULL,
  `corazon` VARCHAR(45) NULL DEFAULT NULL,
  `pulmones` VARCHAR(45) NULL DEFAULT NULL,
  `abdomen` VARCHAR(45) NULL DEFAULT NULL,
  `genitales` VARCHAR(45) NULL DEFAULT NULL,
  `piel_faneras` VARCHAR(45) NULL DEFAULT NULL,
  `neurologico` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idPreClinica`),
  INDEX `FK_Cita_idx` (`idCita` ASC),
  CONSTRAINT `FK_Cita`
    FOREIGN KEY (`idCita`)
    REFERENCES `sirefa`.`cita` (`idCita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`tipo_medicamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`tipo_medicamento`;
CREATE TABLE IF NOT EXISTS `sirefa`.`tipo_medicamento` (
  `idTipo_Medicamento` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipo_Medicamento`),
  UNIQUE INDEX `UNIQUE_DESCRIPCION_TIPO_MEDICAMENTO` (`descripcion` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`medicamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`medicamento`;
CREATE TABLE IF NOT EXISTS `sirefa`.`medicamento` (
  `idMedicamento` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `idTipoMedicamento` INT(11) NOT NULL,
  PRIMARY KEY (`idMedicamento`),
  UNIQUE INDEX `UNIQUE_NOMBREMEDICAMENTO` (`nombre` ASC),
  INDEX `FK_TipoMedicamento_idx` (`idTipoMedicamento` ASC),
  CONSTRAINT `FK_TipoMedicamento`
    FOREIGN KEY (`idTipoMedicamento`)
    REFERENCES `sirefa`.`tipo_medicamento` (`idTipo_Medicamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`medicamento_instancia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`medicamento_instancia`;
CREATE TABLE IF NOT EXISTS `sirefa`.`medicamento_instancia` (
  `idMedicamento_Instancia` INT(11) NOT NULL AUTO_INCREMENT,
  `idMedicamento` INT(11) NOT NULL,
  `cantidad` INT(11) NOT NULL,
  `fechaIngreso` DATE NOT NULL,
  `fechaVencimiento` DATE NOT NULL,
  PRIMARY KEY (`idMedicamento_Instancia`),
  INDEX `FK_Medicamento_idx` (`idMedicamento` ASC),
  CONSTRAINT `FK_Medicamento`
    FOREIGN KEY (`idMedicamento`)
    REFERENCES `sirefa`.`medicamento` (`idMedicamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`prescripcion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`prescripcion`;
CREATE TABLE IF NOT EXISTS `sirefa`.`prescripcion` (
  `idPrescripcion` INT(11) NOT NULL AUTO_INCREMENT,
  `idCita` INT(11) NOT NULL,
  `observacion` VARCHAR(200) NULL DEFAULT NULL,
  `otros` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idPrescripcion`),
  INDEX `FK_Cita_idx` (`idCita` ASC),
  CONSTRAINT `FK_PRESCRIPCION_CITA`
    FOREIGN KEY (`idCita`)
    REFERENCES `sirefa`.`cita` (`idCita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`medicamento_prescripcion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`medicamento_prescripcion`;
CREATE TABLE IF NOT EXISTS `sirefa`.`medicamento_prescripcion` (
  `idMedicamento` INT(11) NOT NULL,
  `idPrescripcion` INT(11) NOT NULL,
  `cantidad` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idMedicamento`, `idPrescripcion`),
  INDEX `FK_Medicamento_idx` (`idMedicamento` ASC),
  INDEX `FK_Prescripcion_idx` (`idPrescripcion` ASC),
  CONSTRAINT `FK_MEDICAMENTO_PRESCRIPCION_MEDICAMENTO`
    FOREIGN KEY (`idMedicamento`)
    REFERENCES `sirefa`.`medicamento` (`idMedicamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_MEDICAMENTO_PRESCRIPCION_PRESCRIPCION`
    FOREIGN KEY (`idPrescripcion`)
    REFERENCES `sirefa`.`prescripcion` (`idPrescripcion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`privilegios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`privilegios`;
CREATE TABLE IF NOT EXISTS `sirefa`.`privilegios` (
  `idPrivilegio` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idPrivilegio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`remitencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`remitencia`;
CREATE TABLE IF NOT EXISTS `sirefa`.`remitencia` (
  `idRemitencia` INT(11) NOT NULL AUTO_INCREMENT,
  `idCita` INT(11) NOT NULL,
  `hospital` VARCHAR(45) NOT NULL,
  `nombreDoctor` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idRemitencia`),
  INDEX `FK_Cita_idx` (`idCita` ASC),
  CONSTRAINT `FK_REMITENCIA_CITA`
    FOREIGN KEY (`idCita`)
    REFERENCES `sirefa`.`cita` (`idCita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`roles`;
CREATE TABLE IF NOT EXISTS `sirefa`.`roles` (
  `idRol` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`roles_privilegios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`roles_privilegios`;
CREATE TABLE IF NOT EXISTS `sirefa`.`roles_privilegios` (
  `idRol` INT(11) NOT NULL,
  `idPrivilegio` INT(11) NOT NULL,
  PRIMARY KEY (`idRol`, `idPrivilegio`),
  INDEX `FK_ROL_idx` (`idRol` ASC),
  INDEX `FK_PRIVILEGIO_idx` (`idPrivilegio` ASC),
  CONSTRAINT `FK_PRIVILEGIO`
    FOREIGN KEY (`idPrivilegio`)
    REFERENCES `sirefa`.`privilegios` (`idPrivilegio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ROL`
    FOREIGN KEY (`idRol`)
    REFERENCES `sirefa`.`roles` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sirefa`.`roles_usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sirefa`.`roles_usuarios`;
CREATE TABLE IF NOT EXISTS `sirefa`.`roles_usuarios` (
  `idRol` INT(11) NOT NULL,
  `idUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`idRol`, `idUsuario`),
  INDEX `FK_USUARIO_idx` (`idUsuario` ASC),
  INDEX `FK_ROL_idx` (`idRol` ASC),
  CONSTRAINT `FK_ROLES_USUARIOS_ROLES`
    FOREIGN KEY (`idRol`)
    REFERENCES `sirefa`.`roles` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ROLES_USUARIOS_USUARIO`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `sirefa`.`empleado` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

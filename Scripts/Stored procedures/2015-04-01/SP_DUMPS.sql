-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sirefa
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping routines for database 'sirefa'
--
/*!50003 DROP PROCEDURE IF EXISTS `SP_GESTIONAR_USUARIOS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_GESTIONAR_USUARIOS`(
	IN pcPrimerNombre VARCHAR(50), -- Primer nombre del usuario
	IN pcSegundoNombre VARCHAR(50),  -- Segundo nombre del usuario
    IN pcPrimerApellido VARCHAR(50), -- Primer apellido del usuario
    IN pcSegundoApellido VARCHAR(50), -- Segundo apellido del usuario
	IN pdFechaNacimiento DATE, 	-- Fecha de nacimiento del usuario
	IN pcDireccion VARCHAR(50), -- Dirección del usuario
	IN pcTelefono VARCHAR(50),	-- Número de telefono
    IN pcNombreUsuario VARCHAR(45), -- Nombre de usuario
    IN pcClave VARCHAR(45), -- Clave de usuario
    OUT pcMensajeError VARCHAR(500) -- Parámetro para mensajes de error
    
)
SP:BEGIN

	DECLARE vcTempMensajeError VARCHAR(500) DEFAULT ''; -- Variable para almacenar posibles errores no controlados de servidor
    DECLARE vnCodigoUltimoInsert INT DEFAULT 0;	-- Variable para obtener el último código insertado (SELECT LAST_INSERT_ID())
    DECLARE vnContadorNombreUsuario INT DEFAULT 0; -- Variable para determinar si el nombre de usuario ya está siendo usado
    DECLARE vcMensajeErrorServidor TEXT; -- Variable para almacenar el mensaje de error del servidor
    DECLARE vnCodigoTipoEmpleadoPermanente INT DEFAULT 1;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
    
		ROLLBACK;
    
		GET DIAGNOSTICS CONDITION 1 vcMensajeErrorServidor = MESSAGE_TEXT;
        SET vcTempMensajeError := CONCAT('Error: ', vcTempMensajeError, ' Error servidor: ', vcMensajeErrorServidor);
        SET pcMensajeError := vcTempMensajeError;
    
    END;

	
    -- Determinar si el nombre de usuario ya está siendo usado
    SET vcTempMensajeError := 'Error al seleccionar COUNT de nombre de usuario';
	SELECT
		COUNT(nombreDeUsuario)
	INTO
		vnContadorNombreUsuario
	FROM
		EMPLEADO
	WHERE
		nombreDeUsuario = pcNombreUsuario;
        
        
	-- El nombre de usuario ya está siendo usado
	IF vnContadorNombreUsuario > 0 then
    
		SET pcMensajeError := 'El nombre de usuario ya está siendo usado, intenta otro';
        LEAVE SP;
    
    END IF;
	
    -- Agregar registro a la tabla PERSONA
    SET vcTempMensajeError := 'Error al crear registro en PERSONA';
    
    INSERT INTO PERSONA(primerNombre, segundoNombre, primerApellido, segundoApellido, fechaDeNacimiento, direccion, numeroTelefono)
	VALUES(pcPrimerNombre, pcSegundoNombre, pcPrimerApellido, pcSegundoApellido, pdFechaNacimiento, pcDireccion, pcTelefono);
    
    -- Agregar registro a la tabla PERSONA
    SET vcTempMensajeError := 'Error al hacer select last_insert_id()';
    SELECT LAST_INSERT_ID()
    INTO vnCodigoUltimoInsert;
    
	-- Agregar registro a la tabla EMPLEADO
    SET vcTempMensajeError := 'Error al crear registro en EMPLEADO';
    
    INSERT INTO EMPLEADO(idPersona, nombreDeUsuario, clave, fechaIngreso, idtipoEmpleado)
    VALUES(vnCodigoUltimoInsert, pcNombreUsuario, pcClave, NOW(), vnCodigoTipoEmpleadoPermanente);
    

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-01  9:03:37

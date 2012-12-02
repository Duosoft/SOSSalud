-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.24-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema bd_centrosalud
--

CREATE DATABASE IF NOT EXISTS bd_centrosalud;
USE bd_centrosalud;

--
-- Definition of table `tbl_ciudad`
--

DROP TABLE IF EXISTS `tbl_ciudad`;
CREATE TABLE `tbl_ciudad` (
  `codCiudad` int(2) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de la ciudad, la referencia utilizada son el numero de las regiones en Chile',
  `nombre` varchar(255) NOT NULL COMMENT 'Nombre de la ciudad',
  `comuna` varchar(255) NOT NULL COMMENT 'Nombre de la comuna',
  PRIMARY KEY (`codCiudad`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Informacion de la region';

--
-- Dumping data for table `tbl_ciudad`
--

/*!40000 ALTER TABLE `tbl_ciudad` DISABLE KEYS */;
INSERT INTO `tbl_ciudad` (`codCiudad`,`nombre`,`comuna`) VALUES 
 (9,'Temuco','Temuco');
/*!40000 ALTER TABLE `tbl_ciudad` ENABLE KEYS */;


--
-- Definition of table `tbl_hospital`
--

DROP TABLE IF EXISTS `tbl_hospital`;
CREATE TABLE `tbl_hospital` (
  `id_hospital` int(10) NOT NULL COMMENT 'Identificador de hospital',
  `nombre` varchar(255) NOT NULL COMMENT 'Nombre del establecimiento de salud',
  `direccion` varchar(255) NOT NULL COMMENT 'Direccion del centro de salud',
  `telefono` varchar(255) NOT NULL COMMENT 'Telefono central del centro de saludo',
  `dependencia` varchar(255) NOT NULL COMMENT 'Tipo de establecimiento(publico o privado)',
  `localizacion` varchar(255) DEFAULT NULL COMMENT 'Localizacion del centro medico en el hospital',
  `cod_ciudad` int(2) NOT NULL,
  PRIMARY KEY (`id_hospital`),
  KEY `FKTBL_Hospit668099` (`cod_ciudad`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Informacion del centro de salud';

--
-- Dumping data for table `tbl_hospital`
--

/*!40000 ALTER TABLE `tbl_hospital` DISABLE KEYS */;
INSERT INTO `tbl_hospital` (`id_hospital`,`nombre`,`direccion`,`telefono`,`dependencia`,`localizacion`,`cod_ciudad`) VALUES 
 (1,'Hospital Dr. Hernan Henriquez Aravena','Montt N° 115 ','045-296537 ','Publico','-38.736845,-72.599427',9),
 (2,'Centro de Salud Familiar Miraflores','Miraflores N° 1369 ','045-555002 ','Publico','-38.735053,-72.583206',9),
 (3,'Centro de Salud Familiar Santa Rosa ','Pircunche N° 0316 ','045-555803 ','Publico','-38.737061,-72.575915',9),
 (4,'Centro de Salud Familiar Amanecer ','Garibaldi N° 01280 ','045-557300 ','Publico','-38.756199,-72.623804',9),
 (5,'Centro de Salud Familiar Pueblo Nuevo ','Nahuelbuta N° 2815 ','045-558260 ','Publico','',9),
 (6,'Centro de Salud Familiar Pedro de Valdivia','Chivilcan N° 0910 ','045-348388 ','Publico','',9),
 (7,'Centro de Salud Familiar Labranza ','Uno Norte N° 011 ','045-376675 ','Publico','',9),
 (8,'Centro de Salud Docente Asistencial Boyeco ','Kilometro Nº 11, camino a Cholchol ','09-8 8563532 ','Publico','',9),
 (9,'CECOSAM','Vicuña Mackenna Nº 51 ','45-557550 ','Publico','',9),
 (10,'Centro Comunitario de Salud Familiar Villa el Salar ','Huasquinta N° 01180, Villa El Salar ','045-558092 ','Publico','',9),
 (11,'Centro Comunitario de Salud Familiar El Carmen ','Avda. Los Creadores N° 495 ','045-551550 ','Publico','',9),
 (12,'Centro Comunitario de Salud Familiar Las Quilas ','Los Copihues Nº S/N ','045-212017 ','Publico','',9),
 (13,'Clínica Alemana de Temuco  ','Senador Estebanez Nº 645 ','45-201201 ','Privado','',9),
 (14,'Hospital del Trabajador AChS','Francia Nº 324 ','45-295700 ','Privado','',9),
 (15,'Hospital Clínico de la Universidad Mayor ','Avda. Gabriela Mistral Nº 01955 ','45-310282 ','Privado','',9),
 (16,'Centro de Salud Mutual CChC','Avda. Holandesa Nº 615 ','45-206000 ','Privado','',9),
 (17,'SIRESA ','Manuel Montt Nº  942 ','45-960000','Privado','',9),
 (18,'Centro de la Cruz Roja','Carrera Nº 676  ','45-210750 ','Privado','',9);
/*!40000 ALTER TABLE `tbl_hospital` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

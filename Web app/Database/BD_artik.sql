# ************************************************************
# Sequel Pro SQL dump
# Versión 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.25)
# Base de datos: artik
# Tiempo de Generación: 2016-03-29 13:15:07 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Volcado de tabla bypass
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bypass`;

CREATE TABLE `bypass` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `modelo` varchar(255) DEFAULT 'Bypass',
  `nombre` varchar(255) DEFAULT NULL,
  `numero_serie` bigint(11) DEFAULT NULL,
  `estado` int(11) unsigned DEFAULT '0',
  `finCiclo` int(11) DEFAULT '0',
  `t_inicio` bigint(11) unsigned DEFAULT '0',
  `t_fin` bigint(11) unsigned DEFAULT '0',
  `temp` int(11) DEFAULT '35',
  PRIMARY KEY (`id`),
  KEY `estado` (`estado`),
  CONSTRAINT `bypass_ibfk_1` FOREIGN KEY (`estado`) REFERENCES `tipo_estado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `bypass` WRITE;
/*!40000 ALTER TABLE `bypass` DISABLE KEYS */;

INSERT INTO `bypass` (`id`, `modelo`, `nombre`, `numero_serie`, `estado`, `finCiclo`, `t_inicio`, `t_fin`, `temp`)
VALUES
	(1,'Bypass','kitchen 1',4567345234,1,0,1458631620053,1458654743879,102),
	(8,'Bypass','bath 1',4678689888,0,0,1456946834270,1456946909214,95),
	(14,'Bypass','bath 2',5646787656,0,0,0,0,98),
	(15,'Bypass','other',3456765478,0,0,1457509154690,1457509243633,95);

/*!40000 ALTER TABLE `bypass` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla ciclos
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ciclos`;

CREATE TABLE `ciclos` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_bypass` int(11) unsigned NOT NULL,
  `anyo` int(11) DEFAULT NULL,
  `enero` int(11) unsigned DEFAULT NULL,
  `febrero` int(11) unsigned DEFAULT NULL,
  `marzo` int(11) unsigned DEFAULT NULL,
  `abril` int(11) unsigned DEFAULT NULL,
  `mayo` int(11) unsigned DEFAULT NULL,
  `junio` int(11) unsigned DEFAULT NULL,
  `julio` int(11) unsigned DEFAULT NULL,
  `agosto` int(11) unsigned DEFAULT NULL,
  `septiembre` int(11) unsigned DEFAULT NULL,
  `octubre` int(11) unsigned DEFAULT NULL,
  `noviembre` int(11) unsigned DEFAULT NULL,
  `diciembre` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_bypass` (`id_bypass`),
  KEY `enero` (`enero`),
  KEY `febrero` (`febrero`),
  KEY `marzo` (`marzo`),
  KEY `abril` (`abril`),
  KEY `mayo` (`mayo`),
  KEY `junio` (`junio`),
  KEY `julio` (`julio`),
  KEY `agosto` (`agosto`),
  KEY `septiembre` (`septiembre`),
  KEY `octubre` (`octubre`),
  KEY `noviembre` (`noviembre`),
  KEY `diciembre` (`diciembre`),
  CONSTRAINT `ciclos_ibfk_1` FOREIGN KEY (`id_bypass`) REFERENCES `bypass` (`id`),
  CONSTRAINT `ciclos_ibfk_10` FOREIGN KEY (`septiembre`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_11` FOREIGN KEY (`octubre`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_12` FOREIGN KEY (`noviembre`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_13` FOREIGN KEY (`diciembre`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_2` FOREIGN KEY (`enero`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_3` FOREIGN KEY (`febrero`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_4` FOREIGN KEY (`marzo`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_5` FOREIGN KEY (`abril`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_6` FOREIGN KEY (`mayo`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_7` FOREIGN KEY (`junio`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_8` FOREIGN KEY (`julio`) REFERENCES `ciclos_mes` (`id`),
  CONSTRAINT `ciclos_ibfk_9` FOREIGN KEY (`agosto`) REFERENCES `ciclos_mes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `ciclos` WRITE;
/*!40000 ALTER TABLE `ciclos` DISABLE KEYS */;

INSERT INTO `ciclos` (`id`, `id_bypass`, `anyo`, `enero`, `febrero`, `marzo`, `abril`, `mayo`, `junio`, `julio`, `agosto`, `septiembre`, `octubre`, `noviembre`, `diciembre`)
VALUES
	(3,8,2016,NULL,NULL,25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(4,1,2016,NULL,NULL,26,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(5,1,2015,27,28,29,30,31,32,33,34,35,36,37,38),
	(6,15,2016,NULL,NULL,43,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `ciclos` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla ciclos_mes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ciclos_mes`;

CREATE TABLE `ciclos_mes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `d1` int(11) DEFAULT '0',
  `d2` int(11) DEFAULT '0',
  `d3` int(11) DEFAULT '0',
  `d4` int(11) DEFAULT '0',
  `d5` int(11) DEFAULT '0',
  `d6` int(11) DEFAULT '0',
  `d7` int(11) DEFAULT '0',
  `d8` int(11) DEFAULT '0',
  `d9` int(11) DEFAULT '0',
  `d10` int(11) DEFAULT '0',
  `d11` int(11) DEFAULT '0',
  `d12` int(11) DEFAULT '0',
  `d13` int(11) DEFAULT '0',
  `d14` int(11) DEFAULT '0',
  `d15` int(11) DEFAULT '0',
  `d16` int(11) DEFAULT '0',
  `d17` int(11) DEFAULT '0',
  `d18` int(11) DEFAULT '0',
  `d19` int(11) DEFAULT '0',
  `d20` int(11) DEFAULT '0',
  `d21` int(11) DEFAULT '0',
  `d22` int(11) DEFAULT '0',
  `d23` int(11) DEFAULT '0',
  `d24` int(11) DEFAULT '0',
  `d25` int(11) DEFAULT '0',
  `d26` int(11) DEFAULT '0',
  `d27` int(11) DEFAULT '0',
  `d28` int(11) DEFAULT '0',
  `d29` int(11) DEFAULT '0',
  `d30` int(11) DEFAULT '0',
  `d31` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `ciclos_mes` WRITE;
/*!40000 ALTER TABLE `ciclos_mes` DISABLE KEYS */;

INSERT INTO `ciclos_mes` (`id`, `d1`, `d2`, `d3`, `d4`, `d5`, `d6`, `d7`, `d8`, `d9`, `d10`, `d11`, `d12`, `d13`, `d14`, `d15`, `d16`, `d17`, `d18`, `d19`, `d20`, `d21`, `d22`, `d23`, `d24`, `d25`, `d26`, `d27`, `d28`, `d29`, `d30`, `d31`)
VALUES
	(10,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
	(25,0,44,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
	(26,0,37,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
	(27,6,5,6,6,5,6,4,6,8,9,6,5,4,6,4,6,4,6,7,4,3,4,6,8,6,5,3,3,7,4,0),
	(28,5,5,5,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,0,0,0),
	(29,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3),
	(30,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,0),
	(31,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3),
	(32,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,0),
	(33,2,2,2,2,2,2,3,3,4,5,5,6,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,3),
	(34,2,2,3,3,4,4,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3),
	(35,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,0),
	(36,6,6,6,6,6,6,6,6,6,6,6,6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3),
	(37,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0),
	(38,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5,3),
	(43,0,0,0,0,0,0,0,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
	(44,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0);

/*!40000 ALTER TABLE `ciclos_mes` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla datos
# ------------------------------------------------------------

DROP TABLE IF EXISTS `datos`;

CREATE TABLE `datos` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_bypass` int(11) unsigned NOT NULL,
  `anyo` int(11) DEFAULT NULL,
  `enero` int(11) unsigned DEFAULT NULL,
  `febrero` int(11) unsigned DEFAULT NULL,
  `marzo` int(11) unsigned DEFAULT NULL,
  `abril` int(11) unsigned DEFAULT NULL,
  `mayo` int(11) unsigned DEFAULT NULL,
  `junio` int(11) unsigned DEFAULT NULL,
  `julio` int(11) unsigned DEFAULT NULL,
  `agosto` int(11) unsigned DEFAULT NULL,
  `septiembre` int(11) unsigned DEFAULT NULL,
  `octubre` int(11) unsigned DEFAULT NULL,
  `noviembre` int(11) unsigned DEFAULT NULL,
  `diciembre` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_bypass` (`id_bypass`),
  KEY `enero` (`enero`),
  KEY `febrero` (`febrero`),
  KEY `marzo` (`marzo`),
  KEY `abril` (`abril`),
  KEY `mayo` (`mayo`),
  KEY `junio` (`junio`),
  KEY `julio` (`julio`),
  KEY `agosto` (`agosto`),
  KEY `septiembre` (`septiembre`),
  KEY `octubre` (`octubre`),
  KEY `noviembre` (`noviembre`),
  KEY `diciembre` (`diciembre`),
  CONSTRAINT `datos_ibfk_1` FOREIGN KEY (`id_bypass`) REFERENCES `bypass` (`id`),
  CONSTRAINT `datos_ibfk_10` FOREIGN KEY (`septiembre`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_11` FOREIGN KEY (`octubre`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_12` FOREIGN KEY (`noviembre`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_13` FOREIGN KEY (`diciembre`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_2` FOREIGN KEY (`enero`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_3` FOREIGN KEY (`febrero`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_4` FOREIGN KEY (`marzo`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_5` FOREIGN KEY (`abril`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_6` FOREIGN KEY (`mayo`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_7` FOREIGN KEY (`junio`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_8` FOREIGN KEY (`julio`) REFERENCES `mes` (`id`),
  CONSTRAINT `datos_ibfk_9` FOREIGN KEY (`agosto`) REFERENCES `mes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `datos` WRITE;
/*!40000 ALTER TABLE `datos` DISABLE KEYS */;

INSERT INTO `datos` (`id`, `id_bypass`, `anyo`, `enero`, `febrero`, `marzo`, `abril`, `mayo`, `junio`, `julio`, `agosto`, `septiembre`, `octubre`, `noviembre`, `diciembre`)
VALUES
	(3,8,2016,NULL,NULL,25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(4,1,2016,NULL,NULL,26,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(5,1,2015,27,28,29,30,31,32,33,34,35,36,37,38),
	(6,15,2016,NULL,NULL,53,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `datos` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla mes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mes`;

CREATE TABLE `mes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `d1` int(11) DEFAULT '0',
  `d2` int(11) DEFAULT '0',
  `d3` int(11) DEFAULT '0',
  `d4` int(11) DEFAULT '0',
  `d5` int(11) DEFAULT '0',
  `d6` int(11) DEFAULT '0',
  `d7` int(11) DEFAULT '0',
  `d8` int(11) DEFAULT '0',
  `d9` int(11) DEFAULT '0',
  `d10` int(11) DEFAULT '0',
  `d11` int(11) DEFAULT '0',
  `d12` int(11) DEFAULT '0',
  `d13` int(11) DEFAULT '0',
  `d14` int(11) DEFAULT '0',
  `d15` int(11) DEFAULT '0',
  `d16` int(11) DEFAULT '0',
  `d17` int(11) DEFAULT '0',
  `d18` int(11) DEFAULT '0',
  `d19` int(11) DEFAULT '0',
  `d20` int(11) DEFAULT '0',
  `d21` int(11) DEFAULT '0',
  `d22` int(11) DEFAULT '0',
  `d23` int(11) DEFAULT '0',
  `d24` int(11) DEFAULT '0',
  `d25` int(11) DEFAULT '0',
  `d26` int(11) DEFAULT '0',
  `d27` int(11) DEFAULT '0',
  `d28` int(11) DEFAULT '0',
  `d29` int(11) DEFAULT '0',
  `d30` int(11) DEFAULT '0',
  `d31` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `mes` WRITE;
/*!40000 ALTER TABLE `mes` DISABLE KEYS */;

INSERT INTO `mes` (`id`, `d1`, `d2`, `d3`, `d4`, `d5`, `d6`, `d7`, `d8`, `d9`, `d10`, `d11`, `d12`, `d13`, `d14`, `d15`, `d16`, `d17`, `d18`, `d19`, `d20`, `d21`, `d22`, `d23`, `d24`, `d25`, `d26`, `d27`, `d28`, `d29`, `d30`, `d31`)
VALUES
	(25,0,44,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
	(26,0,37,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
	(27,120,100,111,102,103,100,129,130,80,70,75,100,105,110,120,111,111,111,111,111,111,122,123,123,123,120,120,130,140,100,0),
	(28,100,101,120,130,120,123,123,134,123,111,111,110,123,121,122,122,122,122,123,123,123,133,122,122,122,122,90,99,91,0,0),
	(29,123,122,111,123,123,123,122,124,145,111,111,111,111,111,122,122,122,122,122,122,122,122,122,111,111,122,122,111,122,101,101),
	(30,122,122,122,122,122,122,111,111,111,111,111,132,109,109,109,109,111,123,120,111,120,109,107,107,107,107,107,110,100,100,0),
	(31,122,122,122,122,132,133,133,109,123,105,104,104,103,100,100,120,110,132,122,111,111,111,120,122,122,122,122,122,122,122,122),
	(32,122,111,111,111,111,111,111,111,111,111,133,122,122,122,122,122,122,122,122,122,122,143,132,100,100,102,102,102,122,122,0),
	(33,130,133,120,111,123,123,111,111,111,111,121,122,122,122,133,101,101,101,101,80,90,99,90,90,99,99,99,90,90,88,67),
	(34,109,108,103,105,100,104,120,120,120,122,133,70,78,78,79,76,74,77,77,77,77,77,66,88,77,77,77,88,77,88,67),
	(35,120,120,123,123,123,103,105,105,107,105,107,102,124,122,122,122,122,122,122,144,130,109,109,108,109,109,109,109,110,110,0),
	(36,100,102,102,111,111,130,120,130,133,122,123,123,144,120,111,111,123,123,123,132,133,133,133,120,123,123,123,111,111,111,111),
	(37,120,123,123,111,111,120,111,111,111,112,131,123,123,123,123,123,123,80,80,80,88,87,89,89,88,120,111,123,123,123,0),
	(38,123,112,144,144,114,142,67,77,77,88,99,100,100,102,123,123,77,88,89,89,90,91,92,123,124,77,88,88,88,88,100),
	(53,0,0,0,0,0,0,0,9,29,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
	(54,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,194,0,0,0,0,0,0,0,0,0);

/*!40000 ALTER TABLE `mes` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla tipo_estado
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tipo_estado`;

CREATE TABLE `tipo_estado` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `tipo_estado` WRITE;
/*!40000 ALTER TABLE `tipo_estado` DISABLE KEYS */;

INSERT INTO `tipo_estado` (`id`, `estado`)
VALUES
	(0,'frio'),
	(1,'caliente'),
	(2,'recirculando'),
	(3,'error');

/*!40000 ALTER TABLE `tipo_estado` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

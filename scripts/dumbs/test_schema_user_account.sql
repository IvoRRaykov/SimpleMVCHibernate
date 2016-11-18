-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: test_schema
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `password` varchar(65) NOT NULL,
  `money` float NOT NULL,
  `email` varchar(45) NOT NULL,
  `avatar` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`,`user_name`),
  UNIQUE KEY `id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (63,'ivoqwe1','m','$2a$10$WoEQwyC1fy0BUfo0kymfvejNTMDoJuJejXq4B4j6uZuuLqM9rkiyG',35,'ivo123_321@abv.bg','http://api.adorable.io/avatar/200/972'),(65,'kamil','m','$2a$06$doZnT/Klfo3ECvVaKm4Uce4fuJ2uUbGlL.erqwz/0UDAJGqL5az4i',20,'ivo123_321@abv.bg','http://api.adorable.io/avatar/200/144'),(68,'ivoqwe','f','$2a$10$YzC4X8LtAhZPYtRhVIPj2u2FLCaLEnM1EIT/A8IKHySi0UkSMyZuy',218,'ivo123_321@abv.bg','http://api.adorable.io/avatar/150/150'),(73,'ivoqwe11','m','$2a$10$237cmzeQ5DON.3eUG7WmjepPrt9z6d5HXfeOwT7oQktm3CCIfJN3W',73,'ivo123_321@abv.bg','http://api.adorable.io/avatar/200/195'),(74,'malin','m','$2a$10$pVSEXIz5Zc0mRWzQT96mzONHB8I.E.A6Rk9aZzGh.ZzeTP5vIs836',73,'ivo123_321@abv.bg','http://api.adorable.io/avatar/200/865'),(76,'kolokolo','m','$2a$10$f3dY6nCuqYqC8Z3MJHgUjOifvgZkp.ohIgghKu77kCMHjISEXXD/e',100,'ivo123_321@abv.bg','http://api.adorable.io/avatar/200/575'),(77,'ivchou','m','$2a$10$s96t5J/roSEDX1DqpzzIaOvb9CLngw3EpJY4z1.VSDS/0ISVaz22K',184,'ivo123_321@abv.bg','http://api.adorable.io/avatar/200/397'),(78,'ivoqwe2','m','$2a$10$JauF0sO.qJvDMhdEvH1a8.pGTaTjkky.8Oe.uaPmLwnx55mYEEy.2',98,'ivo123_321@abv.bg','http://api.adorable.io/avatar/150/822'),(79,'admin','m','$2a$10$VaglTQZ48h1GQLr19DQZVO6oSQO1yP1kNuorxp5w5qncTDUHV1CDq',100,'ivo123_321@abv.bg','http://api.adorable.io/avatar/150/272');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-18 14:51:07

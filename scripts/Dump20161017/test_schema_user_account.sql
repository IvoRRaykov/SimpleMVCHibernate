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
  `password` varchar(30) NOT NULL,
  `money` float NOT NULL,
  `email` varchar(45) NOT NULL,
  `avatar` varchar(45) NOT NULL,
  `user_accountcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`user_name`),
  UNIQUE KEY `id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,'ivoqwe','F','ivoasd',115,'ivo123_321@abv.bg','',NULL),(2,'ivoqwe1','M','ivoasd1',288,'ivo123_321@abv.bg','',NULL),(3,'newuser','M','newpassword',0,'','',NULL),(4,'newuser1','F','newpassword1',0,'','',NULL),(6,'ivoqwe11','M','ivoasd11',26,'','',NULL),(7,'newuser2','F','newuser2',100,'','',NULL),(9,'ivoqwe3','F','ivoasd3',242,'','',NULL),(11,'ivoqwe33','M','ivoasd33',12.99,'','',NULL),(13,'ivoqwe2','M','ivoasd2',77.99,'','',NULL),(15,'kolyo','M','terorista',1001,'','',NULL),(16,'adminUser69','F','12345',0.99,'','',NULL),(19,'adminUser3','F','12345',234,'','',NULL),(20,'kocho','M','mocho',20,'','',NULL),(21,'newuser691','M','newuser69',100,'','',NULL),(22,'sharan','M','maran',100,'shaan@gmail.com','',NULL),(28,'ivoqwe76','M','ivoasd76',100,'afaswf@abv.bg','',NULL),(41,'ivo123','M','ivo123',100,'ivo123_321@abv.bg','',NULL),(42,'donyyy','F','bonbony',69.99,'dpeneva9@gmail.com','',NULL),(43,'ivocarq','M','ivopicarq',85,'ivo123_321@abv.bg','',NULL),(44,'qazwsx','M','qazwsx',60,'ivo123_321@abv.bg','http://api.adorable.io/avatar/200/52',NULL),(45,'koljo','M','koljo',167,'ivo123_321@abv.bg','http://api.adorable.io/avatar/200/11',NULL),(46,'kokicha','M','mokicha',80,'ivo123_321@abv.bg','http://api.adorable.io/avatar/200/371',NULL),(47,'ivoqwe1111','M','ivoqwe33',100,'qwe@asdasd.bg','http://api.adorable.io/avatar/200/367',NULL);
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

-- Dump completed on 2016-10-17 16:34:58

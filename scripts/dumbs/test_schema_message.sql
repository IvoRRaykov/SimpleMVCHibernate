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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(200) NOT NULL,
  `date_sent` datetime NOT NULL,
  `fromu` int(11) NOT NULL,
  `tou` int(11) NOT NULL,
  `seen` bit(1) NOT NULL,
  UNIQUE KEY `message_id_UNIQUE` (`message_id`),
  KEY `to_fk_idx` (`tou`),
  KEY `from_fk_idx` (`fromu`),
  CONSTRAINT `from_fk` FOREIGN KEY (`fromu`) REFERENCES `user_account` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `to_fk` FOREIGN KEY (`tou`) REFERENCES `user_account` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (9,'zdr ko pr','2016-10-19 18:08:32',73,68,''),(10,'WAZAp','2016-10-19 18:18:27',74,68,''),(11,'dont read!','2016-10-19 18:18:57',68,63,''),(12,'dadada','2016-10-25 18:03:00',63,68,''),(13,'dsgsgdf','2016-10-26 18:58:35',68,63,''),(14,'fhfhfhfhfhfhffhfhfhfhfhfhfhfhfhfhffhfhfhfhfhfhfhfhfhfhffhfhfhfhfhfhfhfhfhfhffhfhfhfhfhfhfhfhfhfhfhfhfhfhfhfh','2016-10-26 18:59:33',68,68,''),(15,'fasdfdsf','2016-10-27 12:04:34',68,63,''),(16,'dadada','2016-10-27 12:04:55',68,77,''),(17,'Hello....','2016-11-02 10:58:42',78,77,'');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
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

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `code` varchar(20) NOT NULL,
  `name` varchar(128) NOT NULL,
  `price` float NOT NULL,
  `user_id` int(11) NOT NULL,
  `for_sale` bit(1) NOT NULL,
  `picture_file_path` varchar(80) DEFAULT NULL,
  `genre` varchar(45) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `user_id_fk_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('b6969','Wild Light',16,77,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover1.jpg','Art Punk'),('c73823','Vampire Weekend',29,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover21.jpg','Disco'),('c7676','Clever Girl Goes Alone',17,73,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover4.jpg','Alternative Rock'),('c8765','Foals - Holy Fire',76,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover8.jpg','Disco'),('c8767','Ginger',15,65,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover24.jpg','Pop'),('ch132','The Welacharid Orchestra',220,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover2.jpg','Punk'),('d2123','Daughter',78,78,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover26.jpg','Jazz'),('d2717','Devils Trap',67,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover6.jpg','Classic Blues'),('e7878','Evocations Antiques',99,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover7.jpg','Classic Blues'),('i9076','Leaking Lakes',100,65,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover5.jpg','Alternative Rock'),('j73623','Day Dreams',80,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover20.jpg','Jazz'),('l3223','Lost Hopes',29,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover15.jpg','Disco'),('l4443','Linking Parking',100,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover17.jpg','Punk'),('l8976','Little Dreams',7,74,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover3.jpg','Punk'),('m343','Mars',100,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover11.jpg','Disco'),('p923','Push The Sky Away',44,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover9.jpg','Disco'),('p999','Pronit',23,78,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover12.jpg','Disco'),('p9991','Peter Gabriel',23,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover18.jpg','Punk'),('r2239','Razerblades',15,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\.jpg','Alternative Rock'),('r2323','Relapse',299,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover23.jpg','Rap'),('r8512','Riverdale',89,78,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover25.jpg','Disco'),('robld','Royal Blood',44,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover19.jpg','Jazz'),('t3423','The Dark Side Of The Moon',369,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover13.jpg','Rock'),('x2222','Project X',34,68,'','D:\\apache-tomcat-7.0.70\\bin\\tmpFiles\\cover14.jpg','Disco');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-18 14:51:06

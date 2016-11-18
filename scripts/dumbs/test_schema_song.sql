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
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song` (
  `song_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(45) NOT NULL,
  `name` varchar(95) NOT NULL,
  PRIMARY KEY (`song_id`),
  UNIQUE KEY `song_id_UNIQUE` (`song_id`),
  KEY `product_code_idx` (`product_code`),
  CONSTRAINT `product_code` FOREIGN KEY (`product_code`) REFERENCES `product` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (64,'c73823','1.32 Flavors'),(65,'c73823','2.Crazy'),(66,'c73823','3.Blue Jeans'),(67,'c73823','4.Born to Die'),(68,'c73823','5.Cola'),(69,'c73823','6.Diet Mountain Dew'),(70,'c73823','7.Gods and Monsters'),(71,'c73823','8.National Anthem'),(72,'c8765','1.Guantanamera'),(73,'c8765','2.Crickets Sing For Anamaria'),(75,'c8765','3.Panama Red'),(76,'c8765','4.Guantanamera'),(77,'c8765','5.Vay Anam Vay'),(78,'ch132','1.Bend Me, Shape Me'),(79,'ch132','2.Dirty little secret'),(80,'ch132','3.It Ends Tonight'),(81,'ch132','4.Move Along'),(82,'d2717','1.The Last Song'),(83,'d2717','2.Swing, Swing'),(84,'d2717','3.A Horse With No Name'),(85,'d2717','4.Daisy Jane'),(86,'d2717','5.DONT CROSS THE RIVER'),(87,'d2717','6.From A Moving Train'),(88,'d2717','7.I NEED YOU'),(89,'d2717','8.Lonely People'),(90,'d2717','9.Muskrat Love'),(91,'d2717','10.ONE IN A MILLION'),(92,'d2717','11.ONLY IN YOUR HEART'),(93,'d2717','12.To each his own'),(94,'e7878','1.Carita De Angel'),(95,'e7878','2.Bend Me Shape Me'),(96,'e7878','3.Flavor OF The Weak'),(97,'e7878','4.The Art Of Losing'),(98,'e7878','5.Another Perfect Day'),(99,'e7878','6.	Flavor Of The Week'),(100,'j73623','1.What The World Needs Now Is Love'),(101,'j73623','2.Don\'T Let Them'),(102,'j73623','3.Gotta Work'),(103,'j73623','4.One Thing'),(104,'j73623','5.Take Control'),(105,'j73623','6.Talkin\' About'),(106,'j73623','7.Why Don\'t We Fall In Love'),(107,'j73623','8.Lonely People'),(108,'l3223','1.Like A Rose'),(109,'l3223','2.Rose Garden'),(110,'l3223','3.Tattoo Rose'),(111,'l3223','4.A Rose Is Still A Rose'),(112,'l3223','5.La Vie En Rose'),(113,'l3223','6.Rosealia'),(114,'l4443','1.Burn It Down'),(115,'l4443','2.Castle Of Glass'),(116,'l4443','3.Crawlin\''),(117,'l4443','4.Faint'),(118,'l4443','5.Forgotten'),(119,'m343','1.The Kill (Bury Me)'),(120,'m343','2.Anyway (Men Are From Mars)'),(121,'m343','3.Life On Mars'),(122,'m343','4.Lazy Song'),(123,'m343','5.When I Was Your Man'),(124,'m343','6.Whenever You\'re On My Mind'),(125,'m343','7.30 Seconds To Mars Wvocal'),(126,'m343','8.Uptown Funk'),(127,'p923','1.Cry'),(128,'p923','2.Come On Over'),(129,'p923','3.Live It Up'),(130,'p923','4.She Ain\'t Gonna Cry'),(131,'p999','1.Fire On The Mountain'),(132,'p999','2.Fall From Grace'),(133,'p999','3.Let It Rain'),(134,'p999','4.Sunday Morning After'),(135,'p999','5.This Could Take All Night'),(136,'p999','6.Gareth Gates Feat The Kumars'),(137,'p999','7.Last Of The Singing Cowboys'),(138,'p9991','1.Numb'),(139,'p9991','2.One Step Closer'),(140,'p9991','3.Papercut'),(141,'p9991','4.Points Of Authority'),(142,'p9991','5.Pushin\' Me Away'),(143,'r2239','1.Runaway '),(144,'r2239','2.Shadow Of The Day'),(145,'r2239','3.What I\'ve Done'),(146,'r2239','4.With You'),(147,'r2323','1.Cleanin\' Out My Closet'),(148,'r2323','2.Criminal'),(149,'r2323','3.Guilty Conscience'),(150,'r2323','4.Hailie\'s Song'),(151,'r2323','5.I\'m Back'),(152,'r2323','6.Just Lose It'),(153,'r2323','7.Just Lose It (Radio Version)'),(154,'r2323','8.Like Toy Soldiers'),(155,'r2323','9.Lose Yourself'),(156,'r2323','10.Mocking Bird'),(157,'r2323','11.My Name Is'),(158,'r2323','12.Real Slim Shady'),(159,'r2323','13.Sing Fo The Moment'),(160,'r2323','14.Superman'),(161,'robld','1.The Way I Am'),(162,'robld','2.When I\'m Gone'),(163,'robld','3.White America'),(164,'robld','4.Without Me '),(165,'robld','5.You Don\'t Know'),(166,'t3423','1.Another Brick in the Wall'),(167,'t3423','2.Bike'),(168,'t3423','3.Brain Damage'),(169,'t3423','4.Comfortably Numb'),(170,'t3423','5.Coming Back To Life'),(171,'t3423','6.Eclipse'),(172,'t3423','7.Have A Cigar'),(173,'x2222','1.Hey You'),(174,'x2222','2.Learning To Fly'),(175,'x2222','3.Money'),(176,'x2222','4.Mother'),(177,'x2222','5.Nobody Home'),(178,'x2222','6.See Emily Play'),(179,'b6969','1.Sheep'),(180,'b6969','2.Shine On You Crazy Diamond'),(181,'b6969','3.Take It Back'),(182,'b6969','4.Time'),(183,'b6969','5.Us And Them'),(184,'b6969','6.Wish You Were Here'),(185,'b6969','7.Arnold Layne'),(186,'b6969','8.Comfortably Numb'),(187,'b6969','9.No One Like You'),(188,'c7676','1.Send Me An Angel'),(189,'c7676','2.Still Lovin\' You'),(190,'c7676','3.Under The Same Sun'),(191,'c7676','4.Wind Of Change'),(192,'c7676','5.Zoo'),(193,'i9076','1.Fade To Black'),(194,'i9076','2.For Whom The Bell Tolls'),(195,'i9076','3.Fuel'),(196,'i9076','4.Hero Of The Day'),(197,'i9076','5.Nothing Else Matters'),(198,'l8976','1.Stone Cold Crazy'),(199,'l8976','2.The Unnamed Feeling'),(200,'l8976','3.Unforgiven II'),(201,'l8976','4.Until It Sleeps'),(202,'l8976','5.Whiskey In A Jar'),(203,'c8767','1.Green Light'),(204,'c8767','2.Green Leaves Of Summer'),(205,'c8767','3.Alley Oop'),(206,'c8767','4.John Deere'),(207,'c8767','5.American idiot'),(208,'c8767','6.Basket Case'),(209,'r8512','1.Going \'Round'),(210,'r8512','2.Going home'),(211,'r8512','3.Home alone'),(212,'d2123','1.Holy Jesus'),(213,'d2123','2.Broken Angel'),(214,'d2123','3.Down The Slope'),(215,'d2123','4.Four'),(216,'d2123','5.Walking Elephants');
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
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

-- MySQL dump 10.13  Distrib 5.6.21, for osx10.8 (x86_64)
--
-- Host: localhost    Database: ESE
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `Ad`
--

DROP TABLE IF EXISTS `Ad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addCost` bigint(20) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `dateIn` varchar(255) DEFAULT NULL,
  `dateOut` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `houseNr` bigint(20) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lon` varchar(255) DEFAULT NULL,
  `placerId` bigint(20) DEFAULT NULL,
  `rent` bigint(20) DEFAULT NULL,
  `roomSize` bigint(20) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `us` varchar(255) DEFAULT NULL,
  `you` varchar(255) DEFAULT NULL,
  `zip` bigint(20) DEFAULT NULL,
  `ad_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK84346E1CC99` (`ad_id`),
  CONSTRAINT `FK84346E1CC99` FOREIGN KEY (`ad_id`) REFERENCES `Ad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ad`
--

LOCK TABLES `Ad` WRITE;
/*!40000 ALTER TABLE `Ad` DISABLE KEYS */;
INSERT INTO `Ad` VALUES (1,5,'Bern','11.11.14','','Lorem ipsum',3,'46.9467726','7.4442328',1,120,20,'Bundesplatz','2014-11-11 13:18:39','Shabby Home','...','',3012,NULL),(2,20,'Bern','11.11.14','','Lorem ipsom',5,'46.9510265','7.43894575193865',1,120,20,'Sidlerstrasse','2014-11-11 13:21:06','Appartment','','',3012,NULL),(3,200,'Bern','23.12.14','','Lorem ipsom',1,'46.9517195','7.4386666',1,1000,300,'Sidlerstrasse','2014-11-11 13:22:42','Appartmentd','','',3012,NULL),(4,10,'Bern','01.01.2015','','Lorem ipsum',22,'46.9540626','7.4381054',1,120,150,'Alpeneggstrasse','2014-11-11 13:23:54','AppartmentF','','',3012,NULL),(5,20,'Bern','05.03.2015','','Lorem ipsum',14,'46.9452457','7.4282982',2,300,20,'Gartenstrasse','2014-11-11 13:28:59','AppartmentG','','',3074,NULL),(6,30,'Muri','09.03.15','','Lorem ipsum',8,NULL,NULL,2,175,200,'Brunnweg','2014-11-11 13:29:56','AppartmentH','','',3074,NULL),(7,20,'Muri','03.08.15','','Lorem ipsum',10,'46.9296781','7.48666208358586',2,327,175,'Rainweg','2014-11-11 13:31:17','AppartmentI','','',3074,NULL),(8,20,'Muri','07.05.15','','Lorem ipsum',7,'46.9299384','7.4863413507925',2,3828,145,'Belpstrasse','2014-11-11 13:32:37','AppartmentJ','','',3074,NULL),(9,3,'Ostermundigen','13.09.2015','','Lorem ipsum',42,'46.9561337','7.4922628978317',2,50,17,'Blankweg','2014-11-11 13:34:21','AppartmentJ','','',3072,NULL),(10,32,'Ostermundigen','13.09.2015','','Lorem ipsum',44,'46.95591325','7.49207835',2,42,321,'Blankweg','2014-11-11 13:35:44','AppartmentK','','',3072,NULL);
/*!40000 ALTER TABLE `Ad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ad_Picture`
--

DROP TABLE IF EXISTS `Ad_Picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ad_Picture` (
  `Ad_id` bigint(20) NOT NULL,
  `pictures_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Ad_id`,`pictures_id`),
  UNIQUE KEY `pictures_id` (`pictures_id`),
  KEY `FKBEB9B0E277241104` (`pictures_id`),
  KEY `FKBEB9B0E246E1CC99` (`Ad_id`),
  CONSTRAINT `FKBEB9B0E246E1CC99` FOREIGN KEY (`Ad_id`) REFERENCES `Ad` (`id`),
  CONSTRAINT `FKBEB9B0E277241104` FOREIGN KEY (`pictures_id`) REFERENCES `Picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ad_Picture`
--

LOCK TABLES `Ad_Picture` WRITE;
/*!40000 ALTER TABLE `Ad_Picture` DISABLE KEYS */;
INSERT INTO `Ad_Picture` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10);
/*!40000 ALTER TABLE `Ad_Picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `houseNr` int(11) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` VALUES (1,'Bern',3,'Bundesplatz',3012),(2,'Bern',5,'Bundeslatz',3012);
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bookmark`
--

DROP TABLE IF EXISTS `Bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bookmark` (
  `bookmarkId` bigint(20) NOT NULL AUTO_INCREMENT,
  `adId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bookmarkId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bookmark`
--

LOCK TABLES `Bookmark` WRITE;
/*!40000 ALTER TABLE `Bookmark` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Enquiry`
--

DROP TABLE IF EXISTS `Enquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Enquiry` (
  `enquiryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `adId` bigint(20) DEFAULT NULL,
  `messageText` varchar(255) DEFAULT NULL,
  `receiverId` bigint(20) DEFAULT NULL,
  `senderId` bigint(20) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`enquiryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enquiry`
--

LOCK TABLES `Enquiry` WRITE;
/*!40000 ALTER TABLE `Enquiry` DISABLE KEYS */;
/*!40000 ALTER TABLE `Enquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Picture`
--

DROP TABLE IF EXISTS `Picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Picture` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `filePath` varchar(255) DEFAULT NULL,
  `isMainPic` tinyint(1) NOT NULL,
  `ad_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK40C8F4DE46E1CC99` (`ad_id`),
  CONSTRAINT `FK40C8F4DE46E1CC99` FOREIGN KEY (`ad_id`) REFERENCES `Ad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Picture`
--

LOCK TABLES `Picture` WRITE;
/*!40000 ALTER TABLE `Picture` DISABLE KEYS */;
INSERT INTO `Picture` VALUES (1,NULL,1,NULL),(2,NULL,1,NULL),(3,NULL,1,NULL),(4,NULL,1,NULL),(5,NULL,1,NULL),(6,NULL,1,NULL),(7,NULL,1,NULL),(8,NULL,1,NULL),(9,NULL,1,NULL),(10,NULL,1,NULL);
/*!40000 ALTER TABLE `Picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Search`
--

DROP TABLE IF EXISTS `Search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Search` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `priceMax` bigint(20) DEFAULT NULL,
  `priceMin` bigint(20) DEFAULT NULL,
  `roomSizeMax` bigint(20) DEFAULT NULL,
  `roomSizeMin` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Search`
--

LOCK TABLES `Search` WRITE;
/*!40000 ALTER TABLE `Search` DISABLE KEYS */;
/*!40000 ALTER TABLE `Search` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK285FEBF0E6CADB` (`address_id`),
  CONSTRAINT `FK285FEBF0E6CADB` FOREIGN KEY (`address_id`) REFERENCES `Address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'max.mustermann@test.ch','Max','Mustermann','$2a$10$xJA1EsYkHpW7HUin9NUjyOqvdUnJRSB.kcZaEfZrdxpaoKV31pdsi',NULL),(2,'monika.mustermann@test.ch','Monika','Mustermann','$2a$10$80IAkceDFXDjExh3kOLZfu9YckyypXzdduFiXwLb90jCT.0JyIPSO',NULL);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_Search`
--

DROP TABLE IF EXISTS `User_Search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Search` (
  `User_id` bigint(20) NOT NULL,
  `searchs_id` bigint(20) NOT NULL,
  PRIMARY KEY (`User_id`,`searchs_id`),
  UNIQUE KEY `searchs_id` (`searchs_id`),
  KEY `FK2302409CA2E69F6` (`searchs_id`),
  KEY `FK2302409C519857D9` (`User_id`),
  CONSTRAINT `FK2302409C519857D9` FOREIGN KEY (`User_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK2302409CA2E69F6` FOREIGN KEY (`searchs_id`) REFERENCES `Search` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Search`
--

LOCK TABLES `User_Search` WRITE;
/*!40000 ALTER TABLE `User_Search` DISABLE KEYS */;
/*!40000 ALTER TABLE `User_Search` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-11 13:36:36

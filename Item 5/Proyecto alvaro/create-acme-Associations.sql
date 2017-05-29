start transaction;

drop database if exists `Acme-BnB`;
create database `Acme-BnB`;

use `Acme-BnB`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';



grant select, insert, update, delete 
  on `Acme-BnB`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-BnB`.* to 'acme-manager'@'%';

	
	
	
	
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: mylooks
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (248,0,'admin@admin.com','Admin','660709934','https://i.imgur.com/qYy3Kxa.png','Smith',247);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `initialDate` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `totalCost` double NOT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8fv49f77h0espprqid0b5e65s` (`owner_id`),
  CONSTRAINT `FK_8fv49f77h0espprqid0b5e65s` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (299,0,'2017-01-01 12:00:00','2017-01-03 12:00:00','2017-01-02 12:00:00','Summary 1',50,259),(300,0,'2017-02-01 12:00:00','2017-02-03 12:00:00','2017-02-02 12:00:00','Summary 2',25,259),(301,0,'2016-10-10 12:00:00','2016-10-12 12:00:00','2016-10-11 12:00:00','Summary 3',20,259),(302,0,'2017-01-11 12:00:00','2017-01-13 12:00:00','2017-01-12 12:00:00','Summary 4',15,259),(303,0,'2017-03-01 12:00:00','2017-03-03 12:00:00','2017-03-02 12:00:00','Summary 5',32,259),(304,0,'2017-03-01 12:00:00','2017-03-03 12:00:00','2017-03-02 12:00:00','Summary 6',44,259),(305,0,'2017-03-10 12:00:00','2017-03-13 12:00:00','2017-03-12 12:00:00','Summary 7',15,259),(306,0,'2017-03-02 12:00:00','2017-03-04 12:00:00','2017-03-03 12:00:00','Summary 8',16.99,259),(307,0,'2017-03-01 12:00:00','2017-03-03 12:00:00','2017-03-02 12:00:00','Summary 9',27.4,259),(308,0,'2017-03-01 12:00:00','2017-03-03 12:00:00','2017-03-02 12:00:00','Summary 10',25.99,259),(309,0,'2016-12-01 12:00:00','2016-12-03 12:00:00','2016-12-02 12:00:00','Summary 11',25.99,260),(310,0,'2017-03-01 12:00:00','2017-03-03 12:00:00','2017-03-02 12:00:00','Summary 12',60,260);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_booking`
--

DROP TABLE IF EXISTS `bill_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill_booking` (
  `Bill_id` int(11) NOT NULL,
  `bookings_id` int(11) NOT NULL,
  UNIQUE KEY `UK_93smy0iswd2kdc6ienht9l5rm` (`bookings_id`),
  KEY `FK_l9cv8em9472mff075e1y3l71t` (`Bill_id`),
  CONSTRAINT `FK_l9cv8em9472mff075e1y3l71t` FOREIGN KEY (`Bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `FK_93smy0iswd2kdc6ienht9l5rm` FOREIGN KEY (`bookings_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_booking`
--

LOCK TABLES `bill_booking` WRITE;
/*!40000 ALTER TABLE `bill_booking` DISABLE KEYS */;
INSERT INTO `bill_booking` VALUES (299,311),(300,312),(301,313),(302,314),(303,315),(304,316),(305,317),(306,318),(307,319),(308,320),(309,321);
/*!40000 ALTER TABLE `bill_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `bookingFee` double NOT NULL,
  `endMoment` datetime DEFAULT NULL,
  `hidden` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `totalPrice` double NOT NULL,
  `bill_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `service_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dk8j4tnk77dijxv2g69x4rknx` (`bill_id`),
  KEY `FK_j0agih7xap2ja71am2p5obvyp` (`client_id`),
  KEY `FK_5nwxglfpy69da55wsd5k4he3u` (`schedule_id`),
  KEY `FK_luj2rr6jrlywrksmvjsvnvrbg` (`service_id`),
  CONSTRAINT `FK_luj2rr6jrlywrksmvjsvnvrbg` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FK_5nwxglfpy69da55wsd5k4he3u` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FK_dk8j4tnk77dijxv2g69x4rknx` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `FK_j0agih7xap2ja71am2p5obvyp` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (311,1,2.5,'2017-05-09 12:45:00','\0','2017-05-09 12:00:00','PENDING',25,299,263,322,278),(312,1,1.5,'2017-05-09 16:45:00','\0','2017-05-09 16:00:00','PENDING',15,300,264,322,278),(313,1,5,'2017-02-11 12:45:00','','2017-02-11 12:00:00','COMPLETED',30,301,265,322,278),(314,1,5,'2017-06-10 13:00:00','\0','2017-06-10 12:00:00','PENDING',50,302,263,322,279),(315,1,2.5,'2017-02-01 19:00:00','','2017-02-01 18:00:00','COMPLETED',25,303,263,322,279),(316,1,1,'2017-03-01 17:30:00','','2017-03-01 17:00:00','CANCELLED',8,304,264,330,280),(317,1,1,'2017-07-11 13:30:00','\0','2017-07-11 12:00:00','PENDING',10,305,264,322,281),(318,1,2,'2017-07-11 12:30:00','\0','2017-07-11 12:00:00','PENDING',20,306,263,322,282),(319,1,2,'2017-02-11 12:15:00','','2017-02-11 11:00:00','CANCELLED',20,307,264,338,283),(320,1,1.5,'2017-02-03 13:15:00','','2017-02-03 12:00:00','COMPLETED',15,308,263,338,283),(321,1,2,'2017-06-11 17:45:00','\0','2017-06-11 17:00:00','CANCELLED',20,309,263,338,284);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9r9na2usnb9ylnvevarrssfdf` (`userAccount_id`),
  KEY `FK_evuckwrwy6vkpl06rslo9592h` (`creditCard_id`),
  CONSTRAINT `FK_9r9na2usnb9ylnvevarrssfdf` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_evuckwrwy6vkpl06rslo9592h` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (263,0,'liasmith@gmail.com','Lia','611587853','https://i.imgur.com/qYy3Kxa.png','Smith',231,249),(264,0,'kevinownes@gmail.com','Kevin','602587853','https://s-media-cache-ak0.pinimg.com/736x/53/bd/28/53bd2829c72f5d67f6af953cadeaa6ce.jpg','Steen',232,253),(265,0,'sashabanks@gmail.com','Mercedes','636598744','https://s-media-cache-ak0.pinimg.com/736x/71/6c/e7/716ce7109294eaf39c922d2bda5ae502.jpg','Banks',233,254),(266,0,'chrisjericho@gmail.com','Alexis','689587853','https://2.bp.blogspot.com/-4Uwx9DUil2c/WIka081thHI/AAAAAAAAIjc/jZ-c8PxlTDkIf_9jP288lOUp34y2D0_PgCLcB/s1600/alexa_bliss.jpg','Kaufman',234,NULL),(267,0,'Areyes@gmail.com','Angel','6893587853','https://2.bp.blogspot.com/-4Uwx9DUil2c/WIka081thHI/AAAAAAAAIjc/jZ-c8PxlTDkIf_9jP288lOUp34y2D0_PgCLcB/s1600/alexa_bliss.jpg','Reyes',244,256),(268,0,'chrisjericho@gmail.com','Alex','6895872853','https://2.bp.blogspot.com/-4Uwx9DUil2c/WIka081thHI/AAAAAAAAIjc/jZ-c8PxlTDkIf_9jP288lOUp34y2D0_PgCLcB/s1600/alexa_bliss.jpg','Garrido',245,257),(269,0,'chrisjericho@gmail.com','José María','6895878513','https://2.bp.blogspot.com/-4Uwx9DUil2c/WIka081thHI/AAAAAAAAIjc/jZ-c8PxlTDkIf_9jP288lOUp34y2D0_PgCLcB/s1600/alexa_bliss.jpg','Cotan',246,258);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `rating` double NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `commentable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p1tbi23tek6dxa5qp4iurx1p6` (`client_id`,`commentable_id`),
  CONSTRAINT `FK_sijaneofedb7fkcuhro1lc3n` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (450,0,'2017-03-11 17:32:00',3,'I had to wait a lot of time to be attended, but the work was fine.','Nice work',264,270),(451,0,'2017-03-02 11:32:00',4,'I have never had a better result in any hairdressing.','Awesome establishment',265,270),(452,0,'2017-02-22 12:45:00',1,'Nothing good to say, the establishment wasn\'t clean, there was hair all over the floor, the workers\' hands were as black as soot, it was disgusting when they touched me. Do yourselves a favour and get away from that establishment.','I will never come back',264,271),(453,0,'2017-03-11 15:47:00',2.5,'It\'s a good choice if you don\'t want to expend a lot of money.','As simple as expected',264,272),(454,0,'2017-01-10 20:03:00',3.5,'Cheap services, fast and nice work. They should improve the decoration of the establishment, it would give a better impression to the clients.','Acceptable',263,272);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `CVV` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `creditCardNumber` varchar(16) DEFAULT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (249,0,100,'VISA','4111111111111111',6,19,'Nombre1'),(250,0,112,'VISA','340252042596177',9,21,'Nombre2'),(251,0,178,'VISA','4916205200889947',3,19,'Nombre3'),(252,0,120,'VISA','4485598661423946',1,20,'Nombre4'),(253,0,231,'VISA','4916509563156475',2,24,'Nombre5'),(254,0,342,'VISA','4532056300104875',3,18,'Nombre6'),(255,0,342,'VISA','4532056300104875',12,20,'Jacinto'),(256,0,342,'VISA','4532056300104875',12,20,'Angel'),(257,0,342,'VISA','4532056300104875',12,20,'Alex'),(258,0,342,'VISA','4532056300104875',12,20,'José María');
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day`
--

DROP TABLE IF EXISTS `day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `day` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sjfst4b7lwdnkssg91oavu9be` (`schedule_id`),
  CONSTRAINT `FK_sjfst4b7lwdnkssg91oavu9be` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day`
--

LOCK TABLES `day` WRITE;
/*!40000 ALTER TABLE `day` DISABLE KEYS */;
INSERT INTO `day` VALUES (323,0,'MONDAY',322),(324,0,'TUESDAY',322),(325,0,'WEDNESDAY',322),(326,0,'THURSDAY',322),(327,0,'FRIDAY',322),(328,0,'SATURDAY',322),(329,0,'SUNDAY',322),(331,0,'MONDAY',330),(332,0,'TUESDAY',330),(333,0,'WEDNESDAY',330),(334,0,'THURSDAY',330),(335,0,'FRIDAY',330),(336,0,'SATURDAY',330),(337,0,'SUNDAY',330),(339,0,'MONDAY',338),(340,0,'TUESDAY',338),(341,0,'WEDNESDAY',338),(342,0,'THURSDAY',338),(343,0,'FRIDAY',338),(344,0,'SATURDAY',338),(345,0,'SUNDAY',338),(347,0,'MONDAY',346),(348,0,'TUESDAY',346),(349,0,'WEDNESDAY',346),(350,0,'THURSDAY',346),(351,0,'FRIDAY',346),(352,0,'SATURDAY',346),(353,0,'SUNDAY',346),(355,0,'MONDAY',354),(356,0,'TUESDAY',354),(357,0,'WEDNESDAY',354),(358,0,'THURSDAY',354),(359,0,'FRIDAY',354),(360,0,'SATURDAY',354),(361,0,'SUNDAY',354),(363,0,'MONDAY',362),(364,0,'TUESDAY',362),(365,0,'WEDNESDAY',362),(366,0,'THURSDAY',362),(367,0,'FRIDAY',362),(368,0,'SATURDAY',362),(369,0,'SUNDAY',362),(371,0,'MONDAY',370),(372,0,'TUESDAY',370),(373,0,'WEDNESDAY',370),(374,0,'THURSDAY',370),(375,0,'FRIDAY',370),(376,0,'SATURDAY',370),(377,0,'SUNDAY',370),(379,0,'MONDAY',378),(380,0,'TUESDAY',378),(381,0,'WEDNESDAY',378),(382,0,'THURSDAY',378),(383,0,'FRIDAY',378),(384,0,'SATURDAY',378),(385,0,'SUNDAY',378),(387,0,'MONDAY',386),(388,0,'TUESDAY',386),(389,0,'WEDNESDAY',386),(390,0,'THURSDAY',386),(391,0,'FRIDAY',386),(392,0,'SATURDAY',386),(393,0,'SUNDAY',386),(395,0,'MONDAY',394),(396,0,'TUESDAY',394),(397,0,'WEDNESDAY',394),(398,0,'THURSDAY',394),(399,0,'FRIDAY',394),(400,0,'SATURDAY',394),(401,0,'SUNDAY',394),(403,0,'MONDAY',402),(404,0,'TUESDAY',402),(405,0,'WEDNESDAY',402),(406,0,'THURSDAY',402),(407,0,'FRIDAY',402),(408,0,'SATURDAY',402),(409,0,'SUNDAY',402),(411,0,'MONDAY',410),(412,0,'TUESDAY',410),(413,0,'WEDNESDAY',410),(414,0,'THURSDAY',410),(415,0,'FRIDAY',410),(416,0,'SATURDAY',410),(417,0,'SUNDAY',410),(419,0,'MONDAY',418),(420,0,'TUESDAY',418),(421,0,'WEDNESDAY',418),(422,0,'THURSDAY',418),(423,0,'FRIDAY',418),(424,0,'SATURDAY',418),(425,0,'SUNDAY',418),(427,0,'MONDAY',426),(428,0,'TUESDAY',426),(429,0,'WEDNESDAY',426),(430,0,'THURSDAY',426),(431,0,'FRIDAY',426),(432,0,'SATURDAY',426),(433,0,'SUNDAY',426),(435,0,'MONDAY',434),(436,0,'TUESDAY',434),(437,0,'WEDNESDAY',434),(438,0,'THURSDAY',434),(439,0,'FRIDAY',434),(440,0,'SATURDAY',434),(441,0,'SUNDAY',434),(443,0,'MONDAY',442),(444,0,'TUESDAY',442),(445,0,'WEDNESDAY',442),(446,0,'THURSDAY',442),(447,0,'FRIDAY',442),(448,0,'SATURDAY',442),(449,0,'SUNDAY',442);
/*!40000 ALTER TABLE `day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day_workinghours`
--

DROP TABLE IF EXISTS `day_workinghours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `day_workinghours` (
  `Day_id` int(11) NOT NULL,
  `workingHours` time DEFAULT NULL,
  KEY `FK_ll13vboygrgmb7gqcma6b6hlt` (`Day_id`),
  CONSTRAINT `FK_ll13vboygrgmb7gqcma6b6hlt` FOREIGN KEY (`Day_id`) REFERENCES `day` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day_workinghours`
--

LOCK TABLES `day_workinghours` WRITE;
/*!40000 ALTER TABLE `day_workinghours` DISABLE KEYS */;
INSERT INTO `day_workinghours` VALUES (323,'10:00:00'),(323,'14:00:00'),(323,'16:00:00'),(323,'20:00:00'),(324,'10:00:00'),(324,'14:00:00'),(324,'16:00:00'),(324,'20:00:00'),(325,'10:00:00'),(325,'14:00:00'),(325,'16:00:00'),(325,'20:00:00'),(326,'10:00:00'),(326,'14:00:00'),(326,'16:00:00'),(326,'20:00:00'),(327,'10:00:00'),(327,'14:00:00'),(327,'16:00:00'),(327,'20:00:00'),(328,'10:00:00'),(328,'14:00:00'),(328,'16:00:00'),(328,'20:00:00'),(329,'10:00:00'),(329,'14:00:00'),(329,'16:00:00'),(329,'20:00:00'),(331,'10:00:00'),(331,'14:00:00'),(331,'16:00:00'),(331,'20:00:00'),(332,'10:00:00'),(332,'14:00:00'),(332,'16:00:00'),(332,'20:00:00'),(333,'10:00:00'),(333,'14:00:00'),(333,'16:00:00'),(333,'20:00:00'),(334,'10:00:00'),(334,'14:00:00'),(334,'16:00:00'),(334,'20:00:00'),(335,'10:00:00'),(335,'14:00:00'),(335,'16:00:00'),(335,'20:00:00'),(336,'10:00:00'),(336,'14:00:00'),(337,'10:00:00'),(337,'14:00:00'),(337,'16:00:00'),(337,'20:00:00'),(339,'09:00:00'),(339,'14:00:00'),(339,'16:00:00'),(339,'20:00:00'),(340,'09:00:00'),(340,'14:00:00'),(340,'16:00:00'),(340,'20:00:00'),(341,'09:00:00'),(341,'14:00:00'),(341,'16:00:00'),(341,'20:00:00'),(342,'09:00:00'),(342,'14:00:00'),(342,'16:00:00'),(342,'20:00:00'),(343,'09:00:00'),(343,'14:00:00'),(343,'16:00:00'),(343,'20:00:00'),(344,'10:00:00'),(344,'14:00:00'),(345,'10:00:00'),(345,'14:00:00'),(347,'10:00:00'),(347,'14:00:00'),(347,'16:00:00'),(347,'21:00:00'),(348,'10:00:00'),(348,'14:00:00'),(348,'16:00:00'),(348,'21:00:00'),(349,'10:00:00'),(349,'14:00:00'),(349,'16:00:00'),(349,'21:00:00'),(350,'10:00:00'),(350,'14:00:00'),(350,'16:00:00'),(350,'21:00:00'),(351,'10:00:00'),(351,'14:00:00'),(351,'16:00:00'),(351,'21:00:00'),(352,'10:00:00'),(352,'14:00:00'),(352,'16:00:00'),(352,'21:00:00'),(353,'10:00:00'),(353,'14:00:00'),(353,'16:00:00'),(353,'20:00:00'),(355,'10:00:00'),(355,'14:00:00'),(355,'16:00:00'),(355,'20:00:00'),(356,'10:00:00'),(356,'14:00:00'),(356,'16:00:00'),(356,'20:00:00'),(357,'10:00:00'),(357,'14:00:00'),(357,'16:00:00'),(357,'20:00:00'),(358,'10:00:00'),(358,'14:00:00'),(358,'16:00:00'),(358,'20:00:00'),(359,'10:00:00'),(359,'14:00:00'),(359,'16:00:00'),(359,'20:00:00'),(360,'10:00:00'),(360,'14:00:00'),(361,'10:00:00'),(361,'14:00:00'),(363,'10:00:00'),(363,'13:00:00'),(363,'15:00:00'),(363,'20:00:00'),(364,'10:00:00'),(364,'13:00:00'),(364,'15:00:00'),(364,'20:00:00'),(365,'10:00:00'),(365,'13:00:00'),(365,'15:00:00'),(365,'20:00:00'),(366,'10:00:00'),(366,'13:00:00'),(366,'15:00:00'),(366,'20:00:00'),(367,'10:00:00'),(367,'13:00:00'),(367,'15:00:00'),(367,'20:00:00'),(368,'10:00:00'),(368,'14:00:00'),(369,'10:00:00'),(369,'14:00:00'),(369,'16:00:00'),(369,'20:00:00'),(371,'10:00:00'),(371,'14:00:00'),(371,'16:00:00'),(371,'20:00:00'),(372,'10:00:00'),(372,'14:00:00'),(372,'16:00:00'),(372,'20:00:00'),(373,'10:00:00'),(373,'14:00:00'),(373,'16:00:00'),(373,'20:00:00'),(374,'10:00:00'),(374,'14:00:00'),(374,'16:00:00'),(374,'20:00:00'),(375,'10:00:00'),(375,'14:00:00'),(375,'16:00:00'),(375,'20:00:00'),(376,'10:00:00'),(376,'14:00:00'),(376,'16:00:00'),(376,'20:00:00'),(377,'10:00:00'),(377,'14:00:00'),(379,'10:00:00'),(379,'14:00:00'),(379,'16:00:00'),(379,'20:00:00'),(380,'10:00:00'),(380,'14:00:00'),(380,'16:00:00'),(380,'20:00:00'),(381,'10:00:00'),(381,'14:00:00'),(381,'16:00:00'),(381,'20:00:00'),(382,'10:00:00'),(382,'14:00:00'),(382,'16:00:00'),(382,'20:00:00'),(383,'10:00:00'),(383,'14:00:00'),(383,'16:00:00'),(383,'20:00:00'),(384,'10:00:00'),(384,'14:00:00'),(384,'16:00:00'),(384,'20:00:00'),(385,'10:00:00'),(385,'14:00:00'),(387,'10:00:00'),(387,'14:00:00'),(387,'16:00:00'),(387,'20:00:00'),(388,'10:00:00'),(388,'14:00:00'),(388,'16:00:00'),(388,'20:00:00'),(389,'10:00:00'),(389,'14:00:00'),(389,'16:00:00'),(389,'20:00:00'),(390,'10:00:00'),(390,'14:00:00'),(390,'16:00:00'),(390,'20:00:00'),(391,'10:00:00'),(391,'14:00:00'),(391,'16:00:00'),(391,'20:00:00'),(392,'10:00:00'),(392,'14:00:00'),(392,'16:00:00'),(392,'20:00:00'),(393,'10:00:00'),(393,'14:00:00'),(395,'10:00:00'),(395,'14:00:00'),(395,'16:00:00'),(395,'20:00:00'),(396,'10:00:00'),(396,'14:00:00'),(396,'16:00:00'),(396,'20:00:00'),(397,'10:00:00'),(397,'14:00:00'),(397,'16:00:00'),(397,'20:00:00'),(398,'10:00:00'),(398,'14:00:00'),(398,'16:00:00'),(398,'20:00:00'),(399,'10:00:00'),(399,'14:00:00'),(399,'16:00:00'),(399,'20:00:00'),(400,'10:00:00'),(400,'14:00:00'),(400,'16:00:00'),(400,'20:00:00'),(401,'10:00:00'),(401,'14:00:00'),(401,'16:00:00'),(401,'20:00:00'),(403,'10:00:00'),(403,'12:00:00'),(403,'18:00:00'),(403,'20:00:00'),(404,'10:00:00'),(404,'12:00:00'),(404,'18:00:00'),(404,'20:00:00'),(405,'10:00:00'),(405,'12:00:00'),(405,'18:00:00'),(405,'20:00:00'),(406,'10:00:00'),(406,'12:00:00'),(406,'18:00:00'),(406,'20:00:00'),(407,'10:00:00'),(407,'12:00:00'),(407,'18:00:00'),(407,'20:00:00'),(408,'10:00:00'),(408,'12:00:00'),(409,'10:00:00'),(409,'14:00:00'),(409,'16:00:00'),(409,'20:00:00'),(411,'10:00:00'),(411,'14:00:00'),(411,'16:00:00'),(411,'20:00:00'),(412,'10:00:00'),(412,'14:00:00'),(412,'16:00:00'),(412,'20:00:00'),(413,'10:00:00'),(413,'14:00:00'),(413,'16:00:00'),(413,'20:00:00'),(414,'10:00:00'),(414,'14:00:00'),(414,'16:00:00'),(414,'20:00:00'),(415,'10:00:00'),(415,'14:00:00'),(415,'16:00:00'),(415,'20:00:00'),(416,'10:00:00'),(416,'14:00:00'),(417,'10:00:00'),(417,'14:00:00'),(417,'16:00:00'),(417,'20:00:00'),(419,'10:00:00'),(419,'14:00:00'),(419,'16:00:00'),(419,'20:00:00'),(420,'10:00:00'),(420,'14:00:00'),(420,'16:00:00'),(420,'20:00:00'),(421,'10:00:00'),(421,'14:00:00'),(421,'16:00:00'),(421,'20:00:00'),(422,'10:00:00'),(422,'14:00:00'),(422,'16:00:00'),(422,'20:00:00'),(423,'10:00:00'),(423,'14:00:00'),(423,'16:00:00'),(423,'20:00:00'),(424,'10:00:00'),(424,'14:00:00'),(425,'10:00:00'),(425,'14:00:00'),(425,'16:00:00'),(425,'20:00:00'),(427,'09:00:00'),(427,'14:00:00'),(427,'16:00:00'),(427,'20:00:00'),(428,'09:00:00'),(428,'14:00:00'),(428,'16:00:00'),(428,'20:00:00'),(429,'09:00:00'),(429,'14:00:00'),(429,'16:00:00'),(429,'20:00:00'),(430,'09:00:00'),(430,'14:00:00'),(430,'16:00:00'),(430,'20:00:00'),(431,'09:00:00'),(431,'14:00:00'),(431,'16:00:00'),(431,'20:00:00'),(432,'10:00:00'),(432,'14:00:00'),(433,'10:00:00'),(433,'14:00:00'),(435,'10:00:00'),(435,'14:00:00'),(435,'16:00:00'),(435,'21:00:00'),(436,'10:00:00'),(436,'14:00:00'),(436,'16:00:00'),(436,'21:00:00'),(437,'10:00:00'),(437,'14:00:00'),(437,'16:00:00'),(437,'21:00:00'),(438,'10:00:00'),(438,'14:00:00'),(438,'16:00:00'),(438,'21:00:00'),(439,'10:00:00'),(439,'14:00:00'),(439,'16:00:00'),(439,'21:00:00'),(440,'10:00:00'),(440,'14:00:00'),(440,'16:00:00'),(440,'21:00:00'),(441,'10:00:00'),(441,'14:00:00'),(443,'10:00:00'),(443,'14:00:00'),(443,'16:00:00'),(443,'20:00:00'),(444,'10:00:00'),(444,'14:00:00'),(444,'16:00:00'),(444,'20:00:00'),(445,'10:00:00'),(445,'14:00:00'),(445,'16:00:00'),(445,'20:00:00'),(446,'10:00:00'),(446,'14:00:00'),(446,'16:00:00'),(446,'20:00:00'),(447,'10:00:00'),(447,'14:00:00'),(447,'16:00:00'),(447,'20:00:00'),(448,'10:00:00'),(448,'14:00:00'),(448,'16:00:00'),(448,'20:00:00'),(449,'10:00:00'),(449,'14:00:00'),(449,'16:00:00'),(449,'20:00:00');
/*!40000 ALTER TABLE `day_workinghours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `establishment`
--

DROP TABLE IF EXISTS `establishment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `establishment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `average` double DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lv0f7w9o02i8rlq6gck4os9qx` (`owner_id`),
  CONSTRAINT `FK_lv0f7w9o02i8rlq6gck4os9qx` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `establishment`
--

LOCK TABLES `establishment` WRITE;
/*!40000 ALTER TABLE `establishment` DISABLE KEYS */;
INSERT INTO `establishment` VALUES (270,0,0,'Calle Cervantes, 67, 41100 Coria del Río, Sevilla','Peluquería unisex','Peluquería Rafael Bizcocho',259),(271,0,0,'Av de Alemania, Local 4','Peluquería unisex','Peluquería Lyonele',259),(272,0,0,'Av. José Laguillo, 3','Peluquería SevillaPeluquería SevillaPeluquería SevillaPeluquería SevillaPeluquería SevillaPeluquería Sea SevillaPeluquería SevillaPeluquería SevillaPeluquería SevillaPeluquería SevillaPeluquería SevillaPeluquería Sevilla','Lebrón Peluqueros',260),(273,0,0,'Calle Castilla, 146','Pelados para todos','La Peluquería',261),(274,0,0,'Calle Castilla, 146','Pelados para todos','Peluqueria Los Pelos',261),(275,0,0,'Calle Castilla, 146','Pelados para todos','Peluqueria Rizos',261),(276,0,0,'Calle Castilla, 146','Pelados para todos','La Peluquería',261),(277,0,0,'Calle Juan Carlos I, 54, Villanueva del Ariscal','Piloting','Jacinto Peña',262);
/*!40000 ALTER TABLE `establishment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `establishment_pictures`
--

DROP TABLE IF EXISTS `establishment_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `establishment_pictures` (
  `Establishment_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_1xd0md42u46mmuwupur7dhcum` (`Establishment_id`),
  CONSTRAINT `FK_1xd0md42u46mmuwupur7dhcum` FOREIGN KEY (`Establishment_id`) REFERENCES `establishment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `establishment_pictures`
--

LOCK TABLES `establishment_pictures` WRITE;
/*!40000 ALTER TABLE `establishment_pictures` DISABLE KEYS */;
INSERT INTO `establishment_pictures` VALUES (270,'http://www.comerciotalavera.com/wp-content/uploads/2015/05/peluqueriaxenia3.jpg'),(270,'http://www.aartperruquers.com/html/10325_ROSA_MARIA_SaNCHEZ_RODRiGUEZ/img/big_143903_151373_salon_peluqueria_mataro_aart_perruquers.jpg'),(271,'http://img.bezzia.com/wp-content/uploads/2010/12/salon-de-peluqueria.jpg'),(272,'http://www.mariasuinaga.com/img/salon/01_DSC_2286_800x467.jpg'),(273,'http://www.mariasuinaga.com/img/salon/01_DSC_2286_800x467.jpg'),(274,'https://i.ytimg.com/vi/g42E_qYLRlI/hqdefault.jpg'),(275,'http://www.mariasuinaga.com/img/salon/01_DSC_2286_800x467.jpg'),(276,'http://www.mariasuinaga.com/img/salon/01_DSC_2286_800x467.jpg'),(276,'http://media.salir-static.net/_images_/verticales/7/e/6/a/34505-rizos_rodriguez_san_pedro_rodriguez_san_pedro_pv.jpg/ebs95m'),(277,'http://www.mariasuinaga.com/img/salon/01_DSC_2286_800x467.jpg'),(277,'http://media.salir-static.net/_images_/verticales/7/e/6/a/34505-rizos_rodriguez_san_pedro_rodriguez_san_pedro_pv.jpg/ebs95m');
/*!40000 ALTER TABLE `establishment_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `owner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lite1cn1118a8epvp6k4rnb8j` (`userAccount_id`),
  KEY `FK_fml9yaruu8e90kyx4xkfgpmhr` (`creditCard_id`),
  CONSTRAINT `FK_lite1cn1118a8epvp6k4rnb8j` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_fml9yaruu8e90kyx4xkfgpmhr` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner`
--

LOCK TABLES `owner` WRITE;
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
INSERT INTO `owner` VALUES (259,0,'calbetgonzalez@gmail.com','Juan','692397853','http://weknowyourdreams.com/image.php?pic=/images/woman/woman-06.jpg','Garcia González',228,'84516928R',252),(260,0,'deanambrose@gmail.com','Jonathan','600397853','https://static.pexels.com/photos/52648/pexels-photo-52648.jpeg','Good',229,'47430852E',250),(261,0,'sethrollins@gmail.com','Colby','611237853','http://cdn-01.belfasttelegraph.co.uk/opinion/columnists/jane-graham/article34424816.ece/5c02f/AUTOCROP/w620/2016-02-05_opi_16642636_I1.JPG','Lopez',230,'26197420Y',251),(262,0,'jacinto@peña.com','Jacinto','6666666666','http://cdn-01.belfasttelegraph.co.uk/opinion/columnists/jane-graham/article34424816.ece/5c02f/AUTOCROP/w620/2016-02-05_opi_16642636_I1.JPG','Peña',243,'26197420Y',255);
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `establishment_id` int(11) DEFAULT NULL,
  `worker_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_49ivv3k84cg138hhkaanjrjyx` (`establishment_id`),
  KEY `FK_98fbh1s90pu4mykjh9o8cdgqa` (`worker_id`),
  CONSTRAINT `FK_98fbh1s90pu4mykjh9o8cdgqa` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`),
  CONSTRAINT `FK_49ivv3k84cg138hhkaanjrjyx` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (322,0,270,NULL),(330,0,271,NULL),(338,0,272,NULL),(346,0,273,NULL),(354,0,274,NULL),(362,0,275,NULL),(370,0,276,NULL),(378,0,270,291),(386,0,270,292),(394,0,270,293),(402,0,271,294),(410,0,271,295),(418,0,272,296),(426,0,272,297),(434,0,273,298),(442,0,277,NULL);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_freedays`
--

DROP TABLE IF EXISTS `schedule_freedays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule_freedays` (
  `Schedule_id` int(11) NOT NULL,
  `freeDays` date DEFAULT NULL,
  KEY `FK_5red2kt64hvso4x17h7qjkxtn` (`Schedule_id`),
  CONSTRAINT `FK_5red2kt64hvso4x17h7qjkxtn` FOREIGN KEY (`Schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_freedays`
--

LOCK TABLES `schedule_freedays` WRITE;
/*!40000 ALTER TABLE `schedule_freedays` DISABLE KEYS */;
INSERT INTO `schedule_freedays` VALUES (322,'2017-02-11'),(330,'2017-02-11'),(330,'2017-03-11'),(330,'2017-04-11'),(338,'2017-02-01'),(338,'2017-02-02'),(338,'2017-02-03'),(338,'2017-02-04'),(346,'2017-02-10'),(354,'2017-02-11'),(354,'2017-02-09'),(354,'2017-02-07'),(362,'2017-05-02'),(370,'2017-02-11'),(378,'2017-02-11'),(378,'2017-12-05'),(378,'2017-03-11'),(386,'2017-02-11'),(386,'2017-01-01'),(394,'2017-02-11'),(394,'2017-02-01'),(394,'2017-02-03'),(402,'2017-02-11'),(402,'2017-07-07'),(402,'2017-02-09'),(410,'2017-02-11'),(410,'2017-12-12'),(418,'2017-02-11'),(418,'2017-01-01'),(418,'2017-01-02'),(426,'2017-02-11'),(434,'2017-02-11'),(442,'2017-02-11');
/*!40000 ALTER TABLE `schedule_freedays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` int(11) NOT NULL,
  `fee` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `establishment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ps1b83wifcfe9kfi8quomf8b7` (`establishment_id`),
  CONSTRAINT `FK_ps1b83wifcfe9kfi8quomf8b7` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (278,0,'Corte de pelo',45,2,'Corte','http://hbz.h-cdn.co/assets/16/01/980x490/landscape-1452279902-gettyimages-145083553-copy.jpg',12.5,270),(279,0,'Tinte para mujer',60,1.5,'Tinte','http://www.loreal-paris.es/_es/_es/articles/articles_tendencias/tendencias/images/950x440/bi-color-tinte.jpg',15,270),(280,0,'Corte de pelo para hombre',30,1,'Corte hombre','https://s-media-cache-ak0.pinimg.com/736x/e6/38/b7/e638b79b351920a66cdc458879de8d01.jpg',8,271),(281,0,'Mechas para mujer',90,4,'Mechas','http://www.mujeresfemeninas.com/imagenes/belleza/Mechas-californianas-hechas-en-casa.jpg',25,270),(282,0,'Corte de pelo para niño',30,1.5,'Corte niño','http://static.ellahoy.es/ellahoy/fotogallery/1200X0/187297/corte-de-pelo-estilo-moderno-nino.jpg',10,270),(283,0,'Permanente',75,3,'Permanente','http://static.ellahoy.es/r/845X0/www.ellahoy.es/img/permanente-del-pelo.jpg',30,272),(284,0,'Peinado para mujer',45,1.5,'Peinado','http://www.analasa.es/fotografias/files/imagenes/peinado-a-un-lado-2014-verano-peluqueria-gijon.jpeg',15,272),(285,0,'Corte y peinado para mujer',60,2,'Corte y peinado','https://img.grouponcdn.com/deal/jU8QXafvHKojpSYYVdFJ/Ey-1000x600/v1/c700x420.jpg',20,272),(286,0,'Corte de pelo',45,1.6,'Corte','http://s1.dmcdn.net/H8ZF3/1280x720-Xlg.jpg',16.5,272),(287,0,'Permanente',30,3,'Arreglo de barba','https://marinalia.es/318-thickbox_default/corte-de-pelo-para-hombre-y-de-arreglo-de-barba-en-barber-nour-javea.jpg',30,277),(288,0,'Tinte para hombre',60,1.5,'Tinte','http://susanariveratorres.com/wp-content/uploads/2013/09/reflejos-flequillo-hombre.jpg',15,277),(289,0,'Corte y lavado de pelo',30,2,'Corte y lavado de pelo','http://cortesdepelo2017.com/wp-content/uploads/2016/04/cortes-de-pelo-corto-2017-hombres-tupe-rapado-lados.jpg',20,277),(290,0,'Corte de pelo',30,1.6,'Corte','http://cortesdepelo2017.com/wp-content/uploads/2016/04/cortes-de-pelo-corto-2017-hombres-tupe-rapado-lados.jpg',16.5,277);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (228,0,'c26e420fe6f898c68d278174aced4236','JuanGarcia'),(229,0,'810a6e4761738897d23d2abc8755c7e4','RodriguezEsteban'),(230,0,'364a440226e1b575411a0e324e712d17','Eduardo'),(231,0,'dd92740a32b32f254c1f951bb2301750','Fran20'),(232,0,'a61f8198488054c2b55b805d8dc6f9a5','MariaLuisaRamos'),(233,0,'3706f2b676dbb9d2ae5d1bdb5028bbd2','JuanAlberto1982'),(234,0,'de285ec98e0f83211da217a4e1c5923e','client4'),(235,0,'c6f0ae183ba499b9b1be61ab25a0b97d','Lucia93'),(236,0,'87bbc547b4a45ecda91c8a2de9266cf6','SaludGomez'),(237,0,'b504f5a746deafd3e78b9c85c20ca653','worker3'),(238,0,'221393135bcf2a755b8ac9da40365c67','worker4'),(239,0,'6a21db8adb7572cb5d1b15dfaa4469e4','worker5'),(240,0,'16b7e21c964d2e0b98a5a4712a0df241','worker6'),(241,0,'e2954e1abf0b7138d9dd7a3c13f5aaec','worker7'),(242,0,'fc940eab0c998dcac328969e7d921b32','worker8'),(243,0,'c1ca69ea32722905daa8445fce33c285','jpeña'),(244,0,'d009dc9f2cf2698a859dc31ffac57c1e','angelR'),(245,0,'5bb6b76e30db88c0cfcb67ab48067d6e','AGarrido'),(246,0,'1a8465f920e021e1d5e802e3c109ed3d','JMCotan'),(247,0,'671db596883ff63afbe51c4e8fe2041d','Administrador');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (228,'OWNER'),(229,'OWNER'),(230,'OWNER'),(231,'CLIENT'),(232,'CLIENT'),(233,'CLIENT'),(234,'CLIENT'),(235,'WORKER'),(236,'WORKER'),(237,'WORKER'),(238,'WORKER'),(239,'WORKER'),(240,'WORKER'),(241,'WORKER'),(242,'WORKER'),(243,'OWNER'),(244,'CLIENT'),(245,'CLIENT'),(246,'CLIENT'),(247,'ADMIN');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `establishment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e7rogu7ammlrb70qkl4uouhs6` (`userAccount_id`),
  KEY `FK_ipkkqflc6pbxq5bdpsd58r2qo` (`establishment_id`),
  CONSTRAINT `FK_e7rogu7ammlrb70qkl4uouhs6` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_ipkkqflc6pbxq5bdpsd58r2qo` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (291,0,'romanreigns@gmail.com','Joseph','699547711','https://image.freepik.com/free-photo/smiling-young-man-with-crossed-arms-outdoors_1140-255.jpg','Reigns',235,'34043699L',270),(292,0,'stephanie@gmail.com','Stephanie','987445321','https://www.gentlemenhood.com/wp-content/uploads/2014/05/134247814-Happy-woman.jpg','Smith',236,'40809958T',270),(293,0,'nikkibella@gmail.com','Nicole','658997411','https://placeit.net/uploads/stage/stage_image/2486/default_a3819.png','Gates',237,'43676917D',270),(294,0,'charlotte@gmail.com','Charlotte','656425587','http://www.lbbc.org/sites/default/files/styles/image_callout/public/images/callouts/2015%20YWI%20Button%20Woman%20in%20Teal%20Shirt.jpg?itok=mNVB98CF','Evanson',238,'15833491S',271),(295,0,'cesaro@gmail.com','John','645771258','http://orig08.deviantart.net/fce8/f/2008/128/5/9/young_man_smiling_by_jfschmit.jpg','Black',239,'65396745V',271),(296,0,'neville@gmail.com','Adrian','654778622','http://archived.naccho.org/communications/blog/H1N1/Posts/images/young-man-370-px_1.JPG','Neville',240,'94660397X',272),(297,0,'bayley@gmail.com','Rose','636574122','http://voilanatural.com/wp-content/uploads/2013/09/depositphotos_1327129_original.jpg','Martinez',241,'31336075R',272),(298,0,'themiz@gmail.com','Michael','656478811','https://upload.wikimedia.org/wikipedia/commons/8/83/Young_man_with_dimples.jpg','Mizanin',242,'64561465M',273);
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-26 20:25:36
	
	
	
	commit;
	
	
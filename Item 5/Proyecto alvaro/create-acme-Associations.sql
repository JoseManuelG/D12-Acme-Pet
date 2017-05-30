start transaction;

drop database if exists `Acme-Associations`;
create database `Acme-Associations`;

use `Acme-Associations`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';



grant select, insert, update, delete 
  on `Acme-Associations`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Associations`.* to 'acme-manager'@'%';

	
	
	
	
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: acme-associations
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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endMoment` datetime DEFAULT NULL,
  `maximumAttendants` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `publicActivity` bit(1) DEFAULT NULL,
  `startMoment` datetime DEFAULT NULL,
  `association_id` int(11) NOT NULL,
  `item_id` int(11) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  `winner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_75o8nmi0x1db25q6knsv41egi` (`association_id`),
  KEY `FK_av7iu1b4d89c33sr5njtwxqeu` (`item_id`),
  KEY `FK_nwnclsj1gw2rujw59fo9p03vi` (`place_id`),
  KEY `FK_9gyowqbt5c30aa2ss4vrxjc7o` (`winner_id`),
  CONSTRAINT `FK_9gyowqbt5c30aa2ss4vrxjc7o` FOREIGN KEY (`winner_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_75o8nmi0x1db25q6knsv41egi` FOREIGN KEY (`association_id`) REFERENCES `association` (`id`),
  CONSTRAINT `FK_av7iu1b4d89c33sr5njtwxqeu` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_nwnclsj1gw2rujw59fo9p03vi` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (221,0,'asgf','2017-07-23 22:00:00',14,'Activity1','','2017-07-23 15:00:00',188,NULL,NULL,NULL),(222,0,'asgf','2017-07-26 22:00:00',21,'Activity2','\0','2017-07-25 15:00:00',188,NULL,NULL,NULL),(223,0,'asgf','2017-04-23 22:00:00',10,'Activity3','','2017-04-23 15:00:00',188,NULL,216,153),(224,0,'asgf','2017-07-31 23:59:00',14,'Activity4','','2017-07-28 15:00:00',188,NULL,217,NULL),(225,0,'asgf','2018-07-23 22:00:00',2,'Activity5','\0','2018-07-23 15:00:00',188,NULL,220,NULL),(226,0,'asgf','2017-07-23 22:00:00',12,'Activity6','','2017-07-23 15:00:00',188,NULL,219,NULL);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_user`
--

DROP TABLE IF EXISTS `activity_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_user` (
  `Activity_id` int(11) NOT NULL,
  `attendants_id` int(11) NOT NULL,
  KEY `FK_17a44jww9wn6wrgwy709hxsss` (`attendants_id`),
  KEY `FK_lnb4gaokoado2tehxgo31n641` (`Activity_id`),
  CONSTRAINT `FK_lnb4gaokoado2tehxgo31n641` FOREIGN KEY (`Activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_17a44jww9wn6wrgwy709hxsss` FOREIGN KEY (`attendants_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_user`
--

LOCK TABLES `activity_user` WRITE;
/*!40000 ALTER TABLE `activity_user` DISABLE KEYS */;
INSERT INTO `activity_user` VALUES (221,148),(221,150),(222,149),(222,152),(222,151),(223,148),(223,150),(223,153),(223,151),(223,149),(225,148),(225,150),(226,148),(226,150);
/*!40000 ALTER TABLE `activity_user` ENABLE KEYS */;
UNLOCK TABLES;

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
  `idNumber` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  KEY `AdministratorUK_a99onpf5vqpr7eas9f06dihm0` (`name`),
  KEY `AdministratorUK_hn5atmqw0ai39935ekgv39gsu` (`surname`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (146,0,'admin@gmail.com','2345','Administrator','2345','casa','admin',136),(147,0,'system@gmail.com','2345','System','2345','casa','administrator',137);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `association`
--

DROP TABLE IF EXISTS `association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `association` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `adminClosed` bit(1) DEFAULT NULL,
  `announcements` varchar(255) DEFAULT NULL,
  `closedAssociation` bit(1) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `statutes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `association`
--

LOCK TABLES `association` WRITE;
/*!40000 ALTER TABLE `association` DISABLE KEYS */;
INSERT INTO `association` VALUES (188,0,'address','\0','Announcements','\0','2017-03-30 00:00:00','description','Asociacion1','http://www.zoonewengland.org/media/813822/redpanda_gallery10.jpg','http://www.lmgtfy.com'),(189,0,'address','\0','Announcements','\0','2017-03-30 00:00:00','description','Asociacion2','http://www.zoonewengland.org/media/813822/redpanda_gallery10.jpg','http://www.lmgtfy.com'),(190,0,'address','\0','Announcements','\0','2017-03-30 00:00:00','description','Asociacion3','http://www.zoonewengland.org/media/813822/redpanda_gallery10.jpg','http://www.lmgtfy.com'),(191,0,'address','\0','Announcements','\0','2017-03-30 00:00:00','description','Asociacion4','http://www.zoonewengland.org/media/813822/redpanda_gallery10.jpg','http://www.lmgtfy.com'),(192,0,'address','','Announcements','\0','2017-03-30 00:00:00','description','Asociacion5','http://www.zoonewengland.org/media/813822/redpanda_gallery10.jpg','http://www.lmgtfy.com'),(193,0,'address','\0','Announcements','','2017-03-30 00:00:00','description','Asociacion6','http://www.zoonewengland.org/media/813822/redpanda_gallery10.jpg','http://www.lmgtfy.com');
/*!40000 ALTER TABLE `association` ENABLE KEYS */;
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
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `commentable_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jhvt6d9ap8gxv67ftrmshdfhj` (`user_id`),
  CONSTRAINT `FK_jhvt6d9ap8gxv67ftrmshdfhj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (233,0,'2017-04-28 15:00:00','text','title',188,148),(234,0,'2017-04-28 15:00:00','text','title',188,149),(235,0,'2017-04-28 15:00:00','text','title',188,150),(236,0,'2017-04-28 15:00:00','text','title',188,151),(237,0,'2017-04-28 15:00:00','text','title',188,152),(238,0,'2017-04-28 15:00:00','text','title',188,153);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentable`
--

DROP TABLE IF EXISTS `commentable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commentable` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentable`
--

LOCK TABLES `commentable` WRITE;
/*!40000 ALTER TABLE `commentable` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `actor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_n7ijwwfqlsb8pm0npu8uko9h8` (`actor_id`),
  KEY `UK_l1kp977466ddsv762wign7kdh` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (156,0,'Sent',148),(157,0,'Received',148),(158,0,'Sent',149),(159,0,'Received',149),(160,0,'Sent',150),(161,0,'Received',150),(162,0,'Sent',151),(163,0,'Received',151),(164,0,'Sent',152),(165,0,'Received',152),(166,0,'Sent',153),(167,0,'Received',153),(168,0,'Sent',154),(169,0,'Received',154),(170,0,'Sent',155),(171,0,'Received',155),(172,0,'Sent',146),(173,0,'Received',146),(174,0,'Sent',147),(175,0,'Received',147);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
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
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `itemCondition` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `section_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgfptydfsws0h617pcmjklpm1` (`identifier`),
  KEY `FK_7wdvuycddn7tubqhxid3jfsji` (`section_id`),
  CONSTRAINT `FK_7wdvuycddn7tubqhxid3jfsji` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (253,0,'description','20170521-b73','GOOD','name','',206),(254,0,'description','20170521-b72','GOOD','name','http://www.zoonewengland.org/media/813822/redpanda_gallery10.jpg',206),(255,0,'description','20170521-b71','GOOD','name','',206),(256,0,'description','20170521-b70','BAD','name','',206),(257,0,'description','20170521-b79','LOAN','name','',206),(258,0,'description','20170521-b78','EXCELENT','name','http://www.zoonewengland.org/media/813822/redpanda_gallery10.jpg',206);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `expectedDate` date DEFAULT NULL,
  `finalDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `borrower_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `lender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gyy6d0pwoufi07e5cck66wai1` (`borrower_id`),
  KEY `FK_klokjqmpincit3nt92uungba4` (`item_id`),
  KEY `FK_23og9282aafg8lgai314leoto` (`lender_id`),
  CONSTRAINT `FK_23og9282aafg8lgai314leoto` FOREIGN KEY (`lender_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_gyy6d0pwoufi07e5cck66wai1` FOREIGN KEY (`borrower_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_klokjqmpincit3nt92uungba4` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
INSERT INTO `loan` VALUES (259,0,'2017-03-24','2017-04-23','2017-03-21',149,253,148),(260,0,'2017-03-24','2017-04-23','2017-03-21',149,254,148),(261,0,'2017-03-24','2017-04-23','2017-03-21',149,255,148),(262,0,'2017-03-24',NULL,'2017-03-21',149,256,148),(263,0,'2017-03-24',NULL,'2017-03-21',149,257,148),(264,0,'2017-03-24',NULL,'2017-03-21',149,258,148);
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meeting`
--

DROP TABLE IF EXISTS `meeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meeting` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `agenda` varchar(255) DEFAULT NULL,
  `issue` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `association_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3tb327byug89axw7o25tb2n9e` (`association_id`),
  CONSTRAINT `FK_3tb327byug89axw7o25tb2n9e` FOREIGN KEY (`association_id`) REFERENCES `association` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meeting`
--

LOCK TABLES `meeting` WRITE;
/*!40000 ALTER TABLE `meeting` DISABLE KEYS */;
INSERT INTO `meeting` VALUES (247,0,'address','http://www.google.com','issue','2017-07-21 16:00:00',188),(248,0,'address','http://www.google.com','issue','2017-05-21 16:00:00',188),(249,0,'address','http://www.google.com','issue','2017-05-22 16:00:00',188),(250,0,'address','http://www.google.com','issue','2017-05-23 16:00:00',188),(251,0,'address','http://www.google.com','issue','2017-05-24 16:00:00',188),(252,0,'address','http://www.google.com','issue','2017-05-25 16:00:00',188);
/*!40000 ALTER TABLE `meeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `folder_id` int(11) NOT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_7t1ls63lqb52igs4ms20cf94t` (`folder_id`),
  KEY `UK_tbto6hemu447oixxk730el2vx` (`sender_id`),
  KEY `UK_q55vuhpo0cr2pdrb0rejw3bmi` (`recipient_id`),
  CONSTRAINT `FK_7t1ls63lqb52igs4ms20cf94t` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (176,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message1',158,148,149),(177,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message1',157,148,149),(178,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message2',164,150,152),(179,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message2',161,150,152),(180,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message3',162,148,151),(181,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message3',157,148,151),(182,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message4',158,148,149),(183,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message4',157,148,149),(184,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message5',166,149,153),(185,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message5',159,149,153),(186,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message6',156,150,148),(187,0,'2017-03-03 23:49:00','Estoy jugando a la Play','Message6',161,150,148);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `minutes`
--

DROP TABLE IF EXISTS `minutes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `minutes` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `document` varchar(255) DEFAULT NULL,
  `meeting_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kb0essj880i8a9fcdq0pr6cjo` (`meeting_id`),
  CONSTRAINT `FK_kb0essj880i8a9fcdq0pr6cjo` FOREIGN KEY (`meeting_id`) REFERENCES `meeting` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `minutes`
--

LOCK TABLES `minutes` WRITE;
/*!40000 ALTER TABLE `minutes` DISABLE KEYS */;
INSERT INTO `minutes` VALUES (265,0,'http://http://www.google.es',247),(266,0,'http://www.google.es',248),(267,0,'http://www.google.es',249),(268,0,'http://www.google.es',250),(269,0,'http://www.google.es',251),(270,0,'http://www.google.es',252);
/*!40000 ALTER TABLE `minutes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `minutes_user`
--

DROP TABLE IF EXISTS `minutes_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `minutes_user` (
  `Minutes_id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL,
  KEY `FK_9rf2yjgrej8y0k40qmn9uj88l` (`users_id`),
  KEY `FK_hfvrkwtefkt9yxcw42mfvy6ks` (`Minutes_id`),
  CONSTRAINT `FK_hfvrkwtefkt9yxcw42mfvy6ks` FOREIGN KEY (`Minutes_id`) REFERENCES `minutes` (`id`),
  CONSTRAINT `FK_9rf2yjgrej8y0k40qmn9uj88l` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `minutes_user`
--

LOCK TABLES `minutes_user` WRITE;
/*!40000 ALTER TABLE `minutes_user` DISABLE KEYS */;
INSERT INTO `minutes_user` VALUES (265,149),(265,150),(266,149),(266,150),(267,149),(267,150),(268,149),(268,150),(269,149),(269,150),(270,149),(270,150);
/*!40000 ALTER TABLE `minutes_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (213,0,'Calle gotg',0,0),(214,0,'Calle pesadilla',10,80),(215,0,'Calle barbacoa',40,-120),(216,0,'Calle patata',-10,10),(217,0,'Calle chorizo',0,-20),(218,0,'Calle melon',-20,0),(219,0,'Calle vida',-45,150),(220,0,'Calle ',30,20);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `association_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_30yi2hpb1abvdanf728mptov4` (`association_id`),
  KEY `FK_lsq6snl5hdbnrqo9cm8n40ssh` (`user_id`),
  CONSTRAINT `FK_lsq6snl5hdbnrqo9cm8n40ssh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_30yi2hpb1abvdanf728mptov4` FOREIGN KEY (`association_id`) REFERENCES `association` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (227,0,189,148),(228,0,189,150),(229,0,189,151),(230,0,189,152),(231,0,189,153),(232,0,189,154);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `association_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o8kjt21gy7wv9k332iox7p4qe` (`association_id`),
  KEY `FK_q60kd0m349k45ov57gya3mb3w` (`user_id`),
  CONSTRAINT `FK_q60kd0m349k45ov57gya3mb3w` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_o8kjt21gy7wv9k332iox7p4qe` FOREIGN KEY (`association_id`) REFERENCES `association` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (194,0,'MANAGER',188,148),(195,0,'MANAGER',189,149),(196,0,'MANAGER',190,150),(197,0,'MANAGER',191,151),(198,0,'MANAGER',192,152),(199,0,'MANAGER',193,153),(200,0,'COLLABORATOR',188,149),(201,0,'COLLABORATOR',188,150),(202,0,'COLLABORATOR',188,151),(203,0,'COLLABORATOR',188,152),(204,0,'COLLABORATOR',188,153),(205,0,'ASSOCIATE',188,155);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanction`
--

DROP TABLE IF EXISTS `sanction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sanction` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `motiff` varchar(255) DEFAULT NULL,
  `association_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_esuv8eteltyuvrc4d6l4ygkyk` (`association_id`),
  KEY `FK_gs3si4qdjv7gkjeufgj7goiyk` (`user_id`),
  CONSTRAINT `FK_gs3si4qdjv7gkjeufgj7goiyk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_esuv8eteltyuvrc4d6l4ygkyk` FOREIGN KEY (`association_id`) REFERENCES `association` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanction`
--

LOCK TABLES `sanction` WRITE;
/*!40000 ALTER TABLE `sanction` DISABLE KEYS */;
INSERT INTO `sanction` VALUES (239,0,'2017-03-21 15:00:00','motiff',188,150),(240,0,'2017-03-21 15:00:00','motiff',188,149),(241,0,'2017-03-21 15:00:00','motiff',188,151),(242,0,'2017-03-21 15:00:00','motiff',188,152),(243,0,'2017-03-21 15:00:00','motiff',188,153),(244,0,'2017-03-21 15:00:00','motiff',188,148),(245,0,'2017-07-21 14:00:00','motiff',188,150),(246,0,'2017-07-21 16:00:00','motiff',188,151);
/*!40000 ALTER TABLE `sanction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `association_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nou5r9h8dy6inqxyn2fw6lfy` (`association_id`),
  KEY `FK_areids3eh9hc0in0nysc3f65q` (`user_id`),
  CONSTRAINT `FK_areids3eh9hc0in0nysc3f65q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_nou5r9h8dy6inqxyn2fw6lfy` FOREIGN KEY (`association_id`) REFERENCES `association` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (206,0,'Section1',188,148),(207,0,'Section1',188,149),(208,0,'Section1',188,150),(209,0,'Section1',188,151),(210,0,'Section1',188,152),(211,0,'Section1',188,153),(212,0,'Section1',189,148);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `idNumber` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  KEY `UserUK_a99onpf5vqpr7eas9f06dihm0` (`name`),
  KEY `UserUK_hn5atmqw0ai39935ekgv39gsu` (`surname`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (148,0,'user1@gmail.com','2345','User1','2345','casa','user',138),(149,0,'user2@gmail.com','2345','User2','2345','casa','user',139),(150,0,'user3@gmail.com','2345','User3','2345','casa','user',140),(151,0,'user4@gmail.com','2345','User4','2345','casa','user',141),(152,0,'user5@gmail.com','2345','User5','2345','casa','user',142),(153,0,'user6@gmail.com','2345','User6','2345','casa','user',143),(154,0,'user7@gmail.com','2345','User7','2345','casa','user',144),(155,0,'user8@gmail.com','2345','User8','2345','casa','user',145);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
INSERT INTO `useraccount` VALUES (136,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(137,0,'54b53072540eeeb8f8e9343e71f28176','system'),(138,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),(139,0,'7e58d63b60197ceb55a1c487989a3720','user2'),(140,0,'92877af70a45fd6a2ed7fe81e1236b78','user3'),(141,0,'3f02ebe3d7929b091e3d8ccfde2f3bc6','user4'),(142,0,'0a791842f52a0acfbb3a783378c066b8','user5'),(143,0,'affec3b64cf90492377a8114c86fc093','user6'),(144,0,'3e0469fb134991f8f75a2760e409c6ed','user7'),(145,0,'7668f673d5669995175ef91b5d171945','user8');
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
INSERT INTO `useraccount_authorities` VALUES (136,'ADMIN'),(137,'ADMIN'),(138,'USER'),(139,'USER'),(140,'USER'),(141,'USER'),(142,'USER'),(143,'USER'),(144,'USER'),(145,'USER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-30 12:52:10
	
	
	
	commit;
	
	
start transaction;

create database `Acme-Pet`;

use `Acme-Pet`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
on `Acme-Pet`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
create temporary tables, lock tables, create view, create routine,
 alter routine, execute, trigger, show view 
on `Acme-Pet`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Pet
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
-- Table structure for table `abusereport`
--

DROP TABLE IF EXISTS `abusereport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abusereport` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `reportDate` datetime DEFAULT NULL,
  `reported_id` int(11) NOT NULL,
  `reporter_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_9uey56pbnvupofuhngey3ufo` (`reportDate`),
  KEY `FK_keaaa6nrp9l4s2l9r0tlhifn6` (`reported_id`),
  KEY `FK_ivwr43x8a9mihwp27v1jserm2` (`reporter_id`),
  CONSTRAINT `FK_ivwr43x8a9mihwp27v1jserm2` FOREIGN KEY (`reporter_id`) REFERENCES `animaniac` (`id`),
  CONSTRAINT `FK_keaaa6nrp9l4s2l9r0tlhifn6` FOREIGN KEY (`reported_id`) REFERENCES `animaniac` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abusereport`
--

LOCK TABLES `abusereport` WRITE;
/*!40000 ALTER TABLE `abusereport` DISABLE KEYS */;
INSERT INTO `abusereport` VALUES (486,0,'description sample','2017-01-01 12:06:00',280,277),(487,0,'description sample2','2017-01-01 12:07:00',280,278),(488,0,'description sample3','2017-01-01 12:08:00',280,NULL),(489,0,'description sample4','2017-01-01 12:10:00',280,281),(490,0,'description sample5','2017-01-01 12:11:00',280,282),(491,0,'description sample6','2017-01-01 12:12:00',280,283),(492,0,'description sample7','2017-01-01 13:12:00',284,277);
/*!40000 ALTER TABLE `abusereport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
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
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
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
INSERT INTO `administrator` VALUES (276,0,'admin@acme.com','administrator1','+34666666666','administrator1',255);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animaniac`
--

DROP TABLE IF EXISTS `animaniac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animaniac` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `rate` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_irxa91l5gu92tri6xh9ut2pe8` (`userAccount_id`),
  KEY `UK_5xxqq2edxmiojln2mtgjd7x6v` (`rate`),
  CONSTRAINT `FK_irxa91l5gu92tri6xh9ut2pe8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animaniac`
--

LOCK TABLES `animaniac` WRITE;
/*!40000 ALTER TABLE `animaniac` DISABLE KEYS */;
INSERT INTO `animaniac` VALUES (277,0,'animaniac1@acme.com','Animaniac1','+34000000000','Animaniac1',256,'Calle de los animales 1ºA','male','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',25),(278,0,'animaniac2@acme.com','Animaniac2','+34000000000','Animaniac2',257,'Calle de los animales 2ºB','male','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',4),(279,0,'animaniac3@acme.com','Animaniac3','+34000000000','Animaniac3',258,'Calle de los animales 3ºC','male','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',-3),(280,0,'animaniac4@acme.com','Animaniac4','+34000000000','Animaniac4',259,'Calle de los animales 4ºD','male','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',0),(281,0,'animaniac5@acme.com','Animaniac5','+34000000000','Animaniac5',260,'Calle de los animales 5ºA','female','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',5),(282,0,'animaniac6@acme.com','Animaniac6','+34000000000','Animaniac6',261,'Calle de los animales 6ºB','female','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',11),(283,0,'animaniac7@acme.com','Animaniac7','+34000000000','Animaniac7',262,'Calle de los animales 7ºC','female','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',-8),(284,0,'animaniac8@acme.com','Animaniac8','+34000000000','Animaniac8',263,'Calle de los animales 8ºD','female','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',0);
/*!40000 ALTER TABLE `animaniac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `animaniac_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_hcserbda5h22sdv6m0aj54s5v` (`state`),
  KEY `FK_l22gma1de4th8228k4nu487kq` (`animaniac_id`),
  KEY `FK_iycvubnr3mdpkxfykptcqdoq5` (`request_id`),
  CONSTRAINT `FK_iycvubnr3mdpkxfykptcqdoq5` FOREIGN KEY (`request_id`) REFERENCES `request` (`id`),
  CONSTRAINT `FK_l22gma1de4th8228k4nu487kq` FOREIGN KEY (`animaniac_id`) REFERENCES `animaniac` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (453,0,'application description','Accepted',278,438),(454,0,'application description','Denied',279,438),(455,0,'application description','Denied',280,438),(456,0,'application description','Denied',281,438),(457,0,'application description','Denied',282,438),(458,0,'application description','Denied',283,438),(459,0,'application description','Accepted',278,439),(460,0,'application description','Accepted',283,443),(461,0,'application description','Pending',280,444),(462,0,'application description','Pending',281,444),(463,0,'application description','Pending',282,444),(464,0,'application description','Pending',283,444),(465,0,'application description','Pending',284,444);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `message_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t7ugo9lqsetartllgb9787p0s` (`message_id`),
  CONSTRAINT `FK_t7ugo9lqsetartllgb9787p0s` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (402,0,'AttachmentName1','http://www.attachmentName1.com',383),(403,0,'AttachmentName1','http://www.attachmentName2.com',383),(404,0,'AttachmentName1','http://www.attachmentName3.com',383),(405,0,'AttachmentName1','http://www.attachmentName4.com',383),(406,0,'AttachmentName1','http://www.attachmentName5.com',383),(407,0,'AttachmentName1','http://www.attachmentName6.com',383),(408,0,'AttachmentName1','http://www.attachmentName1.com',384),(409,0,'AttachmentName1','http://www.attachmentName2.com',384),(410,0,'AttachmentName1','http://www.attachmentName3.com',384),(411,0,'AttachmentName1','http://www.attachmentName4.com',384),(412,0,'AttachmentName1','http://www.attachmentName5.com',384),(413,0,'AttachmentName1','http://www.attachmentName6.com',384),(414,0,'AttachmentName1','http://www.attachmentName1.com',386),(415,0,'AttachmentName1','http://www.attachmentName1.com',386);
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attribute` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_801wj4mtqf8ar253ewbtvlb03` (`name`,`type_id`),
  KEY `FK_rw3nnk064wdpjrifch2ggjlm` (`type_id`),
  CONSTRAINT `FK_rw3nnk064wdpjrifch2ggjlm` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` VALUES (422,0,'PPP',416),(423,0,'castrated',416),(424,0,'breed',416),(425,0,'stressed',417),(426,0,'castrated',417),(427,0,'breed',417);
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attributevalue`
--

DROP TABLE IF EXISTS `attributevalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attributevalue` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `attribute_id` int(11) NOT NULL,
  `pet_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lar5f4eplm16215ljwbdtdwuk` (`attribute_id`,`pet_id`),
  KEY `FK_cpivibywtlh9oqcd9pig7dgux` (`pet_id`),
  CONSTRAINT `FK_cpivibywtlh9oqcd9pig7dgux` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`),
  CONSTRAINT `FK_5cmva5mxf4bw1xngyghxewb57` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attributevalue`
--

LOCK TABLES `attributevalue` WRITE;
/*!40000 ALTER TABLE `attributevalue` DISABLE KEYS */;
INSERT INTO `attributevalue` VALUES (436,0,'false',422,428),(437,0,'false',423,428);
/*!40000 ALTER TABLE `attributevalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `partner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_xwwo6ppv161cypttaqhqoc13` (`partner_id`),
  CONSTRAINT `FK_xwwo6ppv161cypttaqhqoc13` FOREIGN KEY (`partner_id`) REFERENCES `partner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (466,0,'http://www.webPartner1.com','http://i.imgur.com/7Ijjree.jpg',285),(467,0,'http://www.webPartner1.com','http://i.imgur.com/QxmD35H.gif',285),(468,0,'http://www.webPartner1.com','http://i.imgur.com/rWMhQlw.jpg',285),(469,0,'http://www.webPartner1.com','http://i.imgur.com/xf1TZt7.jpg',285),(470,0,'http://www.webPartner1.com','http://i.imgur.com/RnkWuON.jpg',285),(471,0,'http://i.imgur.com/Q2Wfdfz.jpg','http://foto6.png',285),(472,0,'http://www.webPartner2.com','http://i.imgur.com/TDUqtbC.jpg',286);
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
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
  `body` varchar(255) DEFAULT NULL,
  `postMoment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `animaniac_id` int(11) NOT NULL,
  `commentable_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (473,0,'body Comment1Animaniac1','2017-01-01 12:00:00','title Comment1Animaniac1',277,428),(474,0,'body Comment2Animaniac1','2017-01-01 12:01:00','title Comment2Animaniac1',277,428),(475,0,'body Comment3Animaniac1','2017-01-01 12:02:00','title Comment3Animaniac1',277,428),(476,0,'body Comment4Animaniac1','2017-01-01 12:03:00','title Comment4Animaniac1',277,428),(477,0,'body Comment5Animaniac1','2017-01-01 12:04:00','title Comment5Animaniac1',277,428),(478,0,'body Comment6Animaniac1','2017-01-01 12:05:00','title Comment6Animaniac1',277,428),(479,0,'body Comment1Animaniac2','2017-01-01 12:06:00','title Comment1Animaniac2',277,429),(480,0,'body Comment1Animaniac1','2017-01-01 12:00:00','title Comment1Animaniac1',279,277),(481,0,'body Comment2Animaniac1','2017-01-01 12:01:00','title Comment2Animaniac1',279,277),(482,0,'body Comment3Animaniac1','2017-01-01 12:02:00','title Comment3Animaniac1',279,277),(483,0,'body Comment4Animaniac1','2017-01-01 12:03:00','title Comment4Animaniac1',279,277),(484,0,'body Comment5Animaniac1','2017-01-01 12:04:00','title Comment5Animaniac1',279,277),(485,0,'body Comment6Animaniac1','2017-01-01 12:05:00','title Comment6Animaniac1',279,277);
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
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cacheTime` bigint(20) NOT NULL,
  `partnerFee` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (493,0,21600000,0.25);
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `educationSection` varchar(255) DEFAULT NULL,
  `experienceSection` varchar(255) DEFAULT NULL,
  `hobbiesSection` varchar(255) DEFAULT NULL,
  `animaniac_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i86yk7vab0xh93yi4m4jrlooj` (`animaniac_id`),
  CONSTRAINT `FK_i86yk7vab0xh93yi4m4jrlooj` FOREIGN KEY (`animaniac_id`) REFERENCES `animaniac` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` VALUES (494,0,'education animaniac1','expreciences animaniac1','hobbies animaniac1',277),(495,0,'education animaniac2','expreciences animaniac2','hobbies animaniac2',278);
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
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
  `readOnly` bit(1) NOT NULL,
  `actor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4sx3xvlwf6wa6rhfb24o5jmsw` (`name`,`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (297,0,'inbox','',277),(298,0,'outbox','',277),(299,0,'trashbox','',277),(300,0,'spambox','',277),(301,0,'newFolder','\0',277),(302,0,'newTestFolder','\0',277),(303,0,'inbox','',278),(304,0,'outbox','',278),(305,0,'trashbox','',278),(306,0,'spambox','',278),(307,0,'inbox','',279),(308,0,'outbox','',279),(309,0,'trashbox','',279),(310,0,'spambox','',279),(311,0,'inbox','',280),(312,0,'outbox','',280),(313,0,'trashbox','',280),(314,0,'spambox','',280),(315,0,'inbox','',281),(316,0,'outbox','',281),(317,0,'trashbox','',281),(318,0,'spambox','',281),(319,0,'inbox','',282),(320,0,'outbox','',282),(321,0,'trashbox','',282),(322,0,'spambox','',282),(323,0,'inbox','',283),(324,0,'outbox','',283),(325,0,'trashbox','',283),(326,0,'spambox','',283),(327,0,'inbox','',284),(328,0,'outbox','',284),(329,0,'trashbox','',284),(330,0,'spambox','',284),(331,0,'inbox','',276),(332,0,'outbox','',276),(333,0,'trashbox','',276),(334,0,'spambox','',276),(335,0,'inbox','',285),(336,0,'outbox','',285),(337,0,'trashbox','',285),(338,0,'spambox','',285),(339,0,'inbox','',286),(340,0,'outbox','',286),(341,0,'trashbox','',286),(342,0,'spambox','',286),(343,0,'inbox','',287),(344,0,'outbox','',287),(345,0,'trashbox','',287),(346,0,'spambox','',287),(347,0,'inbox','',288),(348,0,'outbox','',288),(349,0,'trashbox','',288),(350,0,'spambox','',288),(351,0,'inbox','',289),(352,0,'outbox','',289),(353,0,'trashbox','',289),(354,0,'spambox','',289),(355,0,'inbox','',290),(356,0,'outbox','',290),(357,0,'trashbox','',290),(358,0,'spambox','',290),(359,0,'inbox','',291),(360,0,'outbox','',291),(361,0,'trashbox','',291),(362,0,'spambox','',291),(363,0,'inbox','',292),(364,0,'outbox','',292),(365,0,'trashbox','',292),(366,0,'spambox','',292),(367,0,'inbox','',293),(368,0,'outbox','',293),(369,0,'trashbox','',293),(370,0,'spambox','',293),(371,0,'inbox','',294),(372,0,'outbox','',294),(373,0,'trashbox','',294),(374,0,'spambox','',294),(375,0,'inbox','',295),(376,0,'outbox','',295),(377,0,'trashbox','',295),(378,0,'spambox','',295),(379,0,'inbox','',296),(380,0,'outbox','',296),(381,0,'trashbox','',296),(382,0,'spambox','',296);
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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `isSender` bit(1) NOT NULL,
  `recipientName` varchar(255) DEFAULT NULL,
  `senderName` varchar(255) DEFAULT NULL,
  `sendingMoment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `folder_id` int(11) NOT NULL,
  `recipient_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_j57di2pwqhon5xodktsp8ikbx` (`isSender`),
  KEY `FK_7t1ls63lqb52igs4ms20cf94t` (`folder_id`),
  CONSTRAINT `FK_7t1ls63lqb52igs4ms20cf94t` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (383,0,'','Animaniac2 Animaniac2','Animaniac1 Animaniac1','2016-04-04 11:45:00','subject Message','text Message',298,278,277),(384,0,'\0','Animaniac2 Animaniac2','Animaniac1 Animaniac1','2016-04-04 11:45:00','subject Message','text Message',303,278,277),(385,0,'','Animaniac2 Animaniac2','Animaniac1 Animaniac1','2016-04-04 11:45:00','subject Message','text Message',298,278,277),(386,0,'\0','Animaniac2 Animaniac2','Animaniac1 Animaniac1','2016-04-04 11:45:00','subject Message','text Message',303,278,277),(387,0,'','Animaniac2 Animaniac2','Animaniac1 Animaniac1','2016-04-04 11:45:00','subject Message','text Message',298,278,277),(388,0,'','Animaniac2 Animaniac2','Animaniac1 Animaniac1','2016-04-04 11:45:00','subject Message','text Message',298,278,277),(389,0,'','Animaniac2 Animaniac2','Animaniac1 Animaniac1','2016-04-04 11:45:00','subject Message','text Message',298,278,277),(390,0,'','Animaniac2 Animaniac2','Animaniac1 Animaniac1','2016-04-04 11:45:00','subject Message','text Message',298,278,277),(391,0,'\0','Animaniac1 Animaniac1','Animaniac2 Animaniac2','2016-04-04 11:45:00','subject Message','text Message',297,277,278),(392,0,'\0','Animaniac1 Animaniac1','Animaniac2 Animaniac2','2016-04-04 11:45:00','subject Message','text Message',297,277,278),(393,0,'\0','Animaniac1 Animaniac1','Animaniac2 Animaniac2','2016-04-04 11:45:00','subject Message','text Message',297,277,278),(394,0,'\0','Animaniac1 Animaniac1','Animaniac2 Animaniac2','2016-04-04 11:45:00','subject Message','text Message',297,277,278),(395,0,'\0','Animaniac1 Animaniac1','Animaniac2 Animaniac2','2016-04-04 11:45:00','subject Message','text Message',297,277,278),(396,0,'\0','Animaniac1 Animaniac1','Animaniac2 Animaniac2','2016-04-04 11:45:00','subject Message','text Message',297,277,278),(397,0,'','Animaniac3 Animaniac3','Animaniac2 Animaniac2','2016-04-04 11:45:00','subject Message','text Message',304,279,278),(398,0,'','Animaniac4 Animaniac4','Animaniac3 Animaniac3','2016-04-04 11:45:00','subject Message','text Message',308,280,279),(399,0,'\0','Animaniac3 Animaniac3','Animaniac4 Animaniac4','2016-04-04 11:45:00','subject Message','text Message',307,279,280),(400,0,'\0','Animaniac3 Animaniac3','Animaniac- Animaniac-','2016-04-04 11:45:00','subject Message','text Message',307,279,NULL),(401,0,'','Animaniac- Animaniac-','Animaniac3 Animaniac3','2016-04-04 11:45:00','subject Message','text Message',308,NULL,279);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner`
--

DROP TABLE IF EXISTS `partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `numDisplay` int(11) NOT NULL,
  `totalFee` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c3c17xsi1wo35jlhd6sk8bmqc` (`userAccount_id`),
  KEY `UK_4pjyv898tabqs6tpcb7wqoouy` (`totalFee`),
  CONSTRAINT `FK_c3c17xsi1wo35jlhd6sk8bmqc` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner`
--

LOCK TABLES `partner` WRITE;
/*!40000 ALTER TABLE `partner` DISABLE KEYS */;
INSERT INTO `partner` VALUES (285,0,'partner1@acme.com','Partner1','+34000000000','Partner1',264,'Calle de los cuidadores 1ºA','Description Partner1','https://www.carecenter1.com',10,2.5),(286,0,'partner2@acme.com','Partner2','+34000000000','Partner2',265,'Calle de los cuidadores 2ºB','Description Partner2','https://www.carecenter2.com',2,0.5),(287,0,'partner3@acme.com','Partner3','+34000000000','Partner3',266,'Calle de los cuidadores 3ºC','Description Partner3','https://www.carecenter3.com',0,0),(288,0,'partner4@acme.com','Partner4','+34000000000','Partner4',267,'Calle de los cuidadores 4ºD','Description Partner4','https://www.carecenter4.com',0,0),(289,0,'partner5@acme.com','Partner5','+34000000000','Partner5',268,'Calle de los cuidadores 5ºA','Description Partner5','https://www.carecenter5.com',0,0),(290,0,'partner6@acme.com','Partner6','+34000000000','Partner6',269,'Calle de los cuidadores 6ºB','Description Partner6','https://www.carecenter6.com',0,0);
/*!40000 ALTER TABLE `partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `certificateBy` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `weigth` double NOT NULL,
  `animaniac_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `vet_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_hnax5jvwnurg1p4eega9p0l5` (`certificateBy`),
  KEY `FK_qo8tmmwen0o1xq71y9tclg5io` (`animaniac_id`),
  KEY `FK_pt8cfjjvshlk8v57kaxypuuaw` (`type_id`),
  KEY `FK_7e51hyvwn2bp8qlko4cp8j74u` (`vet_id`),
  CONSTRAINT `FK_7e51hyvwn2bp8qlko4cp8j74u` FOREIGN KEY (`vet_id`) REFERENCES `vet` (`id`),
  CONSTRAINT `FK_pt8cfjjvshlk8v57kaxypuuaw` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
  CONSTRAINT `FK_qo8tmmwen0o1xq71y9tclg5io` FOREIGN KEY (`animaniac_id`) REFERENCES `animaniac` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES (428,0,'Vet1 Vet1','male','pet1Animaniac1',5,277,416,291),(429,0,'Vet1 Vet1','male','pet2Animaniac1',5,277,416,291),(430,0,'Vet1 Vet1','male','pet3Animaniac1',5,277,416,291),(431,0,'Vet1 Vet1','female','pet4Animaniac1',5,277,416,291),(432,0,'Vet1 Vet1','female','pet5Animaniac1',5,277,416,291),(433,0,'Vet1 Vet1','female','pet6Animaniac1',5,277,416,291),(434,0,NULL,'male','pet7Animaniac1',0.5,277,418,NULL),(435,0,NULL,'female','pet1Animaniac2',3,278,417,NULL);
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `pet_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4y4341ldgg91lomqpk2yo4a9q` (`pet_id`),
  CONSTRAINT `FK_4y4341ldgg91lomqpk2yo4a9q` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES (496,0,'http://www.todoperros.com/wp-content/uploads/2016/08/perro.jpg',428),(497,0,'http://www.todoperros.com/wp-content/uploads/2016/08/perro.jpg',428),(498,0,'http://www.todoperros.com/wp-content/uploads/2016/08/perro.jpg',428),(499,0,'http://www.todoperros.com/wp-content/uploads/2016/08/perro.jpg',428),(500,0,'http://www.todoperros.com/wp-content/uploads/2016/08/perro.jpg',428),(501,0,'http://www.todoperros.com/wp-content/uploads/2016/08/perro.jpg',428),(502,0,'http://www.todoperros.com/wp-content/uploads/2016/08/perro.jpg',429);
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
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
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `rated` bit(1) NOT NULL,
  `startDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_518u0s52jsvpw25efewvs7v6k` (`endDate`),
  KEY `UK_65mdsjxrqr5y9hbq3xb8q67e1` (`startDate`),
  KEY `UK_if72axt49ls00bwid473ve3tp` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (438,0,'Calle de recogida nº1','Description request','2017-01-25 12:00:00','','2017-01-20 12:00:00'),(439,0,'Calle de recogida nº1','Description request','2016-01-25 12:00:00','\0','2016-01-20 12:00:00'),(440,0,'Calle de recogida nº1','Description request','2015-01-25 12:00:00','\0','2015-01-20 12:00:00'),(441,0,'Calle de recogida nº1','Description request','2014-01-25 12:00:00','\0','2014-01-20 12:00:00'),(442,0,'Calle de recogida nº1','Description request','2013-01-25 12:00:00','\0','2013-01-20 12:00:00'),(443,0,'Calle de recogida nº1','Description request','2018-01-25 12:00:00','\0','2018-01-20 12:00:00'),(444,0,'Calle de recogida nº2','Description request','2017-10-24 12:00:00','\0','2017-10-10 12:00:00');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_pet`
--

DROP TABLE IF EXISTS `request_pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_pet` (
  `Request_id` int(11) NOT NULL,
  `pets_id` int(11) NOT NULL,
  KEY `FK_kj9qbic1kagwkyd8bj4affk06` (`pets_id`),
  KEY `FK_jrh585jv5oq7e2o2lumrp9vag` (`Request_id`),
  CONSTRAINT `FK_jrh585jv5oq7e2o2lumrp9vag` FOREIGN KEY (`Request_id`) REFERENCES `request` (`id`),
  CONSTRAINT `FK_kj9qbic1kagwkyd8bj4affk06` FOREIGN KEY (`pets_id`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_pet`
--

LOCK TABLES `request_pet` WRITE;
/*!40000 ALTER TABLE `request_pet` DISABLE KEYS */;
INSERT INTO `request_pet` VALUES (438,428),(438,429),(438,430),(438,431),(438,432),(438,433),(439,428),(440,428),(441,428),(442,428),(443,428),(444,435);
/*!40000 ALTER TABLE `request_pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchengine`
--

DROP TABLE IF EXISTS `searchengine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchengine` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `searchMoment` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `animaniac_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pedaihl6fix72wf97j2wqhop1` (`animaniac_id`),
  CONSTRAINT `FK_pedaihl6fix72wf97j2wqhop1` FOREIGN KEY (`animaniac_id`) REFERENCES `animaniac` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchengine`
--

LOCK TABLES `searchengine` WRITE;
/*!40000 ALTER TABLE `searchengine` DISABLE KEYS */;
INSERT INTO `searchengine` VALUES (445,0,'nº1',NULL,'2000-10-10 12:00:00',NULL,'',277),(446,0,'','2018-02-10 00:00:00','2000-10-10 12:00:00','2018-01-01 00:00:00','',278),(447,0,'',NULL,'2000-10-10 12:00:00',NULL,'',279),(448,0,'',NULL,'2000-10-10 12:00:00',NULL,'',280),(449,0,'',NULL,'2000-10-10 12:00:00',NULL,'',281),(450,0,'',NULL,'2000-10-10 12:00:00',NULL,'',282),(451,0,'',NULL,'2000-10-10 12:00:00',NULL,'',283),(452,0,'',NULL,'2000-10-10 12:00:00',NULL,'',284);
/*!40000 ALTER TABLE `searchengine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchengine_request`
--

DROP TABLE IF EXISTS `searchengine_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchengine_request` (
  `SearchEngine_id` int(11) NOT NULL,
  `requests_id` int(11) NOT NULL,
  KEY `FK_737wta28ow8eoxx43h8cxr0qo` (`requests_id`),
  KEY `FK_cdnjhqjrs6kehoqsfx367ktwb` (`SearchEngine_id`),
  CONSTRAINT `FK_cdnjhqjrs6kehoqsfx367ktwb` FOREIGN KEY (`SearchEngine_id`) REFERENCES `searchengine` (`id`),
  CONSTRAINT `FK_737wta28ow8eoxx43h8cxr0qo` FOREIGN KEY (`requests_id`) REFERENCES `request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchengine_request`
--

LOCK TABLES `searchengine_request` WRITE;
/*!40000 ALTER TABLE `searchengine_request` DISABLE KEYS */;
INSERT INTO `searchengine_request` VALUES (445,438),(445,439),(445,440),(445,441),(445,442),(445,443),(446,443);
/*!40000 ALTER TABLE `searchengine_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spamword`
--

DROP TABLE IF EXISTS `spamword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spamword` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hw2ujbfk0k77o2np1stk65lv2` (`word`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spamword`
--

LOCK TABLES `spamword` WRITE;
/*!40000 ALTER TABLE `spamword` DISABLE KEYS */;
INSERT INTO `spamword` VALUES (503,0,'sex'),(504,0,'love'),(505,0,'cocaine'),(506,0,'erotic'),(507,0,'porn'),(508,0,'idiot');
/*!40000 ALTER TABLE `spamword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h7c3dpu5vnkp8c2ks83sjsgf0` (`typeName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (416,0,'Dog'),(417,0,'Cat'),(418,0,'Other'),(419,0,'Bird'),(420,0,'Rabbit'),(421,0,'Hamster');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
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
  `enabled` bit(1) NOT NULL,
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
INSERT INTO `useraccount` VALUES (255,0,'','21232f297a57a5a743894a0e4a801fc3','admin'),(256,0,'','8a31cbaa76c0698ec83671dd948c59e1','animaniac1'),(257,0,'','2913052a775f05d3bbc9e2e12da162e5','animaniac2'),(258,0,'','2626de71d006db1b2a35e2041f90723e','animaniac3'),(259,0,'\0','af25ee3dda9da75826a1488dc7329ba9','animaniac4'),(260,0,'','97d678a6a378df89276cdd07e8e5cca2','animaniac5'),(261,0,'','966606b7a3fabe06b064ddf3eab72770','animaniac6'),(262,0,'','fde26df7b01a40f0e69134bf1a3e739e','animaniac7'),(263,0,'\0','a7c8d1f1423b4ff2266af2b631e1b974','animaniac8'),(264,0,'','ac7216c9d4d8ceded4b0095c33524eb3','partner1'),(265,0,'','60e118f9fbec7c1cfef851e5681137d1','partner2'),(266,0,'','2c6aea847954046ab7950d96436f8ad2','partner3'),(267,0,'','5b7d01fb2c49740b51c0491ba04de6e7','partner4'),(268,0,'','5ab3334753f4c831e1857b461c59fc3d','partner5'),(269,0,'','a09f5f394a31d1c28399bd0217d996fe','partner6'),(270,0,'','3cf03435173b08725ac3fc4e8df56aa2','vetvet1'),(271,0,'','d0ffaf357bcd2d4fa0dc4a79204e4c98','vetvet2'),(272,0,'','7b3bdfe513b2e39c2e28818a2ae79951','vetvet3'),(273,0,'','fff12ca5bb11e0c37cfed63db95c716e','vetvet4'),(274,0,'','58a781826102377dc7bbcc232b1f1615','vetvet5'),(275,0,'','7b56b3f8341bb67273f5690a6825e844','vetvet6');
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
INSERT INTO `useraccount_authorities` VALUES (255,'ADMINISTRATOR'),(256,'ANIMANIAC'),(257,'ANIMANIAC'),(258,'ANIMANIAC'),(259,'ANIMANIAC'),(260,'ANIMANIAC'),(261,'ANIMANIAC'),(262,'ANIMANIAC'),(263,'ANIMANIAC'),(264,'PARTNER'),(265,'PARTNER'),(266,'PARTNER'),(267,'PARTNER'),(268,'PARTNER'),(269,'PARTNER'),(270,'VET'),(271,'VET'),(272,'VET'),(273,'VET'),(274,'VET'),(275,'VET');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vet`
--

DROP TABLE IF EXISTS `vet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vet` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_61cwiq4v1q08k6gx5lgs6feqh` (`userAccount_id`),
  CONSTRAINT `FK_61cwiq4v1q08k6gx5lgs6feqh` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vet`
--

LOCK TABLES `vet` WRITE;
/*!40000 ALTER TABLE `vet` DISABLE KEYS */;
INSERT INTO `vet` VALUES (291,0,'vet1@acme.com','Vet1','+34000000000','Vet1',270,'Calle de los veterinarios 1ºA'),(292,0,'vet2@acme.com','Vet2','+34000000000','Vet2',271,'Calle de los veterinarios 2ºB'),(293,0,'vet3@acme.com','Vet3','+34000000000','Vet3',272,'Calle de los veterinarios 3ºC'),(294,0,'vet4@acme.com','Vet4','+34000000000','Vet4',273,'Calle de los veterinarios 4ºD'),(295,0,'vet5@acme.com','Vet5','+34000000000','Vet5',274,'Calle de los veterinarios 5ºA'),(296,0,'vet6@acme.com','Vet6','+34000000000','Vet6',275,'Calle de los veterinarios 6ºB');
/*!40000 ALTER TABLE `vet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-26 12:31:37

commit;

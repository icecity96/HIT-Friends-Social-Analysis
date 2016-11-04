-- MySQL dump 10.13  Distrib 5.7.15, for Win64 (x86_64)
--
-- Host: localhost    Database: HFSA
-- ------------------------------------------------------
-- Server version	5.7.15

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
-- Table structure for table `accesstoken`
--

DROP TABLE IF EXISTS `accesstoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accesstoken` (
  `userId` bigint(20) NOT NULL,
  `accessToken` varchar(60) DEFAULT NULL,
  `expireIn` varchar(60) DEFAULT NULL,
  `refreshToken` varchar(60) DEFAULT NULL,
  `uid` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accesstoken`
--

LOCK TABLES `accesstoken` WRITE;
/*!40000 ALTER TABLE `accesstoken` DISABLE KEYS */;
INSERT INTO `accesstoken` VALUES (1,NULL,NULL,NULL,NULL),(2,'2.00f1JfFGNDRL7D1d285423a1ONKyrC',NULL,NULL,NULL),(19,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `accesstoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `id` bigint(20) NOT NULL,
  `friendname` varchar(40) NOT NULL,
  `friendweibo` varchar(60) NOT NULL,
  `friendtianya` varchar(60) NOT NULL,
  UNIQUE KEY `id` (`id`,`friendname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `userId` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `createdAt` date NOT NULL,
  `text` varchar(100) NOT NULL,
  `inReplyToUserId` bigint(20) NOT NULL,
  `repostsCount` int(11) NOT NULL,
  `commentsCount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) DEFAULT NULL,
  `name` int(11) DEFAULT NULL,
  `img` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (3,6,8),(6,1,3),(1,7,3),(1,3,6),(1,8,2),(1,2,3);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(32) NOT NULL,
  `nickname` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `headImg` varchar(40) NOT NULL,
  `createTime` date DEFAULT NULL,
  `updateTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'lx','5221@qq.com','0a87b8c3a036b40fbbadff34dc9d2c96','cd5bb31404157f6f7af619d97299c4c0','2016-10-30','2016-10-30'),(2,'0a87b8c3a036b40fbbadff34dc9d2c96','lx','5221@qq.com','cd5bb31404157f6f7af619d97299c4c0','2016-10-30','2016-10-30'),(15,'!Lx1234!','x','123@qq.com','abcde123',NULL,NULL),(17,'!Lx1235!','xx','13@qq.com','abcd123',NULL,NULL),(19,'!Lx1255!','x1x','123@qq.com','abcd1323',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weiboandtianya`
--

DROP TABLE IF EXISTS `weiboandtianya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weiboandtianya` (
  `belongto` bigint(20) NOT NULL,
  `url` varchar(60) NOT NULL,
  `time` datetime NOT NULL,
  `context` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weiboandtianya`
--

LOCK TABLES `weiboandtianya` WRITE;
/*!40000 ALTER TABLE `weiboandtianya` DISABLE KEYS */;
/*!40000 ALTER TABLE `weiboandtianya` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-04 18:22:01

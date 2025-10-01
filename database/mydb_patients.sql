CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `age` int NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  CONSTRAINT `patients_chk_1` CHECK ((`age` between 16 and 120))
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Joyal John Chacko','joyal.chacko@example.com','9847001122',24,'Joyal@123','2025-09-28 15:30:44'),(2,'Nihal Ahmed','nihal.ahmed@example.com','9745612345',22,'Nihal@2025','2025-09-28 15:30:44'),(3,'Sinan Basheer','sinan.basheer@example.com','9633012345',23,'Sinan#321','2025-09-28 15:30:44'),(4,'Dheeraj K','dheeraj.k@example.com','9078123456',25,'Dheeraj@456','2025-09-28 15:30:44'),(5,'Fayaz Unas','fayaz.unas@example.com','9526123456',26,'Fayaz@789','2025-09-28 15:30:44'),(6,'Ameen Muhammed','ameen.muhammed@example.com','9447012345',27,'Ameen@999','2025-09-28 15:30:44'),(7,'Alwin Johnson','alwin.johnson@example.com','9895012345',24,'Alwin#123','2025-09-28 15:30:44'),(8,'Mithul Jith','mithul.jith@example.com','9744012345',23,'Mithul@111','2025-09-28 15:30:44'),(9,'Navaneetha Rajesh','navaneetha.rajesh@example.com','9495012345',22,'Navaneetha@222','2025-09-28 15:30:44'),(10,'Kiran Nandakumar','kiran.nandakumar@example.com','9400012345',28,'Kiran#333','2025-09-28 15:30:44'),(11,'Aryan KG','aryan.kg@example.com','9600012345',21,'Aryan@444','2025-09-28 15:30:44'),(12,'Manilal K','manilal.k@example.com','9743012345',32,'Manilal#555','2025-09-28 15:30:44'),(13,'Chinju Varghese','chinju.varghese@example.com','9897012345',29,'Chinju@666','2025-09-28 15:30:44'),(14,'Reshma Nair','reshma.nair@example.com','9742012345',25,'Reshma@777','2025-09-28 15:30:44'),(15,'Akhil Babu','akhil.babu@example.com','9448012345',27,'Akhil@888','2025-09-28 15:30:44'),(16,'Test Patient','test@example.com','1234567890',30,'a_strong_password','2025-09-28 16:28:31'),(17,'Manilal KM','manikundan@gmail.com','8590325475',56,'1@Soyalchacko','2025-09-28 16:41:28'),(18,'Manilal KM','manikundan@email.com','8590325475',56,'1@Soyalchacko','2025-09-28 16:41:53'),(19,'Manilal K','manilankundan@gmail.com','8590215484',58,'1@Joyalch','2025-09-28 16:47:35'),(20,'john doe','dniusiu@famol.com','5454444654445',39,'1@JJCjjc','2025-09-28 16:48:49'),(21,'ronaldo','ronaldo2006@gmail.com','77777777777',40,'1@ronaldO','2025-09-29 06:09:53'),(22,'binuvp','binuvp@gmail.com','8599989878',88,'Binukuttan1','2025-09-29 06:38:19'),(23,'Mammotty','mammotty@mohanlal.com','7893214560',77,'Mammotty@123','2025-10-01 16:42:07');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-01 22:26:11

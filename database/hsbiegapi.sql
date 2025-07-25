-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: localhost    Database: hsbiegapi
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alertsignal`
--

DROP TABLE IF EXISTS `alertsignal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alertsignal` (
  `id` bigint NOT NULL,
  `alarm_status` varchar(255) DEFAULT NULL,
  `alarm_type` varchar(255) DEFAULT NULL,
  `cur_date` date DEFAULT NULL,
  `energysource_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvhp03tdyuqh0i3yu93mj66oh` (`energysource_id`),
  CONSTRAINT `FKhvhp03tdyuqh0i3yu93mj66oh` FOREIGN KEY (`energysource_id`) REFERENCES `energysource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alertsignal`
--

LOCK TABLES `alertsignal` WRITE;
/*!40000 ALTER TABLE `alertsignal` DISABLE KEYS */;
/*!40000 ALTER TABLE `alertsignal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alertsignal_seq`
--

DROP TABLE IF EXISTS `alertsignal_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alertsignal_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alertsignal_seq`
--

LOCK TABLES `alertsignal_seq` WRITE;
/*!40000 ALTER TABLE `alertsignal_seq` DISABLE KEYS */;
INSERT INTO `alertsignal_seq` VALUES (1);
/*!40000 ALTER TABLE `alertsignal_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailyproduction`
--

DROP TABLE IF EXISTS `dailyproduction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dailyproduction` (
  `id` bigint NOT NULL,
  `cur_date` date DEFAULT NULL,
  `daily_prod_amount` double NOT NULL,
  `energysource_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmey9drv2ir14aaq2lt1itssqx` (`energysource_id`),
  CONSTRAINT `FKmey9drv2ir14aaq2lt1itssqx` FOREIGN KEY (`energysource_id`) REFERENCES `energysource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyproduction`
--

LOCK TABLES `dailyproduction` WRITE;
/*!40000 ALTER TABLE `dailyproduction` DISABLE KEYS */;
INSERT INTO `dailyproduction` VALUES (1,'2025-01-02',120,1),(2,'2025-01-03',140,1),(3,'2025-01-04',160,1),(4,'2025-01-05',180,1),(5,'2025-01-06',190,1),(6,'2025-01-06',120,2),(7,'2025-01-07',150,1),(8,'2025-01-07',180,2),(9,'2025-01-08',110,1),(10,'2025-01-08',90,2),(11,'2025-01-09',140,1),(12,'2025-01-09',150,2);
/*!40000 ALTER TABLE `dailyproduction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailyproduction_seq`
--

DROP TABLE IF EXISTS `dailyproduction_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dailyproduction_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyproduction_seq`
--

LOCK TABLES `dailyproduction_seq` WRITE;
/*!40000 ALTER TABLE `dailyproduction_seq` DISABLE KEYS */;
INSERT INTO `dailyproduction_seq` VALUES (1);
/*!40000 ALTER TABLE `dailyproduction_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL,
  `emp_contact_num` varchar(255) DEFAULT NULL,
  `emp_email` varchar(255) DEFAULT NULL,
  `emp_info` varchar(255) DEFAULT NULL,
  `emp_name` varchar(255) DEFAULT NULL,
  `emp_position` varchar(255) DEFAULT NULL,
  `emp_start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfwpnrd5tllegrevvpyir9uab3` (`emp_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'123456789','max@gmail.com','Very good Employee','Max Mustermann','Verwaltung',NULL),(2,'0987654321','mara@gmail.com','Has a deeper Knowledge of designing customer oriented Front ends','Mara Mustermann','Anlagenbau','2025-01-01'),(52,'1234509876','marcel@gmail.com','Full stack developer with deeper a knowledge of different kinds of Front and Back end Technologies','Marcel Mustermann','Au├ƒentechnick','2025-03-01'),(53,'1234512345','luka@gmail.com','Tech Lead with an excellenet Leading experience','Luka Ebmeier','Admin','2025-02-01'),(54,'1122334455','dario@gmail.com','Excellent Financial advisor','Darios Engelking','Admin','2025-02-01'),(252,NULL,'Test@test.com',NULL,'Test','Admin','2025-07-02');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_seq`
--

DROP TABLE IF EXISTS `employee_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_seq`
--

LOCK TABLES `employee_seq` WRITE;
/*!40000 ALTER TABLE `employee_seq` DISABLE KEYS */;
INSERT INTO `employee_seq` VALUES (351);
/*!40000 ALTER TABLE `employee_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `energysource`
--

DROP TABLE IF EXISTS `energysource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `energysource` (
  `id` bigint NOT NULL,
  `energy_available` double NOT NULL,
  `energy_capacity` double NOT NULL,
  `energy_type` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energysource`
--

LOCK TABLES `energysource` WRITE;
/*!40000 ALTER TABLE `energysource` DISABLE KEYS */;
INSERT INTO `energysource` VALUES (1,0,100000,'Solar','2025-01-01'),(2,0,500000,'Solar','2025-01-05');
/*!40000 ALTER TABLE `energysource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `energysource_employee`
--

DROP TABLE IF EXISTS `energysource_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `energysource_employee` (
  `energysource_id` bigint NOT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`energysource_id`,`employee_id`),
  KEY `FK9wx6ev2wyb7q32bgu7okorh4w` (`employee_id`),
  CONSTRAINT `FK9wx6ev2wyb7q32bgu7okorh4w` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKjby5crfaw3p3cm82e0fisuj36` FOREIGN KEY (`energysource_id`) REFERENCES `energysource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energysource_employee`
--

LOCK TABLES `energysource_employee` WRITE;
/*!40000 ALTER TABLE `energysource_employee` DISABLE KEYS */;
INSERT INTO `energysource_employee` VALUES (1,1),(1,2),(2,2),(1,52),(2,53),(2,54);
/*!40000 ALTER TABLE `energysource_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `energysource_location`
--

DROP TABLE IF EXISTS `energysource_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `energysource_location` (
  `energysource_id` bigint NOT NULL,
  `location_id` bigint NOT NULL,
  PRIMARY KEY (`energysource_id`,`location_id`),
  KEY `FKgf6gn09rcryog903d4ltwuewd` (`location_id`),
  CONSTRAINT `FK2dqp6nr3q7rqmww2idoq4bxm9` FOREIGN KEY (`energysource_id`) REFERENCES `energysource` (`id`),
  CONSTRAINT `FKgf6gn09rcryog903d4ltwuewd` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energysource_location`
--

LOCK TABLES `energysource_location` WRITE;
/*!40000 ALTER TABLE `energysource_location` DISABLE KEYS */;
INSERT INTO `energysource_location` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `energysource_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `energysource_seq`
--

DROP TABLE IF EXISTS `energysource_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `energysource_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energysource_seq`
--

LOCK TABLES `energysource_seq` WRITE;
/*!40000 ALTER TABLE `energysource_seq` DISABLE KEYS */;
INSERT INTO `energysource_seq` VALUES (101);
/*!40000 ALTER TABLE `energysource_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id` bigint NOT NULL,
  `loc_address` varchar(255) DEFAULT NULL,
  `loc_capacity` int NOT NULL,
  `loc_name` varchar(255) DEFAULT NULL,
  `loc_start_date` date DEFAULT NULL,
  `loc_status` varchar(255) DEFAULT NULL,
  `loc_latitude` varchar(255) DEFAULT NULL,
  `loc_longitude` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Hauptstra├ƒe 123, Berlin',1200,'Berlin Solar Plant','2023-05-01','Aktiv','52.5200','13.4050'),(2,'Nordstra├ƒe 55, Hamburg',950,'Hamburg Wind Park','2022-11-15','Aktiv','53.5511','9.9937');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_seq`
--

DROP TABLE IF EXISTS `location_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_seq`
--

LOCK TABLES `location_seq` WRITE;
/*!40000 ALTER TABLE `location_seq` DISABLE KEYS */;
INSERT INTO `location_seq` VALUES (1);
/*!40000 ALTER TABLE `location_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance`
--

DROP TABLE IF EXISTS `maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance` (
  `id` bigint NOT NULL,
  `maintenance_cost` double NOT NULL,
  `maintenance_date` date DEFAULT NULL,
  `maintenance_details` varchar(255) DEFAULT NULL,
  `maintenance_status` varchar(255) DEFAULT NULL,
  `energysource_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK749xpgv5nphqeqmnv8icv2a54` (`energysource_id`),
  CONSTRAINT `FK749xpgv5nphqeqmnv8icv2a54` FOREIGN KEY (`energysource_id`) REFERENCES `energysource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance`
--

LOCK TABLES `maintenance` WRITE;
/*!40000 ALTER TABLE `maintenance` DISABLE KEYS */;
/*!40000 ALTER TABLE `maintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance_seq`
--

DROP TABLE IF EXISTS `maintenance_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance_seq`
--

LOCK TABLES `maintenance_seq` WRITE;
/*!40000 ALTER TABLE `maintenance_seq` DISABLE KEYS */;
INSERT INTO `maintenance_seq` VALUES (1);
/*!40000 ALTER TABLE `maintenance_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint NOT NULL,
  `member_contribution` double NOT NULL,
  `member_email` varchar(255) DEFAULT NULL,
  `member_join_date` date DEFAULT NULL,
  `member_mat_number` varchar(255) DEFAULT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `member_payment_type` varchar(255) DEFAULT NULL,
  `member_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3orqjaukiw2b73e2gw8rer4rq` (`member_email`),
  UNIQUE KEY `UKjik4wass16ql0yt92talw2x15` (`member_mat_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,20000,'Darios@gmail.com','2025-06-25','12345678','Darios','Ueberweisung','AKTIVE'),(2,2000,'Luka@gmail.com','2025-06-25',NULL,'Luka',NULL,'AKTIVE'),(102,2000,'Dhanuka@gmail.com','2025-06-25',NULL,'Dhanuka',NULL,'DEAKTIVE');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_energysource`
--

DROP TABLE IF EXISTS `member_energysource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_energysource` (
  `member_id` bigint NOT NULL,
  `energysource_id` bigint NOT NULL,
  PRIMARY KEY (`member_id`,`energysource_id`),
  KEY `FKh2jva8g0g0dr89ach0akw801h` (`energysource_id`),
  CONSTRAINT `FKh2jva8g0g0dr89ach0akw801h` FOREIGN KEY (`energysource_id`) REFERENCES `energysource` (`id`),
  CONSTRAINT `FKhuiu3w161ulaw3cbh6fw769ng` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_energysource`
--

LOCK TABLES `member_energysource` WRITE;
/*!40000 ALTER TABLE `member_energysource` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_energysource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_seq`
--

DROP TABLE IF EXISTS `member_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_seq`
--

LOCK TABLES `member_seq` WRITE;
/*!40000 ALTER TABLE `member_seq` DISABLE KEYS */;
INSERT INTO `member_seq` VALUES (251);
/*!40000 ALTER TABLE `member_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation_status`
--

DROP TABLE IF EXISTS `operation_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation_status` (
  `id` bigint NOT NULL,
  `cur_date` date DEFAULT NULL,
  `op_status` varchar(255) DEFAULT NULL,
  `energysource_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr9eq0r2wb4w2a25rce1lo7kxh` (`energysource_id`),
  CONSTRAINT `FKr9eq0r2wb4w2a25rce1lo7kxh` FOREIGN KEY (`energysource_id`) REFERENCES `energysource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation_status`
--

LOCK TABLES `operation_status` WRITE;
/*!40000 ALTER TABLE `operation_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `operation_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation_status_seq`
--

DROP TABLE IF EXISTS `operation_status_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation_status_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation_status_seq`
--

LOCK TABLES `operation_status_seq` WRITE;
/*!40000 ALTER TABLE `operation_status_seq` DISABLE KEYS */;
INSERT INTO `operation_status_seq` VALUES (1);
/*!40000 ALTER TABLE `operation_status_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` bigint NOT NULL,
  `payment_amount` double NOT NULL,
  `payment_invoice_num` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK80uujv6vcwhagikotxoynv7ta` (`employee_id`),
  CONSTRAINT `FK80uujv6vcwhagikotxoynv7ta` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,2500,'123450987612345','Bank Transfer',1,'2025-01-31'),(2,2800,'123450987612345','Bank Transfer',1,'2025-02-28'),(3,3000,'123450987612345','Bank Transfer',1,'2025-03-31'),(4,2500,'098761234509876','Bank Transfer',2,'2025-01-31'),(5,2200,'098761234509876','Bank Transfer',2,'2025-02-28'),(6,3000,'098761234509876','Bank Transfer',2,'2025-03-31'),(7,4000,'135792468012345','Bank Transfer',53,'2025-02-28'),(8,4500,'135792468012345','Bank Transfer',53,'2025-03-31'),(9,3500,'214365870912345','Bank Transfer',54,'2025-02-28'),(10,3750,'214365870912345','Bank Transfer',54,'2025-03-31'),(11,4000,'123456789012345','Bank Transfer',52,'2025-03-31');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_seq`
--

DROP TABLE IF EXISTS `payment_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_seq`
--

LOCK TABLES `payment_seq` WRITE;
/*!40000 ALTER TABLE `payment_seq` DISABLE KEYS */;
INSERT INTO `payment_seq` VALUES (101);
/*!40000 ALTER TABLE `payment_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualification`
--

DROP TABLE IF EXISTS `qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualification` (
  `id` bigint NOT NULL,
  `qualification_type` varchar(255) DEFAULT NULL,
  `qualification_valid` date DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3r1dqywujwsb4fxx6dfqn8fv6` (`employee_id`),
  CONSTRAINT `FK3r1dqywujwsb4fxx6dfqn8fv6` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification`
--

LOCK TABLES `qualification` WRITE;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
INSERT INTO `qualification` VALUES (1,'AWS Cloud Certificate','2025-07-31',1),(2,'Oracle Certified Professional, Java SE 11 Developer','2027-08-01',1),(3,'Google Cloud Professional Developer','2029-08-01',1),(4,'Adobe Certified Expert (ACE)','2026-12-31',2),(5,'Meta Front-End Developer Professional Certificate','2026-12-31',2);
/*!40000 ALTER TABLE `qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualification_seq`
--

DROP TABLE IF EXISTS `qualification_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualification_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification_seq`
--

LOCK TABLES `qualification_seq` WRITE;
/*!40000 ALTER TABLE `qualification_seq` DISABLE KEYS */;
INSERT INTO `qualification_seq` VALUES (101);
/*!40000 ALTER TABLE `qualification_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_keeping`
--

DROP TABLE IF EXISTS `time_keeping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_keeping` (
  `id` bigint NOT NULL,
  `job` varchar(255) DEFAULT NULL,
  `work_day` date DEFAULT NULL,
  `work_hours` double NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2a69w4f1xb9lwoeon8j4cy0k8` (`employee_id`),
  CONSTRAINT `FK2a69w4f1xb9lwoeon8j4cy0k8` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_keeping`
--

LOCK TABLES `time_keeping` WRITE;
/*!40000 ALTER TABLE `time_keeping` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_keeping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_keeping_seq`
--

DROP TABLE IF EXISTS `time_keeping_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_keeping_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_keeping_seq`
--

LOCK TABLES `time_keeping_seq` WRITE;
/*!40000 ALTER TABLE `time_keeping_seq` DISABLE KEYS */;
INSERT INTO `time_keeping_seq` VALUES (1);
/*!40000 ALTER TABLE `time_keeping_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weatherdata`
--

DROP TABLE IF EXISTS `weatherdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weatherdata` (
  `id` bigint NOT NULL,
  `cur_date` date DEFAULT NULL,
  `highest_temp` double NOT NULL,
  `sun_hours` double NOT NULL,
  `location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1af9yf1ckdj2x62l2yew5rm16` (`location_id`),
  CONSTRAINT `FK1af9yf1ckdj2x62l2yew5rm16` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weatherdata`
--

LOCK TABLES `weatherdata` WRITE;
/*!40000 ALTER TABLE `weatherdata` DISABLE KEYS */;
INSERT INTO `weatherdata` VALUES (1,'2025-06-29',28,16,1),(2,'2025-06-29',23.2,16,2),(52,'2025-06-29',27.7,16,1),(53,'2025-06-29',23.6,16,2);
/*!40000 ALTER TABLE `weatherdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weatherdata_seq`
--

DROP TABLE IF EXISTS `weatherdata_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weatherdata_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weatherdata_seq`
--

LOCK TABLES `weatherdata_seq` WRITE;
/*!40000 ALTER TABLE `weatherdata_seq` DISABLE KEYS */;
INSERT INTO `weatherdata_seq` VALUES (151);
/*!40000 ALTER TABLE `weatherdata_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-17  0:17:17

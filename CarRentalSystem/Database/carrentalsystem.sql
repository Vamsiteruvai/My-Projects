-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: carrentalsystem
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `car_id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(30) NOT NULL,
  `price` double DEFAULT NULL,
  `colour` varchar(20) NOT NULL,
  `model` varchar(30) NOT NULL,
  `mileage` varchar(11) NOT NULL,
  `seating_capacity` int NOT NULL,
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'Toyata',4000000,'Black','Fortuner','12',5),(2,'Hyundai',1200000,'White','Creta','2',5),(3,'Tata',950000,'Grey','Nexon','9',5),(4,'Kia',1450000,'Red','Seltos','10',7),(5,'Mahindra',1800000,'Black','XUV700','12',5),(6,'Maruti',800000,'Blue','Baleno','8',5),(7,'Renault',700000,'Silver','Kiger','9',5),(8,'Ford',1300000,'White','EcoSport','10',7),(9,'Honda',1100000,'Red','Amaze','8',5),(10,'Skoda',1600000,'Black','Kushaq','10',7),(14,'MG',1700000,'Black','Astor','11',5),(16,'Toyata',900000,'White','Glanza G','22.3',5),(17,'Toyata',1150000,'Silver','Urban Cruiser','17.0',5),(18,'Toyata',1830000,'Red','Innova Crysta','15.6',7),(19,'Toyata',3200000,'Black','Fortuner','14.4',7);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent`
--

DROP TABLE IF EXISTS `rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rent` (
  `rent_id` int NOT NULL AUTO_INCREMENT,
  `rent_start_date` date NOT NULL,
  `rent_end_date` date NOT NULL,
  `total_days` int GENERATED ALWAYS AS ((to_days(`rent_end_date`) - to_days(`rent_start_date`))) STORED,
  `rent_price_per_day` double NOT NULL,
  `total_amount` double GENERATED ALWAYS AS ((`total_days` * `rent_price_per_day`)) STORED,
  `booked_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `pickupLocation` varchar(255) NOT NULL,
  `dropLocation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `car_name` varchar(45) NOT NULL,
  `user_id` int DEFAULT NULL,
  `model` varchar(50) NOT NULL,
  PRIMARY KEY (`rent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
INSERT INTO `rent` (`rent_id`, `rent_start_date`, `rent_end_date`, `rent_price_per_day`, `booked_on`, `pickupLocation`, `dropLocation`, `car_name`, `user_id`, `model`) VALUES (16,'2025-05-19','2025-05-30',208,'2025-05-19 13:19:48','Hyderbad','Chennai','Mahindra',16,''),(17,'2025-05-19','2025-05-27',302,'2025-05-19 13:48:56','hyd','ban','Fortuner',10,''),(18,'2025-05-19','2025-05-28',308,'2025-05-19 14:01:38','hyd','Chennai','Fortuner',16,''),(19,'2025-05-20','2025-05-30',173,'2025-05-20 04:53:41','kphb','srnagar','BMW',17,''),(20,'2025-12-02','2025-12-03',150,'2025-05-20 06:07:28','hyderabad','tenali','BMW',18,''),(21,'2025-03-17','2025-06-24',1500,'2025-05-20 06:17:50','kphb','ameerpet','BMW',19,''),(22,'2025-05-21','2025-05-23',2000,'2025-05-21 14:05:56','Hyderabad','Chennai','Mahindra',20,''),(23,'2025-05-22','2025-05-23',180,'2025-05-22 05:31:08','hyd','hyd','Fortuner',10,''),(24,'2025-05-22','2025-05-26',180,'2025-05-22 10:43:43','hyd','chennai','MG',16,''),(29,'2025-05-22','2025-05-25',2000,'2025-05-22 14:40:57','che','hyd','Kia',16,''),(30,'2025-05-22','2025-05-26',1500,'2025-05-22 14:43:36','chennai','Hyderbad','Maruti',16,''),(31,'2025-05-23','2025-05-28',1500,'2025-05-23 14:15:27','kbhp','sr nagar','Toyata',16,'Urban Cruiser'),(32,'2025-05-23','2025-05-29',1500,'2025-05-23 14:31:08','kbhp','madhapur','Hyundai',16,'Creta');
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `phone` bigint NOT NULL,
  `email` varchar(50) NOT NULL,
  `adhaar` bigint NOT NULL,
  `address` varchar(100) NOT NULL,
  `designation` varchar(50) NOT NULL,
  `password` varchar(25) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `adhaar` (`adhaar`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (10,'Thammineedi ','Tharunkumar',9014524499,'tharunchowdary802@gmail.com',901452449999,'7-88 kruthivennu','Software developer','Tharun@12345'),(15,'ramakrishna','K',9896324712,'rama@gmail.com',412563258741,'hyd','developer','@@@Rama123'),(16,'Vamsi','Teruvai',9963383840,'vamsi@gmail.com',775799992222,'kbhp,Hyderabad','Developer','@@@Vamsi143'),(17,'mukhesh','t',8765412345,'mukhesh123@gmail.com',823987654345,'nellore','student','Mukhesh@1319'),(18,'Rama','Krishna',8328689101,'sramakrishna1345@gmail.com',523456789125,'Tenali','student','ParanOrmal#44'),(19,'varsha','nancherla',8885006859,'varshanancherla24@gmail.com',946167693208,'cv corp','student','Varsha17$'),(20,'Raju','R',7894563815,'raju@gmail.com',564235984785,'Hyderabad','Developer','@@@Raju123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-23 21:17:23

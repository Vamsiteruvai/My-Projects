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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'Toyata',4000000,'Black','Fortuner','12',5),(2,'Hyundai',1200000,'White','Creta','2',5),(3,'Tata',950000,'Grey','Nexon','9',5),(4,'Kia',1450000,'Red','Seltos','10',7),(5,'Mahindra',1800000,'Black','XUV700','12',5),(6,'Maruti',800000,'Blue','Baleno','8',5),(7,'Renault',700000,'Silver','Kiger','9',5),(8,'Ford',1300000,'White','EcoSport','10',7),(9,'Honda',1100000,'Red','Amaze','8',5),(10,'Skoda',1600000,'Black','Kushaq','10',7),(14,'MG',1700000,'Black','Astor','11',5),(16,'Toyata',900000,'White','Glanza G','22.3',5),(17,'Toyata',1150000,'Silver','Urban Cruiser','17.0',5),(18,'Toyata',1830000,'Red','Innova Crysta','15.6',7),(19,'Toyata',3200000,'Black','Fortuner','14.4',7),(20,' Maruti ',50000,'white','Ertiga','10',7),(21,' Maruti ',50000,'white','Brezza','15',5),(22,' Maruti ',50000,'white','Grandvitara','18',5),(23,' Maruti ',50000,'white','Swift','10',5),(24,' Hyundai ',50000,'white','Verna','15',5),(25,' Hyundai ',50000,'white','Venue','15',5),(26,' Hyundai',50000,'white','i10','15',5),(27,' Hyundai',50000,'white','i20','18',5),(28,'Kia',50000,'white','Sonet','18',5),(29,'Kia',50000,'white','Carens','10',7),(30,'Kia',50000,'white','Syros','18',5),(31,'Kia',50000,'white','Carnival','10',7),(32,'Mahindra',50000,'white','Thar','7',5),(33,'Mahindra',50000,'white','Thar-Roxx','7',5),(34,'Mahindra',50000,'white','Bolero','10',5),(35,'Mahindra',50000,'white','Scorpio','8',7),(36,'Ford',50000,'white','Endeavour','10',7),(37,'Ford',50000,'white','Fiesta','10',5),(38,'Ford',50000,'white','Figo','10',5),(39,'Ford',50000,'white','Mustang','10',5),(40,'Skoda ',50000,'white','Slavia','12',5),(41,'Skoda ',50000,'white','Kylaq','13',5),(42,'Skoda ',50000,'white','Rapid','15',5),(43,'Skoda ',50000,'white','Octavia','18',5),(44,' Tata ',50000,'white','Punch','18',5),(45,' Tata ',50000,'white','Safari','10',7),(46,' Tata ',50000,'white','Harrier','10',7),(47,' Tata ',50000,'white','Tiago','18',5);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (10,'Thammineedi ','Tharunkumar',9014524499,'tharunchowdary802@gmail.com',901452449999,'7-88 kruthivennu','Software developer','Tharun@12345'),(16,'Vamsi','Teruvai',9963383840,'vamsi@gmail.com',775799992222,'kbhp,Hyderabad','Developer','@@@Vamsi143'),(17,'mukhesh','t',8765412345,'mukhesh123@gmail.com',823987654345,'nellore','student','Mukhesh@1319'),(18,'Rama','Krishna',8328689101,'sramakrishna1345@gmail.com',523456789125,'Tenali','student','ParanOrmal#44'),(19,'varsha','nancherla',8885006859,'varshanancherla24@gmail.com',946167693208,'cv corp','student','Varsha17$'),(21,'vamsi','',8867887653,'venu@gmail.com',987667893456,'hyd','student','@@@Venu123');
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

-- Dump completed on 2025-05-24 17:26:08

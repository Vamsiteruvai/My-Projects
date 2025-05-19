-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2025 at 04:34 PM
-- Server version: 8.0.36
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carrentalsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `car_id` int NOT NULL,
  `brand` varchar(30) NOT NULL,
  `price` double DEFAULT NULL,
  `colour` varchar(20) NOT NULL,
  `model` varchar(30) NOT NULL,
  `mileage` varchar(11) NOT NULL,
  `seating_capacity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`car_id`, `brand`, `price`, `colour`, `model`, `mileage`, `seating_capacity`) VALUES
(1, 'Toyata', 4000000, 'Black', 'Fortuner', '12', 3),
(2, 'Hyundai', 1200000, 'White', 'Creta', '2', 6),
(3, 'Tata', 950000, 'Grey', 'Nexon', '9', 6),
(4, 'Kia', 1450000, 'Red', 'Seltos', '10', 7),
(5, 'Mahindra', 1800000, 'Black', 'XUV700', '12', 6),
(6, 'Maruti', 800000, 'Blue', 'Baleno', '8', 5),
(7, 'Renault', 700000, 'Silver', 'Kiger', '9', 6),
(8, 'Ford', 1300000, 'White', 'EcoSport', '10', 7),
(9, 'Honda', 1100000, 'Red', 'Amaze', '8', 6),
(10, 'Skoda', 1600000, 'Black', 'Kushaq', '10', 7),
(14, 'MG', 1700000, 'White', 'Astor', '11', 6);

-- --------------------------------------------------------

--
-- Table structure for table `rent`
--

CREATE TABLE `rent` (
  `rent_id` int NOT NULL,
  `rent_start_date` date NOT NULL,
  `rent_end_date` date NOT NULL,
  `total_days` int GENERATED ALWAYS AS ((to_days(`rent_end_date`) - to_days(`rent_start_date`))) STORED,
  `rent_price_per_day` double NOT NULL,
  `total_amount` double GENERATED ALWAYS AS ((`total_days` * `rent_price_per_day`)) STORED,
  `booked_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `pickupLocation` varchar(255) NOT NULL,
  `dropLocation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `car_name` varchar(45) NOT NULL,
  `user_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rent`
--

INSERT INTO `rent` (`rent_id`, `rent_start_date`, `rent_end_date`, `rent_price_per_day`, `booked_on`, `pickupLocation`, `dropLocation`, `car_name`, `user_id`) VALUES
(5, '2025-02-12', '2025-03-12', 100, '2025-05-18 07:41:50', 'hyd', 'hyd', 'BMW', 11),
(6, '2025-03-01', '2025-01-06', 150, '2025-05-18 10:06:11', 'hyd', 'chennai', 'Fortuner', 0),
(7, '2025-03-04', '2025-04-04', 150, '2025-05-18 10:25:24', 'hyd', 'chennai', 'Fortuner', 11),
(8, '2025-05-18', '2025-05-22', 150, '2025-05-18 14:24:10', 'hyd', 'chennai', 'Fortuner', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `phone` bigint NOT NULL,
  `email` varchar(50) NOT NULL,
  `adhaar` bigint NOT NULL,
  `address` varchar(100) NOT NULL,
  `designation` varchar(50) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `first_name`, `last_name`, `phone`, `email`, `adhaar`, `address`, `designation`, `password`) VALUES
(10, 'Thammineedi ', 'Tharunkumar', 9014524499, 'tharunchowdary802@gmail.com', 901452449999, '7-88 kruthivennu', 'Software developer', 'Tharun@12345'),
(11, 'anil', 'anil', 9563217892, 'anil@gmail.com', 789456123214, 'hyd', 'aws', '@@@Anil143'),
(12, 'vanu', 'T', 7702870093, 'vanu@gmail.com', 789654123321, 'nellore', 'worker', '@@@Vanu123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`car_id`);

--
-- Indexes for table `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`rent_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `adhaar` (`adhaar`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `car_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `rent`
--
ALTER TABLE `rent`
  MODIFY `rent_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

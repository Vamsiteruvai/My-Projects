-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 09, 2025 at 09:27 AM
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
-- Database: `woodworks`
--

-- --------------------------------------------------------

--
-- Table structure for table `kart`
--

CREATE TABLE `kart` (
  `kart_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '0',
  `image_url` varchar(225) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `user_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int NOT NULL,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `order_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `product_name` varchar(150) NOT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `product_id`, `order_datetime`, `product_name`, `quantity`, `price`) VALUES
(8, 11, 26, '2025-02-05 08:20:21', 'Chair', 1, 1500),
(9, 10, 28, '2025-02-05 08:23:24', 'Window', 2, 32000),
(22, 12, 31, '2025-02-06 11:23:15', 'Dining Table', 3, 135000),
(23, 12, 27, '2025-02-06 11:23:15', 'Bench', 2, 4000),
(24, 12, 26, '2025-02-06 11:23:44', 'Chair', 1, 1500),
(25, 10, 27, '2025-02-06 12:01:15', 'Bench', 1, 2000),
(26, 10, 26, '2025-02-06 12:02:04', 'Chair', 1, 1500),
(27, 10, 28, '2025-02-06 12:13:30', 'Window', 1, 16000),
(28, 10, 29, '2025-02-06 12:17:01', 'Stool', 1, 14000),
(29, 10, 27, '2025-02-06 15:40:52', 'Bench', 1, 2000),
(30, 10, 28, '2025-02-06 15:46:52', 'Window', 1, 16000),
(31, 10, 26, '2025-02-09 12:11:02', 'Chair', 4, 6000);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int NOT NULL,
  `product_name` varchar(150) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `rating` decimal(2,1) DEFAULT NULL,
  `image_url` varchar(255) NOT NULL
) ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `product_name`, `price`, `rating`, `image_url`) VALUES
(25, 'Bed', 50000.00, 5.0, '1738660173416bed.jpg'),
(26, 'Chair', 1500.00, 4.0, '1738660202280chair.jpg'),
(27, 'Bench', 2000.00, 5.0, '1738660228093bench.jpg'),
(28, 'Window', 16000.00, 4.5, '1738660263072window.jpg'),
(29, 'Stool', 14000.00, 5.0, '1738660300632stools.jpg'),
(30, 'Door', 3000.00, 3.8, '1738660328924door.jpg'),
(31, 'Dining Table', 45000.00, 5.0, '1738660353365diningtable.jpg'),
(32, 'Sofa', 35000.00, 4.8, '1738660377467sofa.jpg'),
(35, 'Rack', 10000.00, 4.8, '1738829526788Racks.jpg'),
(36, 'Chair', 15000.00, 5.0, '1738829777737chairs.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rating` decimal(2,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `email`, `password`, `rating`) VALUES
(8, 'Teruvai Vamsi', 'v@gmail.com', 'vamsi123456', NULL),
(9, 'vanu', 'vanu@gmail.com', '789456123', NULL),
(10, 'vanu1', 'v2@gmail.com', 'vamsi123456', 5.0),
(11, 'siraj', 'vamsistyle76@gmail.com', 'siraj123', NULL),
(12, 'vamsi teruvai', 'pecpec899@gmail.com', 'Vamsi@123456', NULL),
(13, 'teruvai vanu', 'vamsiteruvai9@gmail.com', 'Vamsi@789456', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kart`
--
ALTER TABLE `kart`
  ADD PRIMARY KEY (`kart_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `user_id_idx` (`user_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kart`
--
ALTER TABLE `kart`
  MODIFY `kart_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kart`
--
ALTER TABLE `kart`
  ADD CONSTRAINT `kart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

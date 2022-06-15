-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2022 at 07:09 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbtelur`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbdata`
--

CREATE TABLE `tbdata` (
  `No` int(3) NOT NULL,
  `Hari` date NOT NULL,
  `Berat_total` varchar(5) NOT NULL,
  `Berat_ratarata` varchar(5) NOT NULL,
  `Jam` date NOT NULL,
  `Jumlah_telur` varchar(3) NOT NULL,
  `Keuntungan` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbtelur`
--

CREATE TABLE `tbtelur` (
  `Telur` int(3) NOT NULL,
  `Berat` varchar(5) NOT NULL,
  `Kondisi` varchar(10) NOT NULL,
  `Tanggal` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbtelur`
--

INSERT INTO `tbtelur` (`Telur`, `Berat`, `Kondisi`, `Tanggal`) VALUES
(1, '5', 'Buruk', '2022-06-14'),
(2, '5', 'Baik', '2022-06-14'),
(3, '3', 'Baik', '2022-06-14'),
(4, '2', 'Buruk', '2022-06-14'),
(5, '5', 'Buruk', '2022-06-14'),
(6, '2', 'Baik', '2022-06-14'),
(7, '5', 'Buruk', '2022-06-14'),
(8, '2', 'Baik', '2022-06-14'),
(9, '2', 'Buruk', '2022-06-14'),
(10, '3', 'Baik', '2022-06-14'),
(11, '5', 'Baik', '2022-06-14'),
(12, '3', 'Buruk', '2022-06-14'),
(13, '5', 'Buruk', '2022-06-14'),
(14, '2', 'Buruk', '2022-06-14'),
(15, '5', 'Baik', '2022-06-14'),
(16, '2', 'Buruk', '2022-06-14'),
(17, '2', 'Buruk', '2022-06-14'),
(18, '2.4', 'Buruk', '2022-06-14'),
(19, '2', 'Baik', '2022-06-14'),
(20, '4.5', 'Buruk', '2022-06-14');

-- --------------------------------------------------------

--
-- Table structure for table `tbuser`
--

CREATE TABLE `tbuser` (
  `id` int(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `last_login` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbuser`
--

INSERT INTO `tbuser` (`id`, `name`, `username`, `password`, `created_at`, `last_login`) VALUES
(1, 'Administrator', 'admin', '$2y$10$hRi1qju2KOeEPcBZ0wYfhu/PN5e9Wl.ddWeDTds8Uokad764X9D1a', '2021-08-14 23:22:33', '2022-06-14 23:57:03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbdata`
--
ALTER TABLE `tbdata`
  ADD PRIMARY KEY (`No`);

--
-- Indexes for table `tbtelur`
--
ALTER TABLE `tbtelur`
  ADD PRIMARY KEY (`Telur`);

--
-- Indexes for table `tbuser`
--
ALTER TABLE `tbuser`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbdata`
--
ALTER TABLE `tbdata`
  MODIFY `No` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbtelur`
--
ALTER TABLE `tbtelur`
  MODIFY `Telur` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tbuser`
--
ALTER TABLE `tbuser`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

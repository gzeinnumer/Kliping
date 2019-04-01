-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2019 at 06:07 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_koran`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_koran`
--

CREATE TABLE `tb_koran` (
  `koran_id` int(11) NOT NULL,
  `koran_nama` text NOT NULL,
  `koran_tanggal` date NOT NULL,
  `koran_jum_hal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_koran`
--

INSERT INTO `tb_koran` (`koran_id`, `koran_nama`, `koran_tanggal`, `koran_jum_hal`) VALUES
(27, 'aplikasi ini sudah siap 2halaman', '2019-03-26', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_koran_halaman`
--

CREATE TABLE `tb_koran_halaman` (
  `koran_id` int(11) NOT NULL,
  `nama_page` text NOT NULL,
  `hal_page` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_koran_halaman`
--

INSERT INTO `tb_koran_halaman` (`koran_id`, `nama_page`, `hal_page`) VALUES
(27, '27_1_2019-3-26.jpg', 1),
(27, '27_3_2019-3-26.jpg', 3),
(27, '27_4_2019-3-26.jpg', 4),
(27, '27_2_2019-3-26.jpg', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(31) NOT NULL,
  `user_email` text NOT NULL,
  `user_pass` text NOT NULL,
  `status` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`user_id`, `user_name`, `user_email`, `user_pass`, `status`) VALUES
(1, 'admin', 'admin@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'admin'),
(8, 'user', 'user@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'user'),
(9, 'user1@gmail.com', 'user1@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_koran`
--
ALTER TABLE `tb_koran`
  ADD PRIMARY KEY (`koran_id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_koran`
--
ALTER TABLE `tb_koran`
  MODIFY `koran_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

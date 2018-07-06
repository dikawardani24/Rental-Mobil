-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 06, 2018 at 05:43 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rental_mobil`
--
CREATE DATABASE IF NOT EXISTS `rental_mobil` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `rental_mobil`;

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `idcar` int(11) NOT NULL,
  `no_plat` varchar(45) NOT NULL,
  `warna` varchar(45) NOT NULL,
  `no_rangka` varchar(45) NOT NULL,
  `no_mesin` varchar(45) NOT NULL,
  `idmerk` int(11) NOT NULL,
  `harga_sewa` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`idcar`, `no_plat`, `warna`, `no_rangka`, `no_mesin`, `idmerk`, `harga_sewa`, `status`) VALUES
(2, 'B5464EHS', 'Silver', 'MH3SE8810GJ554667', 'T3R3EO618051', 1, '450000.0', 'Sedang Di Sewa'),
(3, 'B64644HHH', 'Hitam', '782473HG67364JH', 'JSDH647387878HH8', 2, '450000.0', 'Ada'),
(4, 'B6464GF', 'Silver', '782473HG67364JH', 'JSDH647387878HH8', 2, '450000.0', 'Ada'),
(5, 'B6464YT', 'Hitam', '782473HG67364JH', 'JSDH647387878HH8', 3, '450000.0', 'Ada');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `idkaryawan` int(11) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `alamat` varchar(45) NOT NULL,
  `jenis_kelamin` varchar(45) NOT NULL,
  `no_ktp` varchar(45) NOT NULL,
  `no_hp` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`idkaryawan`, `nama`, `alamat`, `jenis_kelamin`, `no_ktp`, `no_hp`) VALUES
(1, 'Dika Wardani', 'Kp, Bojong Sompok', 'Pria', '2084284', '089643158780'),
(3, 'Ismail', 'Bojong Gede', 'Pria', '1323434343433434', '089676569897'),
(4, 'Wahyudin', 'Blok M, Jakarta Selatan', 'Pria', '254346467588768', '08976646665'),
(5, 'Aksani', 'Bogor', 'Pria', '2454534646', '087864468787'),
(6, 'Agus', 'Ps. Minggu', 'Pria', '8475847583545', '089864667777'),
(7, 'Eka Dea', 'Bekasi', 'Wanita', '2745834758', '0897646464664'),
(8, 'Jeko', 'Bojong Gede', 'Pria', '84379738535', '08987577646464');

-- --------------------------------------------------------

--
-- Table structure for table `merk`
--

CREATE TABLE `merk` (
  `idmerk` int(11) NOT NULL,
  `nama` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `merk`
--

INSERT INTO `merk` (`idmerk`, `nama`) VALUES
(1, 'Mitshubisi'),
(2, 'Isuzu'),
(3, 'Toyota');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `idpelanggan` int(11) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `alamat` varchar(45) NOT NULL,
  `jenis_kelamin` varchar(45) NOT NULL,
  `no_ktp` varchar(45) NOT NULL,
  `no_hp` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`idpelanggan`, `nama`, `alamat`, `jenis_kelamin`, `no_ktp`, `no_hp`) VALUES
(1, 'Asep', 'dhkfjhskjdfdf', 'Pria', '1234', '782374928745'),
(3, 'Hasanudin', 'Bogor', 'Pria', '94759848735', '087867663453'),
(4, 'Ismail', 'Depok', 'Pria', '87954938589495', '089783874756'),
(5, 'Putri', 'Jakarta Selatan', 'Wanita', '8934757395749', '085878980987');

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE `pengembalian` (
  `idpengembalian` int(11) NOT NULL,
  `idsewa` int(11) NOT NULL,
  `idkaryawan` int(11) NOT NULL,
  `tgl_transaki` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `overtime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pengembalian`
--

INSERT INTO `pengembalian` (`idpengembalian`, `idsewa`, `idkaryawan`, `tgl_transaki`, `overtime`) VALUES
(1, 1, 1, '2018-07-06 02:56:22', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sewa`
--

CREATE TABLE `sewa` (
  `idsewa` int(11) NOT NULL,
  `tgl_transaki` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idcar` int(11) NOT NULL,
  `idpelanggan` int(11) NOT NULL,
  `idkaryawan` int(11) NOT NULL,
  `lama_sewa` int(11) NOT NULL,
  `total_tagihan` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sewa`
--

INSERT INTO `sewa` (`idsewa`, `tgl_transaki`, `idcar`, `idpelanggan`, `idkaryawan`, `lama_sewa`, `total_tagihan`) VALUES
(1, '2018-07-06 02:53:27', 2, 1, 1, 1, 43345),
(2, '2018-07-06 03:05:44', 2, 1, 1, 2, 86690);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `idkaryawan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`iduser`, `username`, `password`, `idkaryawan`) VALUES
(1, 'dw', '1f2121f36f817bd18540e5fa7de06f59', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`idcar`),
  ADD KEY `fk_car_merk_idx` (`idmerk`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`idkaryawan`);

--
-- Indexes for table `merk`
--
ALTER TABLE `merk`
  ADD PRIMARY KEY (`idmerk`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`idpelanggan`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`idpengembalian`),
  ADD KEY `fk_transaksi_pengembalian_karyawan1_idx` (`idkaryawan`),
  ADD KEY `fk_transaksi_pengembalian_transaksi_sewa1_idx` (`idsewa`);

--
-- Indexes for table `sewa`
--
ALTER TABLE `sewa`
  ADD PRIMARY KEY (`idsewa`),
  ADD KEY `fk_transaksi_car1_idx` (`idcar`),
  ADD KEY `fk_transaksi_sewa_karyawan1_idx` (`idkaryawan`),
  ADD KEY `fk_transaksi_sewa_pelanggan1_idx` (`idpelanggan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`),
  ADD KEY `fk_user_karyawan1_idx` (`idkaryawan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `idcar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `idkaryawan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `merk`
--
ALTER TABLE `merk`
  MODIFY `idmerk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `idpelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `pengembalian`
--
ALTER TABLE `pengembalian`
  MODIFY `idpengembalian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `sewa`
--
ALTER TABLE `sewa`
  MODIFY `idsewa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `fk_car_merk` FOREIGN KEY (`idmerk`) REFERENCES `merk` (`idmerk`) ON DELETE CASCADE;

--
-- Constraints for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD CONSTRAINT `fk_transaksi_pengembalian_karyawan1` FOREIGN KEY (`idkaryawan`) REFERENCES `karyawan` (`idkaryawan`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_transaksi_pengembalian_transaksi_sewa1` FOREIGN KEY (`idsewa`) REFERENCES `sewa` (`idsewa`) ON DELETE CASCADE;

--
-- Constraints for table `sewa`
--
ALTER TABLE `sewa`
  ADD CONSTRAINT `fk_transaksi_car1` FOREIGN KEY (`idcar`) REFERENCES `car` (`idcar`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_transaksi_sewa_karyawan1` FOREIGN KEY (`idkaryawan`) REFERENCES `karyawan` (`idkaryawan`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_transaksi_sewa_pelanggan1` FOREIGN KEY (`idpelanggan`) REFERENCES `pelanggan` (`idpelanggan`) ON DELETE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_karyawan1` FOREIGN KEY (`idkaryawan`) REFERENCES `karyawan` (`idkaryawan`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

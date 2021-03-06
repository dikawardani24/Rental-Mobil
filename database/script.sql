-- MySQL Script generated by MySQL Workbench
-- Min 20 Mei 2018 01:06:01  WIB
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema rental_mobil
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rental_mobil
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rental_mobil` DEFAULT CHARACTER SET utf8 ;
USE `rental_mobil` ;

-- -----------------------------------------------------
-- Table `rental_mobil`.`merk`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rental_mobil`.`merk` (
  `idmerk` INT NOT NULL AUTO_INCREMENT,
  `nama` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idmerk`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rental_mobil`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rental_mobil`.`car` (
  `idcar` INT NOT NULL AUTO_INCREMENT,
  `no_plat` VARCHAR(45) NOT NULL,
  `warna` VARCHAR(45) NOT NULL,
  `no_rangka` VARCHAR(45) NOT NULL,
  `no_mesin` VARCHAR(45) NOT NULL,
  `idmerk` INT NOT NULL,
  `harga_sewa` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcar`),
  INDEX `fk_car_merk_idx` (`idmerk` ASC),
  CONSTRAINT `fk_car_merk`
    FOREIGN KEY (`idmerk`)
    REFERENCES `rental_mobil`.`merk` (`idmerk`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rental_mobil`.`karyawan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rental_mobil`.`karyawan` (
  `idkaryawan` INT NOT NULL AUTO_INCREMENT,
  `nama` VARCHAR(45) NOT NULL,
  `alamat` VARCHAR(45) NOT NULL,
  `jenis_kelamin` VARCHAR(45) NOT NULL,
  `no_ktp` VARCHAR(45) NOT NULL,
  `no_hp` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idkaryawan`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rental_mobil`.`pelanggan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rental_mobil`.`pelanggan` (
  `idpelanggan` INT NOT NULL AUTO_INCREMENT,
  `nama` VARCHAR(45) NOT NULL,
  `alamat` VARCHAR(45) NOT NULL,
  `jenis_kelamin` VARCHAR(45) NOT NULL,
  `no_ktp` VARCHAR(45) NOT NULL,
  `no_hp` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpelanggan`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rental_mobil`.`sewa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rental_mobil`.`sewa` (
  `idsewa` INT NOT NULL AUTO_INCREMENT,
  `tgl_transaki` TIMESTAMP NOT NULL,
  `idcar` INT NOT NULL,
  `idpelanggan` INT NOT NULL,
  `idkaryawan` INT NOT NULL,
  `lama_sewa` INT NOT NULL,
  `total_tagihan` DOUBLE NOT NULL,
  PRIMARY KEY (`idsewa`),
  INDEX `fk_transaksi_car1_idx` (`idcar` ASC),
  INDEX `fk_transaksi_sewa_karyawan1_idx` (`idkaryawan` ASC),
  INDEX `fk_transaksi_sewa_pelanggan1_idx` (`idpelanggan` ASC),
  CONSTRAINT `fk_transaksi_car1`
    FOREIGN KEY (`idcar`)
    REFERENCES `rental_mobil`.`car` (`idcar`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_transaksi_sewa_karyawan1`
    FOREIGN KEY (`idkaryawan`)
    REFERENCES `rental_mobil`.`karyawan` (`idkaryawan`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_transaksi_sewa_pelanggan1`
    FOREIGN KEY (`idpelanggan`)
    REFERENCES `rental_mobil`.`pelanggan` (`idpelanggan`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rental_mobil`.`pengembalian`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rental_mobil`.`pengembalian` (
  `idpengembalian` INT NOT NULL AUTO_INCREMENT,
  `idsewa` INT NOT NULL,
  `idkaryawan` INT NOT NULL,
  `tgl_transaki` TIMESTAMP NOT NULL,
  `overtime` INT NOT NULL,
  PRIMARY KEY (`idpengembalian`),
  INDEX `fk_transaksi_pengembalian_karyawan1_idx` (`idkaryawan` ASC),
  INDEX `fk_transaksi_pengembalian_transaksi_sewa1_idx` (`idsewa` ASC),
  CONSTRAINT `fk_transaksi_pengembalian_karyawan1`
    FOREIGN KEY (`idkaryawan`)
    REFERENCES `rental_mobil`.`karyawan` (`idkaryawan`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_transaksi_pengembalian_transaksi_sewa1`
    FOREIGN KEY (`idsewa`)
    REFERENCES `rental_mobil`.`sewa` (`idsewa`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rental_mobil`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rental_mobil`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `idkaryawan` INT NOT NULL,
  PRIMARY KEY (`iduser`),
  INDEX `fk_user_karyawan1_idx` (`idkaryawan` ASC),
  CONSTRAINT `fk_user_karyawan1`
    FOREIGN KEY (`idkaryawan`)
    REFERENCES `rental_mobil`.`karyawan` (`idkaryawan`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

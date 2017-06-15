-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 15 Jun 2017 pada 04.49
-- Versi Server: 10.1.22-MariaDB
-- PHP Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `diskumal`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `bankumstatus`
--

CREATE TABLE `bankumstatus` (
  `idStatus` varchar(8) NOT NULL,
  `ketStatus` varchar(17) DEFAULT NULL,
  `kdPemilik` varchar(2) DEFAULT NULL,
  `Pemilik` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `bankumstatus`
--

INSERT INTO `bankumstatus` (`idStatus`, `ketStatus`, `kdPemilik`, `Pemilik`) VALUES
('00', '-', '01', 'Perdata Personel'),
('01', 'Penggugat', '01', 'Perdata Personel'),
('02', 'Pelawan', '01', 'Perdata Personel'),
('03', 'Tergugat', '01', 'Perdata Personel'),
('04', 'Terlawan', '01', 'Perdata Personel'),
('05', 'Pembanding', '01', 'Perdata Personel'),
('06', 'Terbanding', '01', 'Perdata Personel'),
('07', 'Pemohon Kasasi', '01', 'Perdata Personel'),
('08', 'Termohon Kasasi', '01', 'Perdata Personel'),
('09', 'Pemohon PK', '01', 'Perdata Personel'),
('0A', '-', '02', 'Perdata Dinas'),
('0B', '-', '03', 'Tun Personel'),
('0C', '-', '04', 'Tun Dinas'),
('10', 'Termohon PK', '01', 'Perdata Personel'),
('A0', 'Penggungat', '02', 'Perdata Dinas'),
('A1', 'Pelawan', '02', 'Perdata Dinas'),
('A2', 'Tergugat', '02', 'Perdata Dinas'),
('A3', 'Terlawan', '02', 'Perdata Dinas'),
('A4', 'Pembanding', '02', 'Perdata Dinas'),
('A5', 'Terbanding', '02', 'Perdata Dinas'),
('A6', 'Pemohon Kasasi', '02', 'Perdata Dinas'),
('A7', 'Termohon Kasasi', '02', 'Perdata Dinas'),
('A8', 'Pemohon PK', '02', 'Perdata Dinas'),
('A9', 'Termohon PK', '02', 'Perdata Dinas'),
('B0', 'Penggugat', '03', 'Tun Personel'),
('B1', 'Pelawan', '03', 'Tun Personel'),
('B2', 'Tergugat', '03', 'Tun Personel'),
('B3', 'Terlawan', '03', 'Tun Personel'),
('B4', 'Pembanding', '03', 'Tun Personel'),
('B5', 'Terbanding', '03', 'Tun Personel'),
('B6', 'Pemohon Kasasi', '03', 'Tun Personel'),
('B7', 'Termohon Kasasi', '03', 'Tun Personel'),
('B8', 'Pemohon PK', '03', 'Tun Personel'),
('B9', 'Termohon PK', '03', 'Tun Personel'),
('C0', 'Penggugat', '04', 'Tun Dinas'),
('C1', 'Pelawan', '04', 'Tun Dinas'),
('C2', 'Tergugat', '04', 'Tun Dinas'),
('C3', 'Terlawan', '04', 'Tun Dinas'),
('C4', 'Pembanding', '04', 'Tun Dinas'),
('C5', 'Terbanding', '04', 'Tun Dinas'),
('C6', 'Pemohon Kasasi', '04', 'Tun Dinas'),
('C7', 'Termohon Kasasi', '04', 'Tun Dinas'),
('C8', 'Pemohon PK', '04', 'Tun Dinas'),
('C9', 'Termohon PK', '04', 'Tun Dinas'),
('D1', 'Tersangka', '05', 'Pidana Militer'),
('D2', 'Terdakwa', '05', 'Pidana Militer'),
('E1', 'Pelapor', '06', 'Pidana Umum'),
('E2', 'Terlapor', '06', 'Pidana Umum'),
('E3', 'Tersangka', '06', 'Pidana Umum'),
('E4', 'Terdakwa', '06', 'Pidana Umum');

-- --------------------------------------------------------

--
-- Struktur dari tabel `bankumstatustingkat`
--

CREATE TABLE `bankumstatustingkat` (
  `id_status_tingkat` varchar(2) NOT NULL,
  `StatusTingkat` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `bankumstatustingkat`
--

INSERT INTO `bankumstatustingkat` (`id_status_tingkat`, `StatusTingkat`) VALUES
('01', 'Proses'),
('02', 'Selesai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `bankum_d_pidanamiliter`
--

CREATE TABLE `bankum_d_pidanamiliter` (
  `noUrut` int(11) NOT NULL,
  `idPidana` varchar(30) CHARACTER SET utf8 NOT NULL,
  `namaPers` varchar(30) CHARACTER SET utf8 NOT NULL,
  `Pangkat` varchar(30) CHARACTER SET utf8 NOT NULL,
  `Nrp` varchar(20) CHARACTER SET utf8 NOT NULL,
  `Satker` varchar(40) CHARACTER SET utf8 NOT NULL,
  `noSurat` varchar(30) CHARACTER SET utf8 NOT NULL,
  `tglDasar` date NOT NULL,
  `Dasar` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Permasalahan` text CHARACTER SET utf8 NOT NULL,
  `id_status_tingkat` varchar(2) CHARACTER SET utf8 NOT NULL,
  `kdTingkat` varchar(2) CHARACTER SET utf8 NOT NULL,
  `tglStatus` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `bankum_d_pidanamiliter`
--

INSERT INTO `bankum_d_pidanamiliter` (`noUrut`, `idPidana`, `namaPers`, `Pangkat`, `Nrp`, `Satker`, `noSurat`, `tglDasar`, `Dasar`, `Permasalahan`, `id_status_tingkat`, `kdTingkat`, `tglStatus`) VALUES
(0, 'diskum12/14/2016 11:07:46 PM', 'ARMAN', 'LETDA LAUT (KH)', '1111', 'Disinfolahtal', 'B.23', '2016-12-01', 'DASAR SURAT', 'ASASAS', '02', 'D1', '2016-12-02');

-- --------------------------------------------------------

--
-- Struktur dari tabel `bankum_jnstingkat`
--

CREATE TABLE `bankum_jnstingkat` (
  `kdTingkat` varchar(2) NOT NULL,
  `ketTingkat` varchar(16) DEFAULT NULL,
  `kdPemilik` varchar(2) DEFAULT NULL,
  `Pemilik` varchar(17) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `bankum_jnstingkat`
--

INSERT INTO `bankum_jnstingkat` (`kdTingkat`, `ketTingkat`, `kdPemilik`, `Pemilik`) VALUES
('01', 'NON LITIGASI', '01', 'Perdata Personel'),
('02', 'TINGKAT I', '01', 'Perdata Personel'),
('03', 'TINGKAT BANDING', '01', 'Perdata Personel'),
('04', 'TINGKAT KASASI', '01', 'Perdata Personel'),
('05', 'TINGKAT PK', '01', 'Perdata Personel'),
('A0', 'NON LITIGASI', '02', 'Perdata Dinas'),
('A1', 'TINGKAT I', '02', 'Perdata Dinas'),
('A2', 'TINGKAT BANDING', '02', 'Perdata Dinas'),
('A3', 'TINGKAT KASASI', '02', 'Perdata Dinas'),
('A4', 'TINGKAT PK', '02', 'Perdata Dinas'),
('B0', 'NON LITIGASI', '03', 'Tun Personel'),
('B1', 'TINGKAT I', '03', 'Tun Personel'),
('B2', 'TINGKAT BANDING', '03', 'Tun Personel'),
('B3', 'TINGKAT KASASI', '03', 'Tun Personel'),
('B4', 'TINGKAT PK', '03', 'Tun Personel'),
('C0', 'NON LITIGASI', '04', 'Tun Dinas'),
('C1', 'TINGKAT I', '04', 'Tun Dinas'),
('C2', 'TINGKAT BANDING', '04', 'Tun Dinas'),
('C3', 'TINGKAT KASASI', '04', 'Tun Dinas'),
('C4', 'TINGKAT PK', '04', 'Tun Dinas'),
('D0', 'PENYIDIKAN', '05', 'Pidana Militer'),
('D1', 'TINGKAT I', '05', 'Pidana Militer'),
('D2', 'TINGKAT BANDING', '05', 'Pidana Militer'),
('D3', 'TINGKAT KASASI', '05', 'Pidana Militer'),
('D4', 'TINGKAT PK', '05', 'Pidana Militer'),
('E0', 'PENYIDIKAN', '06', 'Pidana Umum'),
('E1', 'TINGKAT I', '06', 'Pidana Umum'),
('E2', 'TINGKAT BANDING', '06', 'Pidana Umum'),
('E3', 'TINGKAT KASASI', '06', 'Pidana Umum'),
('E4', 'TINGKAT PK', '06', 'Pidana Umum');

-- --------------------------------------------------------

--
-- Struktur dari tabel `bankum_pemiliktingkat`
--

CREATE TABLE `bankum_pemiliktingkat` (
  `kdPemilik` varchar(2) NOT NULL,
  `Pemilik` varchar(17) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `bankum_pemiliktingkat`
--

INSERT INTO `bankum_pemiliktingkat` (`kdPemilik`, `Pemilik`) VALUES
('01', 'Perdata Personel'),
('02', 'Perdata Dinas'),
('03', 'Tun Personel'),
('04', 'Tun Dinas'),
('05', 'Pidana Militer'),
('06', 'Pidana Umum');

-- --------------------------------------------------------

--
-- Struktur dari tabel `bankum_tundinas`
--

CREATE TABLE `bankum_tundinas` (
  `noUrut` int(11) NOT NULL,
  `idTundinas` varchar(50) CHARACTER SET utf8 NOT NULL,
  `lokasiDT` text CHARACTER SET utf8,
  `Dasar` int(100) DEFAULT NULL,
  `noSurat` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `tglDasar` date DEFAULT NULL,
  `Permasalahan` text CHARACTER SET utf8,
  `id_status_tingkat` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `kdTingkat` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `tglStatus` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `bankum_tundinasproses`
--

CREATE TABLE `bankum_tundinasproses` (
  `idRtundinas` int(11) NOT NULL,
  `idR` int(11) NOT NULL,
  `idTundinas` varchar(50) CHARACTER SET utf8 NOT NULL,
  `kdTingkat` varchar(2) CHARACTER SET utf8 NOT NULL,
  `tgl` date NOT NULL,
  `proses` text CHARACTER SET utf8 NOT NULL,
  `id_status_tingkat` varchar(2) CHARACTER SET utf8 NOT NULL,
  `ketStatusTingkat` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `bankum_tundinastingkat`
--

CREATE TABLE `bankum_tundinastingkat` (
  `idR` int(11) NOT NULL,
  `idTundinas` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `kdTingkat` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `idStatus` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `ketstat` varchar(350) CHARACTER SET utf8 DEFAULT NULL,
  `Keterangan` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  `File_lampiran` longblob,
  `id_status_tingkat` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `tglStatusAkhir` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `username` varchar(50) CHARACTER SET utf8 NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 NOT NULL,
  `role` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`username`, `password`, `role`) VALUES
('admin', 'admin123', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bankumstatus`
--
ALTER TABLE `bankumstatus`
  ADD PRIMARY KEY (`idStatus`),
  ADD KEY `bankumstatus_FK` (`kdPemilik`);

--
-- Indexes for table `bankumstatustingkat`
--
ALTER TABLE `bankumstatustingkat`
  ADD PRIMARY KEY (`id_status_tingkat`);

--
-- Indexes for table `bankum_d_pidanamiliter`
--
ALTER TABLE `bankum_d_pidanamiliter`
  ADD PRIMARY KEY (`idPidana`),
  ADD KEY `bankum_d_pidanamiliter_FK` (`id_status_tingkat`),
  ADD KEY `bankum_d_pidanamiliter_FK2` (`kdTingkat`);

--
-- Indexes for table `bankum_jnstingkat`
--
ALTER TABLE `bankum_jnstingkat`
  ADD PRIMARY KEY (`kdTingkat`),
  ADD KEY `bankum_jnstingkat_FK` (`kdPemilik`);

--
-- Indexes for table `bankum_pemiliktingkat`
--
ALTER TABLE `bankum_pemiliktingkat`
  ADD PRIMARY KEY (`kdPemilik`);

--
-- Indexes for table `bankum_tundinas`
--
ALTER TABLE `bankum_tundinas`
  ADD PRIMARY KEY (`idTundinas`),
  ADD KEY `bankum_tundinas_FK` (`id_status_tingkat`),
  ADD KEY `bankum_tundinas_FK2` (`kdTingkat`);

--
-- Indexes for table `bankum_tundinasproses`
--
ALTER TABLE `bankum_tundinasproses`
  ADD PRIMARY KEY (`idRtundinas`),
  ADD KEY `bankum_tundinasproses_FK` (`idR`),
  ADD KEY `bankum_tundinasproses_FK2` (`idTundinas`),
  ADD KEY `bankum_tundinasproses_FK3` (`kdTingkat`),
  ADD KEY `bankum_tundinasproses_FK4` (`id_status_tingkat`);

--
-- Indexes for table `bankum_tundinastingkat`
--
ALTER TABLE `bankum_tundinastingkat`
  ADD PRIMARY KEY (`idR`),
  ADD KEY `bankum_tundinastingkat_FK` (`kdTingkat`),
  ADD KEY `bankum_tundinastingkat_FK2` (`id_status_tingkat`),
  ADD KEY `bankum_tundinastingkat_FK3` (`idTundinas`),
  ADD KEY `bankum_tundinastingkat_FK4` (`idStatus`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`username`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `bankumstatus`
--
ALTER TABLE `bankumstatus`
  ADD CONSTRAINT `bankumstatus_FK` FOREIGN KEY (`kdPemilik`) REFERENCES `bankum_pemiliktingkat` (`KdPemilik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `bankum_d_pidanamiliter`
--
ALTER TABLE `bankum_d_pidanamiliter`
  ADD CONSTRAINT `bankum_d_pidanamiliter_FK` FOREIGN KEY (`id_status_tingkat`) REFERENCES `bankumstatustingkat` (`id_status_tingkat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bankum_d_pidanamiliter_FK2` FOREIGN KEY (`kdTingkat`) REFERENCES `bankum_jnstingkat` (`kdTingkat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `bankum_jnstingkat`
--
ALTER TABLE `bankum_jnstingkat`
  ADD CONSTRAINT `bankum_jnstingkat_FK` FOREIGN KEY (`kdPemilik`) REFERENCES `bankum_pemiliktingkat` (`KdPemilik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `bankum_tundinas`
--
ALTER TABLE `bankum_tundinas`
  ADD CONSTRAINT `bankum_tundinas_FK` FOREIGN KEY (`id_status_tingkat`) REFERENCES `bankumstatustingkat` (`id_status_tingkat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bankum_tundinas_FK2` FOREIGN KEY (`kdTingkat`) REFERENCES `bankum_jnstingkat` (`kdTingkat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `bankum_tundinasproses`
--
ALTER TABLE `bankum_tundinasproses`
  ADD CONSTRAINT `bankum_tundinasproses_FK` FOREIGN KEY (`idR`) REFERENCES `bankum_tundinastingkat` (`idR`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bankum_tundinasproses_FK2` FOREIGN KEY (`idTundinas`) REFERENCES `bankum_tundinas` (`idTundinas`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bankum_tundinasproses_FK3` FOREIGN KEY (`kdTingkat`) REFERENCES `bankum_jnstingkat` (`kdTingkat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bankum_tundinasproses_FK4` FOREIGN KEY (`id_status_tingkat`) REFERENCES `bankumstatustingkat` (`id_status_tingkat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `bankum_tundinastingkat`
--
ALTER TABLE `bankum_tundinastingkat`
  ADD CONSTRAINT `bankum_tundinastingkat_FK` FOREIGN KEY (`kdTingkat`) REFERENCES `bankum_jnstingkat` (`kdTingkat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bankum_tundinastingkat_FK2` FOREIGN KEY (`id_status_tingkat`) REFERENCES `bankumstatustingkat` (`id_status_tingkat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bankum_tundinastingkat_FK3` FOREIGN KEY (`idTundinas`) REFERENCES `bankum_tundinas` (`idTundinas`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bankum_tundinastingkat_FK4` FOREIGN KEY (`idStatus`) REFERENCES `bankumstatus` (`idStatus`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

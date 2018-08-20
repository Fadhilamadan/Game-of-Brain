-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 26 Jun 2016 pada 17.12
-- Versi Server: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbgame`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `dbsoal`
--

CREATE TABLE `dbsoal` (
  `idsoal` int(11) NOT NULL,
  `isi_soal` text NOT NULL,
  `jawaban_a` varchar(100) NOT NULL,
  `jawaban_b` varchar(100) NOT NULL,
  `jawaban_c` varchar(100) NOT NULL,
  `jawaban_d` varchar(100) NOT NULL,
  `jawaban` varchar(1) NOT NULL,
  `selected` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dbsoal`
--

INSERT INTO `dbsoal` (`idsoal`, `isi_soal`, `jawaban_a`, `jawaban_b`, `jawaban_c`, `jawaban_d`, `jawaban`, `selected`) VALUES
(1, 'Pusat Keuangan Amerika Serikat berada di?', 'New York', 'Washington DC', 'Chicago', 'Las Vegas', 'A', 0),
(2, 'Berikut ini adalah negara yang dilalui oleh\r\nPegunungan Alpen, kecuali...', 'Austria', 'Liechtenstein', 'Swiss', 'Hungaria', 'D', 0),
(3, 'Benua biru adalah sebutan bagi benua?', 'Asia', 'Eropa', 'Afrika', 'Amerika', 'B', 0),
(4, 'Ankara adalah ibukota dari negara?', 'Libanon', 'Bahrain', 'Turki', 'Qatar', 'C', 0),
(5, 'Hari perdamaian dunia diperingati setiap tanggal?', '1 Januari', '1 Februari', '1 Maret', '1 April', 'A', 0),
(6, 'Kereta api ditemukan oleh?', 'Civrac', 'Murdocks', 'Robert Fulton', 'Nikola Tesla', 'B', 0),
(7, 'Berikut ini adalah pelopor berdirinya ASEAN\r\nyang berasal dari negara Filipina, yaitu...', 'Adam Malik', 'Tun Abdul Razak', 'Thanat Khoman', 'Narcisco Ramos', 'D', 0),
(8, 'Benua kuning adalah sebutan bagi benua?', 'Asia', 'Eropa', 'Afrika', 'Amerika', 'A', 0),
(9, 'Kejuaraan sepak bola dunia (World Cup)\r\ndilaksanakan pertama kali pada tahun 1930 di\r\nUruguay dan juaranya adalah...', 'Italia', 'Inggris', 'Uruguay', 'Argentina', 'C', 0),
(10, 'Gubernur provinsi Jawa Timur yang pertama\r\nadalah...', 'Dr. Moedjani', 'R. Samadikun', 'R.T. Soeryo', 'Imam Utomo', 'C', 0),
(11, 'Suku Bugis berada di provinsi?', 'Sumatera Utara', 'Sulawesi Selatan', 'Kalimantan Timur', 'NTT', 'B', 0),
(12, 'O Ina Ni Keke adalah lagu daerah yang berasal\r\ndari...', 'Kalimantan Selatan', 'Sumatera Barat', 'Sulawesi Utara', 'Maluku', 'C', 0),
(13, 'Tanggal 14 Agustus diperingati sebagai hari?', 'Sumpah Pemuda', 'Kesaktian Pancasila', 'Pramuka', 'PMI', 'C', 0),
(14, 'Taman hutan raya Ir. Juanda terletak di provinsi?', 'Jawa Barat', 'Jawa Tengah', 'Jawa Timur', 'DKI Jakarta', 'A', 0),
(15, 'Pasukan perdamaian Indonesia dibawah bendera\r\nPBB yang diberi nama Pasukan Garuda I (yang\r\npertama) dikirim ke wilayah?', 'Kongo', 'Vietnam', 'Mesir', 'Iraq', 'C', 0),
(16, 'Kabinet yang dipimpin oleh Presiden Megawati\r\nSoekarnoputri dinamakan?', 'Reformasi Pembangunan', 'Indonesia Bersatu', 'Gotong Royong', 'Persatuan Nasional', 'C', 0),
(17, 'Rudi Hartono memenangi kejuaraan All England\r\nsebanyak?', '8 Kali', '9 Kali', '10 Kali', '11 Kali', 'A', 0),
(18, 'Danau yang terdalam di dunia dan terletak di\r\nSiberia yaitu...', 'Tanganyika', 'Baikal', 'Malawi', 'Superior', 'B', 0),
(19, 'Bunga nasional dari Negara Indonesia adalah...', 'Anggrek', 'Tulip', 'Melati', 'Teratai Biru', 'C', 0),
(20, 'Salah satu peninggalan sejarah adalah kitab\r\nRamayana yang dikarang oleh...', 'Empu Walmiki', 'Empu Kanwa', 'Empu Darmaja', 'Empu Sedah', 'A', 0),
(21, 'Museum Satria Mandala terletak di...', 'Surabaya', 'Bandung', 'Semarang', 'Jakarta', 'D', 0),
(22, 'Nama kantor berita dari negara Amerika Serikat\r\nadalah...', 'ANTARA', 'ANP', 'AN', 'AP', 'D', 0),
(23, 'Harbour Bridge terletak di Negara...', 'Inggris', 'Italia', 'Australia', 'Austria', 'C', 0),
(24, 'Berikut ini adalah nama negara-negara ASEAN\r\nyang berbentuk kerajaan, kecuali...', 'Thailand', 'Malaysia', 'Filipina', 'Brunei Darussalam', 'D', 0),
(25, 'Allah Akbar adalah lagu kebangsaan dari negara...', 'Libya', 'Saudi Arabia', 'Afghanistan', 'Suriah', 'A', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `lobby`
--

CREATE TABLE `lobby` (
  `no_lobby` int(11) NOT NULL,
  `Nama_lobby` varchar(32) NOT NULL,
  `jumlah_player` int(11) NOT NULL,
  `kapasitas` int(11) NOT NULL,
  `isActive` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `lobby`
--

INSERT INTO `lobby` (`no_lobby`, `Nama_lobby`, `jumlah_player`, `kapasitas`, `isActive`) VALUES
(1, 'Miracle', 0, 4, 0),
(2, 'Infinite', 0, 4, 0),
(6, 'hahaha', 0, 4, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(20) NOT NULL,
  `jumlahmain` int(11) NOT NULL,
  `no_lobby` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `highscore` float NOT NULL,
  `is_login` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`userid`, `username`, `password`, `jumlahmain`, `no_lobby`, `score`, `highscore`, `is_login`) VALUES
(1, 'billie', '123', 8, 0, 20, 60, 0),
(2, 'sonny', '123', 10, 0, 0, 10, 0),
(3, 'fadhil', '123', 0, 0, 0, 0, 0),
(4, 'faishal', '123', 1, 0, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dbsoal`
--
ALTER TABLE `dbsoal`
  ADD PRIMARY KEY (`idsoal`);

--
-- Indexes for table `lobby`
--
ALTER TABLE `lobby`
  ADD PRIMARY KEY (`no_lobby`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dbsoal`
--
ALTER TABLE `dbsoal`
  MODIFY `idsoal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `lobby`
--
ALTER TABLE `lobby`
  MODIFY `no_lobby` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

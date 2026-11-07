-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 02, 2026 at 01:44 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_shift2`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id_karyawan` int NOT NULL,
  `tanggal_mulai_bertugas` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id_karyawan`, `tanggal_mulai_bertugas`) VALUES
(1, '2024-01-15'),
(2, '2024-03-01');

-- --------------------------------------------------------

--
-- Table structure for table `detail_jasa_servis`
--

CREATE TABLE `detail_jasa_servis` (
  `id_detail` int NOT NULL,
  `harga_jasa_saat_servis` decimal(12,2) NOT NULL,
  `id_servis` int NOT NULL,
  `id_jasa` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `detail_jasa_servis`
--

INSERT INTO `detail_jasa_servis` (`id_detail`, `harga_jasa_saat_servis`, `id_servis`, `id_jasa`) VALUES
(1, '75000.00', 1, 1),
(2, '150000.00', 2, 2),
(3, '250000.00', 3, 3),
(4, '85000.00', 4, 4),
(5, '200000.00', 5, 5),
(6, '100000.00', 6, 6),
(7, '350000.00', 7, 7),
(8, '50000.00', 8, 8),
(9, '300000.00', 9, 9),
(10, '175000.00', 10, 10),
(11, '125000.00', 11, 11),
(12, '180000.00', 12, 12),
(13, '275000.00', 13, 13),
(14, '400000.00', 14, 14),
(15, '90000.00', 15, 15);

-- --------------------------------------------------------

--
-- Table structure for table `jasa_servis`
--

CREATE TABLE `jasa_servis` (
  `id_jasa` int NOT NULL,
  `nama_jasa` varchar(30) NOT NULL,
  `harga_jasa` decimal(12,2) NOT NULL,
  `deskripsi` varchar(255) DEFAULT NULL,
  `estimasi_menit` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `jasa_servis`
--

INSERT INTO `jasa_servis` (`id_jasa`, `nama_jasa`, `harga_jasa`, `deskripsi`, `estimasi_menit`) VALUES
(1, 'Ganti Oli Mesin', '75000.00', 'Penggantian oli mesin kendaraan', 30),
(2, 'Servis Rem', '150000.00', 'Pemeriksaan dan perbaikan sistem rem', 60),
(3, 'Tune Up', '250000.00', 'Pemeriksaan dan penyetelan mesin kendaraan', 120),
(4, 'Ganti Busi', '85000.00', 'Penggantian busi kendaraan', 30),
(5, 'Servis AC', '200000.00', 'Pemeriksaan dan perawatan sistem AC', 90),
(6, 'Ganti Filter Udara', '100000.00', 'Penggantian filter udara kendaraan', 45),
(7, 'Servis Transmisi', '350000.00', 'Pemeriksaan dan perawatan transmisi', 120),
(8, 'Ganti Aki', '50000.00', 'Pemeriksaan dan penggantian aki', 30),
(9, 'Servis Sistem Kemudi', '300000.00', 'Pemeriksaan sistem kemudi kendaraan', 90),
(10, 'Servis Kelistrikan', '175000.00', 'Pemeriksaan sistem kelistrikan kendaraan', 60),
(11, 'Servis Radiator', '125000.00', 'Pemeriksaan dan perawatan radiator', 60),
(12, 'Ganti Kampas Rem', '180000.00', 'Penggantian kampas rem kendaraan', 60),
(13, 'Servis Suspensi', '275000.00', 'Pemeriksaan dan perbaikan suspensi', 120),
(14, 'Servis Kopling', '400000.00', 'Pemeriksaan dan perawatan sistem kopling', 150),
(15, 'Ganti Wiper', '90000.00', 'Penggantian wiper kendaraan', 30);

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` int NOT NULL,
  `nama_karyawan` varchar(100) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status_aktif` enum('aktif','tidak aktif') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama_karyawan`, `no_hp`, `alamat`, `username`, `password`, `status_aktif`) VALUES
(1, 'Rudi Hartono', '081300000001', 'Jl. Merdeka No. 1', 'rudi', 'pass001', 'aktif'),
(2, 'Sinta Dewi', '081300000002', 'Jl. Merdeka No. 2', 'sinta', 'pass002', 'aktif'),
(3, 'Agus Setiawan', '081300000003', 'Jl. Merdeka No. 3', 'agus', 'pass003', 'aktif'),
(4, 'Dian Permata', '081300000004', 'Jl. Merdeka No. 4', 'dian', 'pass004', 'aktif'),
(5, 'Eko Prabowo', '081300000005', 'Jl. Merdeka No. 5', 'eko', 'pass005', 'aktif'),
(6, 'Fitri Amelia', '081300000006', 'Jl. Merdeka No. 6', 'fitri', 'pass006', 'aktif'),
(7, 'Galang Saputra', '081300000007', 'Jl. Merdeka No. 7', 'galang', 'pass007', 'aktif'),
(8, 'Hani Lestari', '081300000008', 'Jl. Merdeka No. 8', 'hani', 'pass008', 'aktif'),
(9, 'Irwan Maulana', '081300000009', 'Jl. Merdeka No. 9', 'irwan', 'pass009', 'aktif'),
(10, 'Jihan Putri', '081300000010', 'Jl. Merdeka No. 10', 'jihan', 'pass010', 'aktif'),
(11, 'Kevin Ramadhan', '081300000011', 'Jl. Merdeka No. 11', 'kevin', 'pass011', 'aktif'),
(12, 'Lina Safitri', '081300000012', 'Jl. Merdeka No. 12', 'lina', 'pass012', 'aktif'),
(13, 'Miko Firmansyah', '081300000013', 'Jl. Merdeka No. 13', 'miko', 'pass013', 'aktif'),
(14, 'Novi Andriani', '081300000014', 'Jl. Merdeka No. 14', 'novi', 'pass014', 'aktif'),
(15, 'Oscar Wijaya', '081300000015', 'Jl. Merdeka No. 15', 'oscar', 'pass015', 'aktif');

-- --------------------------------------------------------

--
-- Table structure for table `kendaraan`
--

CREATE TABLE `kendaraan` (
  `id_kendaraan` int NOT NULL,
  `no_polisi` varchar(15) NOT NULL,
  `tipe_kendaraan` varchar(50) NOT NULL,
  `no_rangka` varchar(30) NOT NULL,
  `no_mesin` varchar(30) NOT NULL,
  `tahun_produksi` int NOT NULL,
  `id_pelanggan` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kendaraan`
--

INSERT INTO `kendaraan` (`id_kendaraan`, `no_polisi`, `tipe_kendaraan`, `no_rangka`, `no_mesin`, `tahun_produksi`, `id_pelanggan`) VALUES
(1, 'KT 1234 AA', 'Honda Beat', 'MH1JFZ12345678901', 'JFZ12345678901', 2022, 1),
(2, 'KT 2345 AB', 'Honda Vario 125', 'MH1K2F12345678902', 'K2F12345678902', 2023, 2),
(3, 'KT 3456 AC', 'Honda Brio', 'MHRDD123456789003', 'L12B123456789003', 2022, 3),
(4, 'KT 4567 AD', 'Honda PCX 160', 'MH1KFZ12345678904', 'KFZ12345678904', 2024, 4),
(5, 'KT 5678 AE', 'Honda HR-V', 'MHRRV123456789005', 'L15B123456789005', 2023, 5),
(6, 'KT 6789 AF', 'Honda Scoopy', 'MH1JMZ12345678906', 'JMZ12345678906', 2022, 6),
(7, 'KT 7890 AG', 'Honda Vario 160', 'MH1K2F12345678907', 'K2F12345678907', 2024, 7),
(8, 'KT 8901 AH', 'Honda BR-V', 'MHRDG123456789008', 'L15Z123456789008', 2023, 8),
(9, 'KT 9012 AI', 'Honda CB150R', 'MH1KCZ12345678909', 'KCZ12345678909', 2021, 9),
(10, 'KT 0123 AJ', 'Honda CR-V', 'MHRRW123456789010', 'L15B123456789010', 2024, 10),
(11, 'KT 1122 AK', 'Honda Supra X 125', 'MH1JBP12345678911', 'JBP12345678911', 2021, 11),
(12, 'KT 2233 AL', 'Honda Mobilio', 'MHRDD123456789012', 'L15Z123456789012', 2022, 12),
(13, 'KT 3344 AM', 'Honda Genio', 'MH1JFT12345678913', 'JFT12345678913', 2023, 13),
(14, 'KT 4455 AN', 'Honda City Hatchback', 'MHRGM123456789014', 'L15Z123456789014', 2023, 14),
(15, 'KT 5566 AO', 'Honda CBR150R', 'MH1KYZ12345678915', 'KYZ12345678915', 2022, 15);

-- --------------------------------------------------------

--
-- Table structure for table `mekanik`
--

CREATE TABLE `mekanik` (
  `mekanik_id_karyawan` int NOT NULL,
  `spesialisasi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `mekanik`
--

INSERT INTO `mekanik` (`mekanik_id_karyawan`, `spesialisasi`) VALUES
(3, 'Mesin'),
(4, 'Kelistrikan'),
(5, 'Rem'),
(6, 'Transmisi'),
(7, 'Mesin'),
(8, 'Suspensi'),
(9, 'Kelistrikan'),
(10, 'Mesin');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `no_hp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_hp`) VALUES
(1, 'Andi Saputra', '81299998888'),
(2, 'Budi Santoso', '081234567802'),
(3, 'Citra Lestari', '081234567803'),
(4, 'Deni Pratama', '081234567804'),
(5, 'Eka Putri', '081234567805'),
(6, 'Fajar Ramadhan', '081234567806'),
(7, 'Gita Permata', '081234567807'),
(8, 'Hendra Wijaya', '081234567808'),
(9, 'Indah Sari', '081234567809'),
(10, 'Joko Susanto', '081234567810'),
(11, 'Kiki Amelia', '081234567811'),
(12, 'Lukman Hakim', '081234567812'),
(13, 'Maya Anggraini', '081234567813'),
(14, 'Nanda Prakoso', '081234567814'),
(15, 'Oki Firmansyah', '081234567815');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `sales_id_karyawan` int NOT NULL,
  `jumlah_unit_terjual` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`sales_id_karyawan`, `jumlah_unit_terjual`) VALUES
(11, 0),
(12, 1),
(13, 0),
(14, 2),
(15, 3);

-- --------------------------------------------------------

--
-- Table structure for table `servis`
--

CREATE TABLE `servis` (
  `id_servis` int NOT NULL,
  `tanggal_servis` date NOT NULL,
  `kilometer` int NOT NULL,
  `keluhan` varchar(255) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `jadwal_servis_berikutnya` date DEFAULT NULL,
  `id_kendaraan` int NOT NULL,
  `mekanik_id_karyawan` int NOT NULL,
  `id_transaksi` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `servis`
--

INSERT INTO `servis` (`id_servis`, `tanggal_servis`, `kilometer`, `keluhan`, `diagnosis`, `jadwal_servis_berikutnya`, `id_kendaraan`, `mekanik_id_karyawan`, `id_transaksi`) VALUES
(1, '2026-10-01', 12000, 'Mesin terasa kurang bertenaga', 'Pemeriksaan mesin dan sistem bahan bakar', '2027-01-01', 1, 5, 1),
(2, '2026-10-02', 18500, 'Rem terasa kurang pakem', 'Kampas rem mulai aus', '2027-01-02', 2, 6, 2),
(3, '2026-10-03', 22500, 'Motor bergetar saat berjalan', 'Perlu pemeriksaan sistem penggerak', '2027-01-03', 3, 7, 3),
(4, '2026-10-04', 30000, 'Transmisi terasa kasar', 'Oli transmisi perlu diganti', '2027-01-04', 4, 5, 4),
(5, '2026-10-05', 15000, 'Mesin sulit dinyalakan', 'Busi perlu diperiksa', '2027-01-05', 5, 6, 5),
(6, '2026-10-06', 27500, 'AC kurang dingin', 'Filter AC kotor', '2027-01-06', 6, 7, 6),
(7, '2026-10-07', 35000, 'Suara mesin berisik', 'Pemeriksaan komponen mesin', '2027-01-07', 7, 5, 7),
(8, '2026-10-08', 19000, 'Konsumsi bahan bakar meningkat', 'Sistem bahan bakar perlu diperiksa', '2027-01-08', 8, 6, 8),
(9, '2026-10-09', 42000, 'Kendaraan sulit dikendalikan', 'Pemeriksaan sistem kemudi', '2027-01-09', 9, 7, 9),
(10, '2026-10-10', 25000, 'Lampu kendaraan bermasalah', 'Pemeriksaan sistem kelistrikan', '2027-01-10', 10, 5, 10),
(11, '2026-10-11', 16500, 'Mesin cepat panas', 'Pemeriksaan sistem pendingin', '2027-01-11', 11, 6, 11),
(12, '2026-10-12', 31000, 'Rem mengeluarkan suara', 'Kampas rem perlu diganti', '2027-01-12', 12, 7, 12),
(13, '2026-10-13', 28000, 'Kendaraan kurang stabil', 'Pemeriksaan suspensi', '2027-01-13', 13, 5, 13),
(14, '2026-10-14', 45000, 'Mesin mengalami penurunan performa', 'Servis dan pemeriksaan mesin', '2027-01-14', 14, 6, 14),
(15, '2026-10-15', 20000, 'Suara kendaraan tidak normal', 'Pemeriksaan komponen kendaraan', '2027-01-15', 15, 7, 15);

-- --------------------------------------------------------

--
-- Table structure for table `sparepart`
--

CREATE TABLE `sparepart` (
  `id_sparepart` int NOT NULL,
  `nama_sparepart` varchar(100) NOT NULL,
  `harga` decimal(12,2) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `id_servis` int NOT NULL,
  `id_transaksi` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sparepart`
--

INSERT INTO `sparepart` (`id_sparepart`, `nama_sparepart`, `harga`, `satuan`, `id_servis`, `id_transaksi`) VALUES
(1, 'Oli Mesin', '75000.00', 'botol', 1, 1),
(2, 'Kampas Rem Depan', '120000.00', 'set', 2, 2),
(3, 'Busi', '35000.00', 'buah', 3, 3),
(4, 'Filter Udara', '85000.00', 'buah', 4, 4),
(5, 'Oli Transmisi', '65000.00', 'botol', 5, 5),
(6, 'Filter Oli', '45000.00', 'buah', 6, 6),
(7, 'Kampas Rem Belakang', '110000.00', 'set', 7, 7),
(8, 'Aki Motor', '450000.00', 'unit', 8, 8),
(9, 'Aki Mobil', '850000.00', 'unit', 9, 9),
(10, 'Lampu Depan', '95000.00', 'buah', 10, 10),
(11, 'Filter AC', '100000.00', 'buah', 11, 11),
(12, 'Radiator Coolant', '55000.00', 'botol', 12, 12),
(13, 'V-Belt', '150000.00', 'buah', 13, 13),
(14, 'Kampas Kopling', '350000.00', 'set', 14, 14),
(15, 'Wiper', '75000.00', 'buah', 15, 15);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `jenis_transaksi` varchar(50) NOT NULL,
  `diskon` decimal(12,2) NOT NULL,
  `total_bayar` decimal(12,2) NOT NULL,
  `metode_pembayaran` varchar(30) NOT NULL,
  `status_pembayaran` varchar(20) NOT NULL,
  `sales_id_karyawan` int NOT NULL,
  `id_pelanggan` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tanggal_transaksi`, `jenis_transaksi`, `diskon`, `total_bayar`, `metode_pembayaran`, `status_pembayaran`, `sales_id_karyawan`, `id_pelanggan`) VALUES
(1, '2026-10-01', 'Servis', '0.00', '350000.00', 'Tunai', 'Lunas', 11, 1),
(2, '2026-10-02', 'Servis, Sparepart', '10000.00', '515000.00', 'Transfer', 'Lunas', 12, 2),
(3, '2026-10-03', 'Sparepart', '0.00', '250000.00', 'Tunai', 'Lunas', 13, 3),
(4, '2026-10-04', 'Servis, Sparepart', '15000.00', '610000.00', 'QRIS', 'Lunas', 14, 4),
(5, '2026-10-05', 'Unit Kendaraan', '500000.00', '309500000.00', 'Transfer', 'Lunas', 15, 5),
(6, '2026-10-06', 'Servis', '0.00', '400000.00', 'Tunai', 'Lunas', 11, 6),
(7, '2026-10-07', 'Servis, Sparepart', '20000.00', '705000.00', 'QRIS', 'Lunas', 12, 7),
(8, '2026-10-08', 'Sparepart', '0.00', '225000.00', 'Transfer', 'Lunas', 13, 8),
(9, '2026-10-09', 'Servis, Unit Kendaraan', '750000.00', '35550000.00', 'Transfer', 'Lunas', 14, 9),
(10, '2026-10-10', 'Servis, Sparepart, Unit Kendaraan', '1000000.00', '244825000.00', 'Transfer', 'Lunas', 15, 10),
(11, '2026-10-11', 'Servis', '0.00', '250000.00', 'Tunai', 'Lunas', 11, 11),
(12, '2026-10-12', 'Sparepart, Unit Kendaraan', '500000.00', '20750000.00', 'Transfer', 'Lunas', 12, 12),
(13, '2026-10-13', 'Servis, Sparepart', '25000.00', '700000.00', 'QRIS', 'Lunas', 13, 13),
(14, '2026-10-14', 'Unit Kendaraan', '750000.00', '339250000.00', 'Transfer', 'Lunas', 14, 14),
(15, '2026-10-15', 'Servis, Sparepart, Unit Kendaraan', '1000000.00', '36325000.00', 'Transfer', 'Lunas', 15, 15);

-- --------------------------------------------------------

--
-- Table structure for table `unit_kendaraan`
--

CREATE TABLE `unit_kendaraan` (
  `id_unit` int NOT NULL,
  `tipe_kendaraan` varchar(50) NOT NULL,
  `warna` varchar(30) NOT NULL,
  `no_rangka` varchar(30) NOT NULL,
  `no_mesin` varchar(30) NOT NULL,
  `tahun_produksi` int NOT NULL,
  `status_unit` varchar(10) NOT NULL,
  `tanggal_masuk` date DEFAULT NULL,
  `harga` decimal(12,2) NOT NULL,
  `id_transaksi` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `unit_kendaraan`
--

INSERT INTO `unit_kendaraan` (`id_unit`, `tipe_kendaraan`, `warna`, `no_rangka`, `no_mesin`, `tahun_produksi`, `status_unit`, `tanggal_masuk`, `harga`, `id_transaksi`) VALUES
(1, 'Honda Brio', 'Putih', 'MHRDD123456789001', 'L12B123456789001', 2024, 'Tersedia', '2026-09-01', '185000000.00', 1),
(2, 'Honda Vario 125', 'Hitam', 'MH1K2F123456789002', 'K2F123456789002', 2024, 'Dipesan', '2026-09-03', '25000000.00', 2),
(3, 'Honda HR-V', 'Abu-abu', 'MHRRV123456789003', 'L15B123456789003', 2025, 'Dipesan', '2026-09-05', '380000000.00', 3),
(4, 'Honda Beat', 'Merah', 'MH1JFZ123456789004', 'JFZ123456789004', 2025, 'Tersedia', '2026-09-06', '19000000.00', 4),
(5, 'Honda BR-V', 'Putih', 'MHRDG123456789005', 'L15Z123456789005', 2024, 'Terjual', '2026-09-07', '310000000.00', 5),
(6, 'Honda Scoopy', 'Cream', 'MH1JMZ123456789006', 'JMZ123456789006', 2025, 'Tersedia', '2026-09-08', '24000000.00', 6),
(7, 'Honda PCX 160', 'Hitam', 'MH1KFZ123456789007', 'KFZ123456789007', 2025, 'Dipesan', '2026-09-09', '34000000.00', 7),
(8, 'Honda CR-V', 'Hitam', 'MHRRW123456789008', 'L15B123456789008', 2024, 'Tersedia', '2026-09-10', '450000000.00', 8),
(9, 'Honda CB150R', 'Merah', 'MH1KCZ123456789009', 'KCZ123456789009', 2024, 'Terjual', '2026-09-11', '36000000.00', 9),
(10, 'Honda Mobilio', 'Putih', 'MHRDD123456789010', 'L15Z123456789010', 2023, 'Terjual', '2026-09-12', '245000000.00', 10),
(11, 'Honda Supra X 125', 'Hitam', 'MH1JBP123456789011', 'JBP123456789011', 2024, 'Tersedia', '2026-09-13', '22000000.00', 11),
(12, 'Honda Genio', 'Biru', 'MH1JFT123456789012', 'JFT123456789012', 2025, 'Terjual', '2026-09-14', '21000000.00', 12),
(13, 'Honda CBR150R', 'Merah', 'MH1KYZ123456789013', 'KYZ123456789013', 2024, 'Dipesan', '2026-09-15', '39000000.00', 13),
(14, 'Honda City Hatchback', 'Putih', 'MHRGM123456789014', 'L15Z123456789014', 2025, 'Terjual', '2026-09-16', '340000000.00', 14),
(15, 'Honda ADV 160', 'Abu-abu', 'MH1KFZ123456789015', 'KFZ123456789015', 2025, 'Terjual', '2026-09-17', '37000000.00', 15);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id_karyawan`);

--
-- Indexes for table `detail_jasa_servis`
--
ALTER TABLE `detail_jasa_servis`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `fk_detail_jasa_servis_servis` (`id_servis`),
  ADD KEY `fk_detail_jasa_servis_jasa` (`id_jasa`);

--
-- Indexes for table `jasa_servis`
--
ALTER TABLE `jasa_servis`
  ADD PRIMARY KEY (`id_jasa`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `kendaraan`
--
ALTER TABLE `kendaraan`
  ADD PRIMARY KEY (`id_kendaraan`),
  ADD KEY `fk_kendaraan_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `mekanik`
--
ALTER TABLE `mekanik`
  ADD PRIMARY KEY (`mekanik_id_karyawan`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`sales_id_karyawan`);

--
-- Indexes for table `servis`
--
ALTER TABLE `servis`
  ADD PRIMARY KEY (`id_servis`),
  ADD UNIQUE KEY `unique_id_transaksi` (`id_transaksi`),
  ADD KEY `fk_servis_kendaraan` (`id_kendaraan`),
  ADD KEY `fk_servis_mekanik` (`mekanik_id_karyawan`);

--
-- Indexes for table `sparepart`
--
ALTER TABLE `sparepart`
  ADD PRIMARY KEY (`id_sparepart`),
  ADD KEY `fk_sparepart_servis` (`id_servis`),
  ADD KEY `fk_sparepart_transaksi` (`id_transaksi`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `fk_transaksi_sales` (`sales_id_karyawan`),
  ADD KEY `fk_transaksi_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `unit_kendaraan`
--
ALTER TABLE `unit_kendaraan`
  ADD PRIMARY KEY (`id_unit`),
  ADD UNIQUE KEY `unit_kendaraan_UN` (`id_transaksi`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_admin_karyawan` FOREIGN KEY (`admin_id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);

--
-- Constraints for table `detail_jasa_servis`
--
ALTER TABLE `detail_jasa_servis`
  ADD CONSTRAINT `fk_detail_jasa_servis_jasa` FOREIGN KEY (`id_jasa`) REFERENCES `jasa_servis` (`id_jasa`),
  ADD CONSTRAINT `fk_detail_jasa_servis_servis` FOREIGN KEY (`id_servis`) REFERENCES `servis` (`id_servis`);

--
-- Constraints for table `kendaraan`
--
ALTER TABLE `kendaraan`
  ADD CONSTRAINT `fk_kendaraan_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`);

--
-- Constraints for table `mekanik`
--
ALTER TABLE `mekanik`
  ADD CONSTRAINT `fk_mekanik_karyawan` FOREIGN KEY (`mekanik_id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `fk_sales_karyawan` FOREIGN KEY (`sales_id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);

--
-- Constraints for table `servis`
--
ALTER TABLE `servis`
  ADD CONSTRAINT `fk_servis_kendaraan` FOREIGN KEY (`id_kendaraan`) REFERENCES `kendaraan` (`id_kendaraan`),
  ADD CONSTRAINT `fk_servis_mekanik` FOREIGN KEY (`mekanik_id_karyawan`) REFERENCES `karyawan` (`id_karyawan`),
  ADD CONSTRAINT `fk_servis_transaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`);

--
-- Constraints for table `sparepart`
--
ALTER TABLE `sparepart`
  ADD CONSTRAINT `fk_sparepart_servis` FOREIGN KEY (`id_servis`) REFERENCES `servis` (`id_servis`),
  ADD CONSTRAINT `fk_sparepart_transaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_transaksi_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`),
  ADD CONSTRAINT `fk_transaksi_sales` FOREIGN KEY (`sales_id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);

--
-- Constraints for table `unit_kendaraan`
--
ALTER TABLE `unit_kendaraan`
  ADD CONSTRAINT `unit_kendaraan_transaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

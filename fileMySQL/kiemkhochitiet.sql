-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 14, 2021 lúc 02:09 PM
-- Phiên bản máy phục vụ: 10.4.19-MariaDB
-- Phiên bản PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanly_banhang_duan1`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `kiemkhochitiet`
--

CREATE TABLE `kiemkhochitiet` (
  `makiemkhochitiet` int(11) NOT NULL,
  `makiemkho` int(11) NOT NULL,
  `mahanghoa` int(11) NOT NULL,
  `soluongbandau` int(11) NOT NULL,
  `toncuoi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `kiemkhochitiet`
--

INSERT INTO `kiemkhochitiet` (`makiemkhochitiet`, `makiemkho`, `mahanghoa`, `soluongbandau`, `toncuoi`) VALUES
(10, 13, 50, 0, 40000),
(11, 14, 51, 0, 500),
(12, 15, 50, 40000, 400),
(13, 16, 52, 0, 800),
(14, 17, 53, 0, 200),
(15, 18, 54, 0, 10000),
(16, 19, 55, 0, 700),
(17, 20, 56, 0, 50),
(18, 21, 57, 0, 60),
(19, 22, 58, 0, 100),
(20, 23, 59, 0, 200),
(21, 24, 60, 0, 100),
(22, 25, 61, 0, 130),
(23, 26, 62, 0, 120),
(24, 27, 63, 0, 50),
(25, 28, 64, 0, 200),
(26, 29, 65, 0, 400),
(27, 30, 66, 0, 200),
(28, 31, 67, 0, 300),
(29, 32, 68, 0, 200),
(30, 33, 69, 0, 300),
(31, 34, 70, 0, 250),
(32, 35, 71, 0, 300),
(33, 36, 72, 0, 200),
(34, 37, 73, 0, 100),
(35, 38, 74, 0, 50),
(36, 39, 75, 0, 200),
(37, 40, 76, 0, 100),
(38, 41, 77, 0, 50),
(39, 42, 78, 0, 70),
(40, 43, 79, 0, 100);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `kiemkhochitiet`
--
ALTER TABLE `kiemkhochitiet`
  ADD PRIMARY KEY (`makiemkhochitiet`),
  ADD KEY `FK_KiemKho_KiemKhoChiTiet` (`makiemkho`),
  ADD KEY `FK_HangHoa_KiemKhoChiTiet` (`mahanghoa`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `kiemkhochitiet`
--
ALTER TABLE `kiemkhochitiet`
  MODIFY `makiemkhochitiet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `kiemkhochitiet`
--
ALTER TABLE `kiemkhochitiet`
  ADD CONSTRAINT `FK_HangHoa_KiemKhoChiTiet` FOREIGN KEY (`mahanghoa`) REFERENCES `hanghoa` (`idhanghoa`),
  ADD CONSTRAINT `FK_KiemKho_KiemKhoChiTiet` FOREIGN KEY (`makiemkho`) REFERENCES `kiemkho` (`makiemkho`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 14, 2021 lúc 02:08 PM
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
-- Cấu trúc bảng cho bảng `hanghoa`
--

CREATE TABLE `hanghoa` (
  `idhanghoa` int(11) NOT NULL,
  `idloaihang` int(11) NOT NULL,
  `tenhanghoa` varchar(50) NOT NULL,
  `vitri` varchar(15) NOT NULL,
  `hinhanh` varchar(530) NOT NULL,
  `soluong` int(11) NOT NULL,
  `giaban` double NOT NULL,
  `giavon` double NOT NULL,
  `dinhmuctonmin` int(11) NOT NULL,
  `dinhmuctonmax` int(11) NOT NULL,
  `ghichu` varchar(200) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hanghoa`
--

INSERT INTO `hanghoa` (`idhanghoa`, `idloaihang`, `tenhanghoa`, `vitri`, `hinhanh`, `soluong`, `giaban`, `giavon`, `dinhmuctonmin`, `dinhmuctonmax`, `ghichu`, `trangthai`) VALUES
(70, 34, 'Pin dự phòng eSaver ', 'A2', '865415891_1628935199.jpeg', 236, 250000, 230000, 5, 100, '', 0),
(71, 31, 'Tai nghe Bluetooth SamSung', 'A3', '45808178_1628935276.jpeg', 276, 300000, 240000, 2, 50, '', 0),
(72, 31, 'Tai nghe Xiaomi', 'A3', '893395082_1628935355.jpeg', 189, 350000, 310000, 10, 100, '', 0),
(74, 32, 'Xiaomi 6X', 'A1', '232859130_1628935506.jpeg', 40, 3800000, 3400000, 5, 50, '', 0),
(75, 32, 'Vsmart ', 'A1', '1281451199_1628935565.jpeg', 189, 4300000, 4000000, 5, 50, '', 0),
(76, 34, 'Pin dự phòng Anker', 'A3', '629042467_1628935787.jpeg', 84, 350000, 300000, 5, 50, '', 0),
(77, 32, 'Oppo A92', 'A1', '666165462_1628935869.jpeg', 35, 4500000, 4000000, 5, 50, '', 0),
(78, 32, 'ViVo A1', 'A1', '56009316_1628935968.jpeg', 55, 4100000, 3600000, 10, 60, '', 0),
(79, 31, 'Tai nghe Xiaomi', 'A2', '315299928_1628936030.jpeg', 85, 340000, 300000, 10, 100, '', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hanghoa`
--
ALTER TABLE `hanghoa`
  ADD PRIMARY KEY (`idhanghoa`),
  ADD KEY `idloaihang` (`idloaihang`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hanghoa`
--
ALTER TABLE `hanghoa`
  MODIFY `idhanghoa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hanghoa`
--
ALTER TABLE `hanghoa`
  ADD CONSTRAINT `FK_HangHoa_LoaiHang` FOREIGN KEY (`idloaihang`) REFERENCES `loaihang` (`idloaihang`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

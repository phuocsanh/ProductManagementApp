-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 14, 2021 lúc 02:10 PM
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
-- Cấu trúc bảng cho bảng `nhaphang`
--

CREATE TABLE `nhaphang` (
  `idnhaphang` int(11) NOT NULL,
  `idhanghoa` int(11) NOT NULL,
  `idnhanvien` int(11) NOT NULL,
  `soluongbandau` int(11) NOT NULL,
  `soluongnhap` int(11) NOT NULL,
  `thoigian` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhaphang`
--

INSERT INTO `nhaphang` (`idnhaphang`, `idhanghoa`, `idnhanvien`, `soluongbandau`, `soluongnhap`, `thoigian`) VALUES
(7, 51, 1, 494, 6, '2021-08-12 02:58:45'),
(8, 50, 1, 398, 2, '2021-08-12 02:59:09');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `nhaphang`
--
ALTER TABLE `nhaphang`
  ADD PRIMARY KEY (`idnhaphang`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `nhaphang`
--
ALTER TABLE `nhaphang`
  MODIFY `idnhaphang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

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
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makhachhang` int(11) NOT NULL,
  `tenkhachhang` varchar(25) NOT NULL,
  `sdtkhachhang` varchar(15) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makhachhang`, `tenkhachhang`, `sdtkhachhang`, `trangthai`) VALUES
(4, 'Trần Đức Sơn', '0987275387', 0),
(5, 'Nguyễn Hoàng Nguyên', '0919372684', 0),
(6, 'Phan Đức Hải', '0973261838', 0),
(7, 'Trần Thanh Sơn', '0907372289', 0),
(8, 'Trần Thanh Phong', '0987383606', 0),
(9, 'Huỳnh Ngọc Lam', '0773372890', 0),
(10, 'Hoàng Hải', '098274618', 0),
(11, 'Nguyễn Mạnh Cường', '0782484728', 0),
(12, 'Đỗ Văn Lâm', '0911838379', 0),
(13, 'Nguyễn Thị Ngọc Hà', '0938726189', 0),
(14, 'Lê Quang Long', '0773736188', 0),
(15, 'sanh', '', 1),
(16, 'sanhtran', '', 1),
(17, 'Trần Phước Hùng', '0987183726', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makhachhang`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makhachhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

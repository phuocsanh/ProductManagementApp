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
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `mahoadon` int(11) NOT NULL,
  `makhachhang` int(11) NOT NULL,
  `manhanvien` int(11) NOT NULL,
  `ghichu` varchar(250) NOT NULL,
  `thoigiantao` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`mahoadon`, `makhachhang`, `manhanvien`, `ghichu`, `thoigiantao`) VALUES
(88, 4, 1, '', '2020-01-14 10:06:38'),
(89, 4, 1, 'hit', '2020-02-14 10:08:23'),
(90, 5, 1, '', '2020-03-14 10:14:05'),
(91, 6, 1, 'hit', '2020-04-14 10:14:30'),
(92, 7, 1, 'hit', '2020-05-14 10:14:51'),
(93, 8, 1, 'hit', '2020-06-14 10:15:12'),
(94, 9, 1, 'hit', '2020-07-14 10:15:25'),
(95, 10, 1, 'hit', '2020-08-14 10:15:35'),
(96, 11, 1, 'hit', '2020-09-14 10:15:57'),
(97, 12, 1, 'hit', '2020-10-14 10:16:51'),
(98, 13, 1, 'hit', '2020-11-14 10:17:10'),
(99, 17, 1, 'hit', '2020-12-14 10:18:19'),
(100, 13, 1, 'hit', '2021-01-14 10:19:10'),
(101, 13, 1, 'hit', '2021-02-14 10:19:21'),
(102, 14, 1, 'hit', '2021-03-14 10:19:40'),
(103, 17, 1, 'hit', '2021-04-14 10:20:07'),
(104, 4, 1, 'hit', '2021-05-14 10:20:32'),
(105, 10, 1, 'hit', '2021-06-14 10:20:47'),
(106, 9, 1, 'hit', '2021-07-14 10:21:07'),
(107, 6, 1, 'hit', '2021-08-14 10:21:21'),
(108, 4, 1, 'hit', '2021-09-14 10:21:43'),
(109, 9, 1, 'hit', '2021-10-14 10:22:27'),
(110, 8, 1, 'hit', '2021-11-14 10:22:37'),
(111, 10, 1, 'hit', '2021-12-14 10:23:16'),
(112, 12, 1, 'hit', '2021-12-14 10:29:07');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahoadon`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `mahoadon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

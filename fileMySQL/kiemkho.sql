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
-- Cấu trúc bảng cho bảng `kiemkho`
--

CREATE TABLE `kiemkho` (
  `makiemkho` int(11) NOT NULL,
  `manhanvien` int(11) NOT NULL,
  `thoigian` timestamp NOT NULL DEFAULT current_timestamp(),
  `noitaophieu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `kiemkho`
--

INSERT INTO `kiemkho` (`makiemkho`, `manhanvien`, `thoigian`, `noitaophieu`) VALUES
(13, 1, '2021-08-12 00:32:57', 'tự động khi tạo mới hàng hóa'),
(14, 1, '2021-08-12 00:38:53', 'tự động khi tạo mới hàng hóa'),
(15, 1, '2021-08-12 00:39:22', 'tự động khi cập nhập hàng hóa'),
(16, 1, '2021-08-12 00:41:07', 'tự động khi tạo mới hàng hóa'),
(17, 1, '2021-08-12 00:47:41', 'tự động khi tạo mới hàng hóa'),
(18, 1, '2021-08-12 00:51:50', 'tự động khi tạo mới hàng hóa'),
(19, 1, '2021-08-12 00:55:26', 'tự động khi tạo mới hàng hóa'),
(20, 1, '2021-08-12 01:02:32', 'tự động khi tạo mới hàng hóa'),
(21, 1, '2021-08-12 01:04:44', 'tự động khi tạo mới hàng hóa'),
(22, 1, '2021-08-12 01:06:27', 'tự động khi tạo mới hàng hóa'),
(23, 1, '2021-08-12 01:08:33', 'tự động khi tạo mới hàng hóa'),
(24, 1, '2021-08-12 01:11:46', 'tự động khi tạo mới hàng hóa'),
(25, 1, '2021-08-12 01:13:29', 'tự động khi tạo mới hàng hóa'),
(26, 1, '2021-08-12 01:15:04', 'tự động khi tạo mới hàng hóa'),
(27, 1, '2021-08-12 02:36:06', 'tự động khi tạo mới hàng hóa'),
(28, 1, '2021-08-12 03:18:28', 'tự động khi tạo mới hàng hóa'),
(29, 1, '2021-08-14 04:12:37', 'tự động khi tạo mới hàng hóa'),
(30, 1, '2021-08-14 04:15:43', 'tự động khi tạo mới hàng hóa'),
(31, 1, '2021-08-14 09:54:25', 'tự động khi tạo mới hàng hóa'),
(32, 1, '2021-08-14 09:57:06', 'tự động khi tạo mới hàng hóa'),
(33, 1, '2021-08-14 09:58:46', 'tự động khi tạo mới hàng hóa'),
(34, 1, '2021-08-14 09:59:59', 'tự động khi tạo mới hàng hóa'),
(35, 1, '2021-08-14 10:01:16', 'tự động khi tạo mới hàng hóa'),
(36, 1, '2021-08-14 10:02:35', 'tự động khi tạo mới hàng hóa'),
(37, 1, '2021-08-14 10:03:55', 'tự động khi tạo mới hàng hóa'),
(38, 1, '2021-08-14 10:05:06', 'tự động khi tạo mới hàng hóa'),
(39, 1, '2021-08-14 10:06:05', 'tự động khi tạo mới hàng hóa'),
(40, 1, '2021-08-14 10:09:47', 'tự động khi tạo mới hàng hóa'),
(41, 1, '2021-08-14 10:11:09', 'tự động khi tạo mới hàng hóa'),
(42, 1, '2021-08-14 10:12:48', 'tự động khi tạo mới hàng hóa'),
(43, 1, '2021-08-14 10:13:50', 'tự động khi tạo mới hàng hóa');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `kiemkho`
--
ALTER TABLE `kiemkho`
  ADD PRIMARY KEY (`makiemkho`),
  ADD KEY `FK_KiemKho_NhanVien` (`manhanvien`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `kiemkho`
--
ALTER TABLE `kiemkho`
  MODIFY `makiemkho` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `kiemkho`
--
ALTER TABLE `kiemkho`
  ADD CONSTRAINT `FK_KiemKho_NhanVien` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manhanvien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

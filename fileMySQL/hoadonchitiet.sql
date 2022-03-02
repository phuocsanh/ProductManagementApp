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
-- Cấu trúc bảng cho bảng `hoadonchitiet`
--

CREATE TABLE `hoadonchitiet` (
  `mahoadonchitiet` int(11) NOT NULL,
  `mahoadon` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadonchitiet`
--

INSERT INTO `hoadonchitiet` (`mahoadonchitiet`, `mahoadon`, `masanpham`, `soluong`) VALUES
(35, 61, 50, 1),
(36, 61, 54, 1),
(37, 61, 51, 1),
(90, 88, 69, 1),
(91, 88, 68, 1),
(92, 88, 73, 1),
(93, 88, 67, 1),
(94, 89, 71, 1),
(95, 89, 74, 1),
(96, 89, 70, 1),
(97, 90, 78, 1),
(98, 90, 79, 1),
(99, 90, 76, 1),
(100, 91, 70, 2),
(101, 91, 71, 2),
(102, 91, 74, 3),
(103, 92, 71, 1),
(104, 92, 70, 1),
(105, 92, 72, 1),
(106, 93, 77, 2),
(107, 93, 75, 1),
(108, 93, 76, 1),
(109, 94, 79, 2),
(110, 94, 77, 1),
(111, 94, 78, 2),
(112, 95, 75, 1),
(113, 95, 76, 1),
(114, 95, 70, 1),
(115, 95, 72, 1),
(116, 95, 71, 1),
(117, 96, 71, 2),
(118, 96, 72, 2),
(119, 96, 74, 1),
(120, 96, 75, 1),
(121, 96, 76, 1),
(122, 97, 74, 1),
(123, 97, 76, 1),
(124, 97, 75, 1),
(125, 97, 77, 2),
(126, 98, 78, 2),
(127, 98, 77, 2),
(128, 98, 75, 1),
(129, 98, 79, 1),
(130, 99, 76, 1),
(131, 99, 77, 1),
(132, 99, 74, 1),
(133, 99, 75, 1),
(134, 100, 77, 1),
(135, 100, 78, 1),
(136, 100, 79, 1),
(137, 101, 76, 1),
(138, 101, 70, 1),
(139, 101, 71, 1),
(140, 101, 72, 1),
(141, 102, 75, 1),
(142, 102, 76, 2),
(143, 102, 78, 1),
(144, 102, 77, 2),
(145, 103, 79, 1),
(146, 103, 78, 2),
(147, 103, 76, 2),
(148, 103, 77, 1),
(149, 104, 77, 2),
(150, 104, 76, 1),
(151, 104, 74, 1),
(152, 104, 75, 2),
(153, 105, 79, 1),
(154, 105, 71, 1),
(155, 105, 72, 1),
(156, 106, 71, 7),
(157, 106, 72, 2),
(158, 106, 70, 5),
(159, 107, 78, 2),
(160, 107, 79, 3),
(161, 108, 77, 2),
(162, 108, 76, 2),
(163, 108, 79, 4),
(164, 108, 78, 1),
(165, 109, 77, 1),
(166, 109, 74, 1),
(167, 109, 75, 1),
(168, 110, 77, 1),
(169, 110, 78, 1),
(170, 110, 79, 1),
(171, 111, 72, 3),
(172, 111, 70, 3),
(173, 111, 71, 9),
(174, 112, 77, 1),
(175, 112, 76, 1),
(176, 112, 78, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hoadonchitiet`
--
ALTER TABLE `hoadonchitiet`
  ADD PRIMARY KEY (`mahoadonchitiet`),
  ADD KEY `FK_HoaDon_HoaDonChiTiet` (`mahoadon`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoadonchitiet`
--
ALTER TABLE `hoadonchitiet`
  MODIFY `mahoadonchitiet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=180;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hoadonchitiet`
--
ALTER TABLE `hoadonchitiet`
  ADD CONSTRAINT `FK_HoaDon_HoaDonChiTiet` FOREIGN KEY (`mahoadon`) REFERENCES `hoadon` (`mahoadon`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

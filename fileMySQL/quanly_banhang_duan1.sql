-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 14, 2021 lúc 02:06 PM
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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaihang`
--

CREATE TABLE `loaihang` (
  `idloaihang` int(11) NOT NULL,
  `tenloaihang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaihang`
--

INSERT INTO `loaihang` (`idloaihang`, `tenloaihang`) VALUES
(31, 'Phụ kiện điện thoại'),
(32, 'Điện thoại '),
(34, 'Pin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manhanvien` int(11) NOT NULL,
  `tendangnhap` varchar(25) NOT NULL,
  `matkhau` varchar(25) NOT NULL,
  `chucvu` varchar(15) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manhanvien`, `tendangnhap`, `matkhau`, `chucvu`, `trangthai`) VALUES
(1, 'admin', '1111', 'Admin', 0),
(9, 'quocvuong', '123456', 'Admin', 0),
(10, 'tanthanh', '1234', 'Admin', 0),
(11, 'phuocsanh', '123', 'Nhân viên', 0),
(12, 'hongquan', '123', 'Nhân viên', 0),
(13, 'anhtu', '123', 'Nhân viên', 0),
(14, 'sanh', '123', 'Nhân viên', 1),
(15, 'duong', '123', 'Nhân viên', 1),
(16, 'sanhtran', '123', 'Nhân viên', 0);

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
-- Chỉ mục cho bảng `hanghoa`
--
ALTER TABLE `hanghoa`
  ADD PRIMARY KEY (`idhanghoa`),
  ADD KEY `idloaihang` (`idloaihang`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahoadon`);

--
-- Chỉ mục cho bảng `hoadonchitiet`
--
ALTER TABLE `hoadonchitiet`
  ADD PRIMARY KEY (`mahoadonchitiet`),
  ADD KEY `FK_HoaDon_HoaDonChiTiet` (`mahoadon`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makhachhang`);

--
-- Chỉ mục cho bảng `kiemkho`
--
ALTER TABLE `kiemkho`
  ADD PRIMARY KEY (`makiemkho`),
  ADD KEY `FK_KiemKho_NhanVien` (`manhanvien`);

--
-- Chỉ mục cho bảng `kiemkhochitiet`
--
ALTER TABLE `kiemkhochitiet`
  ADD PRIMARY KEY (`makiemkhochitiet`),
  ADD KEY `FK_KiemKho_KiemKhoChiTiet` (`makiemkho`),
  ADD KEY `FK_HangHoa_KiemKhoChiTiet` (`mahanghoa`);

--
-- Chỉ mục cho bảng `loaihang`
--
ALTER TABLE `loaihang`
  ADD PRIMARY KEY (`idloaihang`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manhanvien`);

--
-- Chỉ mục cho bảng `nhaphang`
--
ALTER TABLE `nhaphang`
  ADD PRIMARY KEY (`idnhaphang`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hanghoa`
--
ALTER TABLE `hanghoa`
  MODIFY `idhanghoa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `mahoadon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- AUTO_INCREMENT cho bảng `hoadonchitiet`
--
ALTER TABLE `hoadonchitiet`
  MODIFY `mahoadonchitiet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=180;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makhachhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `kiemkho`
--
ALTER TABLE `kiemkho`
  MODIFY `makiemkho` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT cho bảng `kiemkhochitiet`
--
ALTER TABLE `kiemkhochitiet`
  MODIFY `makiemkhochitiet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT cho bảng `loaihang`
--
ALTER TABLE `loaihang`
  MODIFY `idloaihang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manhanvien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `nhaphang`
--
ALTER TABLE `nhaphang`
  MODIFY `idnhaphang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hanghoa`
--
ALTER TABLE `hanghoa`
  ADD CONSTRAINT `FK_HangHoa_LoaiHang` FOREIGN KEY (`idloaihang`) REFERENCES `loaihang` (`idloaihang`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadonchitiet`
--
ALTER TABLE `hoadonchitiet`
  ADD CONSTRAINT `FK_HoaDon_HoaDonChiTiet` FOREIGN KEY (`mahoadon`) REFERENCES `hoadon` (`mahoadon`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `kiemkho`
--
ALTER TABLE `kiemkho`
  ADD CONSTRAINT `FK_KiemKho_NhanVien` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manhanvien`);

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

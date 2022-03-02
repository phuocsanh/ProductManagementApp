<?php
require "config.php";
class DB_Functions
{
	private $db;
	function __construct()
	{
		$this->db=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
		mysqli_set_charset($this->db,"utf8");
	}
	
	function __destruct()
	{
	}
	
	//luu user va database
	public function storeUser($name)
	{
		$sql="INSERT INTO sinhvien
			(ten)VALUES 
			('$name')";
		$result=mysqli_query($this->db,$sql);
	}
	
	public function getAllItem(){
		$sql="select * from sinhvien";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
//	table loai hang
	public function getAllLoaiHang(){
		$sql="select * from loaihang";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function addLoaiHang($name)
	{
		$sql="insert into loaihang
		(tenloaihang) values
		('$name')";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}

	public function getLoaiHangByID($id)
	{
		$sql="select * from loaihang
				where idloaihang='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function updateLoaiHang($id,$name)
	{
		$sql="update loaihang set tenloaihang='$name'
			 where idloaihang='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function deleteLoaiHang($id)
	{
		$sql="delete from loaihang where idloaihang='$id' ";
		$result=mysqli_query($this->db,$sql);
		//ham affected_rows tra ve so record bi
		//anh huong boi cau lenh insert, update,delete
		return mysqli_affected_rows($this->db);
	}

// table hang hoa
	public function getAllHangHoa(){
		$sql="select * from hanghoa where trangthai = 0";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function getHangHoaByID($id)
	{
		$sql="select * from hanghoa
				where idhanghoa='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function addHangHoa($idHangHoa,$idLoaiHang,$tenHangHoa,$viTri,$hinhAnh,$soLuong,$giaBan,
								  $giaVon,$dinhMucTonMin,$dinhMucTonMax,$ghiChu)
	{
		$sql="insert into hanghoa (idhanghoa, idloaihang, tenhanghoa, vitri, hinhanh, soluong, giaban, giavon,
		dinhmuctonmin, dinhmuctonmax,ghichu) values ( '$idHangHoa','$idLoaiHang', '$tenHangHoa', '$viTri', 
		'$hinhAnh', '$soLuong', '$giaBan', '$giaVon', '$dinhMucTonMin', '$dinhMucTonMax','$ghiChu')";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function updateHangHoa($id,$idLoaiHang,$tenHangHoa,$viTri,$hinhAnh,$soLuong,$giaBan,
								  $giaVon,$dinhMucTonMin,$dinhMucTonMax,$ghiChu)
	{
		$sql="update hanghoa set idloaihang='$idLoaiHang',tenhanghoa = '$tenHangHoa',
		vitri='$viTri', hinhanh='$hinhAnh', soluong='$soLuong', giaban='$giaBan',
		giavon='$giaVon', dinhmuctonmin='$dinhMucTonMin', dinhmuctonmax='$dinhMucTonMax', ghichu='$ghiChu'
		where idhanghoa='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	public function updateSoLuongHangHoa($id,$soLuong)
	{
		$sql="update hanghoa set soluong='$soLuong'
		where idhanghoa='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	//khong update hinh
	public function updateHHKhongHinh($id,$idLoaiHang,$tenHangHoa,$viTri,$soLuong,$giaBan,
								  $giaVon,$dinhMucTonMin,$dinhMucTonMax,$ghiChu)
	{
		$sql="update hanghoa set idloaihang='$idLoaiHang',tenhanghoa = '$tenHangHoa',
		vitri='$viTri', soluong='$soLuong', giaban='$giaBan',
		giavon='$giaVon', dinhmuctonmin='$dinhMucTonMin', dinhmuctonmax='$dinhMucTonMax', 			ghichu='$ghiChu'
		where idhanghoa='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function deleteHangHoa($id)
	{
		$sql="UPDATE hanghoa
		SET trangthai= 1
		WHERE idhanghoa = '$id'";
		$result=mysqli_query($this->db,$sql);
		//ham affected_rows tra ve so record bi
		//anh huong boi cau lenh insert, update,delete
		return mysqli_affected_rows($this->db);
	}
		public function unRemoveHangHoa($id)
	{
		$sql="UPDATE hanghoa
		SET trangthai= 0
		WHERE idhanghoa = '$id'";
		$result=mysqli_query($this->db,$sql);
		return  $result;
		
	}
	
//table khach hang
	public function getAllKhachHang(){
		$sql="select * from khachhang where trangthai = 0";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	public function addKhachHang($name,$sdt)
	{
		$sql="insert into khachhang
		(tenkhachhang, sdtkhachhang) values
		('$name','$sdt')";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	public function updateKhachHang($id,$tenkhachhang,$sdt)
	{
		$sql="update khachhang set tenkhachhang='$tenkhachhang', sdtkhachhang = '$sdt'
			 where makhachhang='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	public function getKhachHangByID($id)
	{
		$sql="select * from khachhang
				where makhachhang='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	public function deleteKhachHang($id)
	{
		$sql="UPDATE khachhang
		SET trangthai= 1
		WHERE makhachhang = '$id'";
		$result=mysqli_query($this->db,$sql);
		return  $result;
	}
	
		public function unRemoveKhachHang($id)
	{
		$sql="UPDATE khachhang
		SET trangthai= 0
		WHERE makhachhang = '$id'";
		$result=mysqli_query($this->db,$sql);
		return  $result;
		
	}
//table nhan vien
	public function getAllNhanVien(){
		$sql="select * from nhanvien where trangthai = 0";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function addNhanVien($tendangnhap, $matkhau,$chucvu)
	{
		$sql="insert into nhanvien
		(tendangnhap,matkhau,chucvu) values
		('$tendangnhap','$matkhau','$chucvu')";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function updateNhanVien($id,$tendangnhap,$chucvu)
	{
		$sql="update nhanvien set tendangnhap='$tendangnhap', chucvu = '$chucvu'
			 where manhanvien='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	public function getNhanVienByID($id)
	{
		$sql="select * from nhanvien
				where manhanvien='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	public function deleteNhanVien($id)
	{
		$sql="UPDATE nhanvien
			SET trangthai= 1
			WHERE manhanvien = '$id'";
		$result=mysqli_query($this->db,$sql);
		return mysqli_affected_rows($this->db);
			mysqli_close($this->db);
	}
	public function unRemoveNhanVien($id)
	{
		$sql="UPDATE nhanvien
		SET trangthai= 0
		WHERE manhanvien = '$id'";
		$result=mysqli_query($this->db,$sql);
		//ham affected_rows tra ve so record bi
		//anh huong boi cau lenh insert, update,delete
		return mysqli_affected_rows($this->db);
	}
	
	public function updateMatKhau($id,$matkhau)
	{
		$sql="update nhanvien set matkhau='$matkhau'
			 where manhanvien='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
			mysqli_close($this->db);
	}
	
//table hoa don
	public function getAllHoaDon(){
		$sql="SELECT a.mahoadon, d.tenkhachhang, c.tendangnhap,a.ghichu,a.thoigiantao,SUM(e.giaban*b.soluong) as tongtien
		FROM hoadon a inner join hoadonchitiet b on a.mahoadon = b.mahoadon INNER JOIN nhanvien c ON a.manhanvien = c.manhanvien
        INNER JOIN khachhang d ON a.makhachhang = d.makhachhang  INNER JOIN hanghoa e ON b.masanpham = e.idhanghoa
        GROUP BY a.mahoadon";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	/*public function getGiaHoaDon($idhd){
		$sql = "select  sum(a.giaban*b.soluong)  as tonggia
		from hanghoa a inner join hoadonchitiet b on a.idhanghoa = b.masanpham
		inner join hoadon c on c.mahoadon = b.mahoadon where c.mahoadon ='$idhd' group by c.mahoadon";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}*/
	
	public function getHHtrongHoaDon($idhd){
		$sql = "SELECT c.idhanghoa, c.tenhanghoa, c.giaban, b.soluong
		FROM hoadon a inner join hoadonchitiet b on a.mahoadon = b.mahoadon 
        INNER JOIN hanghoa c on b.masanpham = c.idhanghoa
        WHERE a.mahoadon = '$idhd'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
		
//table hoa don chi tiet
	public function addHoaDonChiTiet($maHD,$maSP,$soLuong)
	{
		$sql="insert into hoadonchitiet
		(mahoadon,masanpham,soluong) values
		('$maHD','$maSP','$soLuong')";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function getAllHoaDonChiTiet(){
		$sql="select * from hoadonchitiet";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
		public function getHoaDonChiTietByIDHD($id)
	{
		$sql="select * from hoadonchitiet
				where mahoadon='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
//Login
	public function checkLogin($tendangnhap, $matkhau){
		$sql = "select * from nhanvien where tendangnhap = '$tendangnhap' 
		and matkhau = '$matkhau'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
//table kiem kho
	public function addKiemKho($maNV,$noiTaoPhieu)
	{
		$sql="insert into kiemkho
		(manhanvien,noitaophieu) values
		('$maNV','$noiTaoPhieu')";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function getAllKiemKho(){
		$sql="select * from kiemkho";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}

//table kiem kho chi tiet
	public function addKiemKhoChiTiet($maKK,$maHH,$soLuongBD, $tonCuoi)
	{
		$sql="insert into kiemkhochitiet
		(makiemkho,mahanghoa,soluongbandau,toncuoi) values
		('$maKK','$maHH','$soLuongBD','$tonCuoi')";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function getAllKiemKhoChiTiet(){
		$sql="select * from kiemkhochitiet";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
		public function getKiemKhoChiTietByIDHD($id)
	{
		$sql="select * from kiemkhochitiet
				where mahoadon='$id'";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	//lay hang hoa theo ma kiem kho
	public function getHHByKK($id){
		$sql = "select a.*, b.soluongbandau, b.toncuoi
		from hanghoa a inner join kiemkhochitiet b on a.idhanghoa = b.mahanghoa
		inner join kiemkho c on c.makiemkho = b.makiemkho
		where c.makiemkho ='$id'";
			$result=mysqli_query($this->db,$sql);
			return $result;
		}
	public function getDoanhThuLoiNhuan($tuNgay, $denNgay){
		$sql="select  sum(a.giaban* b.soluong)  as doanhthu,
		sum(a.giaban* b.soluong) - sum(a.giavon* b.soluong) as loinhuan
		from hanghoa a inner join hoadonchitiet b on a.idhanghoa = b.masanpham
		inner join hoadon c on c.mahoadon = b.mahoadon where date_format(c.thoigiantao , '%Y-%m-%d' )  between '$tuNgay' and   '$denNgay'";
		$result=mysqli_query($this->db,$sql);
		return $result;
			mysqli_close($this->db);
	}
	//nhap hang
	public function addNhapHang($maHH,$maNV,$slBanDau,$soluongnhap)
	{
		$sql="insert into nhaphang
		(idhanghoa,idnhanvien,soluongbandau,soluongnhap) values
		('$maHH','$maNV','$slBanDau','$soluongnhap')";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	public function getAllNhapHang(){
		$sql="SELECT a.idnhaphang, a.soluongbandau, a.soluongnhap, a.thoigian,b.idhanghoa, b.tenhanghoa,b.hinhanh, c.tendangnhap
		FROM nhaphang a inner join hanghoa b on a.idhanghoa = b.idhanghoa INNER JOIN nhanvien c ON a.idnhanvien = c.manhanvien";
		$result=mysqli_query($this->db,$sql);
		return $result;
	}
	
	//thong ke
	public function getDsNam(){
		$sql = "select date_format(thoigiantao , '%Y' ) as nam from hoadon group by date_format(thoigiantao, '%y') order by date_format(thoigiantao, '%Y') desc ";
		$result=mysqli_query($this->db,$sql);
		return $result;
		
	}
	public function getTop($nam){
		$sql = "select a.idhanghoa , a.tenhanghoa,  sum(b.soluong) as soluong 
			from hanghoa a inner join hoadonchitiet b on a.idhanghoa = b.masanpham
			inner join hoadon c on c.mahoadon = b.mahoadon where date_format(c.thoigiantao , '%Y' ) ='$nam'
 			group by a.idhanghoa order by soluong desc limit 10";
		$result=mysqli_query($this->db,$sql);
		return $result;
		
	}
	public function getDoanhThuLoiNhuan_Thang($nam, $thang){
		$sql="select  sum(a.giaban* b.soluong)  as doanhthu , sum(a.giaban*b.soluong) - sum(a.giavon*b.soluong) as loinhuan
from hanghoa a inner join hoadonchitiet b on a.idhanghoa = b.masanpham
inner join hoadon c on c.mahoadon = b.mahoadon where date_format(c.thoigiantao, '%Y') = '$nam' and date_format(c.thoigiantao, '%m') = '$thang'";
		$result=mysqli_query($this->db,$sql);
		return $result;
			mysqli_close($this->db);
	}
	
	public function getDoanhThuChart($nam){
		$sql = "select date_format(c.thoigiantao , '%m' ) as thang, sum(a.giaban * b.soluong)  as doanhthu 
from hanghoa a inner join hoadonchitiet b on a.idhanghoa = b.masanpham
inner join hoadon c on c.mahoadon = b.mahoadon where date_format(c.thoigiantao , '%Y' ) ='$nam' group by month(c.thoigiantao) ORDER BY month(c.thoigiantao) asc";
		$result=mysqli_query($this->db,$sql);
		return $result;		
	}
	
	public function getLoiNhuanChart($nam){
		$sql = "select date_format(c.thoigiantao , '%m' ) as thang, sum(a.giaban* b.soluong) - sum(a.giavon* b.soluong) as loinhuan from hanghoa a inner join hoadonchitiet b on a.idhanghoa = b.masanpham
inner join hoadon c on c.mahoadon = b.mahoadon where date_format(c.thoigiantao , '%Y' ) ='$nam' group by month(c.thoigiantao) ORDER BY month(c.thoigiantao) asc";
		$result=mysqli_query($this->db,$sql);
		return $result;
			
	}
	
}
?>
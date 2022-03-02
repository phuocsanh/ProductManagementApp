<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['idhanghoa']) && isset($_POST['idloaihang']) && isset($_POST['tenhanghoa']) &&
	  isset($_POST['vitri']) && isset($_POST['hinhanh']) && isset($_POST['soluong']) && isset($_POST['giaban']) &&
	  isset($_POST['giavon']) && isset($_POST['dinhmuctonmin']) && isset($_POST['dinhmuctonmax']) && isset($_POST['ghichu']))
	{
		$link="hinhsanpham/";
		$linkanh=rand()."_".time().".jpeg";
		$link=$link."/".$linkanh;
		
		$id = $_POST['idhanghoa'];
		$idLoaiHang=$_POST['idloaihang'];
		$tenHangHoa=$_POST['tenhanghoa'];
		$viTri=$_POST['vitri'];
		$hinhAnh=$_POST['hinhanh'];
		$soLuong=$_POST['soluong'];
		$giaBan=$_POST['giaban'];
		$giaVon=$_POST['giavon'];
		$dinhMucTonMin=$_POST['dinhmuctonmin'];
		$dinhMucTonMax=$_POST['dinhmuctonmax'];
		$ghiChu=$_POST['ghichu'];
		
		if($hinhAnh == ""){
			$result=$db->updateHHKhongHinh($id,$idLoaiHang,$tenHangHoa,$viTri,$soLuong,$giaBan,
								  $giaVon,$dinhMucTonMin,$dinhMucTonMax,$ghiChu);
		}else{
			$result=$db->updateHangHoa($id,$idLoaiHang,$tenHangHoa,$viTri,$linkanh,$soLuong,$giaBan,
								  $giaVon,$dinhMucTonMin,$dinhMucTonMax,$ghiChu);
		}
	
		
		if($result)
		{
			file_put_contents($link,base64_decode($hinhAnh));
			$json["thanhcong"]=1;
			$json["thongbao"]="cap nhap hang hoa thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="cap nhap hang hoa khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=2;
		$json["thongbao"]="chua nhap ";
	}

	echo json_encode($json);
?>
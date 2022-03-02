<?php
	require "include/config.php";
	$json = array();

	if(isset($_POST['idhanghoa']) && isset($_POST['idloaihang']) && isset($_POST['tenhanghoa']) &&
	  isset($_POST['vitri']) && isset($_POST['hinhanh']) && isset($_POST['soluong']) && isset($_POST['giaban']) &&
	  isset($_POST['giavon']) && isset($_POST['dinhmuctonmin']) && isset($_POST['dinhmuctonmax']) && isset($_POST['ghichu']))
	{
		$link="hinhsanpham/";
		$linkanh=rand()."_".time().".jpeg";
		$link=$link."/".$linkanh;
	
		$idHangHoa=$_POST['idhanghoa'];
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
		
		$con = mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
		$sql="insert into hanghoa (idhanghoa, idloaihang, tenhanghoa, vitri, hinhanh, soluong, giaban, giavon,
		dinhmuctonmin, dinhmuctonmax,ghichu) values ( '$idHangHoa','$idLoaiHang', '$tenHangHoa', '$viTri', 
		'$linkanh', '$soLuong', '$giaBan', '$giaVon', '$dinhMucTonMin', '$dinhMucTonMax','$ghiChu')";
		$result=mysqli_query($con,$sql);
		
		
		if($result)
		{
			file_put_contents($link,base64_decode($hinhAnh));
			$id = mysqli_insert_id($con);
			$json["thanhcong"]=1;
			$json["thongbao"]="them hang hoa thanh cong";
			$json["mahanghoa"]=$id;
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="them hang hoa khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap ";
	}

	echo json_encode($json);
?>
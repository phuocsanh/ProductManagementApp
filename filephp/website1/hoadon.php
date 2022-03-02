<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	$result = $db->getAllHoaDon();

	if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["hoadon"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$hoadon=array();
			$hoadon["mahoadon"]=$row["mahoadon"];
			$hoadon["tenkhachhang"]=$row["tenkhachhang"];
			$hoadon["tendangnhap"]=$row["tendangnhap"];
			$hoadon["ghichu"]=$row["ghichu"];
			$hoadon["thoigiantao"]=$row["thoigiantao"];
			$hoadon["tongtien"]=$row["tongtien"];
			
			array_push($json["hoadon"],$hoadon);
			
		}
		echo json_encode($json);
	}
?>
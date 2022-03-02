<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	$result = $db->getAllKhachHang();

	if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["khachhang"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$khachhang=array();
			$khachhang["makhachhang"]=$row["makhachhang"];
			$khachhang["tenkhachhang"]=$row["tenkhachhang"];
			$khachhang["sdtkhachhang"]=$row["sdtkhachhang"];
			
			array_push($json["khachhang"],$khachhang);
			
		}
		echo json_encode($json);
	}
?>
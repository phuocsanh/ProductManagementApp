<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	$result = $db->getAllHoaDonChiTiet();

	if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["hoadonchitiet"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$hoadonchitiet=array();
			$hoadonchitiet["mahoadonchitiet"]=$row["mahoadonchitiet"];
			$hoadonchitiet["mahoadon"]=$row["mahoadon"];
			$hoadonchitiet["masanpham"]=$row["masanpham"];
			$hoadonchitiet["soluong"]=$row["soluong"];
		
			array_push($json["hoadonchitiet"],$hoadonchitiet);
			
		}
		echo json_encode($json);
	}
?>
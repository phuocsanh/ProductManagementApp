<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	$result = $db->getAllLoaiHang();

	if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["loaihang"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$loaihang=array();
			$loaihang["idloaihang"]=$row["idloaihang"];
			$loaihang["tenloaihang"]=$row["tenloaihang"];
			
			array_push($json["loaihang"],$loaihang);
		}
		echo json_encode($json);
	}
?>
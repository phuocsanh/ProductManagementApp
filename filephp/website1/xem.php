<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	$result = $db->getAllItem();

	if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["sinhvien"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$sinhvien=array();
			$sinhvien["ma"]=$row["ma"];
			$sinhvien["ten"]=$row["ten"];
			
			array_push($json["sinhvien"],$sinhvien);
			
		}
		echo json_encode($json);
	}
?>
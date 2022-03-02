<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	$result = $db->getAllKiemKho();

	if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["kiemkho"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$kiemkho=array();
			$kiemkho["makiemkho"]=$row["makiemkho"];
			$kiemkho["manhanvien"]=$row["manhanvien"];
			$kiemkho["thoigian"]=$row["thoigian"];
			$kiemkho["noitaophieu"]=$row["noitaophieu"];
			
			array_push($json["kiemkho"],$kiemkho);
			
		}	
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="khong lay duoc du lieu";
	}
echo json_encode($json);
?>
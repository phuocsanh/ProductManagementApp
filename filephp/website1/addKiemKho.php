<?php
require "include/config.php";
	$json=array();
	
	if(isset($_POST['manhanvien']) && isset($_POST['noitaophieu']))
	{
		$nhanvien =$_POST['manhanvien'];
		$noitao =$_POST['noitaophieu'];
		
		$con = mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
		
		$sql="insert into kiemkho
		(manhanvien,noitaophieu) values ('$nhanvien','$noitao')";
		$result=mysqli_query($con,$sql);
		
		if($result)
		{			
			$id = mysqli_insert_id($con);
			$json["thanhcong"]=1;
			$json["thongbao"]="Thêm kiem kho thanh cong";
			$json["makiemkho"]=$id;
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="Thêm kiem kho không thành công";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="Chưa nhập";
	}

	echo json_encode($json);
?>

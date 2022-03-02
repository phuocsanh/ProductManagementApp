<?php
require "include/config.php";
	$json=array();
	if(isset($_POST['makhachhang']) && $_POST['manhanvien']!='')
	{
		$maKH=$_POST['makhachhang'];
		$maNV=$_POST['manhanvien'];
		$ghiChu=$_POST['ghichu'];

		$con = mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
		
		$sql="insert into hoadon
		(makhachhang,manhanvien,ghichu) values
		('$maKH','$maNV', '$ghiChu')";
		$result=mysqli_query($con,$sql);
		
		if($result)
		{
			$id = mysqli_insert_id($con);
			$json["thanhcong"]=1;
			$json["thongbao"]="tao thanh cong";
			$json["mahoadon"]=$id;
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="tao khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap";
	}

	echo json_encode($json);
?>

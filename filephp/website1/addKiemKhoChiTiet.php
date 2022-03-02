<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['makiemkho'])&& $_POST['mahanghoa']!='' && $_POST['soluongBD']!=''&& $_POST['toncuoi']!='')
	{
		$maKK=$_POST['makiemkho'];
		$maHH=$_POST['mahanghoa'];
		$soLuongBD=$_POST['soluongBD'];
		$tonCuoi=$_POST['toncuoi'];
		
		$result=$db->addKiemKhoChiTiet($maKK,$maHH,$soLuongBD,$tonCuoi);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="tao thanh cong";
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
		$json["thongbao"]="chua nhap ten";
	}

	echo json_encode($json);
?>

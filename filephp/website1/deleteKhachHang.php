<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['makhachhang'])&& $_POST['makhachhang']!='')
	{
		$id=$_POST['makhachhang'];
		$result=$db->deleteKhachHang($id);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="xoa khach hang thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="xoa khach hang khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap ma khach hang";
	}

	echo json_encode($json);
?>
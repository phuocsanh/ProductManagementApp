<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['makhachhang'])&& $_POST['makhachhang']!='')
	{
		$id=$_POST['makhachhang'];
		$result=$db->unRemoveKhachHang($id);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="xoa nhan vien thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="xoa nhan vien khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua co ma nhan vien";
	}

	echo json_encode($json);
?>
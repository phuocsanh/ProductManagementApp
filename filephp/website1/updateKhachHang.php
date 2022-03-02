<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['makhachhang']) && isset($_POST['tenkhachhang']))
	{
		$id = $_POST['makhachhang'];
		$name=$_POST['tenkhachhang'];
		$sdt=$_POST['sdtkhachhang'];
		$result=$db->updateKhachHang($id,$name,$sdt);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="cập nhật thông tin khách hàng thành công";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="cap nhap khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap ten khách hàng";
	}

	echo json_encode($json);
?>
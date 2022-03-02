<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['tenkhachhang'])&& $_POST['tenkhachhang']!='')
	{
		$name=$_POST['tenkhachhang'];
		$sdt =$_POST['sdtkhachhang'];
		$result=$db->addKhachHang($name,$sdt);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="Thêm khách hàng thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="Thêm khách hàng không thành công";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="Chưa nhập tên khách hàng";
	}

	echo json_encode($json);
?>

<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['manhanvien']) && isset($_POST['matkhau']))
	{
		$id = $_POST['manhanvien'];
		$matkhau=$_POST['matkhau'];
	
		$result=$db->updateMatKhau($id,$matkhau);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="thay đổi mật khẩu thành công";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="thay đổi mật khẩu không thành công";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chưa nhập tên đăng nhập hoặc mật khẩu";
	}

	echo json_encode($json);
?>
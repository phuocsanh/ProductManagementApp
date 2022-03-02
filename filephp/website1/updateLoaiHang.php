<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['idloaihang']) && isset($_POST['tenloaihang']))
	{
		$id = $_POST['idloaihang'];
		$name=$_POST['tenloaihang'];
		$result=$db->updateLoaiHang($id,$name);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="cap nhap loai hang thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="cap nhap loai hang khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap ten loai hang";
	}

	echo json_encode($json);
?>
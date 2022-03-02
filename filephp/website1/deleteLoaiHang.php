<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['idloaihang'])&& $_POST['idloaihang']!='')
	{
		$id=$_POST['idloaihang'];
		$result=$db->deleteLoaiHang($id);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="xoa loai hang thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="xoa loai hang khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap ten loai hang";
	}

	echo json_encode($json);
?>

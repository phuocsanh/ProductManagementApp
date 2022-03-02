<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['tenhanghoa'])&& $_POST['tenhanghoa']!='')
	{
		$name=$_POST['tenhanghoa'];
		$result=$db->addLoaiHang($name);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="tao loai hang thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="tao loai hang khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap ten loai hang";
	}

	echo json_encode($json);
?>

<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['mahanghoa'])&& isset($_POST['manhanvien'])&&isset($_POST['soluongbandau'])&&isset($_POST['soluongnhap']))
	{
		$mahanghoa=$_POST['mahanghoa'];
		$manhanvien =$_POST['manhanvien'];
		$soluongbandau =$_POST['soluongbandau'];
		$soluongnhap =$_POST['soluongnhap'];
		
		$result=$db->addNhapHang($mahanghoa,$manhanvien,$soluongbandau,$soluongnhap);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="Thêm thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="Thêm không thành công";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="Chưa nhập ";
	}

	echo json_encode($json);
?>

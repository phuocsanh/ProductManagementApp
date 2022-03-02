<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['mahoadon'])&& $_POST['masanpham']!='' && $_POST['soluong']!='')
	{
		$maHD=$_POST['mahoadon'];
		$maSP=$_POST['masanpham'];
		$soLuong=$_POST['soluong'];
		
		$result=$db->addHoaDonChiTiet($maHD,$maSP,$soLuong);
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

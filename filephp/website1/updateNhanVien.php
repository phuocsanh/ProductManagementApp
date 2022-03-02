<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['manhanvien']) && isset($_POST['tendangnhap']) && isset($_POST['chucvu']))
	{
		$id = $_POST['manhanvien'];
		$name=$_POST['tendangnhap'];
		$chucvu=$_POST['chucvu'];
		$result=$db->updateNhanVien($id,$name,$chucvu);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="cap nhap nhan vien thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="cap nhap nhan vien khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap ten dang nhap or mat khau or chuc vu nhan vien";
	}

	echo json_encode($json);
?>
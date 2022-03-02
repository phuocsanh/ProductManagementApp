<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['idhanghoa']) && isset($_POST['soluong']))
	{
		$id = $_POST['idhanghoa'];
		$soLuong=$_POST['soluong'];
	
		$result=$db->updateSoLuongHangHoa($id,$soLuong);
		
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="cap nhap hang hoa thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="cap nhap hang hoa khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=2;
		$json["thongbao"]="chua nhap ";
	}

	echo json_encode($json);
?>
<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['idhanghoa'])&& $_POST['idhanghoa']!='')
	{
		$id=$_POST['idhanghoa'];
		$result=$db->deleteHangHoa($id);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="xoa hang thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="xoa hang khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap ten loai hang";
	}

	echo json_encode($json);
?>

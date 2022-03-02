<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['makhachhang'])&& $_POST['makhachhang']!='')
	{
		$id=$_POST['makhachhang'];
		$result=$db->getKhachHangByID($id);
		if(mysqli_num_rows($result)>0){
			$json["thanhcong"] = 1;
			$json["khachhang"] = array();

			while($row = mysqli_fetch_array($result)){
				$khachhang=array();
				$khachhang["makhachhang"]=$row["makhachhang"];
				$khachhang["tenkhachhang"]=$row["tenkhachhang"];
				$khachhang["sdtkhachhang"]=$row["sdtkhachhang"];

				array_push($json["khachhang"],$khachhang);
			}
		
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap id hang hoa";
	}
	
	echo json_encode($json);

?>
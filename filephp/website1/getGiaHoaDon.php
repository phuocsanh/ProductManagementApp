<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['idhoadon'])&& $_POST['idhoadon']!='')
	{
		$id=$_POST['idhoadon'];
		$result = $db->getGiaHoaDon($id);

		$json["thanhcong"] = 1;
		$json["giahoadon"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$giahoadon=array();
			$giahoadon["tonggia"]=$row["tonggia"];
			
			array_push($json["giahoadon"],$giahoadon);
		}
	}
	
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap";
	}
	
	echo json_encode($json);

?>
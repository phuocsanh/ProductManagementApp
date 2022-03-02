<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['nam'])&& $_POST['nam']!='')
	{
		$nam=$_POST['nam'];
		$result=$db->getTop($nam);
		if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["top"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$top=array();
			$top["idhanghoa"]=$row["idhanghoa"];
			$top["tenhanghoa"]=$row["tenhanghoa"];
			$top["soluong"]=$row["soluong"];
		
			array_push($json["top"],$top);
		}
	}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap id hang hoa hoac id không tồn tại";
	}
	
	echo json_encode($json);

?>
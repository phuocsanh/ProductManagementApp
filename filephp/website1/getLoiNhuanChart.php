<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['nam']))
	{
		$id=$_POST['nam'];
		$result=$db->getLoiNhuanChart($id);
		if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["loinhuanchart"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$loinhuanchart=array();
			$loinhuanchart["thang"]=$row["thang"];
			$loinhuanchart["loinhuan"]=$row["loinhuan"];
			
			array_push($json["loinhuanchart"],$loinhuanchart);
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
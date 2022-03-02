<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['nam']))
	{
		$id=$_POST['nam'];
		$result=$db->getDoanhThuChart($id);
		if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["doanhthuchart"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$doanhthuchart=array();
			$doanhthuchart["thang"]=$row["thang"];
			$doanhthuchart["doanhthu"]=$row["doanhthu"];
			
			array_push($json["doanhthuchart"],$doanhthuchart);
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
<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['nam'])&& isset( $_POST['thang'])) 
	{
		$nam=$_POST['nam'];
		$thang=$_POST['thang'];
		$result=$db-> getDoanhThuLoiNhuan_Thang($nam, $thang);
		if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["doanhthuvaloinhuanthang"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$doanhthuvaloinhuan=array();
			$doanhthuvaloinhuan["doanhthu"]=$row["doanhthu"];
			$doanhthuvaloinhuan["loinhuan"]=$row["loinhuan"];
			
			array_push($json["doanhthuvaloinhuanthang"],$doanhthuvaloinhuan);
			
		}
		
	}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua chon ngay";
	}
	
	echo json_encode($json);

?>
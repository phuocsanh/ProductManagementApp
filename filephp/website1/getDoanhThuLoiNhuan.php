<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['tuNgay'])&& isset( $_POST['denNgay'])) 
	{
		$tuNgay=$_POST['tuNgay'];
		$denNgay=$_POST['denNgay'];
		$result=$db->getDoanhThuLoiNhuan($tuNgay, $denNgay);
		if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["doanhthuvaloinhuan"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$doanhthuvaloinhuan=array();
			$doanhthuvaloinhuan["doanhthu"]=$row["doanhthu"];
			$doanhthuvaloinhuan["loinhuan"]=$row["loinhuan"];
			
			array_push($json["doanhthuvaloinhuan"],$doanhthuvaloinhuan);	
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

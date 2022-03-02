
<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['idhoadon']))
	{
		$id=$_POST['idhoadon'];
		$result=$db->getHHtrongHoaDon($id);
		if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["hanghoatronghoadon"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$hanghoatronghoadon=array();
			$hanghoatronghoadon["idhanghoa"]=$row["idhanghoa"];
			$hanghoatronghoadon["tenhanghoa"]=$row["tenhanghoa"];
			$hanghoatronghoadon["giaban"]=$row["giaban"];
			$hanghoatronghoadon["soluong"]=$row["soluong"];
	
			array_push($json["hanghoatronghoadon"],$hanghoatronghoadon);
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
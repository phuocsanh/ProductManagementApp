<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['idkiemkho']))
	{
		$id=$_POST['idkiemkho'];
		$result=$db->getHHByKK($id);
		if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["hanghoa"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$hanghoa=array();
			$hanghoa["idhanghoa"]=$row["idhanghoa"];
			$hanghoa["idloaihang"]=$row["idloaihang"];
			$hanghoa["tenhanghoa"]=$row["tenhanghoa"];
			$hanghoa["vitri"]=$row["vitri"];
			$hanghoa["hinhanh"]=$row["hinhanh"];
			$hanghoa["soluong"]=$row["soluong"];
			$hanghoa["giaban"]=$row["giaban"];
			$hanghoa["giavon"]=$row["giavon"];
			$hanghoa["dinhmuctonmin"]=$row["dinhmuctonmin"];
			$hanghoa["dinhmuctonmax"]=$row["dinhmuctonmax"];
			$hanghoa["ghichu"]=$row["ghichu"];
			$hanghoa["soluongbandau"]=$row["soluongbandau"];
			$hanghoa["toncuoi"]=$row["toncuoi"];
			
			array_push($json["hanghoa"],$hanghoa);
		}
		
	}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap id";
	}
	
	echo json_encode($json);

?>
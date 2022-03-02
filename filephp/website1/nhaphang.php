<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	$result = $db->getAllNhapHang();

	if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["nhaphang"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$nhaphang=array();
			$nhaphang["idnhaphang"]=$row["idnhaphang"];
			$nhaphang["soluongbandau"]=$row["soluongbandau"];
			$nhaphang["soluongnhap"]=$row["soluongnhap"];
			$nhaphang["thoigian"]=$row["thoigian"];	
			$nhaphang["idhanghoa"]=$row["idhanghoa"];	
			$nhaphang["tenhanghoa"]=$row["tenhanghoa"];
			$nhaphang["hinhanh"]=$row["hinhanh"];
			$nhaphang["tennhanvien"]=$row["tendangnhap"];
			
	
			array_push($json["nhaphang"],$nhaphang);
			
		}
		echo json_encode($json);
	}
?>
<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	$result = $db->getDsNam();

	if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["listnam"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$listnam=array();
			$listnam["nam"]=$row["nam"];
			
			array_push($json["listnam"],$listnam);
		}
		echo json_encode($json);
	}
?>
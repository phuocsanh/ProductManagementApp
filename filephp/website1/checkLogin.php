<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json = array();

	if(isset($_POST['tendangnhap'])&& isset($_POST['matkhau']))
	{
		$tendangnhap=$_POST['tendangnhap'];
		$matkhau =$_POST['matkhau'];
		$result=$db->checkLogin($tendangnhap,$matkhau);
		if(mysqli_num_rows($result)>0){
		$json["thanhcong"] = 1;
		$json["nhanvien"] = array();
		
		while($row = mysqli_fetch_array($result)){
			$nhanvien=array();
			$nhanvien["manhanvien"]=$row["manhanvien"];
			$nhanvien["tendangnhap"]=$row["tendangnhap"];
			$nhanvien["matkhau"]=$row["matkhau"];
			$nhanvien["chucvu"]=$row["chucvu"];

			array_push($json["nhanvien"],$nhanvien);
		}
		}
		
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="tên hoặc mật khẩu không đúng";
	}
	
	echo json_encode($json);

?>
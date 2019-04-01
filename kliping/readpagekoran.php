<?php 

     include 'config.php';

     $koran_id     = $_POST['koran_id'];

     $sql_cek = "SELECT * FROM tb_koran_halaman WHERE koran_id='".$koran_id."' ORDER BY hal_page ASC;";
     $result_cek = mysqli_query($con,$sql_cek);
     $res = array();
     while($row = mysqli_fetch_assoc($result_cek)){
//         $temp = array();
//         $temp['koran_id']=$row['koran_id'];
//         $temp['nama_page']=$row['nama_page'];
//         $temp['hal_page']=$row['hal_page'];
         $res[]=$row;
     }


    echo($result_cek) ?
        json_encode(array('kode' =>1,'result'=>$res)) :
        json_encode(array('kode' =>2,'result' =>'Data tidak ditemukan'));
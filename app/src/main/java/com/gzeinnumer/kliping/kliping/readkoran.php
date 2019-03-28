<?php 

     include 'config.php';


        $koran_tanggal     = $_POST['koran_tanggal'];
     if($koran_tanggal==null){
         $sql_cek = "SELECT * FROM tb_koran ;";
         $result_cek = mysqli_query($con,$sql_cek);
         $res = array();
         while($row = mysqli_fetch_assoc($result_cek)){
             $res[]=$row;
         }
     } else{

         $sql_cek = "SELECT * FROM tb_koran WHERE koran_tanggal='".$koran_tanggal."';";
         $result_cek = mysqli_query($con,$sql_cek);
         $res = array();
         while($row = mysqli_fetch_assoc($result_cek)){
             $res[]=$row;
         }
     }


    echo($result_cek) ?
        json_encode(array('kode' =>1,'result'=>$res)) :
        json_encode(array('kode' =>2,'result' =>'Data tidak ditemukan'));
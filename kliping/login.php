<?php 

     include 'config.php';

    $user_email     = $_POST['user_email'];
    $user_pass     = md5($_POST['user_pass']);

    $sql_cek = "SELECT * FROM tb_user WHERE user_email='".$user_email."' AND user_pass='".$user_pass."' ;";
    $result_cek = mysqli_query($con,$sql_cek);
    $res = array();
    while($row = mysqli_fetch_assoc($result_cek)){
        $res[]=$row;
    }

    echo($result_cek) ?
        json_encode(array('kode' =>1,'result_login'=>$res)) :
        json_encode(array('kode' =>2,'result_login' =>'Data tidak ditemukan'));


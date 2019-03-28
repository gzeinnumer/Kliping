<?php
    require_once 'config.php';
    if($_SERVER['REQUEST_METHOD'] == 'POST')
    {

        $user_name     = $_POST['user_name'];
        $user_email     = $_POST['user_email'];
        $user_pass     = md5($_POST['user_pass']);

        $query = "INSERT INTO tb_user(user_name, user_email, user_pass, status) VALUES('".$user_name."','".$user_email."','".$user_pass."','user');";
        $exeQuery = mysqli_query($con, $query);
        echo ($exeQuery) ?
            json_encode(array('kode' => 1,'pesan' => 'Kamu Sudah Terdatar', 'user_email' => $user_email)) :
            json_encode(array('kode' => 2,'pesan' => 'Kamu Gagal Mendaftar'));
    } else {
        echo json_encode(array('kode' => 11, 'pesan' => 'request tidak valid'));
    }

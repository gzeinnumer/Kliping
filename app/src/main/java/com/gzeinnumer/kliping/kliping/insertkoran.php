<?php
    require_once 'config.php';
    if($_SERVER['REQUEST_METHOD'] == 'POST')
    {
        $koran_nama     = $_POST['koran_nama'];
        $koran_tanggal  = $_POST['koran_tanggal'];
        $koran_jum_hal  = $_POST['koran_jum_hal'];
        $query = "INSERT INTO tb_koran(koran_nama, koran_tanggal, koran_jum_hal) VALUES('".$koran_nama."','".$koran_tanggal."','".$koran_jum_hal."');";
        $exeQuery = mysqli_query($con, $query);
        echo ($exeQuery) ?
            json_encode(array('kode' => 1,'pesan' => 'Koran Sudah Dibuat')) :
            json_encode(array('kode' => 2,'pesan' => 'Koran Gagal Dibuat'));
    } else {
        echo json_encode(array('kode' => 11, 'pesan' => 'request tidak valid'));
    }

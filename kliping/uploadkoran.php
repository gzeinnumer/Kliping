<?php 

     include 'config.php';

     $upload_path = 'uploads/';
     $server_ip = gethostbyname(gethostname());
     $upload_url = 'http://'.$server_ip.'/kliping/'.$upload_path;
     $response = array();
     $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');

     if($_SERVER['REQUEST_METHOD']=='POST'){
        $koran_nama     = $_POST['koran_nama'];
        $koran_tanggal  = $_POST['koran_tanggal'];
        $koran_jum_hal  = $_POST['koran_jum_hal'];
        $sql_cek = "SELECT koran_id FROM tb_koran WHERE koran_nama='".$koran_nama."'AND koran_tanggal='".$koran_tanggal."'AND koran_jum_hal='".$koran_jum_hal."';";
        $result_cek = mysqli_query($con,$sql_cek);

         $res = array();
         while($row = mysqli_fetch_array($result_cek)){
            $koran_id=$row['koran_id'];
         }

         if(isset($_POST['hal_page']) and isset($_FILES['image']['name'])){
             $hal_page = $_POST['hal_page'];
             $fileinfo = pathinfo($_FILES['image']['name']);
             $extension = "jpg";
             $file_url = $koran_id.'_'.$hal_page. '_'.$koran_tanggal. '.' . $extension;
             $file_path = $upload_path .$koran_id. '_'. $hal_page. '_'.$koran_tanggal. '.'. $extension;

             try{
                move_uploaded_file($_FILES['image']['tmp_name'],$file_path);
                $sql = "INSERT INTO tb_koran_halaman(`koran_id`, `nama_page`, `hal_page`) VALUES ('$koran_id', '$file_url', '$hal_page');";

                if(mysqli_query($con,$sql)){
                    $response['success'] = true;
                    $response['page_name'] = $file_url;
                    $response['hal_page'] = $hal_page;
                } else{
                    $response['success']=false;
                }
            }catch(Exception $e){
                $response['success']=false;
                $response['message']=$e->getMessage();
            }

             mysqli_close($con);

         }else{
             $response['success']=false;
             $response['message']='Please choose a file';
         }

         echo json_encode($response);

     }


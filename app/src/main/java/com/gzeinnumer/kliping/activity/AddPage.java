package com.gzeinnumer.kliping.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.adapter.AdapterAddPage;
import com.gzeinnumer.kliping.data.DataKoran;
import com.gzeinnumer.kliping.model.ItemAddPage;
import com.gzeinnumer.kliping.modelpojo.ResponsePageUpload;
import com.gzeinnumer.kliping.network.RetroServer;
import com.gzeinnumer.kliping.presenter.I_AddPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

public class AddPage extends AppCompatActivity implements AdapterAddPage.onItemClick, I_AddPage {

    @BindView(R.id.rv_koran_page)
    RecyclerView rvKoranPage;
    @BindView(R.id.save_koran)
    Button saveKoran;

    public static String HAL ="hal";
    public static String TANGGAL_KORAN="tanggal_koran";
    public static String NAMA_KORAN="nama_koran";

    int varHal;
    String varNamaKoran;
    String varTanggalKoran;
    String varHalPage;

    AdapterAddPage adapter;

    ArrayList<ItemAddPage> list;


    int i;

    File imagefile;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;

    private Uri filePath;
    private String path;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        varHal= intent.getIntExtra(HAL,0);
        varNamaKoran= intent.getStringExtra(NAMA_KORAN);
        varTanggalKoran= intent.getStringExtra(TANGGAL_KORAN);

//        Toast.makeText(this, String.valueOf(varHal), Toast.LENGTH_SHORT).show();

        list = new ArrayList<>();
        for (i=1; i<=varHal; i++){
            list.add(new ItemAddPage(i));
        }

        adapter = new AdapterAddPage(AddPage.this, list, varHal);
        rvKoranPage.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvKoranPage.setAdapter(adapter);
        adapter.setOnClickListener2(AddPage.this);
        
        requestStoragePermission();
    }

    @OnClick(R.id.save_koran)
    public void onViewClicked() {
        int jumlah_page = DataKoran.listKoran.size();
        for (int i=0; i<jumlah_page; i++){

            imagefile = new File(DataKoran.listKoran.get(i).getPathFoto());
            varHalPage = String.valueOf(i+1);

            RequestBody reqNama = RequestBody.create(MediaType.parse("text/plain"), varNamaKoran);
            RequestBody reqTanggal = RequestBody.create(MediaType.parse("text/plain"), varTanggalKoran);
            RequestBody reqJumHal = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(varHal));
            RequestBody reqHalPage = RequestBody.create(MediaType.parse("text/plain"), varHalPage);
            RequestBody reqImage = RequestBody.create(MediaType.parse("multipart/form-file"),imagefile);

            String imagePost = "image";
            MultipartBody.Part partImage = MultipartBody.Part.createFormData(imagePost, imagefile.getName(),reqImage);

            RetroServer.getInstance().uploadKoranHalaman(reqNama, reqTanggal, reqJumHal, reqHalPage, partImage).enqueue(new Callback<ResponsePageUpload>() {
                @Override
                public void onResponse(Call<ResponsePageUpload> call, Response<ResponsePageUpload> response) {

                }

                @Override
                public void onFailure(Call<ResponsePageUpload> call, Throwable t) {

                }
            });

        }
    }

    @Override
    public void onItemClick(int position) {
        showFileChooser();
    }

    @Override
    public void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            String[] imageprojection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(filePath,imageprojection,null,null,null);

            if (cursor != null) {
                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageprojection[0]);
                path = cursor.getString(indexImage);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    String myPath=getPath(filePath);
                    adapter.setPath(myPath);
                    adapter.setBitmap(bitmap);
                    adapter.setBitmapToImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    @Override
    public void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

}

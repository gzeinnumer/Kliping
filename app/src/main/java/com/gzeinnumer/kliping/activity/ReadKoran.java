package com.gzeinnumer.kliping.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.modelpojo.ResponsePageKoran;
import com.gzeinnumer.kliping.modelpojo.ResultItemPage;
import com.gzeinnumer.kliping.network.RetroServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadKoran extends AppCompatActivity {

    public static String ID_KORAN = "ID_KORAN";
    String id_koran;
    List<ResultItemPage> list;
    ArrayList<ResultItemPage> listData;

    ViewPager viewPager;

    int colors = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_koran);

        viewPager = findViewById(R.id.viewPager);

        Intent intent = getIntent();
        id_koran = intent.getStringExtra(ID_KORAN);
        getDataKoranById(id_koran);
    }

    private void getDataKoranById(String id_koran) {
        RetroServer.getInstance().readPage(id_koran).enqueue(new Callback<ResponsePageKoran>() {
            @Override
            public void onResponse(Call<ResponsePageKoran> call, Response<ResponsePageKoran> response) {

                initDataToPager();
            }

            @Override
            public void onFailure(Call<ResponsePageKoran> call, Throwable t) {

            }
        });
    }

    private void initDataToPager() {
        Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show();
    }
}

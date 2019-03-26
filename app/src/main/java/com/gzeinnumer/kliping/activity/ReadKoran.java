package com.gzeinnumer.kliping.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.adapter.AdapterAddPage;
import com.gzeinnumer.kliping.adapter.AdapterPage;
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
    ViewPager viewPager;
    AdapterPage adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

                list=response.body().getResult();
                initDataToPager();
            }

            @Override
            public void onFailure(Call<ResponsePageKoran> call, Throwable t) {

            }
        });
    }

    private void initDataToPager() {
        adapter = new AdapterPage(this, list);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(0, 0, 0, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}

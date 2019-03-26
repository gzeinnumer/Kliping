package com.gzeinnumer.kliping;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.gzeinnumer.kliping.activity.AddKoran;
import com.gzeinnumer.kliping.adapter.AdapterReadKoran;
import com.gzeinnumer.kliping.modelpojo.ResponseReadKoran;
import com.gzeinnumer.kliping.modelpojo.ResultItem;
import com.gzeinnumer.kliping.network.RetroServer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMenuBerita;
    Unbinder unbinder;
    Button btnDate, btnAddKoran;
    Button pick;
    DatePicker datePicker;
    private View view;
    private Context context;
    private Dialog dialog;
    private String date;

    private List<ResultItem> list ;
    private ArrayList<ResultItem> listDataKoran;

    private AdapterReadKoran adapterReadKoran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;


        rvMenuBerita = findViewById(R.id.rv_menu_berita);
        btnDate = findViewById(R.id.btn_date);
        btnAddKoran = findViewById(R.id.btn_add_koran);

        date = "";

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBtnData();
            }
        });
        btnAddKoran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBtnAddKoran();
            }
        });

        initDataKoran();
    }

    private void actionBtnData() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_date);
        dialog.setTitle("Pilih Tanggal");
        Calendar calendar = Calendar.getInstance();
        datePicker = dialog.findViewById(R.id.datePicker);
        pick = dialog.findViewById(R.id.pick);
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = datePicker.getYear() + "-" + (datePicker.getMonth()+1) +"-"+ datePicker.getDayOfMonth();
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDataKoran();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void initDataKoran() {
        getDataKoran();
    }

    private void getDataKoran() {
        RetroServer.getInstance().readKoran(date).enqueue(new Callback<ResponseReadKoran>() {
            @Override
            public void onResponse(Call<ResponseReadKoran> call, Response<ResponseReadKoran> response) {
                list = response.body().getResult();
                listDataKoran = new ArrayList<>();
                if (response.body().getKode()==1){
                    for (int i=0; i<list.size(); i++){
                        listDataKoran.add(new ResultItem(list.get(i).getKoranJumHal(),list.get(i).getKoranId(), list.get(i).getKoranTanggal(), list.get(i).getKoranNama()));
                        fetchRecycler();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseReadKoran> call, Throwable t) {

            }
        });
    }

    private void fetchRecycler() {
        adapterReadKoran = new AdapterReadKoran(context, listDataKoran);
        rvMenuBerita.setLayoutManager(new LinearLayoutManager(context));
        rvMenuBerita.setHasFixedSize(true);
        rvMenuBerita.setAdapter(adapterReadKoran);
    }

    private void actionBtnAddKoran() {
        Intent intent = new Intent(context, AddKoran.class);
        context.startActivity(intent);
    }


}

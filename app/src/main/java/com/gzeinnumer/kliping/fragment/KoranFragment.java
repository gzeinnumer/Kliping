package com.gzeinnumer.kliping.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.activity.AddKoran;

import java.util.Calendar;

import butterknife.Unbinder;

public class KoranFragment extends Fragment {

    RecyclerView rvMenuBerita;
    Unbinder unbinder;
    Button btnDate, btnAddKoran;
    Button pick;
    DatePicker datePicker;
    private View view;
    private Context context;
    private Dialog dialog;
    private String date;

    public KoranFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_koran, container, false);
        context = view.getContext();

        rvMenuBerita = view.findViewById(R.id.rv_menu_berita);
        btnDate = view.findViewById(R.id.btn_date);
        btnAddKoran = view.findViewById(R.id.btn_add_koran);

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
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                date = datePicker.getDayOfMonth() + "-" + datePicker.getMonth() +"-"+ datePicker.getYear();
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDataKoran();
            }
        });
        dialog.show();
    }



    private void initDataKoran() {
        fetchRecycler();
    }

    private void fetchRecycler() {
        Toast.makeText(context, "Fetch Recycler "+ date, Toast.LENGTH_SHORT).show();
    }

    private void actionBtnAddKoran() {
        Intent intent = new Intent(context, AddKoran.class);
        context.startActivity(intent);
    }

}

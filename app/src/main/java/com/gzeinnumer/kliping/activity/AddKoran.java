package com.gzeinnumer.kliping.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.model.ItemNewKoran;
import com.gzeinnumer.kliping.modelpojo.ResponseNewKoran;
import com.gzeinnumer.kliping.network.RetroServer;
import com.gzeinnumer.kliping.presenter.I_AddKoran;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddKoran extends AppCompatActivity implements I_AddKoran {


    @BindView(R.id.koran_nama)
    TextInputEditText koranNama;
    @BindView(R.id.koran_tanggal)
    TextInputEditText koranTanggal;
    @BindView(R.id.koran_pilih_tgl)
    Button koranPilihTgl;
    @BindView(R.id.koran_jum_hal)
    TextInputEditText koranJumHal;
    @BindView(R.id.submit)
    Button submit;

    Dialog dialog;
    DatePicker datePicker;
    Button pick;

    private String date;
    String namaKoran, tanggalKoran, jumHalaman;

    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_koran);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Please Wait");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(true);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        date = sdf.format(new Date());
        koranTanggal.setText(date);

    }

    @OnClick({R.id.koran_pilih_tgl, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.koran_pilih_tgl:
                actionKoranPilihTanggal();
                break;
            case R.id.submit:
                validField();
                break;
        }
    }

    @Override
    public void actionKoranPilihTanggal() {
        dialog = new Dialog(AddKoran.this);
        dialog.setContentView(R.layout.dialog_date);
        dialog.setTitle("Pilih Tanggal");
        Calendar calendar = Calendar.getInstance();
        datePicker = dialog.findViewById(R.id.datePicker);
        pick = dialog.findViewById(R.id.pick);
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = datePicker.getYear()+ "-"+ (datePicker.getMonth()+1)+ "-"  +datePicker.getDayOfMonth();
                koranTanggal.setText(date);
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                koranTanggal.setText(date);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void validField() {
        namaKoran = koranNama.getText().toString().trim();
        tanggalKoran = koranTanggal.getText().toString();
        jumHalaman = koranJumHal.getText().toString();

        if (namaKoran.equals("")){
            koranNama.setError(getString(R.string.empty));
        } else if(tanggalKoran.equals("") || tanggalKoran.equals(R.string.pilih_tanggal)){
            koranTanggal.setError(getString(R.string.empty));
        } else if(jumHalaman.equals("")){
            koranJumHal.setError(getString(R.string.empty));
        } else {
            mProgressDialog.show();
            fetchNewKoran();
        }
    }

    private void fetchNewKoran() {

        RetroServer.getInstance().newKoran(namaKoran,tanggalKoran,Integer.parseInt(jumHalaman)).enqueue(new Callback<ResponseNewKoran>() {
            @Override
            public void onResponse(Call<ResponseNewKoran> call, Response<ResponseNewKoran> response) {
                ResponseNewKoran result = response.body();
                if(result.getKode() == 1){
                    Toast.makeText(AddKoran.this, result.getPesan(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), AddPage.class);
                    intent.putExtra(AddPage.NAMA_KORAN,namaKoran);
                    intent.putExtra(AddPage.TANGGAL_KORAN,tanggalKoran);
                    intent.putExtra(AddPage.HAL, Integer.parseInt(jumHalaman));
                    mProgressDialog.dismiss();
                    startActivity(intent);
                    AddKoran.this.finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseNewKoran> call, Throwable t) {

            }
        });
    }
}

package com.gzeinnumer.kliping.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.modelpojo.ResponseLogin;
import com.gzeinnumer.kliping.network.RetroServer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.edt_email_login)
    EditText edtEmailLogin;
    @BindView(R.id.edt_password_login)
    EditText edtPasswordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_sign_in)
    TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_login, R.id.tv_sign_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                actionBtnLogin();
                break;
            case R.id.tv_sign_in:
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void actionBtnLogin() {
        String email = edtEmailLogin.getText().toString();
        String pass = edtPasswordLogin.getText().toString();

        if(email.equals("") && pass.equals("")){
            Toast.makeText(this, "input dulu", Toast.LENGTH_SHORT).show();
        } else {
            RetroServer.getInstance().login(email, pass).enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if (response.body().getKode() == 1){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        String stat = "user";
                        if (response.body().getResultLogin().get(0).getStatus().equals("admin")){
                            stat = "admin";
                        } else if(!response.body().getResultLogin().get(0).getStatus().equals("admin") || response.body().getResultLogin().get(0).getStatus().equals("")){

                        }

                        SessionPreference mSession = new SessionPreference(LoginActivity.this);

                        mSession.setEmail(response.body().getResultLogin().get(0).getUserEmail());
                        mSession.setStatus(response.body().getResultLogin().get(0).getStatus());

                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {

                }
            });
        }

    }

    public static class SessionPreference {
        //TODO kenalin
        String KEY_EMAIL = "EMAIL";
        String KEY_STATUS = "STATUS";
        String PREF_NAME = "SIMPAN";
        //TODO panggil Sharedpreference
        SharedPreferences mSharedPreferences;
        SharedPreferences.Editor mEditor;

        //TODO konstruktor
        public SessionPreference(Context context) {
            mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }

        //TODO SET_NAME (POSISI LOGIN)
        public void setEmail(String email) {
            mEditor = mSharedPreferences.edit();
            mEditor.putString(KEY_EMAIL, email).apply();
        }

        //TODO GET_NAME (CEK LOGIN)
        public String getEmail() {
            return mSharedPreferences.getString(KEY_EMAIL, null);
        }

        //TODO SET_NAME (POSISI LOGIN)
        public void setStatus(String status) {
            mEditor = mSharedPreferences.edit();
            mEditor.putString(KEY_STATUS, status).apply();
        }

        //TODO GET_NAME (CEK LOGIN)
        public String getStatus() {
            return mSharedPreferences.getString(KEY_STATUS, null);
        }

        //TODO LOGOUT (MENGHAPUS)
        public void logout() {
            mEditor = mSharedPreferences.edit();
            mEditor.clear().commit();
        }
    }
}

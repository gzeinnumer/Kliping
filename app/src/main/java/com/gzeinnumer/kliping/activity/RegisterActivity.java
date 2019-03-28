package com.gzeinnumer.kliping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.modelpojo.ResponseDaftar;
import com.gzeinnumer.kliping.network.RetroServer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edt_username_register)
    EditText edtUsernameRegister;
    @BindView(R.id.edt_email_register)
    EditText edtEmailRegister;
    @BindView(R.id.edt_password_register)
    EditText edtPasswordRegister;
    @BindView(R.id.edt_con_password_register)
    EditText edtConPasswordRegister;
    @BindView(R.id.btn_sign_up)
    Button btnSignUp;
    @BindView(R.id.tv_sign_in)
    TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_sign_up, R.id.tv_sign_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                actionBtnSignUp();
                break;
            case R.id.tv_sign_in:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void actionBtnSignUp() {
        String name = edtUsernameRegister.getText().toString().trim();
        String email = edtEmailRegister.getText().toString().trim();
        String pass = edtPasswordRegister.getText().toString().trim();
        String cpass = edtConPasswordRegister.getText().toString().trim();
        if (name.equals("")){
            edtUsernameRegister.setError("isi ini");
        } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.equals("")){
            edtEmailRegister.setError("isi ini");
        } else if (pass.equals("") ){
            edtPasswordRegister.setError("isi ini");
        } else if (!pass.equals(cpass)){
            edtPasswordRegister.setError("harus sama");
            edtConPasswordRegister.setError("harus sama");
        } else {
            RetroServer.getInstance().daftar(name, email, pass).enqueue(new Callback<ResponseDaftar>() {
                @Override
                public void onResponse(Call<ResponseDaftar> call, Response<ResponseDaftar> response) {
                    if (response.body().getKode()==1){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra(MainActivity.EMAIL_KEY, response.body().getUserEmail());
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Gagal Register", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseDaftar> call, Throwable t) {

                }
            });
        }

    }
}

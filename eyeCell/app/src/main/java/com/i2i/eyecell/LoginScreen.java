package com.i2i.eyecell;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.i2i.eyecell.connect.RetrofitClientInstance;
import com.i2i.eyecell.model.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    EditText msisdn, password;
    Button buttonLogin;
    Button buttonSignUp;
    Button buttonForgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        msisdn = findViewById(R.id.editTextPhone);
        password = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonForgotPassword = findViewById(R.id.buttonForgot);

        buttonForgotPassword.setOnClickListener(v -> {
            Intent nextPage= new Intent(LoginScreen.this, ForgotPasswordScreen.class);
            startActivity(nextPage);
        });

        buttonSignUp.setOnClickListener(view -> {
            Intent nextPage= new Intent(LoginScreen.this, RegisterScreen.class);
            startActivity(nextPage);
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(msisdn.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(LoginScreen.this,"Telefon numarası veya şifre eksik", Toast.LENGTH_LONG).show();
                }else{
                    login_user();
                }
            }
        });
    }

    public void login_user(){
        String edtmsisdn = msisdn.getText().toString();
        String edtpassword = password.getText().toString();

        Call<LoginRequest> loginRequestCall = RetrofitClientInstance.getUserService().login(edtmsisdn,edtpassword);
        loginRequestCall.enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginScreen.this,"Giriş Başarılı", Toast.LENGTH_LONG).show();
                    LoginRequest loginRequest = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoginScreen.this,MainScreen.class).putExtra("msisdn", edtmsisdn));
                        }
                    },700);
                }else{
                    Toast.makeText(LoginScreen.this,"Kullanıcı adı veya şifre hatalı", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LoginRequest> call, Throwable t) {
                Toast.makeText(LoginScreen.this,"Hata!  "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                System.out.println(t.getLocalizedMessage());
            }
        });
    }
}


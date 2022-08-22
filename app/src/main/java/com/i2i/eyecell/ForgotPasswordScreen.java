package com.i2i.eyecell;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordScreen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        Button buttonSendMail = (Button) findViewById(R.id.buttonSendMail);
        buttonSendMail.setOnClickListener(view -> {
            Intent nextPage= new Intent(ForgotPasswordScreen.this, LoginScreen.class);
            startActivity(nextPage);
        });
    }
}

package com.example.donorhub.common.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.donorhub.R;

public class SignUp2nd extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd);



    }

    public void backBtn(View view){
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }

    public void callNextScreen(View view){
        Intent intent = new Intent(getApplicationContext(), Otp_page.class);
        startActivity(intent);
    }
}
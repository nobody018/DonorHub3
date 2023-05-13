package com.example.donorhub.common.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.donorhub.R;
import com.example.donorhub.common.WelcomeScreen;

public class Signup extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



    }

    public void backBtn(View view){
        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivity(intent);
    }

    public void donotorBtn(View view){

    }

    public void ngoBtn(View view){
        Intent intent = new Intent(getApplicationContext(),SignUp2nd.class);
        startActivity(intent);
    }

}
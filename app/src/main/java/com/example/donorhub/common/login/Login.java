package com.example.donorhub.common.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.donorhub.R;
import com.example.donorhub.common.WelcomeScreen;
import com.example.donorhub.user.UserDashboard;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_login);
    }

    public void backBtn(View view){
        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivity(intent);
    }

    public void dashBoarding(View view){
        Toast.makeText(this, "Hi there", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
        startActivity(intent);
    }
}
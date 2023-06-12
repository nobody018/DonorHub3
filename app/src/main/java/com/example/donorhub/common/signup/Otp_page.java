package com.example.donorhub.common.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.donorhub.R;

public class Otp_page extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);

        textView = findViewById(R.id.phone_number_otp);
        textView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("PhoneNumber")
        ));
    }
}
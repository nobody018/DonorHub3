package com.example.donorhub.common.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.donorhub.R;
import com.example.donorhub.common.WelcomeScreen;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpSecond extends AppCompatActivity {

    TextInputLayout ngoRegNo;
    RadioGroup radioGroup;

    RadioButton selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigb_up_second);

        ngoRegNo = findViewById(R.id.ngo_reg_no);
        radioGroup = findViewById(R.id.radioGroup);

        ngoRegNo.setVisibility(View.GONE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton) {
                    ngoRegNo.setVisibility(View.GONE);
                } else {
                    ngoRegNo.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private boolean validateUser(){
        if (radioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Check The Button", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    public void backBtn(View view) {
            Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
            startActivity(intent);
    }


    public void NxtBtn(View view) {
        if (!validateUser()){
            return;
        }
        selectedUser = findViewById(radioGroup.getCheckedRadioButtonId());

        String _fullName = getIntent().getStringExtra("fullName");
        String _userName = getIntent().getStringExtra("userName");
        String _email = getIntent().getStringExtra("email");
        String _phoneNumber = getIntent().getStringExtra("phoneNumber");
        String _password = getIntent().getStringExtra("password");
        String _user = selectedUser.getText().toString();

        Intent intent = new Intent(getApplicationContext(), Otp_page.class);
        intent.putExtra("user", _user);
        intent.putExtra("fullName", _fullName);
        intent.putExtra("userName", _userName);
        intent.putExtra("email", _email);
        intent.putExtra("phoneNumber", _phoneNumber);
        intent.putExtra("password", _password);

        startActivity(intent);

    }
}
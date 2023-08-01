package com.example.donorhub.common.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.donorhub.R;
import com.example.donorhub.common.WelcomeScreen;
import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {


//Get Data Variables
    TextInputLayout full_name, username, email, phone_number, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    //Hooks for data
        full_name = findViewById(R.id.full_name);
        username = findViewById(R.id.user_name);
        email = findViewById(R.id.email);
        phone_number = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);


    }
    private boolean validateFullName(){
        String val = full_name.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            full_name.setError("Field can not be empty");
            return false;
        }else {
            full_name.setError(null);
            full_name.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validateUserName(){
        String val = username.getEditText().getText().toString().trim();
        String checkspace = "\\A\\w{1,20}\\z";

        if(val.isEmpty()){
            username.setError("Field can not be empty");
            return false;
        } else if (val.length()>20) {
            username.setError("Username is too large");
            return false;
        }else if (!val.matches((checkspace))){
            username.setError("No white space are allowed");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateEmail(){
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if(val.isEmpty()){
            email.setError("Field can not be empty");
            return false;
        }else if (!val.matches((checkEmail))){
            email.setError("Invalid Email");
            return false;
        }
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePhone(){
        String val = phone_number.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            phone_number.setError("Field can not be empty");
            return false;
        }else if (val.length()!=10){
            phone_number.setError("Invalid phone number");
            return false;
        }
        else {
            phone_number.setError(null);
            phone_number.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword(){
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if(val.isEmpty()){
            password.setError("Field can not be empty");
            return false;
        }else if (!val.matches((checkPassword))){
            password.setError("Password must contain at least 4 characters and no white spaces");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void backBtn(View view){
        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivity(intent);
    }




    public void NxtBtn(View view) {
        if (!validateFullName() | !validateUserName() | !validateEmail() | !validatePhone() | validatePassword()){
            return;
        }
        Intent intent = new Intent(getApplicationContext(), SignUpSecond.class);
        intent.putExtra("fullName", full_name.getEditText().getText().toString());
        intent.putExtra("userName", username.getEditText().getText().toString());
        intent.putExtra("email", email.getEditText().getText().toString());
        intent.putExtra("phoneNumber", phone_number.getEditText().getText().toString());
        intent.putExtra("password", password.getEditText().getText().toString());
        startActivity(intent);
    }
}
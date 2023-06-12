package com.example.donorhub.common.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.donorhub.R;
import com.example.donorhub.common.WelcomeScreen;
import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {


//Get Data Variables
    TextInputLayout full_name, username, email, phone_number, password, confirm_pass;

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
        confirm_pass = findViewById(R.id.password_confirm);

    }
    private boolean validateFullName(){
        String val = full_name.getEditText().toString().trim();

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
        String val = username.getEditText().toString().trim();
        String checkspace = "\\A\\w{1,20}\\z";

        if(val.isEmpty()){
            username.setError("Field can not be empty");
            return false;
        } else if (val.length()>20) {
            username.setError("Usernmae is too large");
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
        String val = email.getEditText().toString().trim();
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
        String val = phone_number.getEditText().toString().trim();

        if(val.isEmpty()){
            phone_number.setError("Field can not be empty");
            return false;
        }else if (val.length()!=10){
            phone_number.setError("Please add country code");
            return false;
        }
        else {
            phone_number.setError(null);
            phone_number.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword(){
        String val = password.getEditText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if(val.isEmpty()){
            password.setError("Field can not be empty");
            return false;
        }else if (!val.matches((checkPassword))){
            password.setError("Password must contain at least 1 special character, at least 4 characters and no white spaces");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateConfirmPassword(){
        String val = confirm_pass.getEditText().toString().trim();
        if(val.isEmpty()){
            confirm_pass.setError("Field can not be empty");
            return false;
        }else if (!password.equals(confirm_pass)) {
            confirm_pass.setError("Enter correct password.");
            confirm_pass.requestFocus();
            return false;
        } else {
            confirm_pass.setError(null);
            confirm_pass.setErrorEnabled(false);
            return true;
        }
    }
    public void backBtn(View view){
        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivity(intent);
    }

    public void donotorBtn(View view){

        if (!validateFullName() | !validateUserName() | !validateEmail() | !validatePhone() | validatePassword() | validateConfirmPassword()){
            return;
        }
        Intent intent = new Intent(getApplicationContext(),Otp_page.class);
        intent.putExtra("FullName", full_name.getEditText().toString());
        intent.putExtra("UserName", username.getEditText().toString());
        intent.putExtra("Email", email.getEditText().toString());
        intent.putExtra("PhoneNumber", phone_number.getEditText().toString());
        intent.putExtra("Password", password.getEditText().toString());
        intent.putExtra("ConfirmPassword", confirm_pass.getEditText().toString());
        startActivity(intent);
    }

    public void ngoBtn(View view){
        Intent intent = new Intent(getApplicationContext(),Otp_page.class);
        startActivity(intent);
    }

}
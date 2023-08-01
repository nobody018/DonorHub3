package com.example.donorhub.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donorhub.R;
import com.example.donorhub.common.WelcomeScreen;
import com.example.donorhub.database.UserHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    FirebaseAuth mAuth;
    private TextView textviewFullName, textviewUserName, textviewPhoneNo, textviewEmail, textviewPWD, textviewUserType;
    private String fullName, userName, phoneNO, email, password, userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        textviewFullName = findViewById(R.id.show_fullName);
        textviewUserName = findViewById(R.id.show_userName);
        textviewPhoneNo = findViewById(R.id.show_phonenumber);
        textviewEmail = findViewById(R.id.show_email);
        textviewUserType=findViewById(R.id.show_usertype);
        textviewPWD = findViewById(R.id.show_pwd);

        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        userName = getIntent().getStringExtra("userName");
        phoneNO = getIntent().getStringExtra("phoneNo");
        password = getIntent().getStringExtra("password");
        userType = getIntent().getStringExtra("user");






        textviewFullName.setText(fullName);
        textviewUserName.setText(userName);
        textviewPhoneNo.setText(phoneNO);
        textviewEmail.setText(email);
        textviewUserType.setText(userType);
        textviewPWD.setText(password);


    }




    public void logOutBtn(View view) {
        mAuth.signOut();
        Intent intent = new Intent(UserProfile.this, WelcomeScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
}
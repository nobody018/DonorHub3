package com.example.donorhub.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class UserProfile extends AppCompatActivity {
    FirebaseAuth mAuth;
    private TextView textviewFullName, textviewUserName, textviewPhoneNo, textviewEmail, textviewPWD, textviewUserType;
    private String fullName, userName, phoneNO, email, password, userType;

    Uri ImageUri;

    RelativeLayout relativeLayout, relativeLayout2;

    private ImageView uploadImg, showImg;
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
        uploadImg = findViewById(R.id.profileDpBtn);
        showImg = findViewById(R.id.profileDp);
        relativeLayout = findViewById(R.id.relative);
        relativeLayout2= findViewById(R.id.relative2);

        phoneNO = getIntent().getStringExtra("phoneNo");


        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout2.setVisibility(View.GONE);
            }
        });


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





    private void UploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent =  new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(UserProfile.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK){
            ImageUri = data.getData();
            showImg.setImageURI(ImageUri);
        }
    }




    public void logOutBtn(View view) {
        mAuth.signOut();
        Intent intent = new Intent(UserProfile.this, WelcomeScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    public void backBtn(View view) {
        Intent intent = new Intent(UserProfile.this, UserDashboard.class);
        startActivity(intent);
        finish();
    }
}
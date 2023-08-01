package com.example.donorhub.common.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.donorhub.R;
import com.example.donorhub.common.WelcomeScreen;
import com.example.donorhub.common.login.Login;
import com.example.donorhub.database.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.concurrent.TimeUnit;

public class Otp_page extends AppCompatActivity {
    TextView textView;
    PinView pinView;
    String name, username, email, getUserPhoneNumber, password, _user;

    String codeBySystem;

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);

        mAuth = FirebaseAuth.getInstance();

        name = getIntent().getStringExtra("fullName");
        username = getIntent().getStringExtra("userName");
        getUserPhoneNumber = getIntent().getStringExtra("phoneNumber");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        _user = getIntent().getStringExtra("user");
        //hooks
        textView = findViewById(R.id.phone_number_otp);
        pinView = findViewById(R.id.pin_view);

        //to show the phone number
        textView.setText(String.format(
                "+91-%s", getUserPhoneNumber
        ));

        String phoneNumber = "+91" + getUserPhoneNumber;

        sendVerificationToUser(phoneNumber);

        findViewById(R.id.resend_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                        String code = credential.getSmsCode();
                        if (code!=null){
                            pinView.setText(code);
                            verifyCode(code);
                        }
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(Otp_page.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId,
                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        super.onCodeSent(verificationId, token);
                        codeBySystem = verificationId;
                    }
                };
                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber(phoneNumber)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                // (optional) Activity for callback binding
                                // If no activity is passed, reCAPTCHA verification can not be used.
                                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });
    }

    private void sendVerificationToUser(String phoneNumber) {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                String code = credential.getSmsCode();
                if (code!=null){
                    pinView.setText(code);
                    verifyCode(code);
                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(Otp_page.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(Otp_page.this, "Sent", Toast.LENGTH_SHORT).show();
                super.onCodeSent(verificationId, token);
                codeBySystem = verificationId;
            }
        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)// (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void verifyCode(String code) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
            FirebaseAuth
                    .getInstance()
                    .signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        storeNewUsersData();

                        Toast.makeText(Otp_page.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Otp_page.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();


                    } else{
                        Toast.makeText(Otp_page.this, "Verification not Completed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void storeNewUsersData() {
            FirebaseUser firebaseUser = mAuth.getCurrentUser();
            FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://donorhub-41409-default-rtdb.asia-southeast1.firebasedatabase.app/");
            DatabaseReference reference = rootNode.getReference("Users");

            UserHelperClass addNewUser = new UserHelperClass(name, username, email, getUserPhoneNumber, password, _user);

            reference.child(getUserPhoneNumber).setValue(addNewUser);

    }


    public void callNextScreenFromOtp(View view) {
        String code = pinView.getText().toString();
        if (!code.isEmpty()) {
            verifyCode(code);
        }
    }

    public void crossBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivity(intent);
    }
}

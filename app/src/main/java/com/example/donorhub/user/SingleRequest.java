package com.example.donorhub.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donorhub.R;
import com.squareup.picasso.Picasso;

public class SingleRequest extends AppCompatActivity {
    private TextView ngoname, phoneNo;
    private ImageView ngoimage;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_request);

        phoneNo=findViewById(R.id.show_phonenumber);
        ngoname = findViewById(R.id.ngoFullName);
        ngoimage= findViewById(R.id.ngoDp);

        String ngoname1 = getIntent().getStringExtra("ngoName");
        ngoname.setText(ngoname1);

        String ngonum = getIntent().getStringExtra("phoneNo");
        phoneNo.setText(ngonum);

        String ngoDp = getIntent().getStringExtra("ngoImage");
        Picasso.get().load(ngoDp).into(ngoimage);



    }

}
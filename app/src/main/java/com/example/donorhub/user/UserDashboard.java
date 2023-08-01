package com.example.donorhub.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.donorhub.Helperfile.FeatuedAdapter;
import com.example.donorhub.Helperfile.FeaturedHelperClass;
import com.example.donorhub.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String fullName, userName, phoneNO, email, password, userType;
    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ImageView menuIcon;

    CircleImageView circleImageView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //hook
        featuredRecycler = findViewById(R.id.featured_recycler);

        //menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        circleImageView = findViewById(R.id.profile);

        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        userName = getIntent().getStringExtra("userName");
        phoneNO = getIntent().getStringExtra("phoneNo");
        password = getIntent().getStringExtra("password");
        userType = getIntent().getStringExtra("user");

        fab = findViewById(R.id.fab);

        if (userType.equals("NGO")){
            fab.setVisibility(View.VISIBLE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this, RequestUpload.class);
                intent.putExtra("phoneNo", phoneNO);
                startActivity(intent);
            }
        });


        navigationDrawer();

        //RecyclerView function call
        featuredRecycler();

        /*databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String profilePicUrl = snapshot.child("Users").child("phoneNo").child("profile_pic").getValue(String.class);

                if (!profilePicUrl.isEmpty()) {
                    Picasso.get().load(profilePicUrl).into(circleImageView);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
    //NavigationDrawer Function
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(),UserDashboard.class));
                break;

            case R.id.nav_about_us:
                startActivity(new Intent(getApplicationContext(),AboutUs.class));
                break;

            case R.id.nav_version:
                Toast.makeText(this, "Version: 2.0.1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_share:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = "https://drive.google.com/drive/folders/1K9eYiokHfmTvSZtA8s0UYprvoX6eb_7o?usp=sharing";
                String sub = "DonorHub apk";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
                break;
        }

        return true;
    }
    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    //RecyclerView Functions
    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        ArrayList<FeaturedHelperClass> featuredLocation = new ArrayList<>();

        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,fullName, "We are gathering Cloths for poor ladies in our locality. "));

        adapter = new FeatuedAdapter(featuredLocation);
        featuredRecycler.setAdapter(adapter);
    }


    public void getUserProfile(View view) {
        Intent intent = new Intent(UserDashboard.this , UserProfile.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("userName", userName);
        intent.putExtra("user", userType);
        intent.putExtra("phoneNo", phoneNO);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);

    }
}
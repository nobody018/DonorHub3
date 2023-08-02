package com.example.donorhub.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
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
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FeatuedAdapter.itemClickListener{
    private String fullName, userName, phoneNO, email, password, userType;
    RecyclerView featuredRecycler;

    SearchView searchView;
    RecyclerView.Adapter adapter;

    FeatuedAdapter myAdapter;
    FloatingActionButton fab;

    ArrayList<FeaturedHelperClass> featuredLocation = new ArrayList<>();

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ImageView menuIcon;

    CircleImageView circleImageView;
    DatabaseReference firebaseDatabase;

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
        searchView = findViewById(R.id.search_view);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);
                return true;
            }
        });

        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        userName = getIntent().getStringExtra("userName");
        phoneNO = getIntent().getStringExtra("phoneNo");
        password = getIntent().getStringExtra("password");
        userType = getIntent().getStringExtra("user");

        String userType2 = userType.toString();




        fab = findViewById(R.id.fab);

        if (userType2 == "Donor"){
            fab.setVisibility(View.GONE);
        }else {
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

    private void fileList(String newText) {
        ArrayList<FeaturedHelperClass> filteredList = new ArrayList<>();
        for (FeaturedHelperClass featuredHelperClass : featuredLocation){
            if(featuredHelperClass.getNgo_name().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(featuredHelperClass);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
        }else {

        }

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
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Request");
        myAdapter = new FeatuedAdapter(featuredLocation);
        featuredRecycler.setAdapter(myAdapter);
        firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    FeaturedHelperClass featuredHelperClass = dataSnapshot.getValue(FeaturedHelperClass.class);
                    featuredLocation.add(featuredHelperClass);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*firebaseDatabase.getReference("Request").child("Request").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    FeaturedHelperClass featuredHelperClass = snapshot.getValue(FeaturedHelperClass.class);
                    featuredLocation.add(featuredHelperClass);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        /*featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,"Radhika donation", "We are gathering Cloths for poor ladies in our locality. "));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_2,"Friend Zone", "We are gathering Cloths for poor ladies in our locality. "));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_3,"Global Helper", "We are gathering Cloths for poor ladies in our locality. "));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_4,"My People", "We are gathering Cloths for poor ladies in our locality. "));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donation,"Human Society", "We are gathering Cloths for poor ladies in our locality. "));
           */
       /* adapter = new FeatuedAdapter(featuredLocation);
        featuredRecycler.setAdapter(adapter);*/
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

    @Override
    public void onItemcheck(int position) {

    }
}
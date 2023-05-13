package com.example.donorhub.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.donorhub.Helperfile.FeatuedAdapter;
import com.example.donorhub.Helperfile.FeaturedHelperClass;
import com.example.donorhub.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ImageView menuIcon;

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



        navigationDrawer();

        //RecyclerView function call
        featuredRecycler();
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

        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,"Ngo2", "A donation is a gift for charity, humanitarian aid, or to benefit a cause. A donation may take various forms, including money, alms, services, or goods such as clothing, toys, food, or vehicles. A donation may satisfy medical needs such as blood or organs for transplant"));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,"Ngo2", "Hello i am a idiot"));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,"Ngo2", "Hello i am a idiot"));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,"Ngo2", "Hello i am a idiot"));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,"Ngo2", "Hello i am a idiot"));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,"Ngo2", "Hello i am a idiot"));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,"Ngo2", "Hello i am a idiot"));

        adapter = new FeatuedAdapter(featuredLocation);
        featuredRecycler.setAdapter(adapter);
    }


}
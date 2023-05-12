package com.example.donorhub.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.donorhub.Helperfile.FeatuedAdapter;
import com.example.donorhub.Helperfile.FeaturedHelperClass;
import com.example.donorhub.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {

    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

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

        //view function call
        featuredRecycler();
    }
    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        ArrayList<FeaturedHelperClass> featuredLocation = new ArrayList<>();

        featuredLocation.add(new FeaturedHelperClass(R.drawable.donate_1,"Ngo2", "Hello i am a idiot"));
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
package com.example.primaryschool.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.primaryschool.Fragment.Home_fragment;
import com.example.primaryschool.Fragment.LogFragment;
import com.example.primaryschool.Fragment.NoticeFragment;
import com.example.primaryschool.Fragment.ProfileFragment;
import com.example.primaryschool.R;
import com.example.primaryschool.Summary.District_Fragment;
import com.example.primaryschool.Summary.DivisionSummary;
import com.example.primaryschool.Summary.School_Fragment;
import com.example.primaryschool.Summary.Upazila_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {



    String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        admin = prefs.getString("user_address_type","defaultValue");

        setContentView(R.layout.activity_main3);


       BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
       bottomnav.setOnNavigationItemSelectedListener(navListener);
       if(admin.equals("Division"))
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                   new DivisionSummary()).commit();

       }
       if(admin.equals("District"))
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                   new District_Fragment()).commit();

       }
        if(admin.equals("Upazila"))
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Upazila_Fragment()).commit();

        }




    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch(menuItem.getItemId()){
                        case R.id.nav_home:

                            break;

                        case R.id.nav_graph:
                            selectedFragment = new LogFragment();
                            break;
                        case R.id.notification:
                        selectedFragment = new NoticeFragment();
                        break;
                        case R.id.nav_search:
                            selectedFragment = new ProfileFragment();
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}

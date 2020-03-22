package com.example.primaryschool.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.primaryschool.Division.DivisionActivity;
import com.example.primaryschool.R;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setTitle("নোটিশ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottom_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                Intent home = new Intent(NoticeActivity.this, HomeActivity.class);
                startActivity(home);

                return true;
            case R.id.nav_graph:
                Intent log = new Intent(NoticeActivity.this, LogActivity.class);
                startActivity(log);

                return true;
            case R.id.notification:



                return true;
            case R.id.nav_search:
                Intent profile = new Intent(NoticeActivity.this, ProfileActivity.class);
                startActivity(profile);


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

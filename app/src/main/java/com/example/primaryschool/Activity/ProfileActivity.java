package com.example.primaryschool.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.primaryschool.R;

public class ProfileActivity extends AppCompatActivity {
    String headMasterName,schoolname,eiinno, phoneno, emailid, totalstudent;
    Button addinfo;
    TextView tvprofileName,tvdesignation,tvschoolname,tveiin,tvphone,tvemail;

    SharedPreferences prefs;
    private final static String sharedstring = "olivineltd.com.primaryschooladmin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile_fargment);
        prefs = getSharedPreferences(sharedstring,MODE_PRIVATE);
        headMasterName = prefs.getString("username","defaultValue");
        //schoolname = prefs.getString("school_name_bn","defaultValue");
        //eiinno = prefs.getString("school_eiin_no","defaultValue");
        phoneno = prefs.getString("user_mobile","defaultValue");
        emailid = prefs.getString("user_email","defaultvalue");

        tvprofileName = findViewById(R.id.profileNameId);
        tvprofileName.setText(headMasterName);
        tvschoolname = findViewById(R.id.tvSchoolName);
        tvschoolname.setText(schoolname);
        tveiin = findViewById(R.id.eiinno);
        tveiin.setText(eiinno);
        tvphone = findViewById(R.id.phnNoId);
        tvphone.setText(phoneno);
        tvemail = findViewById(R.id.emailId);
        tvemail.setText(emailid);
        addinfo = findViewById(R.id.addinfo);

        addinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(ProfileActivity.this, SplashActivity.class);
                SharedPreferences preferences =getSharedPreferences(sharedstring, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(info);
                finish();

            }
        });
    }
}

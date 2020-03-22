package com.example.primaryschool.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.primaryschool.Activity.SplashActivity;
import com.example.primaryschool.R;


public class ProfileFragment extends Fragment {

    String headMasterName,schoolname,eiinno, phoneno, emailid, totalstudent;
    Button addinfo;
    TextView tvprofileName,tvdesignation,tvschoolname,tveiin,tvphone,tvemail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView =  inflater.inflate(R.layout.fragment_profile_fargment, container, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
// then you use
        headMasterName = prefs.getString("username","defaultValue");
        //schoolname = prefs.getString("school_name_bn","defaultValue");
        //eiinno = prefs.getString("school_eiin_no","defaultValue");
        phoneno = prefs.getString("user_mobile","defaultValue");
        emailid = prefs.getString("user_email","defaultvalue");

        tvprofileName = rootView.findViewById(R.id.profileNameId);
        tvprofileName.setText(headMasterName);
        tvschoolname = rootView.findViewById(R.id.tvSchoolName);
        tvschoolname.setText(schoolname);
        tveiin = rootView.findViewById(R.id.eiinno);
        tveiin.setText(eiinno);
        tvphone = rootView.findViewById(R.id.phnNoId);
        tvphone.setText(phoneno);
        tvemail = rootView.findViewById(R.id.emailId);
        tvemail.setText(emailid);


        /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        schoolid = prefs.getString("u_id", "Empty");*/
        addinfo = rootView.findViewById(R.id.addinfo);
        if(totalstudent.equals("true"))
        {
            addinfo.setVisibility(View.GONE);
        }
        addinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(getContext(), SplashActivity.class);
                startActivity(info);
                getActivity().finish();

            }
        });
        return rootView ;

    }

}

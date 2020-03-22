package com.example.primaryschool.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.primaryschool.Activity.AdminList;
import com.example.primaryschool.R;

import org.json.JSONException;
import org.json.JSONObject;


public class Home_fragment extends Fragment {
    SharedPreferences prefs;
    String user_address, user_address_type;
    String div;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        user_address = prefs.getString("user_address","defaultValue");
        user_address.toLowerCase();
        user_address_type = prefs.getString("user_address_type","defaultValue");


        getSummary();
        return rootView;



    }

    private void getSummary() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://192.168.10.108:1000/api/"+user_address_type+"/"+user_address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int id = jsonObject.getInt("division_id");
                                    prefs.edit().putString("division_id",String.valueOf(id)).apply();
                            Intent details = new Intent(getContext(), AdminList.class);
                            startActivity(details);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }


}

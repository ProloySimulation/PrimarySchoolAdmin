package com.example.primaryschool.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.primaryschool.Adapter.DistrictListAdpater;
import com.example.primaryschool.Model.District;
import com.example.primaryschool.Model.Log;
import com.example.primaryschool.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminList extends AppCompatActivity {
    SharedPreferences prefs;
    String division_id,division_name, id;
    String districtlist = "http://demo.olivineltd.com/primary_attendance/api/district/list/";
    TextView name;

    private RecyclerView mList;
    private GridLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<District> logList;
    private DistrictListAdpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        /*prefs = PreferenceManager.getDefaultSharedPreferences(this);
        division_id = prefs.getString("division_id","defaultValue");*/
        Intent division = getIntent();
        division_id = division.getStringExtra("div_id");
        division_name = division.getStringExtra("divname");
        mList = findViewById(R.id.rvLog);
        districtList();

        logList = new ArrayList<>();
        adapter = new DistrictListAdpater(this,logList);
        name = findViewById(R.id.name);
        name.setText(division_name);

        linearLayoutManager = new GridLayoutManager(this,2);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);

        mList.setAdapter(adapter);

    }

    private void districtList() {
        final ProgressDialog progressDialog = new ProgressDialog(AdminList.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(districtlist+division_id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);


                        String district_id =  jsonObject.getString("district_id");
                        String district_name_bn = jsonObject.getString("district_name_bn");



                        District log = new District(district_id,district_name_bn);


                        logList.add(log);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


}

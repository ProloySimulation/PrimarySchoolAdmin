package com.example.primaryschool.District;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.primaryschool.Division.DivAdapter.DistrictListAll_Div;
import com.example.primaryschool.Model.District;
import com.example.primaryschool.R;
import com.example.primaryschool.Util.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DistrictListByDis extends AppCompatActivity {
    SharedPreferences prefs;
    String districtid,districtname, id;
    String divisionid, divisionname;
    TextView name;


    private RecyclerView mList;
    private GridLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<District> logList;
    private DistrictListAll_Div adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        Intent district = getIntent();
        divisionid = district.getStringExtra("division_id");
        divisionname = district.getStringExtra("division_name_bn");
        getSupportActionBar().setTitle(divisionname);
        mList = findViewById(R.id.rvLog);
        districtList();

        name = findViewById(R.id.name);
        name.setText(districtname);
        logList = new ArrayList<>();
        adapter = new DistrictListAll_Div(this,logList);

        linearLayoutManager = new GridLayoutManager(this,2);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);

        mList.setAdapter(adapter);

    }

    private void districtList() {
        final ProgressDialog progressDialog = new ProgressDialog(DistrictListByDis.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Api.BASE_URL+Api.district_list+divisionid, new Response.Listener<JSONArray>() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_activity_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:

                return true;
            case R.id.nav_slideshow:

                return true;
            case R.id.nav_tools:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

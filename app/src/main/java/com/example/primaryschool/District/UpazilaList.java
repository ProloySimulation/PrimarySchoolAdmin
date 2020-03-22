package com.example.primaryschool.District;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.primaryschool.Adapter.DistrictListAdpater;

import com.example.primaryschool.Model.District;
import com.example.primaryschool.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpazilaList extends AppCompatActivity {
    SharedPreferences prefs;
    String districtid, id;
    String subdistrict = "http://demo.olivineltd.com/primary_attendance/api/upazila/list/";
    TextView txtview;

    private RecyclerView mList;
    private GridLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<District> logList;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        txtview = findViewById(R.id.name);

        /*prefs = PreferenceManager.getDefaultSharedPreferences(this);*/
        Intent district_id = getIntent();
        districtid = district_id.getStringExtra("district_id");

        mList = findViewById(R.id.rvLog);

        logList = new ArrayList<>();
        adapter = new DistrictListAdpater(this,logList);

        linearLayoutManager = new GridLayoutManager(this,2);

        mList.setAdapter(adapter);
        subdistrictList();
    }

    private void subdistrictList() {
        final ProgressDialog progressDialog = new ProgressDialog(UpazilaList.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(subdistrict+districtid, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);


                        String upazila_id =  jsonObject.getString("upazila_id");
                        String upazila_name_bn = jsonObject.getString("upazila_name_bn");
                        //prefs.edit().putString("upazila_id",String.valueOf(upazila_id)).apply();
                        txtview.setText(upazila_name_bn);

                        District log = new District(upazila_id,upazila_name_bn);


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

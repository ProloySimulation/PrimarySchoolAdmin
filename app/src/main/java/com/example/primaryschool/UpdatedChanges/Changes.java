package com.example.primaryschool.UpdatedChanges;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.primaryschool.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Changes extends AppCompatActivity {
    ArrayList<String> x;
    ArrayList<Entry> y;
    private LineChart mChart;
    public String TAG = "YOUR CLASS NAME";
    String summary_url = "http://demo.olivineltd.com/primary_attendance/api/district_summary/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changes);

        x = new ArrayList<String>();
        y = new ArrayList<Entry>();
        mChart = (LineChart) findViewById(R.id.linegraph);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);

        mChart.setPinchZoom(true);


        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);


        xl.setAvoidFirstLastClipping(true);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setInverted(false);



        drawChart();
    }

    private void getSummary() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, summary_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);





                            JSONArray jsonArray = jsonObject.getJSONArray("chartInfo");
                            //Log.d("attendance_date",String.valueOf(jsonArray.length()));

                            for(int i=0;i<jsonArray.length();i++)
                            {




                               //String date =  jsonArray.getJSONObject("attendance_date");
                               String  percentage = jsonObject.getString("attendance_percentage");
                                //Log.d("date",date);
                                //int d = Integer.parseInt(date);
                                //float part = Float.parseFloat(percentage);
                                /*x.add(new Entry(date,i));
                                y.add(String.format("%.2f", percentage));*/




                            }
                           /* LineDataSet set1 = new LineDataSet(x, "NAV Data Value");
                            set1.setLineWidth(1.5f);

                            LineData data = new LineData(y, set1);
                            mChart.setData(data);
                            mChart.invalidate();*/






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(Changes.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        // then you use
        //division_id = prefs.getString("school_teacher_name","defaultValue");




    }

    private void drawChart() {

        String tag_string_req = "req_chart";

        StringRequest strReq = new StringRequest(Request.Method.GET, summary_url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, "Response: " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //String id = jsonObject.getString("id");
                            JSONArray jsonArray = jsonObject.getJSONArray("chartInfo");
                            for (int i = 0; i < jsonArray.length(); i++) {


                              JSONObject  jsonObject1 = jsonArray.getJSONObject(i);
                                String dat = jsonObject1.getString("attendance_date");
                                //String date = String.valueOf(dat);
                                float percent = Float.parseFloat(jsonObject1.getString("attendance_percentage"));
                                Log.d(TAG, "Percentage: " + percent);

                                //Log.d("date",date);
                               x.add(dat);
                              y.add(new Entry(percent,i));

                            }
                           LineDataSet set1 = new LineDataSet(y, "Summary of Attendance");
                            set1.setLineWidth(1.5f);

                            LineData data = new LineData(x, set1);
                            mChart.setData(data);
                            mChart.invalidate();

                            set1.setLineWidth(2f);
                            set1.setCircleSize(5f);
                            set1.setColor(getResources().getColor(R.color.colorPrimaryDark));
                            set1.setCircleColor(getResources().getColor(R.color.colorPrimaryDark));
                            set1.setFillColor(getResources().getColor(R.color.colorAccent));
                            set1.setDrawCircleHole(true);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("gowrab", "Error: " + error.getMessage());
            }
        });
        strReq.setRetryPolicy(new RetryPolicy() {

            @Override
            public void retry(VolleyError arg0) throws VolleyError {
            }

            @Override
            public int getCurrentTimeout() {
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }
        });
        strReq.setShouldCache(false);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);
    }
}

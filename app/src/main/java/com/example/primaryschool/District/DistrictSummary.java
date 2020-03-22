package com.example.primaryschool.District;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.primaryschool.Division.DistrictListByDiv;
import com.example.primaryschool.Division.UpazilaList_Div;
import com.example.primaryschool.R;
import com.example.primaryschool.Util.Api;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DistrictSummary extends AppCompatActivity  {
    String districtid,districtname,admin_id,division_name,today_date, total_student, total_male, total_female, total_male_present, total_female_present, total_male_absent, total_female_absent;

    TextView divname, tv_date, total_student_no, totalmale, totalfemale, totalmalepresent, totalfemalepresent, totalmaleabsent, totalfemaleabsent;
    private LineChart mChart;

    SharedPreferences prefs;
    String user_type,user_id;
    Button btn;
    String date;
    String percentage;
    Date datedata;
    ArrayList<Entry> yvalues;
    private static DecimalFormat df = new DecimalFormat("0.00");

    ArrayList<String> x;
    ArrayList<Entry> y;

    public String TAG = "YOUR CLASS NAME";
    private final static String sharedstring = "olivineltd.com.primaryschooladmin";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(sharedstring,MODE_PRIVATE);
        user_type = prefs.getString("user_type","");
        user_id = prefs.getString("users_id","");
        setContentView(R.layout.nav_activity);
        Intent district_id = getIntent();
        districtid = district_id.getStringExtra("district_id");
        districtname = district_id.getStringExtra("district_name");


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






        tv_date = findViewById(R.id.today_date);
        total_student_no = findViewById(R.id.total_student_no);
        totalmale = findViewById(R.id.total_male);
        totalfemale = findViewById(R.id.total_female);
        totalmalepresent = findViewById(R.id.total_present_male_no);
        totalfemalepresent = findViewById(R.id.total_present_female_no);
        totalmaleabsent = findViewById(R.id.total_absent_male_no);
        totalfemaleabsent = findViewById(R.id.total_absent_female_no);
        btn = findViewById(R.id.viewmore);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        today_date = simpleDateFormat.format(new Date()).replaceAll("0", "০").replaceAll("1", "১").replaceAll("2", "২").replaceAll("3", "৩").replaceAll("4", "৪").replaceAll("5", "৫").replaceAll("6", "৬").replaceAll("7", "৭").replaceAll("8", "৮").replaceAll("9", "৯");
        tv_date.setText(today_date);
/*        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();*/
         //graph = (GraphView) findViewById(R.id.graph);



       getDisInfo();



    }



    private void getDisInfo() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.BASE_URL+Api.dist_details+districtid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            division_name = jsonObject.getString("district_name_bn");
                            //divname.setText(division_name);


                            getSummary();
                            btn.setVisibility(View.VISIBLE);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent adminlist = new Intent(DistrictSummary.this, UpazilaList_Div.class);
                                    adminlist.putExtra("district_id",districtid);
                                    adminlist.putExtra("district_name",districtname);
                                    startActivity(adminlist);
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(DistrictSummary.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void getSummary() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.BASE_URL+Api.district_sum+districtid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);


                            total_student = jsonObject.getString("studentInTotal").replaceAll("0", "০").replaceAll("1", "১").replaceAll("2", "২").replaceAll("3", "৩").replaceAll("4", "৪").replaceAll("5", "৫").replaceAll("6", "৬").replaceAll("7", "৭").replaceAll("8", "৮").replaceAll("9", "৯");
                            total_male = jsonObject.getString("total_boys").replaceAll("0", "০").replaceAll("1", "১").replaceAll("2", "২").replaceAll("3", "৩").replaceAll("4", "৪").replaceAll("5", "৫").replaceAll("6", "৬").replaceAll("7", "৭").replaceAll("8", "৮").replaceAll("9", "৯");
                            total_female = jsonObject.getString("total_girls").replaceAll("0", "০").replaceAll("1", "১").replaceAll("2", "২").replaceAll("3", "৩").replaceAll("4", "৪").replaceAll("5", "৫").replaceAll("6", "৬").replaceAll("7", "৭").replaceAll("8", "৮").replaceAll("9", "৯");
                            total_male_present = jsonObject.getString("total_present_boys").replaceAll("0", "০").replaceAll("1", "১").replaceAll("2", "২").replaceAll("3", "৩").replaceAll("4", "৪").replaceAll("5", "৫").replaceAll("6", "৬").replaceAll("7", "৭").replaceAll("8", "৮").replaceAll("9", "৯");
                            total_female_present = jsonObject.getString("total_present_girls").replaceAll("0", "০").replaceAll("1", "১").replaceAll("2", "২").replaceAll("3", "৩").replaceAll("4", "৪").replaceAll("5", "৫").replaceAll("6", "৬").replaceAll("7", "৭").replaceAll("8", "৮").replaceAll("9", "৯");
                            total_male_absent = jsonObject.getString("total_absent_boys").replaceAll("0", "০").replaceAll("1", "১").replaceAll("2", "২").replaceAll("3", "৩").replaceAll("4", "৪").replaceAll("5", "৫").replaceAll("6", "৬").replaceAll("7", "৭").replaceAll("8", "৮").replaceAll("9", "৯");
                            total_female_absent = jsonObject.getString("total_absent_girls").replaceAll("0", "০").replaceAll("1", "১").replaceAll("2", "২").replaceAll("3", "৩").replaceAll("4", "৪").replaceAll("5", "৫").replaceAll("6", "৬").replaceAll("7", "৭").replaceAll("8", "৮").replaceAll("9", "৯");

                            //division_name = jsonObject.getString("division_name_bn");
                            //divname.setText(division_name);

                            total_student_no.setText(total_student);
                            totalmale.setText(total_male);
                            totalfemale.setText(total_female);
                            if(total_male_present.equals("null"))
                            {
                                totalmalepresent.setText("০");
                            }
                            else{
                                totalmalepresent.setText(total_male_present);
                            }

                            if(total_male_absent.equals("null"))
                            {
                                totalmaleabsent.setText("০");
                            }
                            else{
                                totalmaleabsent.setText(total_male_absent);
                            }

                            if(total_female_present.equals("null"))
                            {
                                totalfemalepresent.setText("০");
                            }
                            else{
                                totalfemalepresent.setText(total_female_present);
                            }

                            if(total_female_absent.equals("null"))
                            {
                                totalfemaleabsent.setText("০");
                            }
                            else {
                                totalfemaleabsent.setText(total_female_absent);
                            }


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






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(DistrictSummary.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        // then you use
        //division_id = prefs.getString("school_teacher_name","defaultValue");




    }




}

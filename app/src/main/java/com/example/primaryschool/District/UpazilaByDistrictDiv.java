package com.example.primaryschool.District;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.primaryschool.Division.SchoolByUpzilaDiv;
import com.example.primaryschool.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpazilaByDistrictDiv extends AppCompatActivity {
    String subdistrictid,subdistrict_name,subdistrict_id,admin_id,division_name,today_date, total_student, total_male, total_female, total_male_present, total_female_present, total_male_absent, total_female_absent;
    String div_url = "http://demo.olivineltd.com/primary_attendance/api/upazila/";
    String summary_url = "http://demo.olivineltd.com/primary_attendance/api/division_summary/";
    TextView divname, tv_date, total_student_no, totalmale, totalfemale, totalmalepresent, totalfemalepresent, totalmaleabsent, totalfemaleabsent;

    SharedPreferences prefs;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_division);
        Intent district_id = getIntent();
        subdistrictid = district_id.getStringExtra("district_id");
        subdistrict_name = district_id.getStringExtra("district_name");




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
        Calendar calendar = Calendar.getInstance();
       /* Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(d1, 1),
                new DataPoint(d2, 5),
                new DataPoint(d3, 3),

        });
        graph.addSeries(series);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

// set manual x bounds to have nice steps
        graph.getViewport().setMinX(d1.getTime());
        graph.getViewport().setMaxX(d3.getTime());
        graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);*/


        getDivInfo();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminlist = new Intent(UpazilaByDistrictDiv.this, SchoolByUpzilaDiv.class);
                adminlist.putExtra("sub_district_id",subdistrictid);
                adminlist.putExtra("sub_district_name",subdistrict_name);
                startActivity(adminlist);
            }
        });


    }

   private void getDivInfo() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, div_url+subdistrictid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            subdistrict_id = jsonObject.getString("upazila_id");
                            division_name = jsonObject.getString("upazila_name_bn");



                            getSummary(subdistrict_id);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(UpazilaByDistrictDiv.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void getSummary(String div) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, summary_url+div,
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



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(UpazilaByDistrictDiv.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        // then you use
        //division_id = prefs.getString("school_teacher_name","defaultValue");




    }


}

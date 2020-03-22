package com.example.primaryschool.District.DisAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.primaryschool.Division.SchoolSummary_Div;
import com.example.primaryschool.Model.School;
import com.example.primaryschool.R;

import java.util.List;

public class SchoolListAdapter_Dis extends  RecyclerView.Adapter<SchoolListAdapter_Dis.ViewHolder> {

    private Context context;
    private List<School> logList;

    public SchoolListAdapter_Dis(Context context, List<School> list) {
        this.context = context;
        this.logList = list;
    }

    @Override
    public SchoolListAdapter_Dis.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_log, parent, false);
        return new SchoolListAdapter_Dis.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SchoolListAdapter_Dis.ViewHolder holder, final int position) {
        School school = logList.get(position);

        holder.tvDate.setText(school.getSchoolname());
        holder.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent districtsummary = new Intent(context, SchoolSummary_Div.class);
                districtsummary.putExtra("school_id",logList.get(position).getSchoolid());
                districtsummary.putExtra("school_name",logList.get(position).getSchoolname());
                context.startActivity(districtsummary);
            }
        });
        /*holder.textRating.setText(String.valueOf(log.getDate()));
        holder.textYear.setText(String.valueOf(log.getTotalFAbsent()));
        holder.textYear.setText(String.valueOf(log.getTotalMAbsent()));
        holder.textYear.setText(String.valueOf(log.getTotalMalePresent()));
        holder.textYear.setText(String.valueOf(log.getTotalFemalePresent()));*/
//        holder.textYear.setText(String.valueOf(log.getTotalFAbsent()));

    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate, textRating, textYear;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            //     textRating = itemView.findViewById(R.id.main_rating);
            //    textYear = itemView.findViewById(R.id.main_year);

        }
    }
}


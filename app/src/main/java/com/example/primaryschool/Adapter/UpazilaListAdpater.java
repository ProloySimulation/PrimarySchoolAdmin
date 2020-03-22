package com.example.primaryschool.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.primaryschool.Division.UpazilaSummary_Div;
import com.example.primaryschool.Model.District;
import com.example.primaryschool.R;

import java.util.List;

public class

UpazilaListAdpater extends  RecyclerView.Adapter<UpazilaListAdpater.ViewHolder> {

    private Context context;
    private List<District> logList;

    public UpazilaListAdpater(Context context, List<District> list) {
        this.context = context;
        this.logList = list;
    }

    @Override
    public UpazilaListAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_log, parent, false);
        return new UpazilaListAdpater.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UpazilaListAdpater.ViewHolder holder, final int position) {
        District district = logList.get(position);

        holder.tvDate.setText(district.getDistrictname());
        holder.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schoolsummary = new Intent(context, UpazilaSummary_Div.class);
                schoolsummary.putExtra("upazila_id",logList.get(position).getDistrictid());
                schoolsummary.putExtra("upazila_name",logList.get(position).getDistrictname());
                context.startActivity(schoolsummary);

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

package com.sagar.android.officetimer;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewHolder> {

    ArrayList<Times> times;

    public TodayAdapter(ArrayList<Times> times) {
        this.times = times;
    }

    @Override
    public TodayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.times_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodayAdapter.ViewHolder holder, int position) {
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        holder.row_startTime.setText(timeFormat.format(times.get(position).getStartTime()));
        holder.row_stopTime.setText(timeFormat.format(times.get(position).getStopTime()));
        holder.row_timeSpent.setText(dateFormat.format(times.get(position).getStartTime()));
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView row_startTime;
        TextView row_stopTime;
        TextView row_timeSpent;

        public ViewHolder(View itemView) {
            super(itemView);
            row_startTime = itemView.findViewById(R.id.startTime_row);
            row_stopTime = itemView.findViewById(R.id.stopTime_row);
            row_timeSpent = itemView.findViewById(R.id.timeSpent_row);
        }
    }

}

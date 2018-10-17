package com.sagar.android.officetimer;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewHolder> {

    List<Times> times;

    public TodayAdapter(List<Times> times) {
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
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date mStartTime = times.get(position).getStartTime();
        Date mStopTime = times.get(position).getStopTime();

        //create a time spent string
        Long timeDifMillis = mStopTime.getTime() - mStartTime.getTime();
        Long hours = timeDifMillis/(1000*60*60);
        Long minutes = (timeDifMillis/1000 - (hours*60*60))/60;
        Long seconds = timeDifMillis/1000 - (hours*60*60 + minutes*60);
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String hour = decimalFormat.format(hours);
        String minute = decimalFormat.format(minutes);
        String second = decimalFormat.format(seconds);
        String timeSpent = hour+":"+minute+":"+second;

        holder.row_startTime.setText(timeFormat.format(times.get(position).getStartTime()));
        holder.row_stopTime.setText(timeFormat.format(times.get(position).getStopTime()));
        holder.row_timeSpent.setText(timeSpent);
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

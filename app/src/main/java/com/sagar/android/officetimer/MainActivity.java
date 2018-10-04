package com.sagar.android.officetimer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TextView mTextMessage;
    AnalogClock mAnalogClock;
    private TextView startTimeTextView;
    private TextView stopTimeTextView;
    private Button mStartButton;
    private Button mStopButton;
    public Date mStartTime;
    public Date mStopTime;
    long mTimeSpent;
    ArrayList<Times> todayTimeList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    Intent dashboardIntent = new Intent(MainActivity.this, DashboardActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("timeData", todayTimeList);
                    dashboardIntent.putExtras(b);
                    startActivity(dashboardIntent);
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        mAnalogClock = findViewById(R.id.analog_clock);
        startTimeTextView = findViewById(R.id.timeCounter_textView);
        stopTimeTextView = findViewById(R.id.timeCounter2_textView);
        mStartButton =  findViewById(R.id.start_button);
        mStopButton = findViewById(R.id.stop_button);
        todayTimeList = new ArrayList<>();

        /**Date demoDate = Calendar.getInstance().getTime();

        Times time1 = new  Times();
        Times time2 = new Times();
        time1.setStartTime(demoDate);
        time1.setStopTime(demoDate);
        time2.setStartTime(demoDate);
        time2.setStopTime(demoDate);

        todayTimeList.add(time1);
        todayTimeList.add(time2);
         */


        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClock();
                mStartButton.setVisibility(View.INVISIBLE);
                mStopButton.setVisibility(View.VISIBLE);
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopClock();
                mStartButton.setVisibility(View.VISIBLE);
                mStopButton.setVisibility(View.INVISIBLE);
            }
        });
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void startClock() {
        mStartTime = Calendar.getInstance().getTime();
        DateFormat date = new SimpleDateFormat("hh:mm:ss a");
        //date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String startTimeString = date.format(mStartTime);
        startTimeTextView.setText(startTimeString);
    }

    private void stopClock() {
        mStopTime = Calendar.getInstance().getTime();
        DateFormat date = new SimpleDateFormat("hh:mm:ss a");
        String stopTimeString = date.format(mStopTime);
        stopTimeTextView.setText(stopTimeString);
        Times newTime = new Times();
        newTime.setStartTime(mStartTime);
        newTime.setStopTime(mStopTime);
        //Log.d(TAG, "Start Time: " + newTime.getStartTime());
        //Log.d(TAG, "Stop Time: " + newTime.getStopTime());
        todayTimeList.add(newTime);
    }

}

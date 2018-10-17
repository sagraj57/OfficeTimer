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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    TimesDatabase db;

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
        db = TimesDatabase.getTimesDatabase(this);

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
        DateFormat time = new SimpleDateFormat("hh:mm:ss a");
        String stopTimeString = time.format(mStopTime);
        stopTimeTextView.setText(stopTimeString);
        Times newTime = new Times();
        newTime.setStartTime(mStartTime);
        newTime.setStopTime(mStopTime);

        // set Date
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        String dateColumn = date.format(mStopTime);
        newTime.setDateColumn(dateColumn);
        DatabaseInitializer.populateAsync(db, newTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TimesDatabase.destroyInstance();
    }
}

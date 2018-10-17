package com.sagar.android.officetimer;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    RecyclerView tRecyclerView;
    RecyclerView.Adapter adapter;
    List<Times> timesList;
    Button datePicker;
    TextView chosenDateTextview;
    String chosenDate;
    String chosenDateString;
    TimesDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        chosenDateTextview = findViewById(R.id.selectedDate_textview);
        chosenDateString = chosenDateTextview.getText().toString();
        // Get List Data from Database
        db = TimesDatabase.getTimesDatabase(this);
        timesList = db.timesDao().getTimesByDate(chosenDate);
        // recyclerview and adapter
        datePicker = findViewById(R.id.date_picker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        BottomNavigationView navigation = findViewById(R.id.navigation2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public List<Times> creatlistByDate(Context context, String givenDate) {
        db = TimesDatabase.getTimesDatabase(this);
        List<Times> newTimesList = db.timesDao().getTimesByDate(givenDate);
        return newTimesList;
    }
    public void updateRecycler(List<Times> timesList2) {
        tRecyclerView = findViewById(R.id.recyclerView);
        tRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodayAdapter(timesList2);
        tRecyclerView.setAdapter(adapter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
}

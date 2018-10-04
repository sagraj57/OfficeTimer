package com.sagar.android.officetimer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView tRecyclerView;
        RecyclerView.Adapter adapter;
        ArrayList<Times> todayTimeList;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Get Array List from Intent Data
        Bundle getB = getIntent().getExtras();
        todayTimeList = (ArrayList<Times>) getB.getSerializable("timeData");
        // recyclerview and adapter
        tRecyclerView = findViewById(R.id.today_recyclerView);
        tRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodayAdapter(todayTimeList);
        tRecyclerView.setAdapter(adapter);

        BottomNavigationView navigation = findViewById(R.id.navigation2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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

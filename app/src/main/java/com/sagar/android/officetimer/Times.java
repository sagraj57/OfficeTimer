package com.sagar.android.officetimer;

import java.util.Date;

public class Times {
    private int id;

    public Date getStartTime() {
        return startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    private Date startTime;
    private Date stopTime;


}

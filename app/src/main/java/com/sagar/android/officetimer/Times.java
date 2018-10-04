package com.sagar.android.officetimer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "timeTable")
public class Times implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "start_time")
    private Date startTime;

    @ColumnInfo(name = "stop_time")
    private Date stopTime;

    public Date getDateColumn() {
        return dateColumn;
    }

    public void setDateColumn(Date dateColumn) {
        this.dateColumn = dateColumn;
    }

    @ColumnInfo(name = "date")
    private Date dateColumn;

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


}

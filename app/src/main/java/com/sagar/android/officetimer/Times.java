package com.sagar.android.officetimer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "timeTable")
public class Times implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters({Converters.class})
    @ColumnInfo(name = "start_time")
    private Date startTime;

    @TypeConverters({Converters.class})
    @ColumnInfo(name = "stop_time")
    private Date stopTime;

    @ColumnInfo(name = "date")
    private String dateColumn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateColumn() {
        return dateColumn;
    }

    public void setDateColumn(String dateColumn) {
        this.dateColumn = dateColumn;
    }

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

package com.sagar.android.officetimer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface TimesDao {
    @Query("SELECT * FROM TIMETABLE")
    List<Times> getAllUsers();

    @Insert
    void insertAll(Times... times);

    @Query("SELECT * FROM timeTable WHERE date LIKE :givenDate")
    List<Times> getTimesByDate(Date givenDate);
}

package com.sagar.android.officetimer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = Times.class, version = 1)
public abstract class TimesDatabase extends RoomDatabase {
    private static TimesDatabase INSTANCE;
    public abstract TimesDao timesDao();

    public static TimesDatabase getTimesDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TimesDatabase.class, "outTimes")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
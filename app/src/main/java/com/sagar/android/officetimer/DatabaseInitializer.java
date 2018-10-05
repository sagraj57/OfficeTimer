package com.sagar.android.officetimer;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

public class DatabaseInitializer {
    public static void populateAsync(@NonNull final TimesDatabase db, Times times) {
        PopulateDbAsync task = new PopulateDbAsync(db, times);
        task.execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TimesDatabase mDb;
        private final Times mTimes;

        PopulateDbAsync(TimesDatabase db, Times times) {
            mDb = db;
            mTimes = times;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDb.timesDao().insertAll(mTimes);
            return null;
        }
    }
}

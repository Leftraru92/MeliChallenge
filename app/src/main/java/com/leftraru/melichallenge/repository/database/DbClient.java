package com.leftraru.melichallenge.repository.database;

import android.content.Context;

import com.leftraru.melichallenge.repository.database.interfaces.AppDatabase;

import androidx.room.Room;

public class DbClient {

    private static DbClient mInstance;
    private static String DB_NAME = "meli.db";

    private static AppDatabase appDatabase;
    private DbClient() {

    }

    public static synchronized DbClient getInstance() {
        return mInstance;
    }

    public static void setInstance(Context context) {
        if(mInstance == null) {
            synchronized (AppDatabase.class) {
                if (mInstance == null) {
                    mInstance = new DbClient();
                    appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
                }
            }
        }

    }

    public AppDatabase getAppDatabase() { return appDatabase; }

}

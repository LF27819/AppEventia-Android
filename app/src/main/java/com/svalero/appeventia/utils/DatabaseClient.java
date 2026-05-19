package com.svalero.appeventia.utils;

import android.content.Context;

import androidx.room.Room;

import com.svalero.appeventia.database.AppDatabase;

public class DatabaseClient {

    private static AppDatabase database;

    public static AppDatabase getInstance(Context context) {

        if (database == null) {

            database = Room.databaseBuilder(
                            context,
                            AppDatabase.class,
                            "eventia-db-app"
                    )
                    .allowMainThreadQueries()
                    .build();
        }

        return database;
    }
}
package com.example.project_todolist_hamidi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 2, exportSchema = false, entities = {Task.class})
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase(Context context) {

        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder((context.getApplicationContext()), AppDatabase.class, "App_db1").allowMainThreadQueries().build();
        }
        return appDatabase;
    }

    public abstract TaskDao getTaskDao();
}
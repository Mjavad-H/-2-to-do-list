package com.example.project_todolist_hamidi;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    int id;


    String title;

    String time;


    public Task(String title, String time) {
        this.title = title;
        this.time = time;
    }
}

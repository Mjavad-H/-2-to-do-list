package com.example.project_todolist_hamidi;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    public static TaskDao taskdao;
    private RecyclerView rv_main;

    private Button btn_main;

    private MainAdapter mainAdapter;


    @Override
    protected void onResume() {
        super.onResume();
        mainAdapter.addTask(taskdao.getAll());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_main = findViewById(R.id.rv_main);
        btn_main = findViewById(R.id.btn_main);
        taskdao = AppDatabase.getAppDatabase(this).getTaskDao();

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        mainAdapter = new MainAdapter(taskdao.getAll());

        rv_main.setLayoutManager(new LinearLayoutManager(this));

        rv_main.setAdapter(mainAdapter);
    }
}
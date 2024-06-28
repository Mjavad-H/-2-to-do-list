package com.example.project_todolist_hamidi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {


    private EditText edt_title;
    private EditText edt_time;
    private Button btn_add;
    private TaskDao taskDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_main);
        taskDao = AppDatabase.getAppDatabase(this).getTaskDao();


        edt_title = findViewById(R.id.edt_title);
        edt_time = findViewById(R.id.edt_time);
        btn_add = findViewById(R.id.btn_add);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task tak = new Task(edt_title.getText().toString(),
                        edt_time.getText().toString());
                taskDao.insertTask(tak);
                finish();
            }
        });
    }
}
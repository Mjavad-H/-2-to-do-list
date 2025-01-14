package com.example.project_todolist_hamidi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static TaskDao taskdao;
    private RecyclerView rv_main;
    private Button btn_main, btn_backup, btn_restore;
    private MainAdapter mainAdapter;
    private SearchView searchView;

    @Override
    protected void onResume() {
        super.onResume();
        mainAdapter.addTask(taskdao.getAll());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_main = findViewById(R.id.recyclerView);
        btn_main = findViewById(R.id.btn_main);
        btn_backup = findViewById(R.id.btn_backup);
        btn_restore = findViewById(R.id.btn_restore);
        searchView = findViewById(R.id.searchView);

        taskdao = AppDatabase.getAppDatabase(this).getTaskDao();

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        btn_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backupTasks();
            }
        });

        btn_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreTasks();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTasks(newText);
                return true;
            }
        });

        mainAdapter = new MainAdapter(taskdao.getAll());
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        rv_main.setAdapter(mainAdapter);
    }

    private void backupTasks() {
        // کد پشتیبان‌گیری (مثلاً ذخیره در SharedPreferences یا فایل)
        // این بخش بستگی به نحوه‌ی پیاده‌سازی شما دارد.
    }

    private void restoreTasks() {
        // کد بازیابی (مثلاً خواندن از SharedPreferences یا فایل)
        // این بخش بستگی به نحوه‌ی پیاده‌سازی شما دارد.
    }

    private void filterTasks(String query) {
        List<Task> filteredTasks = taskdao.searchTasks(query);
        mainAdapter.addTask(filteredTasks);
    }
}
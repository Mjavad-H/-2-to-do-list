package com.example.project_todolist_hamidi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class EditTaskActivity extends AppCompatActivity {

    private EditText titleInput, descriptionInput, startDateInput, endDateInput, reminderInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        startDateInput = findViewById(R.id.startDateInput);
        endDateInput = findViewById(R.id.endDateInput);
        reminderInput = findViewById(R.id.reminderInput);
        saveButton = findViewById(R.id.saveButton);

        // دریافت داده‌های کار از Intent
        String taskTitle = getIntent().getStringExtra("taskTitle");
        String taskDescription = getIntent().getStringExtra("taskDescription");
        String taskStartDate = getIntent().getStringExtra("taskStartDate");
        String taskEndDate = getIntent().getStringExtra("taskEndDate");
        String taskReminder = getIntent().getStringExtra("taskReminder");

        titleInput.setText(taskTitle);
        descriptionInput.setText(taskDescription);
        startDateInput.setText(taskStartDate);
        endDateInput.setText(taskEndDate);
        reminderInput.setText(taskReminder);

        // انتخاب تاریخ شروع
        startDateInput.setOnClickListener(v -> showDatePicker(startDateInput));

        // انتخاب تاریخ پایان
        endDateInput.setOnClickListener(v -> showDatePicker(endDateInput));

        // انتخاب زمان یادآوری
        reminderInput.setOnClickListener(v -> showDatePicker(reminderInput));

        // ذخیره‌ی تغییرات
        saveButton.setOnClickListener(v -> {
            String newTitle = titleInput.getText().toString();
            String newDescription = descriptionInput.getText().toString();
            String newStartDate = startDateInput.getText().toString();
            String newEndDate = endDateInput.getText().toString();
            String newReminder = reminderInput.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("newTitle", newTitle);
            resultIntent.putExtra("newDescription", newDescription);
            resultIntent.putExtra("newStartDate", newStartDate);
            resultIntent.putExtra("newEndDate", newEndDate);
            resultIntent.putExtra("newReminder", newReminder);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private void showDatePicker(final EditText dateInput) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> {
                    String date = year1 + "/" + (month1 + 1) + "/" + dayOfMonth;
                    dateInput.setText(date);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
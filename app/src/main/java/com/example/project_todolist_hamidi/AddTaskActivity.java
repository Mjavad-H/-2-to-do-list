package com.example.project_todolist_hamidi;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    private EditText titleInput, descriptionInput, startDateInput, endDateInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        startDateInput = findViewById(R.id.startDateInput);
        endDateInput = findViewById(R.id.endDateInput);
        saveButton = findViewById(R.id.saveButton);

        // انتخاب تاریخ شروع
        startDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(startDateInput);
            }
        });

        // انتخاب تاریخ پایان
        endDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(endDateInput);
            }
        });

        // ذخیره‌ی کار
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void showDatePicker(final EditText dateInput) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = year + "/" + (month + 1) + "/" + dayOfMonth;
                        dateInput.setText(date);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void saveTask() {
        String title = titleInput.getText().toString();
        String description = descriptionInput.getText().toString();
        String startDate = startDateInput.getText().toString();
        String endDate = endDateInput.getText().toString();

        if (title.isEmpty() || description.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(this, "لطفاً تمام فیلدها را پر کنید.", Toast.LENGTH_SHORT).show();
            return;
        }

        // ذخیره‌ی کار در دیتابیس
        Task task = new Task(title, description, startDate, endDate);
        MainActivity.taskdao.insertTask(task);

        // افزودن رویداد به تقویم
        long startTime = System.currentTimeMillis(); // زمان شروع (میلی‌ثانیه)
        long endTime = startTime + (60 * 60 * 1000); // زمان پایان (۱ ساعت بعد)
        addEventToCalendar(title, description, startTime, endTime);

        Toast.makeText(this, "کار با موفقیت ذخیره شد.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void addEventToCalendar(String title, String description, long startTime, long endTime) {
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.TITLE, title);
        values.put(CalendarContract.Events.DESCRIPTION, description);
        values.put(CalendarContract.Events.DTSTART, startTime);
        values.put(CalendarContract.Events.DTEND, endTime);
        values.put(CalendarContract.Events.CALENDAR_ID, 1); // ID تقویم پیش‌فرض
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Tehran");

        Uri uri = getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);

        if (uri != null) {
            Toast.makeText(this, "رویداد به تقویم اضافه شد.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "خطا در اضافه کردن رویداد به تقویم.", Toast.LENGTH_SHORT).show();
        }
    }
}
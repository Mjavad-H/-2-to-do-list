package com.example.project_todolist_hamidi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    List<Task> tasks = new ArrayList<>();

    public MainAdapter(List<Task> value) {
        tasks = value;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MainViewHolder(view);
    }

    public void addTask(List<Task> value) {
        tasks = value;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.setData(task.title, task.description, task.dueDate, task.startDate, task.endDate, task.reminder);

        // حذف کار با LongClick
        holder.root_item.setOnLongClickListener(v -> {
            MainActivity.taskdao.deleteTask(tasks.get(holder.getAdapterPosition()));
            tasks.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            return false;
        });

        // ویرایش کار با کلیک روی دکمه‌ی ویرایش
        holder.editButton.setOnClickListener(v -> {
            // کد باز کردن صفحه‌ی ویرایش
        });

        // حذف کار با کلیک روی دکمه‌ی حذف
        holder.deleteButton.setOnClickListener(v -> {
            MainActivity.taskdao.deleteTask(tasks.get(holder.getAdapterPosition()));
            tasks.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_title, txt_description, txt_dueDate, txt_startDate, txt_endDate, txt_reminder;
        private ImageButton editButton, deleteButton;
        private View root_item;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.taskTitle);
            txt_description = itemView.findViewById(R.id.taskDescription);
            txt_dueDate = itemView.findViewById(R.id.taskDueDate);
            txt_startDate = itemView.findViewById(R.id.taskStartDate);
            txt_endDate = itemView.findViewById(R.id.taskEndDate);
            txt_reminder = itemView.findViewById(R.id.taskReminder);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            root_item = itemView.findViewById(R.id.root_item);
        }

        public void setData(String title, String description, String dueDate, String startDate, String endDate, String reminder) {
            txt_title.setText(title);
            txt_description.setText(description);
            txt_dueDate.setText("تاریخ انجام: " + dueDate);
            txt_startDate.setText("شروع: " + startDate);
            txt_endDate.setText("پایان: " + endDate);
            txt_reminder.setText("یادآوری: " + reminder);
        }
    }
}
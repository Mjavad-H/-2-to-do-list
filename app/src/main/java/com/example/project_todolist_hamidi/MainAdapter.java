package com.example.project_todolist_hamidi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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


    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MainAdapter.MainViewHolder(view);
          }


    public void addTask(List<Task> value) {
        tasks = value;
        notifyDataSetChanged();
    }


    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        holder.setData(tasks.get(position).title, tasks.get(position).time);


        holder.root_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                MainActivity.taskdao.deleteTask(tasks.get(holder.getAdapterPosition()));
                tasks.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {


        private TextView txt_time;

        private TextView txt_title;

        private LinearLayout root_item;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_title = itemView.findViewById(R.id.txt_title);
            root_item = itemView.findViewById(R.id.root_item);

        }

        public void setData(String title, String time) {
            txt_title.setText(title);
            txt_time.setText(time);
        }
    }

}

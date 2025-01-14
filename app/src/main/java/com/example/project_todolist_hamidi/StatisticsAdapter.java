package com.example.project_todolist_hamidi;

public class StatisticsAdapter {
    import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

    public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {

        private List<StatisticItem> statisticItems;

        public StatisticsAdapter(List<StatisticItem> statisticItems) {
            this.statisticItems = statisticItems;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_statistic, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            StatisticItem item = statisticItems.get(position);
            holder.titleTextView.setText(item.getTitle());
            holder.valueTextView.setText(item.getValue());
        }

        @Override
        public int getItemCount() {
            return statisticItems.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView titleTextView;
            public TextView valueTextView;

            public ViewHolder(View view) {
                super(view);
                titleTextView = view.findViewById(R.id.titleTextView);
                valueTextView = view.findViewById(R.id.valueTextView);
            }
        }
    }
}

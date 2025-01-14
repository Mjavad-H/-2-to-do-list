import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StatisticsAdapter adapter;
    private List<StatisticItem> statisticItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        statisticItems = new ArrayList<>();
        statisticItems.add(new StatisticItem("کارهای انجام شده", "۱۰"));
        statisticItems.add(new StatisticItem("کارهای در حال انجام", "۵"));
        statisticItems.add(new StatisticItem("کارهای باقی‌مانده", "۳"));

        adapter = new StatisticsAdapter(statisticItems);
        recyclerView.setAdapter(adapter);
    }
}
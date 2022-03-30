package ru.samsung.itschool.dbgame;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class  StatActivity extends Activity {
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = DBManager.getInstance(this);
        setContentView(R.layout.activity_stat);
        final ListView listView = this.findViewById(R.id.asdf);
        Button buttonMax = this.findViewById(R.id.buttonmax);
        Button buttonAvg = this.findViewById(R.id.buttonavg);
        TextView total = this.findViewById(R.id.total);
        TextView summ = this.findViewById(R.id.sum);
        ArrayList<Result> arrayList = dbManager.getAllResults();
        String str = "Total plays: " + arrayList.size();
        total.setText(str);
        int sum = 0;
        for (Result res:
             arrayList) {
            sum += res.score;
        }
        String str2 = "Sum of all scores: " + sum;
        summ.setText(str2);

        buttonMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Result> maxResults = dbManager.getHighResults();
                listView.setAdapter(new ResultsAdapter(maxResults, getApplicationContext()));
            }
        });
        buttonAvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Result> avgResults = dbManager.getAvgResults();
                listView.setAdapter(new ResultsAdapter(avgResults, getApplicationContext()));
            }
        });
    }
}

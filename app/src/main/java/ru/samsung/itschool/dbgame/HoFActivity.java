package ru.samsung.itschool.dbgame;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HoFActivity extends Activity {

	private DBManager dbManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ho_f);
		dbManager = DBManager.getInstance(this);

		ListView listView = this.findViewById(R.id.gaysex);
		ArrayList<Result> results = dbManager.getAllResults();
		String[] res = new String[results.size()];
		String resStr = "";
		for (int i = 0; i < results.size(); i++) {
			res[i] = results.get(i).name + "; " + results.get(i).name;
		}
		listView.setAdapter(new ResultsAdapter(results, getApplicationContext()));
	}
}

/*
ListView listview = this.findViewById(R.id.listView);
listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, res));
 */
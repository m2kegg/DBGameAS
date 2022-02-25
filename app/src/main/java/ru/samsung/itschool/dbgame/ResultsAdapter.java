package ru.samsung.itschool.dbgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;

public class ResultsAdapter extends BaseAdapter {

    ArrayList<Result> results;
    Context context;

    ResultsAdapter(ArrayList<Result> results, Context context){
        this.context = context;
        this.results = results;
    }
    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_view, null,  false);
        TextView name = view.findViewById(R.id.name);
        TextView score = view.findViewById(R.id.score);
        if (results.get(position).score > 500){
            score.setTextColor(Color.GREEN);
        }
        else if (results.get(position).score < 100){
            score.setTextColor(Color.BLUE);
        }
        name.setText(results.get(position).name);
        score.setText(results.get(position).score + "");
        return view;
    }
}

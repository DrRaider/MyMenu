package com.github.drraider.mymenu;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class FilterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<TextAndCheckSetters> arrayList;
    TextAndCheckSetters textAndCheckSetters;        // Our Getter Setter class
    FilterRecyclerViewAdapter filterRecyclerViewAdapter;            // Adapter

    public static final int picture = R.drawable.banana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        recyclerView = (RecyclerView) findViewById(R.id.filter_list);

        arrayList = new ArrayList<>();
        for(int i = 1;i <= 40; i++) {
            textAndCheckSetters = new TextAndCheckSetters();
            textAndCheckSetters.setText("rupam"+i+"@gmail.com");
            textAndCheckSetters.setPictureResId(picture);
            arrayList.add(textAndCheckSetters);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FilterActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        filterRecyclerViewAdapter = new FilterRecyclerViewAdapter(FilterActivity.this, arrayList);
        recyclerView.setAdapter(filterRecyclerViewAdapter);
    }

}

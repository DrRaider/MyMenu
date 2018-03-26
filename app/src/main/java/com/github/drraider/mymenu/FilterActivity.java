package com.github.drraider.mymenu;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        JSONObject obj = Utils.getJSon(this, "filters.json");

        try {
            if (obj != null) {
                JSONArray filters = obj.getJSONArray("filters");
                arrayList = new ArrayList<>();
                for(int i = 0; i < filters.length(); i++) {
                    JSONObject filter = filters.getJSONObject(i);
                    textAndCheckSetters = new TextAndCheckSetters();
                    textAndCheckSetters.setText(filter.getString("name"));
                    textAndCheckSetters.setPictureResId(picture);
                    arrayList.add(textAndCheckSetters);
                }
            }
            else {
                throw new Exception("Empty filter list");
            }
        } catch (JSONException e) {
            Log.e("Exception : ", "Json reading error : ", e);
        } catch (Exception ex) {
            Log.e("Exception", " : ", ex);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FilterActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        filterRecyclerViewAdapter = new FilterRecyclerViewAdapter(FilterActivity.this, arrayList);
        recyclerView.setAdapter(filterRecyclerViewAdapter);
    }

}

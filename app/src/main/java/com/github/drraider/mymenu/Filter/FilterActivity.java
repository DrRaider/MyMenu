package com.github.drraider.mymenu.Filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.drraider.mymenu.MainActivity;
import com.github.drraider.mymenu.R;
import com.github.drraider.mymenu.Utils;

public class FilterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<TextAndCheckSetters> arrayList;
    TextAndCheckSetters textAndCheckSetters;
    FilterRecyclerViewAdapter filterRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                    textAndCheckSetters.setPictureResId(getResources().getIdentifier(
                            filter.getString("img"), "drawable", getPackageName())
                    );
                    arrayList.add(textAndCheckSetters);
                }
            }
            else {
                throw new Exception("Empty filter list");
            }
        } catch (Exception ex) {
            Log.e("Exception", " : ", ex);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FilterActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        filterRecyclerViewAdapter = new FilterRecyclerViewAdapter(arrayList);
        recyclerView.setAdapter(filterRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_validate) {
            Intent intent = new Intent(FilterActivity.this, MainActivity.class);
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 0; i < arrayList.size(); i++ ) {
                TextAndCheckSetters e = arrayList.get(i);
                if (e.getSelected())
                    tmp.add(e.getText());
            }
            intent.putStringArrayListExtra("List", tmp);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}

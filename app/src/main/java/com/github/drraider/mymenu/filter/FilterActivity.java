package com.github.drraider.mymenu.filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.drraider.mymenu.MainActivity;
import com.github.drraider.mymenu.R;
import com.github.drraider.mymenu.Utils;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.widget.Toast;

import java.io.*;

public class FilterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<RecyclerViewGetSet> arrayList;
    RecyclerViewGetSet recyclerViewGetSet;
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

                JSONArray savedFilters = loadData(this);

                for(int i = 0; i < filters.length(); i++) {
                    JSONObject filter = filters.getJSONObject(i);
                    recyclerViewGetSet = new RecyclerViewGetSet();
                    if (savedFilters != null) {
                        for (int j = 0; j < savedFilters.length(); j++) {
                            if (savedFilters.get(j).equals(filter.getString("name"))) {
                                recyclerViewGetSet.setSelected(true);
                            }
                        }
                    }

                    recyclerViewGetSet.setText(filter.getString("name"));
                    recyclerViewGetSet.setPictureResId(getResources().getIdentifier(
                            filter.getString("img"), "drawable", getPackageName())
                    );
                    arrayList.add(recyclerViewGetSet);
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
                RecyclerViewGetSet e = arrayList.get(i);
                if (e.getSelected())
                    tmp.add(e.getText());

            }
            saveData(tmp);
            intent.putStringArrayListExtra("List", tmp);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public static ArrayList<String> JSonParserToArrayList(JSONArray jsonArray) {

        ArrayList<String> listdata = new ArrayList<>();

        try {
            if (jsonArray != null) {
                for (int i=0; i < jsonArray.length(); i++){
                    listdata.add(jsonArray.getString(i));
                }
            }
        } catch (JSONException e) {
            Log.e("Error", e.toString(), e);
        }

        return listdata;

    }

    public void saveData (ArrayList<String> data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("saved_filters.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(data.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            Toast.makeText(getBaseContext(), "Exception : " + "File write failed: " + e.toString(), Toast.LENGTH_LONG).show();

        }
    }

    public static JSONArray loadData (Context context) {

        String data = Utils.loadSavedFilters(context, "saved_filters.json");
        JSONArray dataArray = null;
        try {
             dataArray = new JSONArray(data);
        } catch (JSONException e) {
            Log.e("Exception", e.toString());
        }

        return dataArray;

    }
}

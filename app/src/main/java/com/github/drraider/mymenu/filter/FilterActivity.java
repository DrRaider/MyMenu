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
                for(int i = 0; i < filters.length(); i++) {
                    JSONObject filter = filters.getJSONObject(i);
                    recyclerViewGetSet = new RecyclerViewGetSet();
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


    public void saveData (ArrayList data) {

        JSONArray arrayToSave = new JSONArray();
        arrayToSave.put(data);

        try {
            Writer output = null;
            File file = new File("saved_filters.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(arrayToSave.toString());
            output.close();
            Toast.makeText(getApplicationContext(), "Filters saved", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.e("Error read only", e.getMessage());
            Toast.makeText(getBaseContext(), "Error read only : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }


    public void loadData () {
        try {
            JSONObject obj = new JSONObject(getJSon());

            //to do

        } catch (JSONException e) {
            Log.e("exception", "Erreur dans la fonction OnCreate");
            e.printStackTrace();
        }
    }


    public String getJSon() {

        String json = null;
        try {
            InputStream is = getAssets().open("saved_filters.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e("exception", "Erreur dans la fonction getJSon");
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}

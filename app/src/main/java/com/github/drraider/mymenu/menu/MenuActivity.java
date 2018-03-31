package com.github.drraider.mymenu.menu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import com.github.drraider.mymenu.R;
import com.github.drraider.mymenu.Utils;
import com.github.drraider.mymenu.filter.RecyclerViewGetSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Menu menuOfZeDay;

    RecyclerView recyclerView;
    MenuRecyclerViewAdapter menuRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.menu_list);

        menuOfZeDay = new Menu();

        try {
            JSONObject obj = new JSONObject(getIntent().getStringExtra("obj"));

            JSONArray namesArray = new JSONArray(obj.getJSONArray("values").get(0).toString());
            JSONArray descriptionsArray = new JSONArray(obj.getJSONArray("values").get(1).toString());
            JSONArray allergenesArray = new JSONArray(obj.getJSONArray("values").get(2).toString());
            JSONArray typesArray = new JSONArray(obj.getJSONArray("values").get(3).toString());

            for (int j = 0; j <= obj.length(); j++) {

                Dish dish = new Dish();

                dish.setName(namesArray.get(j).toString());
                dish.setDescription(descriptionsArray.get(j).toString());
                dish.setFilters(getIntent().getStringArrayListExtra("filters"));
                dish.setAllergenes(allergenesArray.get(j).toString());
                dish.setType(typesArray.get(j).toString());

                menuOfZeDay.addDish(dish);
            }
            menuOfZeDay.showDishList();
            menuOfZeDay.generateSafeDishList();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MenuActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            menuRecyclerViewAdapter = new MenuRecyclerViewAdapter(menuOfZeDay.getSafeDishList());
            recyclerView.setAdapter(menuRecyclerViewAdapter);

        } catch (JSONException e) {
            Log.e("exception", "Erreur dans la fonction OnCreate");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_eula) {
            Utils.showDialog(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

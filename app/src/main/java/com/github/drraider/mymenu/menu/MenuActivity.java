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

import com.github.drraider.mymenu.R;
import com.github.drraider.mymenu.Utils;
import com.github.drraider.mymenu.filter.RecyclerViewGetSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    /*private TextView mName;
    private TextView mDescription;
    private TextView mAllergenes;
    private TextView mType;*/

    private Menu menuOfZeDay;

    RecyclerView recyclerView;
    ArrayList<RecyclerViewGetSet> recyclerArrayList;
    RecyclerViewGetSet recyclerViewGetSet;
    MenuRecyclerViewAdapter filterRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuOfZeDay = new Menu();

        try {
            JSONObject obj = new JSONObject(getIntent().getStringExtra("obj"));

            for (int j = 0 ; j < obj.length(); j++) {

                Dish dish = new Dish();
                dish.setName((obj.getJSONArray("values").get(0).toString()));
                dish.setDescription((obj.getJSONArray("values").get(1).toString()));

                //Toast.makeText(getApplicationContext(), allergenes[0]+ " and " + allergenes[1],
                //Toast.LENGTH_LONG).show();

                dish.setAllergenes((obj.getJSONArray("values").get(2).toString()));
                dish.setType((obj.getJSONArray("values").get(3).toString()));
                dish.setFilters(getIntent().getStringArrayListExtra("filters"));

                menuOfZeDay.addDish(dish);

            }

            recyclerArrayList = new ArrayList<>();
            recyclerView = (RecyclerView) findViewById(R.id.filter_list);

/*
            if (!test) {
                mName.setText(dish.getName());
                mDescription.setText(dish.getDescription());
                mAllergenes.setText(dish.getAllergenes());
                mType.setText(dish.getType());
            } else {
                mName.setText("");
                mDescription.setText("");
                mAllergenes.setText("Ce plat contient des allergenes.");
                mType.setText("");
            }*/

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

package com.github.drraider.mymenu.menu;

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

import java.util.ArrayList;
import java.util.Objects;


public class MenuActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mDescription;
    private TextView mAllergenes;
    private TextView mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mName = (TextView) findViewById(R.id.MenuDisplay_name_dish);
        mDescription = (TextView) findViewById(R.id.MenuDisplay_description_dish);
        mAllergenes = (TextView) findViewById(R.id.MenuDisplay_allergenes_dish);
        mType = (TextView) findViewById(R.id.MenuDisplay_type_dish);


        try {
            JSONObject obj = new JSONObject(getIntent().getStringExtra("obj"));
            Dish dish = new Dish();
            dish.setName((obj.getJSONArray("values").get(0).toString()));
            dish.setDescription((obj.getJSONArray("values").get(1).toString()));
            dish.setAllergenes((obj.getJSONArray("values").get(2).toString()));
            dish.setType((obj.getJSONArray("values").get(3).toString()));
            ArrayList<String> filters = getIntent().getStringArrayListExtra("filters");
            boolean test = false;
            for (String s: filters) {
                if (Objects.equals("[\"" + s + "\"]", dish.getAllergenes())) {
                    test = true;
                    break;
                }
            }
            if (!test) {
                mName.setText(dish.getName());
                mDescription.setText(dish.getDescription());
                mAllergenes.setText(dish.getAllergenes());
                mType.setText(dish.getType());
            }
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
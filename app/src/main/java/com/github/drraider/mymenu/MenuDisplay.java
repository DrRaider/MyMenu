package com.github.drraider.mymenu;

import android.util.Log;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import java.io.*;


public class MenuDisplay extends AppCompatActivity {

    private Context mContext;
    private TextView mName;
    private TextView mDescription;
    private TextView mAllergenes;
    private TextView mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_display);

        mName = (TextView) findViewById(R.id.MenuDisplay_name_dish);
        mDescription = (TextView) findViewById(R.id.MenuDisplay_description_dish);
        mAllergenes = (TextView) findViewById(R.id.MenuDisplay_allergenes_dish);
        mType = (TextView) findViewById(R.id.MenuDisplay_type_dish);


        try {
             JSONObject obj = new JSONObject(getJSon());
             mName.setText(obj.getString("name"));
             mDescription.setText(obj.getString("description"));
             mAllergenes.setText(obj.getString("allergenes"));
             mType.setText(obj.getString("type"));

        } catch (JSONException e) {
            Log.e("exception", "Erreur dans la fonction OnCreate");
            e.printStackTrace();
        }

    }

    public String getJSon() {

        String json = null;

        try {

            InputStream is = getAssets().open("plat_test.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            Log.e("exception", "Erreur dans la fonction loadJSONFromAsset");
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}

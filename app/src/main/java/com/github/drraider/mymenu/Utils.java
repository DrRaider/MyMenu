package com.github.drraider.mymenu;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class Utils {
    public static JSONObject getJSon(Context context, String filename) {
        try {

            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];

            if(is.read(buffer) != size)
                throw new IOException("Couldn't read Json file");

            is.close();
            return new JSONObject(new String(buffer, "UTF-8"));

        } catch (IOException ex) {
            Log.e("Exception", " : ", ex);
            return null;
        } catch (JSONException e) {
            Log.e("Exception", " : ", e);
        }
        return null;
    }
}
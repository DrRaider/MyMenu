package com.github.drraider.mymenu;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.github.drraider.mymenu.dialogfragment.EulaDialogFragment;

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

    public static String loadSavedFilters (Context context, String file) {
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(file);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public static void showDialog(Activity activity) {
        DialogFragment newFragment = EulaDialogFragment.newInstance(1);
        newFragment.show(activity.getFragmentManager(), "dialog");
    }



}

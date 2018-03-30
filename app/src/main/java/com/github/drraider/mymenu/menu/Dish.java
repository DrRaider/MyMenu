package com.github.drraider.mymenu.menu;

import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

public class Dish {

    private String name;
    private String description;
    private String allergenes;
    private String type;
    private boolean authorized;
    private ArrayList<String> filters;

    public Dish() {
        authorized = true;
        filters = new ArrayList<String>();
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = eraseJSonCharacter(type);
        Log.d("Dish type : ", this.type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = eraseJSonCharacter(name);
        Log.d("Dish Name : ", this.name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = eraseJSonCharacter(description);
        Log.d("Dish description : ", this.description);
    }

    public ArrayList<String> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<String> filters) {
        this.filters = filters;
    }

    public String getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(String allergenes) {
        this.allergenes = eraseJSonCharacter(allergenes);
        Log.d("Dish allergenes : ", this.allergenes);
        checkAllergenes();
    }

    public String eraseJSonCharacter (String str) {
        str = str.replace("\"", "");
        str = str.replace("[", "");
        str = str.replace("]", "");

        return str;
    }

    public void checkAllergenes () {

        Log.d("Test allergenes", "All alergenes are being tested");

        String allergenes_array[] = allergenes.split(",", 2);

        try {
            for (String s: filters) {
                for(String str: allergenes_array) {
                    str = str.replace("\"", "");
                    str = str.replace("[", "");
                    str = str.replace("]", "");

                    Log.d("Test allergenes", "Allergenes tested : " + s.toLowerCase() + " ; " + str.toLowerCase());
                    if (Objects.equals(s.toLowerCase(), str.toLowerCase())) {
                        authorized = false;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

    }

}

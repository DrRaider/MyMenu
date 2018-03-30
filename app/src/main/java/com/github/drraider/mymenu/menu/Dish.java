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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = eraseJSonCharacter(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = eraseJSonCharacter(description);
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
        this.allergenes = allergenes;
    }

    public String eraseJSonCharacter (String str) {
        str = str.replace("\"", "");
        str = str.replace("[", "");
        str = str.replace("]", "");

        return str;
    }

    public void checkAllergenes () {

        String allergenes_array[] = allergenes.split(",", 2);

        boolean test = false;
        for (String s: filters) {
            for(String str: allergenes_array) {
                str = str.replace("\"", "");
                str = str.replace("[", "");
                str = str.replace("]", "");

                if (Objects.equals(s.toLowerCase(), str.toLowerCase())) {
                    test = true;
                }
            }
        }

        if (!test) {
            authorized = true;
        } else {
            authorized = false;
        }
    }

}

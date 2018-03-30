package com.github.drraider.mymenu.menu;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Menu {

    private ArrayList<Dish> mDishList;
    private ArrayList<Dish> mSafeDishList;

    public Menu(ArrayList<Dish> DishList) {
        mDishList = DishList;
        mSafeDishList = new ArrayList<Dish>();
    }

    public Menu() {
        mDishList = new ArrayList<Dish>();
        mSafeDishList = new ArrayList<Dish>();
    }

    public void addDish (Dish data) {
        mDishList.add(data);
    }

    public void showDishList() {
        for (int j = 0 ; j < mDishList.size(); j++) {
            Log.d("Dish List : ", "Dish #" + j + " : " + mDishList.get(j).getName());
        }
    }

    public void generateSafeDishList() {

        for (int j = 0 ; j < mDishList.size(); j++) {
            if (mDishList.get(j).isAuthorized()) {
                Log.d("Safe Dish List: ", "Safe Dish #" + j + " : " + mDishList.get(j).getName());
                mSafeDishList.add(mDishList.get(j));
            }
        }
    }

    public ArrayList<Dish> getSafeDishList() {
        return mSafeDishList;
    }

    public void setSafeDishList(ArrayList<Dish> safeDishList) {
        mSafeDishList = safeDishList;
    }
}

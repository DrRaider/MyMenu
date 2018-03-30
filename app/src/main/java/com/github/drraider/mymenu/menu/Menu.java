package com.github.drraider.mymenu.menu;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Menu {

    private List<Dish> mDishList;

    public Menu(List<Dish> DishList) {
        mDishList = DishList;
    }

    public Menu() {

    }

    public void addDish (Dish data) {
        mDishList.add(data);
    }
}

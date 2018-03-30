package com.github.drraider.mymenu.menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.github.drraider.mymenu.R;
import com.github.drraider.mymenu.filter.FilterRecyclerViewAdapter;

/**
 * Created by Daniel on 30/03/2018.
 */

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MyViewHolder> {

    @Override
    public MenuRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_menu,parent,false);
        return new MenuRecyclerViewAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MenuRecyclerViewAdapter.MyViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}

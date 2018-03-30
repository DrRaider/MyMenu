package com.github.drraider.mymenu.menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.drraider.mymenu.R;
import com.github.drraider.mymenu.filter.RecyclerViewGetSet;

import java.util.ArrayList;

/**
 * Created by Daniel on 30/03/2018.
 */

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<RecyclerViewGetSet> arrayList;

    @Override
    public MenuRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_menu,parent,false);
        return new MenuRecyclerViewAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MenuRecyclerViewAdapter.MyViewHolder holder, final int position) {

        holder.mName.setText(arrayList.get(position).getText());
        holder.mDescription.setText(arrayList.get(position).getText());
        holder.mAllergenes.setText(arrayList.get(position).getText());
        holder.mType.setText(arrayList.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDescription;
        private TextView mAllergenes;
        private TextView mType;

        MyViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.MenuDisplay_name_dish);
            mDescription = (TextView) itemView.findViewById(R.id.MenuDisplay_description_dish);
            mAllergenes = (TextView) itemView.findViewById(R.id.MenuDisplay_allergenes_dish);
            mType = (TextView) itemView.findViewById(R.id.MenuDisplay_type_dish);

        }


    }
}

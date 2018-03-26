package com.github.drraider.mymenu;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class FilterRecyclerViewAdapter extends RecyclerView.Adapter<FilterRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<TextAndCheckSetters> arrayList;

    FilterRecyclerViewAdapter(Context context, ArrayList<TextAndCheckSetters> arrayList) {
        Context context1 = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.cb_custom.setChecked(arrayList.get(position).getSelected());
        holder.cb_custom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                arrayList.get(holder.getAdapterPosition()).setSelected(isChecked);
            }
        });
        holder.tv_text.setText(arrayList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox cb_custom;
        TextView tv_text;
        MyViewHolder(View itemView) {
            super(itemView);
            cb_custom= (CheckBox) itemView.findViewById(R.id.cb_customRow_id);
            tv_text= (TextView) itemView.findViewById(R.id.tv_customRow_id);
        }
    }

}

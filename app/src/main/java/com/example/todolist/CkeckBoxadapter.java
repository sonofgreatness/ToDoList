package com.example.todolist;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;

public class CkeckBoxadapter extends ArrayAdapter<Task_properties> {


    private static class ViewHolder {
        CheckedTextView foodName;
        CheckBox foodSize;

        private ViewHolder() {
        }
    }





    public CkeckBoxadapter(Context context, ArrayList<Task_properties> Orders) {
        super(context, R.layout.checkbox_template, Orders); }

    public View getView(final int position, View convertview, ViewGroup parent) {
        ViewHolder viewholder;
        Task_properties order = (Task_properties) getItem(position);
        if (convertview == null) {
            viewholder = new ViewHolder();
            convertview = LayoutInflater.from(getContext()).inflate(R.layout.checkbox_template, parent, false);
            viewholder.foodName = (CheckedTextView) convertview.findViewById(R.id.check);
            viewholder.foodSize = (CheckBox) convertview.findViewById(R.id.checkbox);













            convertview.setTag(viewholder);


        } else {
            viewholder = (ViewHolder) convertview.getTag();
        }
        //viewholder.foodName.setClickable(true);





        viewholder.foodName.setText(order.getTask_Name());
        viewholder.foodSize.setChecked(order.isChecked());
        //   viewholder.foodSize.setText(BuildConfig.FLAVOR);
        return convertview;
    }
}



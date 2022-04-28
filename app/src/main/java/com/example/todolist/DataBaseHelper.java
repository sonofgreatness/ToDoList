package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TASK_TABLE = "TASK_TABLE";
    public static final String TASK = "TASK ";
    public static final String COLUMN_ID = "ID";
    public static String CHECKED = "IsChecked";
    public static String DATABASE_ALTER_TASKTABLE_TO_V2 = "ALTER TABLE TASK_TABLE ADD COLUMN CHECKED BOOL ";

    public DataBaseHelper(Context context) {

        super(context, "taskdata.db", null, 1);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = " CREATE TABLE " + TASK_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " + TASK + "TEXT)";
        db.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        if (oldVersion < newVersion) {
            //upgrade from version 1 t0 2

            db.execSQL(DATABASE_ALTER_TASKTABLE_TO_V2);


        }
    }

    public boolean addOne(String inputTask) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASK, inputTask);
        cv.put(CHECKED, false);

        final long insert = db.insert(TASK_TABLE, null, cv);

        if (insert == -1) {

            return false;
        } else {

            return true;
        }


    }


    public boolean deleteOne(int ID) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE  FROM " + TASK_TABLE + " WHERE " + COLUMN_ID + " = " + ID;


        final Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {


            return true;

        } else {
            return false;
        }

    }


    public ArrayList<String> getEveryone() {


        String query = "SELECT * FROM  " + TASK_TABLE;

        ArrayList<String> returnlist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            //loop through the cursor (result set ) get the task strings and put them in the return list
            do {
                int taskId = cursor.getInt(0);
                String TaskName = cursor.getString(1);
                //EXPECTING 1OR ZERO
                boolean ischecked = cursor.getInt(2) == 1 ? true : false;

                returnlist.add(Integer.toString(taskId) + "." + " " + TaskName);


            } while (cursor.moveToNext());

        } else {
        }
        cursor.close();
        db.close();
        return returnlist;

    }


    public void ClearDB() {


        SQLiteDatabase db = this.getWritableDatabase();


        db.delete(TASK_TABLE, null, null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE  NAME= 'TASK_TABLE'");


        db.close();


    }


    //method to store state of checkbox (clicked or not)

    public void Clicked(boolean isChecked, int[] id) {


        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 1; i < id.length; i++) {
            int ID = id[i];
            String querystring = "UPDATE TASK_TABLE1 SET COLUMN_CHECKED='isChecked' WHERE ID =ID";
            db.execSQL(querystring);
            db.close();
            //update database with true value at position of id ;

        }


    }
}
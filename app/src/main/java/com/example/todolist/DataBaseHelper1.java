package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper1  extends SQLiteOpenHelper {


    public static final String TASK_TABLE = "TASK_TABLE";
    public static final String TASK = "TASK ";
    public static final String COLUMN_ID = "ID";

    public static final String CHECKED = "CHECKED";
    public static String DATABASE_ALTER_TASKTABLE_TO_V2 = "ALTER TABLE TASK_TABLE ADD COLUMN " + CHECKED + " BOOL ";

    public DataBaseHelper1(Context context) {

        super(context, "taskdata1.db", null, 4);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = " CREATE TABLE " + TASK_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY ,  " + TASK + "TEXT )";
        db.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        if (oldVersion < newVersion) {
            //upgrade from version 1 t0 2

            db.execSQL(DATABASE_ALTER_TASKTABLE_TO_V2);

        }

    }



    public boolean addOne(Task_properties task_properties) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASK, task_properties.getTask_Name());
        cv.put(CHECKED,task_properties.isChecked());

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

            db.close();
            return true;

        } else {db.close();
            return false;
        }


    }


    public ArrayList<Task_properties> getEveryone() {


        String query = "SELECT * FROM  " + TASK_TABLE;

        ArrayList<Task_properties> returnlist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            //loop through the cursor (result set ) get the task strings and put them in the return list
            do {

                int taskId = cursor.getInt(0);
                String TaskName = cursor.getString(1);
                //EXPECTING 1OR ZERO
                boolean ischecked=cursor.getInt(2)== 1 ? true:false;
                String Taskname=Integer.toString(taskId)+" ."+TaskName;
                Task_properties task_properties=new Task_properties(Taskname,ischecked);

                returnlist.add(task_properties);


            } while (cursor.moveToNext());

        } else {
        }
        cursor.close();
        db.close();
        return returnlist;

    }




    public void ClearDB() {




try {

    SQLiteDatabase db = this.getWritableDatabase();


    db.delete(TASK_TABLE, null, null);
    db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE  NAME= 'TASK_TABLE'");


    db.close();

}catch (Exception e){}

    }







    public void UpdateDatabase(int id,Task_properties task_properties)
    {
        SQLiteDatabase db = this.getWritableDatabase();



if(task_properties.isChecked()){
        String querystring = "UPDATE TASK_TABLE SET CHECKED =false WHERE ID ="+id;

        db.execSQL(querystring);
        db.close();

    }
else if(!task_properties.isChecked()){


    String querystring = "UPDATE TASK_TABLE SET CHECKED =true WHERE ID = "+id;

    db.execSQL(querystring);
    db.close();




}
    }


}














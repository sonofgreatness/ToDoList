package com.example.todolist;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity {
      DataBaseHelper1 databasehelper=new DataBaseHelper1(MainActivity.this);
      //CkeckBoxadapter checkboxadapter;



  CkeckBoxadapter  checkboxadapter ;
        //listview.setAdapter(checkboxadapter);
     // Button btn_add = (Button)findViewById(R.id.button);
 EditText Input;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShowList();

    /*    btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Input=findViewById(R.id.editText2);

               databasehelper.addOne( Input.getText().toString());




            }
        });   */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "LongClick Task to delete", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });



final ListView listview=findViewById(R.id.list);

listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        try{


            Task_properties task_properties=(Task_properties) listview.getItemAtPosition(position);






            int Endpoint;
            String tobeconverted;

            String wholestring=task_properties.getTask_Name();

            String substr=task_properties.getTask_Name().substring(task_properties.getTask_Name().lastIndexOf("."));
           String substr1=task_properties.getTask_Name().substring(task_properties.getTask_Name().lastIndexOf(".")+1);
            Endpoint=wholestring.length()-substr.length();
            tobeconverted=wholestring.substring(0,Endpoint-1);
            int ID= Integer.parseInt(tobeconverted);



          databasehelper.UpdateDatabase(ID,task_properties);
            ShowList();
            Toast.makeText(MainActivity.this,substr1+"Marked As Done", Toast.LENGTH_SHORT).show();







        }catch(Exception e){



            Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_SHORT).show();

        }

        ShowList();



    }
});



       listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {



               try {


                   Task_properties task_properties=(Task_properties) listview.getItemAtPosition(position);



                   int Endpoint;
                   String tobeconverted;
                   String substr1=task_properties.getTask_Name().substring(task_properties.getTask_Name().lastIndexOf(".")+1);
                   String wholestring=task_properties.getTask_Name();
                   String substr=task_properties.getTask_Name().substring(task_properties.getTask_Name().lastIndexOf("."));
                   Endpoint=wholestring.length()-substr.length();
                   tobeconverted=wholestring.substring(0,Endpoint-1);
                 int ID= Integer.parseInt(tobeconverted);



                   databasehelper.deleteOne(ID);
                   Toast.makeText(MainActivity.this,substr1+"  "+"Deleted" , Toast.LENGTH_SHORT).show();
                  ShowList();






               } catch (Exception e) {
                   Toast.makeText(MainActivity.this, "Error" + Integer.toString(position + 1), Toast.LENGTH_SHORT).show();


               }

               return false;
           }
       });

    }

    public void fuseki(View view){
        ListView listview = findViewById(R.id.list);

        ArrayList temp_list = databasehelper.getEveryone();
        Input=findViewById(R.id.editText2);
        Task_properties task_properties=new Task_properties(Input.getText().toString(),false);

      databasehelper.addOne(task_properties);
Input.setText("");
      ShowList();





    }
    public void DeleteR(View view){


       databasehelper.ClearDB();
       // databasehelper.getEveryone().clear();
ShowList();

    }



    private void ShowList() {
        ListView listview = findViewById(R.id.list);
        ArrayList temp_list = databasehelper.getEveryone();

        checkboxadapter = new CkeckBoxadapter(this, temp_list);
        listview.setAdapter(checkboxadapter);
        // checkboxadapter.clear();
//  NEED TO READ THROUGH THE LISTVIEW AND CHECK IF THE HE CHECK BOX IS CHECKED OR NOT

// PRODUCE AN ARRAY OF POSITIONS OF CHECKED CHECKBOX


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}

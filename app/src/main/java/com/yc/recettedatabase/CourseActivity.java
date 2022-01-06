package com.yc.recettedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    FoodDbHelper db;

    ListView ingredientList;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    private Button button4;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        db=new FoodDbHelper(this);
        listItem=new ArrayList<>();
        ingredientList=findViewById(R.id.listview3);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
        viewData();

        button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.clear();
                //db.getWritableDatabase().rawQuery("DELETE FROM "+FoodContract.FoodEntry.TABLE_NAME,null);
                db.getWritableDatabase().rawQuery("DROP TABLE IF EXISTS " + FoodContract.FoodEntry.TABLE_NAME,null);
                db.close();
                //context.deleteDatabase("FeedReader.db");
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void viewData(){
        //Cursor cursor =db.viewData();
        Cursor cursor =db.getReadableDatabase().rawQuery("SELECT * FROM "+ FoodContract.FoodEntry.TABLE_NAME,null);
        if(cursor.getCount()==0){
            Toast.makeText(this,"No ingredient",Toast.LENGTH_SHORT).show();

        }else{
            while(cursor.moveToNext()){
                listItem.add(cursor.getString(1));

            }
            ingredientList.setAdapter(adapter);


        }

    }

}
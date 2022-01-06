package com.yc.recettedatabase;

import androidx.appcompat.app.AppCompatActivity;

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

    ListView ingredientlist;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    private Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        db=new FoodDbHelper(this);
        listItem=new ArrayList<>();
        ingredientlist=findViewById(R.id.listview3);
        viewData();
        button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db.getWritableDatabase().rawQuery("DELETE FROM "+FoodContract.FoodEntry.TABLE_NAME,null);

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
            adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            ingredientlist.setAdapter(adapter);
        }

}

}
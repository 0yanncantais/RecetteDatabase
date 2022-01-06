package com.yc.recettedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailRecetteActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLiteDatabase dbr;
    private String nom;
    private int id;
    private Button button;

    ArrayList<String> ingredients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recette);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id=extras.getInt("id");
        }

        if(id != 0){
            String url = new String("https://api.spoonacular.com/recipes/"+id+"/information?apiKey=48ca9abb7ae94bd2952adc499b490971&includeNutrition=false");
            AsyncRecetteDetailJSONData task2 = new AsyncRecetteDetailJSONData(DetailRecetteActivity.this);
            task2.execute(url);
            Log.e("DetailActivity","on entre dans le Async");
        }

        FoodDbHelper dbHelper = new FoodDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        dbr = dbHelper.getReadableDatabase();
        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ListeCourse(v);
                AfficheIngredients(ingredients);
            }
        });

    }

    protected void AfficheIngredients(ArrayList<String> string){

        ingredients=string;
        final ListView lv = (ListView) findViewById(R.id.listview3);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, string);
        lv.setAdapter(arrayAdapter);

    }

    public void ListeCourse(View view){

        addIngredients(view);


    }
    public void addIngredients(View view){
        for(int i=0 ;i<ingredients.size();i++){
            ContentValues values = new ContentValues();
            nom= ingredients.get(i);
            //values.put(FoodContract.FoodEntry.COLUMN_NAME_ID, id);
            values.put(FoodContract.FoodEntry.COLUMN_NAME_NOM,nom);
            Log.i("Ingredients", String.valueOf(values));
            long newRowId = db.insert(FoodContract.FoodEntry.TABLE_NAME, null, values);
            Toast.makeText(DetailRecetteActivity.this, "Liste de course mise à jour", Toast.LENGTH_SHORT).show();
        }
    }


}
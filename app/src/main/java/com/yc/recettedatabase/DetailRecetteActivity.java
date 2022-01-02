package com.yc.recettedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailRecetteActivity extends AppCompatActivity {



    private int id;
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
            Log.e("JLMZ51","on entre dans le Async");
        }
    }
    protected void AfficheIngredients(ArrayList<String> string){


        final ListView lv = (ListView) findViewById(R.id.listview);

        //final Button btn = (Button) findViewById(R.id.button2);

        //final List<String> ingredients_list = new ArrayList<String>(Arrays.asList(string));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, string);
        lv.setAdapter(arrayAdapter);

        //ingredients_list.add(string);

    }
}
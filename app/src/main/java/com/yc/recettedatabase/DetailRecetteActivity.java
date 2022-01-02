package com.yc.recettedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
        Log.e("JLMZ51","on entre dans le Async");
    }
}
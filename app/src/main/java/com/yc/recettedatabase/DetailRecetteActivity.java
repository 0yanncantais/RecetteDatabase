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
            String url = new String("https://api.spoonacular.com/recipes/"+id+"/?api_key=48ca9abb7ae94bd2952adc499b490971&information");
            AsyncRecetteDetailJSONData task2 = new AsyncRecetteDetailJSONData(DetailRecetteActivity.this);
            task2.execute(url);
        }
    }
}
package com.yc.recettedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EditText searchtext;
    private String API_TOKEN= "48ca9abb7ae94bd2952adc499b490971";
    private RecettePreviewAdapter adapter;
    private ListView listview;
    private Button button;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchtext=(EditText) findViewById(R.id.textedit);
        adapter = new RecettePreviewAdapter(this.getApplicationContext());
        listview=(ListView) findViewById(R.id.listview3);
        listview.setAdapter(adapter);
        button=findViewById(R.id.button);
        button3=findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               searchRecette(v);
            }
        });

        listview.setAdapter(adapter);

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CourseActivity.class);
                startActivity(i);
            }
        });
    }

    public void searchRecette(View view){
        adapter.clear();
        String text=searchtext.getText().toString();
        String url = new String("https://api.spoonacular.com/recipes/complexSearch?apiKey=" + API_TOKEN + "&query="+text);
        AsyncRecetteJSONDataList task2 = new AsyncRecetteJSONDataList(adapter);
        task2.execute(url);
    }


}
package com.yc.recettedatabase;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AsyncRecetteDetailJSONData extends AsyncTask<String, Void, JSONObject> {

    private DetailRecetteActivity activity;
    public AsyncRecetteDetailJSONData(DetailRecetteActivity DetailActivity){
        this.activity = DetailActivity;
    }

    @Override
    protected  JSONObject doInBackground(String... strings){
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = readStream(in);

        urlConnection.disconnect();
        JSONObject json = null;
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonobj){
        ArrayList<String> ingredientss = new ArrayList<String>();
        //ArrayList<Bitmap> images=new ArrayList<Bitmap>();
        try {
            JSONArray recettedetailarray = jsonobj.getJSONArray("extendedIngredients");
            for (int i = 0; i<recettedetailarray.length(); i++)
            {

                String ingredient=(String) recettedetailarray.getJSONObject(i).get("nameClean");
                int idIngredient=(int) recettedetailarray.getJSONObject(i).get("id");

                Log.i("Detail", " Adding to adapter ingredients : "+ ingredient);
                ingredientss.add(ingredient);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Detail", " Adding to adapter ingredients : ");
        activity.AfficheIngredients(ingredientss);
    }


    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}

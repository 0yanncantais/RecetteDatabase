package com.yc.recettedatabase;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class AsyncRecetteJSONDataList extends AsyncTask<String, Void, JSONObject> {

    private RecettePreviewAdapter adapter;

    public AsyncRecetteJSONDataList(RecettePreviewAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream

            result = readStream(in); // Read stream
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return json; // returns the result
    }


    @Override
    protected void onPostExecute(JSONObject jsonobj){
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<Bitmap> images=new ArrayList<Bitmap>();
        try {
            JSONArray recettesarray = jsonobj.getJSONArray("results");
            for (int i = 0; i<recettesarray.length(); i++)
            {

                String title=(String) recettesarray.getJSONObject(i).get("title");
                String urlImage=(String) recettesarray.getJSONObject(i).get("image");
                int idRecette=(int) recettesarray.getJSONObject(i).get("id");

                Log.i("Datalist", " Adding to adapter title : "+ title+ ":"+urlImage);

                adapter.add(title,urlImage, idRecette);


            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

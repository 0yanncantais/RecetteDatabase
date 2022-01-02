package com.yc.recettedatabase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

public class RecettePreviewAdapter extends BaseAdapter {
    private Context context_=null;

    public RecettePreviewAdapter(Context context){
        context_ = context;
    }

    Vector<String> vectorTitle = new Vector<String>();
    Vector<String> vectorUrlImage = new Vector<String>();
    Vector<Integer> vectorId = new Vector<Integer>();

    ArrayList<Bitmap> myList = new ArrayList<Bitmap>();
    Context context;
    @Override
    public int getCount() {
        return this.vectorId.size();
    }

    @Override
    public Object getItem(int position) {
        return this.vectorTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View v = LayoutInflater.from(context).inflate(R.layout.listviewlayout, parent,false);
        //TextView tv = (TextView) v.findViewById(R.id.textView);
        //ImageView img = (ImageView) v.findViewById(R.id.imageView);
        //tv.setText(vectorTitle.get(position).toString());
        //LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);

        //return convertView;



        Bitmap image = (Bitmap)getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context_)
                    .inflate(R.layout.listviewlayout, parent, false);
        }

        ImageView iv = (ImageView)convertView.findViewById(R.id.imageView);
        iv.setImageBitmap(image);

        return convertView;

    }


    public void add(Bitmap image){


        myList.add(image);
    }


}

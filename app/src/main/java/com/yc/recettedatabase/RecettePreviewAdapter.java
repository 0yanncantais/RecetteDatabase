package com.yc.recettedatabase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;
import java.util.Vector;

public class RecettePreviewAdapter extends BaseAdapter {

    private Context context=null;

    public RecettePreviewAdapter(Context context){
        this.context = context;
    }

    Vector<String> titleVector = new Vector<String>();
    Vector<String> urlImageVector = new Vector<String>();
    Vector<Integer> idVector = new Vector<Integer>();


    @Override
    public int getCount() {
        return this.idVector.size();
    }

    @Override
    public Object getItem(int position) {
        return this.idVector.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewlayout, parent,false);
        TextView tv = (TextView) v.findViewById(R.id.textView);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        RequestQueue queue = MySingleton.getInstance(parent.getContext()).getRequestQueue();

        Response.Listener<Bitmap> rep_Listener = response -> {
            Log.e("Preview", "Bitmap créé");
            img.setImageBitmap(response);
        };

        ImageRequest request = new ImageRequest(
                urlImageVector.get(position).toString(),
                rep_Listener,
                600,
                600,null,Bitmap.Config.RGB_565,null);
        queue.add(request);


        //img.setImageBitmap(urlImageVector.get(position));
        tv.setText(titleVector.get(position).toString());
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(clickInLinearLayout(idVector.get(position)));
        return v;

    }

    public void add(String title, String url, int idRecette){
        titleVector.add(title);
        urlImageVector.add(url);
        idVector.add(idRecette);
    }
    public void clear(){
        titleVector.clear();
        urlImageVector.clear();
        idVector.clear();
    }

    private View.OnClickListener clickInLinearLayout(int id){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(context,DetailRecetteActivity.class);
                intent.putExtra("id",id);
                v.getContext().startActivity(intent);
            }
        };
    }






}

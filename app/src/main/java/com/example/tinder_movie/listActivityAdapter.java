package com.example.tinder_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.List;

public class listActivityAdapter extends ArrayAdapter {

    List<String> titles;
    List<String> imgs;
    Context c;
    int itemLayout;

    public listActivityAdapter(List<String> text, List<String> img, Context context, int resource) {
        super(context, resource, text);
        titles=text;
        imgs=img;
        itemLayout = resource;
        c = context;
    }


    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scaleview, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.textView);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);

        String text = titles.get(position);
        String picture = imgs.get(position);

        title.setText(text);
        Glide.with(this.getContext()).load(picture).override(400,900).into(image);

        return convertView;
    }
}


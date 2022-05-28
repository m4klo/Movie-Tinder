package com.example.tinder_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<movies> {

    Context context;

    public arrayAdapter(Context context, int resourceId, List<movies> items){
        super(context, resourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        movies card_item  = getItem(position);

        if(convertView == null){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView description = (TextView) convertView.findViewById(R.id.description);


        description.setText(card_item.getDescription());
        title.setText(card_item.getTitle());
        Glide.with(convertView.getContext()).load(card_item.getImgURL()).into(image);

        return convertView;
    }
}

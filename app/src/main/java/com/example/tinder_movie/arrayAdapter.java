package com.example.tinder_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        title.setText(card_item.getTitle());
        return convertView;
    }
}

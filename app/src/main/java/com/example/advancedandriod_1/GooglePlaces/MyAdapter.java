package com.example.advancedandriod_1.GooglePlaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.advancedandriod_1.GooglePlaces.Googleplaces;
import com.example.advancedandriod_1.R;

import static com.example.advancedandriod_1.GooglePlaces.Googleplaces.al;


public class MyAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(Googleplaces.googleplaces);
        View v=inflater.inflate(R.layout.myadapterlayout,null);

        TextView tv1=v.findViewById(R.id.tv1);
        TextView tv2=v.findViewById(R.id.tv2);


        tv1.setText(al.get(position).getName());
        tv2.setText(al.get(position).getVicinity());


        return v;
    }
}

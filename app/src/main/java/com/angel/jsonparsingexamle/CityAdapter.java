package com.angel.jsonparsingexamle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Angel on 9/23/2016.
 */

public class CityAdapter extends BaseAdapter{
    Context context;
    ArrayList<City> listData;

    public CityAdapter(Context context,ArrayList<City> listData){
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {
        private TextView textViewCityName;
        private TextView textViewCityState;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.city_item,null);
            viewHolder = new ViewHolder();
            viewHolder.textViewCityName = (TextView) view.findViewById(R.id.txtViewCityName);
            viewHolder.textViewCityState = (TextView) view.findViewById(R.id.txtViewCityState);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        City city = listData.get(position);
        String cityName = city.getName();
        String cityState= city.getState();
        viewHolder.textViewCityName.setText(cityName);
        viewHolder.textViewCityState.setText(cityState);
        return view;
    }
}


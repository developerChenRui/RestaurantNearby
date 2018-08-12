package com.example.chenrui.favoriteitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenrui on 2018/8/5.
 */


public class RestaurantAdapter extends BaseAdapter {
    Context context;
    List<Restaurant> restaurantData;

    public RestaurantAdapter(Context context, List<Restaurant> restaurantData) {
        this.context = context;
        this.restaurantData = restaurantData;
    }

    @Override
    public int getCount() {
        return restaurantData.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurantData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_restaurant_list_item,
                    parent, false);
        }

        TextView restaurantName = (TextView) convertView.findViewById(
                R.id.restaurant_name);
        TextView restaurantAddress = (TextView) convertView.findViewById(
                R.id.restaurant_address);
        TextView restaurantType = (TextView) convertView.findViewById(
                R.id.restaurant_type);
        ImageView restaurantImage = (ImageView) convertView.findViewById(R.id.restaurant_thumbnail);
        RatingBar ratingBar = (RatingBar)convertView.findViewById(R.id.restaurant_rating_bar);


        // 对于每一个custom化
        Restaurant r = restaurantData.get(position);
        if(r.getRating() == null) {

            ratingBar.setVisibility(View.VISIBLE);
            ratingBar.setRating((int)r.getStars());
        }

        restaurantName.setText(r.getName());
        restaurantAddress.setText(r.getAddress());
        restaurantType.setText(r.getType());
        restaurantImage.setImageBitmap(r.getThumbnail());
        return convertView;
    }
}


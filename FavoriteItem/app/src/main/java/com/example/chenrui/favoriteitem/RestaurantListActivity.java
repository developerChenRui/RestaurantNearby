package com.example.chenrui.favoriteitem;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.model.LatLng;

public class RestaurantListActivity extends AppCompatActivity implements RestaurantListFragment.OnItemSelectListener{
    RestaurantListFragment listFragment;
    RestaurantGridFragment gridFragment;
    RestaurantMapFragment restaurantMapFragment;

    RelativeLayout relativeLayout;
    boolean isTablet;

    @Override
    public void onItemSelected(LatLng mark){
        restaurantMapFragment.onItemSelected(mark);
    }


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        isTablet = isTablet();

        relativeLayout = (RelativeLayout) findViewById(R.id.fragment_list_container);




        if (isTablet) {
            restaurantMapFragment = new RestaurantMapFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_map_container, restaurantMapFragment).commit();

        }

        Intent intent = getIntent();
        String longitude = intent.getStringExtra("longitude");
        String latitude = intent.getStringExtra("latitude");
        listFragment = new RestaurantListFragment();
        if(longitude!= null && latitude!=null) {

            //???????????????????????????这一步及时地在on start中调用set adapter之前执行吗？？？？？
            //listFragment.putParameters(longitude,latitude);

            Bundle args = new Bundle();
            args.putString("longitude",longitude);
            args.putString("latitude", latitude);
            listFragment.setArguments(args);
        } else {
            //如果这里不加上的话，在list fragment里面getArgument()就会是null,就会出错
            Log.d("blank","success");
            Bundle args = new Bundle();
            args.putString("default","");
            listFragment.setArguments(args);

        }
        //TODO:传入的是nearby地址 listFragment = ...


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list_container, listFragment).commit();
        relativeLayout.setVisibility(View.VISIBLE);



        //*******************************************************************************************

/*        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                YelpApi yelp = new YelpApi();
                yelp.searchForBusinessesByLocation("dinner", "San Francisco, CA", 20);
                return null;
            }
        }.execute();


        if (findViewById(R.id.fragment_container) != null) {
            listFragment =  new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, listFragment).commit();
        }*/




    }

    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }



}

    /**
     * A dummy function to get fake restaurant names.
     *
     * @return an array of fake restaurant names.
     */




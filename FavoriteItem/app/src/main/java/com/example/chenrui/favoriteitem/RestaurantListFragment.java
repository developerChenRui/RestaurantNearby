package com.example.chenrui.favoriteitem;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends Fragment {

    OnItemSelectListener mCallback;

    private ListView listView;

    private DataService dataService;

    private String longitude;

    private String latitude;



    // Container Activity must implement this interface
    public interface OnItemSelectListener {
        public void onItemSelected(LatLng mark);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnItemSelectListener) context;
        } catch (ClassCastException e) {
            //do something
        }
    }
    public RestaurantListFragment() {
        // Required empty public constructor
    }

    public void putParameters(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        listView = (ListView) view.findViewById(R.id.restaurant_list);


        // Set a listener to ListView.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant r = (Restaurant) listView.getItemAtPosition(position);

                // 没有 map container证明在手机上，那就启动活动
                if (getActivity().findViewById(R.id.fragment_map_container) == null) {

                    //Prepare all the data we need to start map activity.
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(RestaurantMapActivity.EXTRA_LATLNG,
                            new LatLng(r.getLat(), r.getLng()));
                    Intent intent = new Intent(getActivity(), RestaurantMapActivity.class);
                    intent.putExtras(bundle);

                    startActivity(intent);
                } else {
                    // 如果能在页面上找到map container 那么证明这是一个平板，就通过fragment- activity -fragment方式得到地图
                    mCallback.onItemSelected(new LatLng(r.getLat(), r.getLng()));
                }
            }
        });


        // 这里实例化data service


        dataService = new DataService();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 从这个函数进去在线程中得到yelp 数据
        refreshRestaurantList(dataService);
    }

    // Make a async call to get restaurant data.
    private void refreshRestaurantList(DataService dataService) {
        new GetRestaurantsNearbyAsyncTask(this, dataService).execute();
    }

    //create AsyncTask background thread task
    private class GetRestaurantsNearbyAsyncTask extends AsyncTask<Void, Void, List<Restaurant>> {
        private Fragment fragment;
        private DataService dataService;

        //计算得到restaurant需要多少时间

        private Clock clock;

        public GetRestaurantsNearbyAsyncTask(Fragment fragment, DataService dataService) {
            this.fragment = fragment;
            this.dataService = dataService;
            this.clock = new Clock();
            this.clock.reset();
        }

        @Override
        protected List<Restaurant> doInBackground(Void... params) {
            clock.start();
            List<Restaurant> list;
            Log.d("doinback","success");
            if(getArguments().getString("longitude")!=null && getArguments().getString("latitude")!=null) {
                list = dataService.getNearbyRestaurants(getArguments().getString("longitude"),getArguments().getString("latitude"));
            } else {
                Log.d("fragment","success");
                list = dataService.getNearbyRestaurants();
            }
            clock.stop();
            Log.e("Latency", Long.toString(clock.getCurrentInterval()));
            return list;

        }

        @Override
        protected void onPostExecute(List<Restaurant> restaurants) {
            if (restaurants != null) {
                super.onPostExecute(restaurants);
                RestaurantAdapter adapter = new RestaurantAdapter(fragment.getActivity(), restaurants);
                listView.setAdapter(adapter);
                Log.d("check","success");
            } else {
                Toast.makeText(fragment.getActivity(), "Data service error.", Toast.LENGTH_LONG);
            }
        }


    }
}

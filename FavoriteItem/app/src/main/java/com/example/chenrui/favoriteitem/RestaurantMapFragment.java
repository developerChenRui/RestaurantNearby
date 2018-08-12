package com.example.chenrui.favoriteitem;


import android.content.BroadcastReceiver;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantMapFragment extends Fragment{


    public final static String EXTRA_LATLNG = "EXTRA_LATLNG";

    private MapView mMap;
    private GoogleMap googleMap;

    private int number = 8;
    MapFragment mapFragment;

    private BroadcastReceiver mBroadcastReceiver;

    private LatLng toMark;

    public RestaurantMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_map, container, false);
        mMap = (MapView) view.findViewById(R.id.mapview);
        mMap.onCreate(savedInstanceState);
        mMap.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        mMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMap.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMap.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMap.onDestroy();
    }


    public void onItemSelected(LatLng mark){
        toMark = mark;
        mMap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                if (toMark != null) {
                    googleMap.addMarker(new MarkerOptions().position(toMark).title("Marker"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(toMark));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(number), 2000, null);
                }

            }
        });
    }
}

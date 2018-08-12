package com.example.chenrui.favoriteitem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button findByCoor;
    Button findNearby;
    EditText longitude;
    EditText latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findByCoor = (Button) findViewById(R.id.coordinate);
        findNearby = (Button) findViewById(R.id.nearby);

        longitude = (EditText) findViewById(R.id.longitude);
        latitude = (EditText) findViewById(R.id.latitude);

        findByCoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String longi = longitude.getText().toString();
                String lati = latitude.getText().toString();

                Bundle coor = new Bundle();
                coor.putString("longitude",longi);
                coor.putString("latitude",lati);

                Intent intent = new Intent(MainActivity.this,RestaurantListActivity.class);
                intent.putExtras(coor);

                startActivity(intent);

            }
        });

        findNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RestaurantListActivity.class);
                Log.d("here","success");
                startActivity(intent);
            }
        });

    }
}

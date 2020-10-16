package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView text = findViewById(R.id.text);
        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Location loc = locationManager .getLastKnownLocation();
                if (loc != null) {
                    text.setText(String.valueOf(loc.getLatitude()));                }
                else {
                    text.setText("none");
                }
                handler.postDelayed(this, 2000);
            }
        });
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10),
    }

}
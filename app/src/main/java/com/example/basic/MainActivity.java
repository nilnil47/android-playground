package com.example.basic;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "test";
    static int staticCounter = 0;
    private LocationManager locationManager;
    private TextView text;
    private TextView onegpstv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationManager.requestSingleUpdate();
        Button button = findViewById(R.id.button);
        Button onegps = findViewById(R.id.onegps);

        onegpstv = findViewById(R.id.onegps);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "reset gps location manager");
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                List<String> providers = locationManager.getProviders(true);
                Log.d(TAG, "onClick: " + providers);

            }
        });

        onegps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("MissingPermission") final Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    Log.d(TAG, String.format("onLocationChanged %f, %f:", location.getLongitude(), location.getLatitude()));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(String.format("%f %f", location.getLatitude(), location.getLongitude()));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    onegpstv.setText(String.format("%f %f", location.getLatitude(), location.getLongitude()));
                                }
                            });
                        }
                    });
                } else {
                    Log.d(TAG, "onClick: location is null");
                }
            }
        });
    }
}
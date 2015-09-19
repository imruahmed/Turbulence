package io.github.imruahmed.turbulence;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, LocationListener{

    private GoogleApiClient mGoogleApiClient;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {

                handleShakeEvent(count);
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this).addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    public void handleShakeEvent(int count) {

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        LocationRequest mLocationRequest = createLocationRequest();
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d("SUSPENSION", "Connection to Google API suspended");
    }

    private LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Location Update", "Latitude: " + location.getLatitude() +
                " Longitude: " + location.getLongitude());
    }
}

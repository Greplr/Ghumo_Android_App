package com.greplr.libcabmeter;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class CabMeterService extends Service {
    public static final String LOG_TAG = "CabMeterService";

    public CabMeterService() {
    }

    public LocationManager locationManager;
    public Location lastLocation = null;
    public Float distance = 0.0f;
    public Criteria criteria;
    LocationListener locLis;

    private CabFare cabFare;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        cabFare = (CabFare) intent.getSerializableExtra("CAB_FARE");
        distance = 0.0f;
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locLis = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(LOG_TAG, location.getLatitude() + "  " + location.getLongitude());
                if (lastLocation == null) {
                    lastLocation = location;
                }
                distance += lastLocation.distanceTo(location) / 1000;
                lastLocation = location;
                float totalFare = CabFareOps.calcFare(distance, cabFare);

                Intent fareIntent = new Intent(CabMeter.INTENT_ACTION_FARE_UPDATE);
                fareIntent.putExtra(CabMeter.INTENT_EXTRA_FARE, totalFare);
                fareIntent.putExtra(CabMeter.INTENT_EXTRA_DISTANCE, distance);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(fareIntent);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                }

                locationManager.requestLocationUpdates(locationManager.getBestProvider(criteria, false), 600, 50, this);
            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        try {
            locationManager.requestLocationUpdates(locationManager.getBestProvider(criteria, false), 600, 50, locLis);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(locLis);
        } catch (Exception e ) {
            //Nothing to do here
        }
    }
}

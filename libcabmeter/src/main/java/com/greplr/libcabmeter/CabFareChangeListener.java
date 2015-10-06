package com.greplr.libcabmeter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by championswimmer on 7/10/15.
 */
public abstract class CabFareChangeListener {

    private BroadcastReceiver mMessageReceiver;

    public CabFareChangeListener() {
         mMessageReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if (!intent.getAction().equals(CabMeter.INTENT_ACTION_FARE_UPDATE)) {
                    return; //Return if not a fare update intent
                }
                Float distance = intent.getFloatExtra(CabMeter.INTENT_EXTRA_DISTANCE, 0.0f);
                Float fare = intent.getFloatExtra(CabMeter.INTENT_EXTRA_FARE, 0.0f);
                Float time = intent.getFloatExtra(CabMeter.INTENT_EXTRA_TIME, 0.0f);
                Log.d("CabFareChangeListener", "Km = " + distance + " Fare = " + fare);
                onFareChange(distance, fare, time);

            }
        };
    }

    public abstract void onFareChange(Float distance, Float fare, Float time);

    public void unregisterListener(Context ctx) {
        LocalBroadcastManager.getInstance(ctx.getApplicationContext()).unregisterReceiver(mMessageReceiver);
    }

    public void registerListener(Context ctx) {
        LocalBroadcastManager.getInstance(ctx.getApplicationContext()).registerReceiver(
                mMessageReceiver, new IntentFilter(CabMeter.INTENT_ACTION_FARE_UPDATE));
    }
}

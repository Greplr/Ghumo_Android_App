package com.greplr.libcabmeter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by championswimmer on 7/10/15.
 */
public class CabMeter {

    public static final String INTENT_ACTION_FARE_UPDATE = "fare_update";
    public static final String INTENT_EXTRA_FARE = "fare";
    public static final String INTENT_EXTRA_DISTANCE = "distance";
    public static final String INTENT_EXTRA_TIME = "time";

    private static CabMeter cabMeterSingleton;
    private static HashMap<String, CabFareChangeListener> cabFareChangeListenerMap;
    private CabFare cabFare;
    private Context mContext;

    public CabFare getCabFare() {
        return cabFare;
    }

    public void setCabFare(CabFare cabFare) {
        this.cabFare = cabFare;
    }

    public static CabMeter getInstance() {
        if (cabMeterSingleton == null) {
            cabMeterSingleton = new CabMeter();
            cabFareChangeListenerMap = new HashMap<>();
        }
        return cabMeterSingleton;
    }

    public void addCabFareChangeListener (String key, CabFareChangeListener cfcl) {
        cabFareChangeListenerMap.put(key, cfcl);
    }

    public void removeCabFareChangeListener (String key) {
        cabFareChangeListenerMap.remove(key);
    }

    public void startMeter(Context appCtx) {
        if (mContext == null) {
            mContext = appCtx.getApplicationContext();
        }
        Intent intent = new Intent(appCtx.getApplicationContext(), CabMeterService.class);
        intent.putExtra("CAB_FARE", cabFare);
        mContext.startService(intent);
    }

    public void stopMeter (Context appCtx) {
        if (mContext == null) {
            mContext = appCtx.getApplicationContext();
        }
        Intent intent = new Intent(appCtx.getApplicationContext(), CabMeterService.class);
        mContext.stopService(intent);

    }

}

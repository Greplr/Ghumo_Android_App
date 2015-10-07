package com.greplr.ghumo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greplr.ghumo.R;
import com.greplr.libcabmeter.CabFare;
import com.greplr.libcabmeter.CabFareChangeListener;
import com.greplr.libcabmeter.CabMeter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeterFragment extends Fragment {

    CabMeter mCabMeter;
    CabFareChangeListener mFareListener;


    public MeterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCabMeter = CabMeter.getInstance(new CabFare(
                "Sample Operator",
                "Delhi",
                "Mini",
                10f,
                100f,
                4f,
                50f
        ));
        mFareListener = new CabFareChangeListener() {
            @Override
            public void onFareChange(Float distance, Float fare, Float time) {
                // Change view elements here
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meter, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFareListener.registerListener(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        mFareListener.unregisterListener(getContext());
    }


}

package com.greplr.ghumo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

    Button startBtn, stopBtn, resetBtn;
    TextView kmMeter, fareMeter, timeMeter;


    public MeterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCabMeter = CabMeter.getInstance();
        mCabMeter.setCabFare(new CabFare(
                "Sample Operator",
                "Delhi",
                "Mini",
                10f,
                100f,
                4f,
                50f
        ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_meter, container, false);

        kmMeter = (TextView) rootView.findViewById(R.id.meter_km);
        fareMeter = (TextView) rootView.findViewById(R.id.meter_fare);
        timeMeter = (TextView) rootView.findViewById(R.id.meter_time);

        startBtn = (Button) rootView.findViewById(R.id.btn_meter_start);
        stopBtn = (Button) rootView.findViewById(R.id.btn_meter_stop);
        resetBtn = (Button) rootView.findViewById(R.id.btn_meter_reset);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCabMeter.startMeter(getContext());
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCabMeter.stopMeter(getContext());
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMeters(0f, 0f, 0f);
            }
        });

        mFareListener = new CabFareChangeListener() {
            @Override
            public void onFareChange(Float distance, Float fare, Float time) {
                // Change view elements here
                updateMeters(distance, fare, time);
            }
        };
        mCabMeter.addCabFareChangeListener("meter_fragment", mFareListener);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCabMeter.removeCabFareChangeListener("meter_fragment");
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

    private void updateMeters (Float distance, Float fare, Float time) {
        kmMeter.setText(distance.toString() + " Km");
        fareMeter.setText("\u20b9 " + fare.toString());
        timeMeter.setText(time.toString());
    }


}

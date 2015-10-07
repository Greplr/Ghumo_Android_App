package com.greplr.ghumo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.greplr.ghumo.R;

/**
 * Created by naman on 07/10/15.
 */
public class FindCabsFragment extends Fragment {

    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ride, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setTitle("");
        ab.setDisplayHomeAsUpEnabled(true);

        mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();

        addToMap(new LatLng(28,28));

        return rootView;
    }

    private void addToMap(LatLng latLng){

        MarkerOptions markerOptions;
        markerOptions = new MarkerOptions();

        markerOptions.position(latLng);
        mMap.addMarker(markerOptions);

        CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(latLng, 6.0f);

        mMap.animateCamera(cameraPosition);

    }
}

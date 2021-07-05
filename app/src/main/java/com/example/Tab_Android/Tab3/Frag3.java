package com.example.Tab_Android.Tab3;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.Tab_Android.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Frag3 extends Fragment {

    boolean isCommuted = false;
    Button commute_button;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.tab3_commute_map, container, false);
        //Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        commute_button = (Button) view.findViewById(R.id.commute_button);
        commute_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCommuted == false){
                    isCommuted = true;
                    setButtonUI("Leave", R.color.red);
                    Toast.makeText(view.getContext(),"퇴근되었습니다",Toast.LENGTH_SHORT).show();
                }
                else{
                    isCommuted=false;
                    setButtonUI("Commute", R.color.green);
                    Toast.makeText(view.getContext(),"출근했습니다",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //When map is loaded
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //When clicked on map
                        //Initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();
                        //================Let's put a custom Icon for this marker==============
                        markerOptions.position(latLng);
                        //Remove all marker
                        googleMap.clear();
                        //Animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        //Add marker on map
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });

        //Return view
        return view;
    }

    private void setButtonUI(String text, int color) {
        commute_button.setText(text);
        commute_button.setBackgroundColor(ContextCompat.getColor( getActivity(), color));
    }

}
package com.example.Tab_Android.Tab3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.Tab_Android.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;


public class Frag3_backup extends Fragment {
    // Initialize variable
    boolean isCommuted = false;
    boolean isInRange = false;
    Button commute_button;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    double lat1 =36.374146, long1 = 127.365920; //Location of the company
    double lat2=0, long2=0; //Location of the user
    double dist; //Distance of the company and the user
    int flag=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initialize view
        View view = inflater.inflate(R.layout.tab3_commute_map, container, false);
        //Initialize map fragment
        supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);
        //Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        //Check permission
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //when permission granted
            //call method
            getCurrentLocation();
        }else{
            //When permission denied
            //Request permission
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


        commute_button = (Button) view.findViewById(R.id.commute_button);
        commute_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInRange){
                    if(!isCommuted){
                        isCommuted = true;
                        setButtonUI("Leave", R.color.red);
                        Toast.makeText(view.getContext(),"퇴근 완료",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        isCommuted=false;
                        setButtonUI("Commute", R.color.green);
                        Toast.makeText(view.getContext(),"출근 완료",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(view.getContext(),"직장과 너무 멀리 있습니다",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Return view
        return view;
    }

    private void getCurrentLocation() {
        //Initialize task location
        @SuppressLint("MissingPermission") Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //When success
                if(location!=null){
                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //Initialize lat lng
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            /**
                            //Get lat long numbers
                            lat2 = location.getLatitude();
                            long2 = location.getLongitude();
                            //Calculate distance
                            dist = distance(lat1, long1, lat2, long2);
                            if (dist<=100) isInRange = true;
*/
                            //Create marker options
                            MarkerOptions options = new MarkerOptions().position(latLng).title("here");
                            //Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                            //Add marker on map
                            googleMap.addMarker(options);

                        }
                    });
                }
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //When permission granted
                //Call method
                getCurrentLocation();
            }
        }
    }


    private double distance(double lat1, double long1, double lat2, double long2) {

        //Calculate distance
        double longDiff = long1 - long2;
        //Calculate distance
        double distance = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(longDiff));
        distance = Math.acos(distance);
        //Convert distance radian to degree
        distance = rad2deg(distance);
        //Distance in meters
        distance = distance * 1.609344 *1000;
        //Return distance value
        return distance;

    }

    //Convert radian to degree
    private double rad2deg(double distance) {
        return (distance * 180.0/Math.PI);
    }

    //Convert degree to radian
    private double deg2rad(double lat1) {
        return (lat1*Math.PI/180.0);
    }

    private void setButtonUI(String text, int color) {
        commute_button.setText(text);
        commute_button.setBackgroundColor(ContextCompat.getColor( getActivity(), color));
    }

}


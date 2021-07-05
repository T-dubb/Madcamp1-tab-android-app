package com.example.Tab_Android.Tab3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.PatternMatcher;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class Frag3 extends Fragment {

    boolean isCommuted = false;
    boolean isInRange = false;
    Button commute_button;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

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
        commute_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInRange == true){
                    if(isCommuted == false){
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
                    Toast.makeText(view.getContext(),"직장과 너무 멀리 있습니다",Toast.LENGTH_SHORT).show();햣
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

    private void setButtonUI(String text, int color) {
        commute_button.setText(text);
        commute_button.setBackgroundColor(ContextCompat.getColor( getActivity(), color));
    }

}


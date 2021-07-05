package com.example.Tab_Android.Tab3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.PatternMatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class Frag3 extends Fragment {

    boolean isCommuted = false;
    int distance = 99;
    Button commute_button;
    TextView interval_time;
    TextView commute_time;
    TextView leave_time;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    long clicktime;

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
        interval_time = (TextView) view.findViewById(R.id.interval_time);
        commute_time = (TextView) view.findViewById(R.id.commute_time);
        leave_time = (TextView) view.findViewById(R.id.leave_time);
        commute_button.setOnClickListener( new View.OnClickListener() {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            @Override
            public void onClick(View view) {
                if(distance <= 100){
                    if(isCommuted == false){
                        clicktime = System.currentTimeMillis();
                        Date date = new Date(clicktime);
                        String commutetime = dateFormat.format(date);
                        commute_time.setText("출근시각: " + commutetime );
                        leave_time.setText("퇴근시각: ");
                        interval_time.setText("총 " + Long.toString(0) + "초 근무했습니다");

                        isCommuted = true;
                        setButtonUI("Leave", R.color.red);
                        Toast.makeText(view.getContext(),"출근 완료",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        long temp = clicktime;
                        clicktime = System.currentTimeMillis();
                        Date tempdate = new Date(temp);
                        Date date = new Date(clicktime);
                        long intervaltime = (date.getTime() - tempdate.getTime())/1000+1;

                        if(intervaltime <= 2){
                            Toast.makeText(view.getContext(),"엥 벌써 퇴근하시게요..?",Toast.LENGTH_SHORT).show();
                        }
                        else if(intervaltime > 15){
                            String string_s = ""+intervaltime;
                            String total_work = "총 " + Long.toString(intervaltime) + "초 근무했습니다.";

                            String toomuchwork = "세상에 " + intervaltime + "초나 일하셨네요...ㄷㄷ";

                            SpannableStringBuilder sp = new SpannableStringBuilder(total_work);
                            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#8B0000")), 2, (2+string_s.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                            Toast.makeText(view.getContext(), toomuchwork ,Toast.LENGTH_SHORT).show();
                            String leavetime = dateFormat.format(date);
                            leave_time.setText("퇴근시각: "+leavetime);
                            interval_time.setText(sp);
                            isCommuted=false;
                            setButtonUI("Commute", R.color.green);
                        }
                        else{
                            String string_s = ""+intervaltime;
                            String total_work = "총 " + Long.toString(intervaltime) + "초 근무했습니다.";
                            SpannableStringBuilder sp = new SpannableStringBuilder(total_work);
                            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#006400")), 2, (2+string_s.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                            String leavetime = dateFormat.format(date);
                            leave_time.setText("퇴근시각: "+leavetime);
                            interval_time.setText(sp);
                            isCommuted=false;
                            setButtonUI("Commute", R.color.green);
                            Toast.makeText(view.getContext(),"퇴근 완료",Toast.LENGTH_SHORT).show();
                        }
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


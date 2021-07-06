package com.example.Tab_Android.Tab2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.example.Tab_Android.R;

public class Frag2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.tab2_album, container, false);

        ImageView Album1 = (ImageView) v.findViewById(R.id.imageView);//get the id of first image view
        ImageView Album2 = (ImageView) v.findViewById(R.id.imageView2);//get the id of second image view
        ImageView Album4 = (ImageView) v.findViewById(R.id.imageView4);//get the id of fourth image view
        ImageView Album3 = (ImageView) v.findViewById(R.id.imageView3);//get the id of third image view

        Album1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SubActivity1.class);
                startActivity(intent);
            }
        });
        Album2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SubActivity2.class);
                startActivity(intent);
            }
        });
        Album3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SubActivity3.class);
                startActivity(intent);
            }
        });
        Album4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SubActivity4.class);
                startActivity(intent);
            }
        });
        return v;

    }
}
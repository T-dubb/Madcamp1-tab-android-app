package com.example.Tab_Android.Tab2;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.Tab_Android.R;


public class Frag2 extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab2_album, container, false);

    }

    public void onViewCreated(View view, @Nullable Bundle SavedInstanceState){
        ViewGroup sceneRoot = getView().findViewById(R.id.view_pager);

        ImageView Album1 = (ImageView) getView().findViewById(R.id.imageView);//Cat
        ImageView Album2 = (ImageView) getView().findViewById(R.id.imageView2);//Food
        ImageView Album4 = (ImageView) getView().findViewById(R.id.imageView4);//Dessert
        ImageView Album3 = (ImageView) getView().findViewById(R.id.imageView3);//Rail

        ViewCompat.setTransitionName(Album1, "cat_image");
/**
        Fragment catFragment = new CatFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(...)
                .addSharedElement(itemImageView, “hero_image”)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();

        /**
        Album1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(catScene, fadeTransition);
            }
        });

        Album2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(foodScene, fadeTransition);
            }
        });
        Album3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(dessertScene, fadeTransition);
            }
        });
        Album4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(landScene, fadeTransition);
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true){
            @Override
            public void handleOnBackPressed(){
                TransitionManager.go(rootScene, fadeTransition);
            }
        };
         */
    }
}
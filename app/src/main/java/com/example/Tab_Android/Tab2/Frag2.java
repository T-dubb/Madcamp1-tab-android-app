package com.example.Tab_Android.Tab2;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.Tab_Android.R;

import java.util.Objects;

public class Frag2 extends Fragment {

    Scene rootScene;
    Scene catScene;
    Scene foodScene;
    Scene dessertScene;
    Scene landScene;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab2_album, container, false);

    }

    public void onViewCreated(View view, Bundle SavedInstanceState) {
        ViewGroup sceneRoot = requireView().findViewById(R.id.tab2_main);
        // Create the scenes
        rootScene = Scene.getSceneForLayout(sceneRoot, R.layout.tab2_album, getActivity());
        catScene = Scene.getSceneForLayout(sceneRoot, R.layout.tab2_view_cat, getActivity());
        foodScene = Scene.getSceneForLayout(sceneRoot, R.layout.tab2_view_food, getActivity());
        dessertScene = Scene.getSceneForLayout(sceneRoot, R.layout.tab2_view_dessert, getActivity());
        landScene = Scene.getSceneForLayout(sceneRoot, R.layout.tab2_view_land, getActivity());

        Transition fadeTransition = new Fade();


        ImageView Album1 = (ImageView) getView().findViewById(R.id.imageView);//get the id of first image view
        ImageView Album2 = (ImageView) getView().findViewById(R.id.imageView2);//get the id of second image view
        ImageView Album4 = (ImageView) getView().findViewById(R.id.imageView4);//get the id of fourth image view
        ImageView Album3 = (ImageView) getView().findViewById(R.id.imageView3);//get the id of third image view


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

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                TransitionManager.go(rootScene, fadeTransition);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }
}
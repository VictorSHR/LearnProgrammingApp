package com.mop.learnprogrammingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;

public class MainFragment extends Fragment {
    static MainFragment newInstance() {
        return new MainFragment();
    }

    LottieAnimationView Button_effect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton buttonOpenSettings = view.findViewById(R.id.buttonOpenSettings);
        buttonOpenSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
                ft.replace(R.id.MainConstraintLayout, SettingsFragment.newInstance()).commit();

            }
        });

        Button_effect = view.findViewById(R.id.button_effect);
        ImageButton button_play_corse = view.findViewById(R.id.buttonPlay);

        button_play_corse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button_effect.setVisibility(View.VISIBLE);
                Button_effect.playAnimation();
            }
        });


        ImageButton button_Training_courses = view.findViewById(R.id.Training_courses);

        button_Training_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
                ft.replace(R.id.MainConstraintLayout, Menu_Training_courses.newInstance()).commit();
            }
        });


        return view;
    }

}
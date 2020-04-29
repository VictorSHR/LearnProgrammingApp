package com.mop.learnprogrammingapp;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Objects;

public class MenuTrainingCourses extends Fragment {
    static MenuTrainingCourses newInstance() {
        return new MenuTrainingCourses();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu_training_courses, container, false);

        ImageButton buttonBackToMainMenu = view.findViewById(R.id.buttonBackToMainMenu);
        buttonBackToMainMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right,
                        R.anim.enter_right_to_left, R.anim.exit_right_to_left);
                ft.replace(R.id.MainConstraintLayout, MainFragment.newInstance()).commit();
            }
        });

        return view;
    }
}

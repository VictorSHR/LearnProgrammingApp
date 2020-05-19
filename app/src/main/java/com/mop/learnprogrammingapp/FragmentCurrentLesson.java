package com.mop.learnprogrammingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Objects;

public class FragmentCurrentLesson extends Fragment {

    static FragmentCurrentLesson newInstance() { return new FragmentCurrentLesson(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_lesson, container, false);

        ((ImageButton) view.findViewById(R.id.imgButtonCurrentLessonClose)).setOnClickListener(view1 -> {
            FragmentTransaction ft = Objects.requireNonNull(getActivity())
                    .getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right,
                    R.anim.enter_right_to_left, R.anim.exit_right_to_left);
            ft.replace(R.id.MainConstraintLayout, FragmentMain.newInstance()).commit();
        });

        return view;
    }
}

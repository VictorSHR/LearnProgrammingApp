package com.mop.learnprogrammingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentCurrentLesson extends Fragment {

    static FragmentCurrentLesson newInstance() { return new FragmentCurrentLesson(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_lesson, container, false);



        return view;
    }
}

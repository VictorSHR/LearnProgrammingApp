package com.mop.learnprogrammingapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Objects;

import im.dacer.androidcharts.LineView;

public class SettingsFragment extends Fragment {

    static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

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

        ArrayList<String> strList = new ArrayList<>();
        ArrayList<ArrayList<Float>> dataLists = new ArrayList<>();

        strList.add("Monday");
        strList.add("Tuesday");
        strList.add("Wednesday");
        strList.add("Thursday");
        strList.add("Friday");
        strList.add("Saturday");
        strList.add("Friday");

        ArrayList<Float> dataListF2 = new ArrayList<>();
        float randomF = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < 7; i++) dataListF2.add((float) (Math.random() * randomF));

        ArrayList<Float> dataListF3 = new ArrayList<>();
        randomF = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < 7; i++) dataListF3.add((float) (Math.random() * randomF));

        dataLists.add(dataListF2);
        dataLists.add(dataListF3);

        LineView lineView = view.findViewById(R.id.line_view);
        lineView.setDrawDotLine(false);
        lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY);
        lineView.setBottomTextList(strList);
        lineView.setColorArray(new int[]{Color.BLUE,Color.RED,Color.GRAY,Color.CYAN});
        lineView.setFloatDataList(dataLists);

        return view;
    }

}

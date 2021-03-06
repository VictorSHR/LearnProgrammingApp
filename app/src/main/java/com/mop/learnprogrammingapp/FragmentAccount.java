package com.mop.learnprogrammingapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import im.dacer.androidcharts.LineView;

public class FragmentAccount extends Fragment {

    static FragmentAccount newInstance() {
        return new FragmentAccount();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        ImageButton buttonBackToMainMenu = view.findViewById(R.id.buttonBackToMainMenu);
        buttonBackToMainMenu.setOnClickListener(view12 -> {
            FragmentTransaction ft = Objects.requireNonNull(getActivity())
                    .getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right,
                    R.anim.enter_right_to_left, R.anim.exit_right_to_left);
            ft.replace(R.id.MainConstraintLayout, FragmentMain.newInstance()).commit();
        });

        ImageButton buttonSettings = view.findViewById(R.id.ButtonSettings);
        buttonSettings.setOnClickListener(view1 -> {
            FragmentTransaction ft = Objects.requireNonNull(getActivity()).
                    getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                    R.anim.enter_left_to_right, R.anim.exit_left_to_right);
            ft.replace(R.id.MainConstraintLayout, FragmentSettingsMenu.newInstance()).commit();
        });

        RecyclerView recycleViewGame = view.findViewById(R.id.RecyclerViewGame);
        recycleViewGame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recycleViewGame.setLayoutManager(llm);

        List<game_cards> cardsSettings = new ArrayList<>();
        cardsSettings.add(new game_cards("Game 1", getResources().getDrawable(R.drawable.ic_notifications_28)));
        cardsSettings.add(new game_cards("Game 2", getResources().getDrawable(R.drawable.ic_volume_outline_28)));
        cardsSettings.add(new game_cards("Game 3", getResources().getDrawable(R.drawable.ic_bug_outline_28)));
        cardsSettings.add(new game_cards("Game 4", getResources().getDrawable(R.drawable.ic_clear_data_outline_28)));

        recycleViewGame.setAdapter(new AdapterCardViewGame(getContext(), cardsSettings));

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
        for (int i = 0; i < 7; i++)
            dataListF2.add((float) (Math.random() * randomF));

        ArrayList<Float> dataListF3 = new ArrayList<>();
        randomF = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < 7; i++)
            dataListF3.add((float) (Math.random() * randomF));

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
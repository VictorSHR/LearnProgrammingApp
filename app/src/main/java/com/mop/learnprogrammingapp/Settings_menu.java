package com.mop.learnprogrammingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Settings_menu extends Fragment {

    static Settings_menu newInstance() { return new Settings_menu(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_menu, container, false);

        ImageButton buttonSettings = view.findViewById(R.id.buttonBackToMainMenu);
        buttonSettings.setOnClickListener(view1 -> {
            FragmentTransaction ft = Objects.requireNonNull(getActivity())
                    .getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right,
                    R.anim.enter_right_to_left, R.anim.exit_right_to_left);
            ft.replace(R.id.MainConstraintLayout, SettingsFragment.newInstance()).commit();
        });

        RecyclerView recycleViewSettings = view.findViewById(R.id.recycleViewSettings);
        recycleViewSettings.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recycleViewSettings.setLayoutManager(llm);

        List<settings_cards> cardsSettings = new ArrayList<>();
        cardsSettings.add(new settings_cards("Напоминание", getResources().getDrawable(R.drawable.ic_notifications_28)));
        cardsSettings.add(new settings_cards ("Звук", getResources().getDrawable(R.drawable.ic_volume_outline_28)));
        cardsSettings.add(new settings_cards("У меня проблема", getResources().getDrawable(R.drawable.ic_bug_outline_28)));
        cardsSettings.add(new settings_cards("Сбросить прогресс", getResources().getDrawable(R.drawable.ic_clear_data_outline_28)));
        cardsSettings.add(new settings_cards("Регистрация/Авторизация",getResources().getDrawable(R.drawable.ic_user_outline_28)));

        recycleViewSettings.setAdapter(new AdapterCardViewSettings(getContext(), cardsSettings));

        return view;
    }
}
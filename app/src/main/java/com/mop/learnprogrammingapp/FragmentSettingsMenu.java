package com.mop.learnprogrammingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentSettingsMenu extends Fragment implements View.OnTouchListener {

    float x;
    LottieAnimationView star_animation;
    static FragmentSettingsMenu newInstance() { return new FragmentSettingsMenu(); }

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
            ft.replace(R.id.MainConstraintLayout, FragmentAccount.newInstance()).commit();
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
        cardsSettings.add(new settings_cards("Регистрация/Авторизация",getResources().getDrawable(R.drawable.ic_mail)));
        cardsSettings.add(new settings_cards("О Разработчиках",getResources().getDrawable(R.drawable.ic_share_external_outline_28)));

        recycleViewSettings.setAdapter(new AdapterCardViewSettings(getContext(), cardsSettings));

        star_animation = view.findViewById(R.id.animation_star);
        star_animation.setOnTouchListener((View.OnTouchListener) this);

       /* star_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view) {
                star_animation.setProgress(x);
                star_animation.playAnimation();
            }
        });*/


        return view;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        if(x>137 && x<512)
            star_animation.setProgress((x-100)/750);
        if(x>512 && x<600)
            star_animation.setProgress((x-100)/750);
        return true;
    }
}
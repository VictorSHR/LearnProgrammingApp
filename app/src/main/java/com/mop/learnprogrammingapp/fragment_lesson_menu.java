package com.mop.learnprogrammingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;

import static com.mop.learnprogrammingapp.AdapterCardViewLesson.SaveDataFirebase;
import static com.mop.learnprogrammingapp.FragmentMain.APP_PREFERENCES;
import static com.mop.learnprogrammingapp.FragmentMain.key_current_course;
import static com.mop.learnprogrammingapp.FragmentMain.key_current_lesson;
import static com.mop.learnprogrammingapp.FragmentMain.key_num_current_lesson;


public class fragment_lesson_menu extends Fragment {

    static fragment_lesson_menu newInstance() {
        return new fragment_lesson_menu();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_lesson_menu, container, false);

        TextView textViewCurrentLesson = rootView.findViewById(R.id.textView_name_lesson);
        Button ButtonStartLessonTheory = rootView.findViewById(R.id.button_start_lesson_theory);

        textViewCurrentLesson.setText(key_current_course+" Урок №"+String.valueOf(Integer.parseInt(key_current_lesson)+1));

        ButtonStartLessonTheory.setOnClickListener(view1 -> {
            getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).edit().putString("CURRENT_COURSE", key_current_course).apply();
            getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).edit().putString("CURRENT_LESSON", key_current_lesson).apply();

            SaveDataFirebase();

            FragmentTransaction ft = ((ActivityMain) getContext()).getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                    R.anim.enter_left_to_right, R.anim.exit_left_to_right);
            ft.replace(R.id.MainConstraintLayout, FragmentCurrentLesson.newInstance()).commit();
        });


        return rootView;
    }
}

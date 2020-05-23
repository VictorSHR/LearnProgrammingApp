package com.mop.learnprogrammingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.Arrays;
=======
import java.util.List;
>>>>>>> Stashed changes
import java.util.Objects;

import static com.mop.learnprogrammingapp.FragmentMain.key_current_course;
import static com.mop.learnprogrammingapp.FragmentMain.key_num_current_lesson;

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

<<<<<<< Updated upstream
        TextView textViewCurrentLesson = view.findViewById(R.id.textViewCurrentLesson);
        Button butNextCurrentLesson = view.findViewById(R.id.butNextCurrentLesson);

        String[] tmp_arrayList_lesson = null;

        switch(key_current_course) {
            case "PYTHON":
                switch(key_num_current_lesson) {
                    case 0:
                        tmp_arrayList_lesson = getResources().getStringArray(R.array.PYTHON_0);
                        break;
                    case 1:
                        tmp_arrayList_lesson = getResources().getStringArray(R.array.PYTHON_1);
                        break;
                }

                break;
            case "CPLUS":
                break;
            case "CSHARP":
                break;
        }

        assert tmp_arrayList_lesson != null;
        final int[] counter_current_lesson = {0};

        textViewCurrentLesson.setText(tmp_arrayList_lesson[counter_current_lesson[0]]);

        String[] finalTmp_arrayList_lesson = tmp_arrayList_lesson;
        butNextCurrentLesson.setOnClickListener(view1 -> {
            counter_current_lesson[0]++;

            if(counter_current_lesson[0] > finalTmp_arrayList_lesson.length - 1) {
                // Тут выходим из урока
            }
            else {
                if(counter_current_lesson[0] == finalTmp_arrayList_lesson.length - 1)
                    butNextCurrentLesson.setText("The End!");

                textViewCurrentLesson.setText(finalTmp_arrayList_lesson[counter_current_lesson[0]]);
            }
        });
=======
        RecyclerView recycleViewSettings = view.findViewById(R.id.RecyclerViewGame);
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

>>>>>>> Stashed changes

        return view;
    }
}

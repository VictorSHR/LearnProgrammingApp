package com.mop.learnprogrammingapp;

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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

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

        TextView textViewCurrentLesson = view.findViewById(R.id.textViewCurrentLesson);
        Button butPrevCurrentLesson = view.findViewById(R.id.butPrevCurrentLesson);
        Button butNextCurrentLesson = view.findViewById(R.id.butNextCurrentLesson);
        ProgressBar progressBarCurrentLesson = view.findViewById(R.id.progressBarCurrentLesson);
        ConstraintLayout constraintLayoutCurrentLesson = view.findViewById(R.id.constraintLayoutCurrentLesson);

        ConstraintSet set = new ConstraintSet();

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
                switch(key_num_current_lesson) {
                    case 0:
                        tmp_arrayList_lesson = getResources().getStringArray(R.array.CPLUS_0);
                        break;
                    case 1:
                        tmp_arrayList_lesson = getResources().getStringArray(R.array.CPLUS_1);
                        break;
                    case 2:
                        tmp_arrayList_lesson = getResources().getStringArray(R.array.CPLUS_2);
                        break;
                }

                break;
            case "CSHARP":
                switch(key_num_current_lesson) {
                    case 0:
                        tmp_arrayList_lesson = getResources().getStringArray(R.array.CSHARP_0);
                        break;
                    case 1:
                        tmp_arrayList_lesson = getResources().getStringArray(R.array.CSHARP_1);
                        break;
                    case 2:
                        tmp_arrayList_lesson = getResources().getStringArray(R.array.CSHARP_2);
                        break;
                }

                break;
        }

        assert tmp_arrayList_lesson != null;
        final int[] counter_current_lesson = {0};

        progressBarCurrentLesson.setMax(tmp_arrayList_lesson.length);
        progressBarCurrentLesson.setProgress(1);

        textViewCurrentLesson.setText(tmp_arrayList_lesson[counter_current_lesson[0]]);
        butPrevCurrentLesson.setVisibility(View.GONE);

        set.clone(constraintLayoutCurrentLesson);
        set.clear(R.id.butNextCurrentLesson, ConstraintSet.START);
        set.connect(R.id.butNextCurrentLesson, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, Objects.requireNonNull(getContext()).getResources().getDisplayMetrics()));
        set.applyTo(constraintLayoutCurrentLesson);

        String[] finalTmp_arrayList_lesson = tmp_arrayList_lesson;
        butNextCurrentLesson.setOnClickListener(view1 -> {
            if(counter_current_lesson[0] >= finalTmp_arrayList_lesson.length - 1) {
                // Тут выходим из урока
            }
            else {
                counter_current_lesson[0]++;
                progressBarCurrentLesson.setProgress(counter_current_lesson[0] + 1);

                if(counter_current_lesson[0] > 0 && (butPrevCurrentLesson.getVisibility() == View.GONE)) {
                    butPrevCurrentLesson.setVisibility(View.VISIBLE);

                    set.clone(constraintLayoutCurrentLesson);
                    set.clear(R.id.butNextCurrentLesson, ConstraintSet.START);
                    set.connect(R.id.butNextCurrentLesson, ConstraintSet.START, R.id.butPrevCurrentLesson, ConstraintSet.END,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, Objects.requireNonNull(getContext()).getResources().getDisplayMetrics()));
                    set.applyTo(constraintLayoutCurrentLesson);
                }

                if(counter_current_lesson[0] == finalTmp_arrayList_lesson.length - 1) {
                    butNextCurrentLesson.setText("Выход");
                }

                textViewCurrentLesson.setText(finalTmp_arrayList_lesson[counter_current_lesson[0]]);
            }
        });

        butPrevCurrentLesson.setOnClickListener(view1 -> {
            counter_current_lesson[0]--;
            progressBarCurrentLesson.setProgress(counter_current_lesson[0] + 1);
            butNextCurrentLesson.setText("Далее");
            textViewCurrentLesson.setText(finalTmp_arrayList_lesson[counter_current_lesson[0]]);

            if(counter_current_lesson[0] == 0) {
                butPrevCurrentLesson.setVisibility(View.GONE);

                set.clone(constraintLayoutCurrentLesson);
                set.clear(R.id.butNextCurrentLesson, ConstraintSet.START);
                set.connect(R.id.butNextCurrentLesson, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, Objects.requireNonNull(getContext()).getResources().getDisplayMetrics()));
                set.applyTo(constraintLayoutCurrentLesson);
            }
        });

        return view;
    }
}

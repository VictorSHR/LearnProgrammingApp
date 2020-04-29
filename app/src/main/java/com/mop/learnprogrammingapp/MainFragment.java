package com.mop.learnprogrammingapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class MainFragment extends Fragment {
    private String[] LIST_COURSES = new String[]{"PYTHON", "CPLUS", "CSHARP"};

    static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerViewPythonLessons = view.findViewById(R.id.recyclerViewPythonLessons);
        RecyclerView recyclerViewCPlusLessons = view.findViewById(R.id.recyclerViewCPlusLessons);
        RecyclerView recyclerViewCSharpLessons = view.findViewById(R.id.recyclerViewCSharpLessons);

        recyclerViewPythonLessons.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        recyclerViewCPlusLessons.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        recyclerViewCSharpLessons.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));

        ArrayList<ModelCardViewCourse> PythonList = new ArrayList<>();
        ArrayList<ModelCardViewCourse> CPlusList = new ArrayList<>();
        ArrayList<ModelCardViewCourse> CSharpList = new ArrayList<>();

        for(int i = 1; i < 11; i++) {
            PythonList.add(new ModelCardViewCourse(getResources().getDrawable(
                    R.drawable.ic_video_outline_28), "Lesson " + i, LIST_COURSES[0]));

            CPlusList.add(new ModelCardViewCourse(getResources().getDrawable(
                    R.drawable.ic_video_outline_28), "Lesson " + i, LIST_COURSES[1]));

            CSharpList.add(new ModelCardViewCourse(getResources().getDrawable(
                    R.drawable.ic_video_outline_28), "Lesson " + i, LIST_COURSES[2]));
        }

        AdapterCardViewCourse adapterCardViewPython = new AdapterCardViewCourse(getContext(), PythonList);
        AdapterCardViewCourse adapterCardViewCPlus = new AdapterCardViewCourse(getContext(), CPlusList);
        AdapterCardViewCourse adapterCardViewCSharp = new AdapterCardViewCourse(getContext(), CSharpList);

        recyclerViewPythonLessons.setAdapter(adapterCardViewPython);
        recyclerViewCPlusLessons.setAdapter(adapterCardViewCPlus);
        recyclerViewCSharpLessons.setAdapter(adapterCardViewCSharp);

        imgButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
                ft.replace(R.id.MainConstraintLayout, SettingsFragment.newInstance()).commit();
            }
        });

        return view;
    }
}
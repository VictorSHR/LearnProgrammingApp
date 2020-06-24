package com.mop.learnprogrammingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentMain extends Fragment {
    static final String APP_PREFERENCES = "USER_DATA";

    static String[] LIST_COURSES = new String[]{"PYTHON", "CPLUS", "CSHARP"};

    static String key_current_course;
    static String key_current_lesson;
    static int key_num_current_lesson;
    // static String uid_user;

    private CardView cardViewCurrentCourse;
    private LinearLayout linLayoutCurrentCourseCard;
    private ImageView imgCurrentCourseCard;
    private TextView textViewCurrentCourseCard;

    static FragmentMain newInstance() {
        return new FragmentMain();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint({"WrongConstant", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        cardViewCurrentCourse = view.findViewById(R.id.cardViewCurrentCourse);
        linLayoutCurrentCourseCard = view.findViewById(R.id.linLayoutCurrentCourseCard);
        imgCurrentCourseCard = view.findViewById(R.id.imgCurrentCourseCard);
        textViewCurrentCourseCard = view.findViewById(R.id.textViewCurrentCourseCard);
        ImageButton imgButtonSettings = view.findViewById(R.id.imgButtonSettings);

        /*if(!hasConnection(Objects.requireNonNull(getContext())))
            Toast.makeText(getContext(),
                    "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();*/

        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String user_name = sharedPreferences.getString("USER_NAME", null);
        key_current_course = sharedPreferences.getString("CURRENT_COURSE", null);
        key_current_lesson = sharedPreferences.getString("CURRENT_LESSON", null);

        if (user_name != null) ((TextView) view.findViewById(R.id.textViewNameUser)).setText(user_name);
        else view.findViewById(R.id.textViewNameUser).setVisibility(View.INVISIBLE);

        updateCurrentLesson();

        /*FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            uid_user = currentUser.getUid();
            ((TextView) view.findViewById(R.id.textViewNameUser)).setText(currentUser.getDisplayName());
        }
        else
            view.findViewById(R.id.textViewNameUser).setVisibility(View.INVISIBLE);*/

        /*FirebaseDatabase.getInstance().getReference(uid_user)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                key_current_course = dataSnapshot.child("CURRENT_COURSE").getValue(String.class);
                key_current_lesson = dataSnapshot.child("CURRENT_LESSON").getValue(String.class);
                updateCurrentLesson();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });*/

        RecyclerView recyclerViewPythonLessons = view.findViewById(R.id.recyclerViewPythonLessons);
        RecyclerView recyclerViewCPlusLessons = view.findViewById(R.id.recyclerViewCPlusLessons);
        RecyclerView recyclerViewCSharpLessons = view.findViewById(R.id.recyclerViewCSharpLessons);

        recyclerViewPythonLessons.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        recyclerViewCPlusLessons.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        recyclerViewCSharpLessons.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));

        ArrayList<lessons_cards> PythonList = new ArrayList<>();
        ArrayList<lessons_cards> CPlusList = new ArrayList<>();
        ArrayList<lessons_cards> CSharpList = new ArrayList<>();

        for(int i = 1; i < 11; i++) {
            PythonList.add(new lessons_cards(getResources().getDrawable(
                    R.drawable.ic_video_outline_28), "Lesson " + i, LIST_COURSES[0]));

            CPlusList.add(new lessons_cards(getResources().getDrawable(
                    R.drawable.ic_video_outline_28), "Lesson " + i, LIST_COURSES[1]));

            CSharpList.add(new lessons_cards(getResources().getDrawable(
                    R.drawable.ic_video_outline_28), "Lesson " + i, LIST_COURSES[2]));
        }

        AdapterCardViewLesson adapterCardViewPython = new AdapterCardViewLesson(getContext(), PythonList);
        AdapterCardViewLesson adapterCardViewCPlus = new AdapterCardViewLesson(getContext(), CPlusList);
        AdapterCardViewLesson adapterCardViewCSharp = new AdapterCardViewLesson(getContext(), CSharpList);

        recyclerViewPythonLessons.setAdapter(adapterCardViewPython);
        recyclerViewCPlusLessons.setAdapter(adapterCardViewCPlus);
        recyclerViewCSharpLessons.setAdapter(adapterCardViewCSharp);

        imgButtonSettings.setOnClickListener(view1 -> {
            FragmentTransaction ft = Objects.requireNonNull(getActivity())
                    .getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                    R.anim.enter_left_to_right, R.anim.exit_left_to_right);
            ft.replace(R.id.MainConstraintLayout, FragmentAccount.newInstance()).commit();
        });

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void updateCurrentLesson() {
        if(key_current_course != null) {
            textViewCurrentCourseCard.setText("Lesson №" +
                    String.valueOf(Integer.parseInt(key_current_lesson) + 1));
            imgCurrentCourseCard.setImageDrawable(getResources().getDrawable(R.drawable.ic_video_outline_100));
            textViewCurrentCourseCard.setTextColor(getResources().getColor(R.color.bg_register));
            cardViewCurrentCourse.setClickable(true);
            cardViewCurrentCourse.setFocusable(true);

            if(key_current_course.equals(LIST_COURSES[0]))
                linLayoutCurrentCourseCard.setBackground(getResources().getDrawable(R.drawable.corner_radius_item_python));
            else if(key_current_course.equals(LIST_COURSES[1]))
                linLayoutCurrentCourseCard.setBackground(getResources().getDrawable(R.drawable.corner_radius_item_cplus));
            else if(key_current_course.equals(LIST_COURSES[2]))
                linLayoutCurrentCourseCard.setBackground(getResources().getDrawable(R.drawable.corner_radius_item_csharp));
        }
    }
}
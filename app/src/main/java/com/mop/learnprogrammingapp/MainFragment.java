package com.mop.learnprogrammingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainFragment extends Fragment {
    private String[] LIST_COURSES = new String[]{"PYTHON", "CPLUS", "CSHARP"};

    static String key_current_course;
    static String key_current_lesson;
    static String uid_user;

    private CardView cardViewCurrentCourse;
    private LinearLayout linLayoutCurrentCourseCard;
    private ImageView imgCurrentCourseCard;
    private TextView textViewCurrentCourseCard;

    static MainFragment newInstance() {
        return new MainFragment();
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

        if(!hasConnection(Objects.requireNonNull(getContext())))
            Toast.makeText(getContext(),
                    "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            uid_user = currentUser.getUid();
            ((TextView) view.findViewById(R.id.textViewNameUser)).setText(currentUser.getDisplayName());
        }
        else
            view.findViewById(R.id.textViewNameUser).setVisibility(View.INVISIBLE);

        cardViewCurrentCourse = view.findViewById(R.id.cardViewCurrentCourse);
        linLayoutCurrentCourseCard = view.findViewById(R.id.linLayoutCurrentCourseCard);
        imgCurrentCourseCard = view.findViewById(R.id.imgCurrentCourseCard);
        textViewCurrentCourseCard = view.findViewById(R.id.textViewCurrentCourseCard);
        ImageButton imgButtonSettings = view.findViewById(R.id.imgButtonSettings);

        FirebaseDatabase.getInstance().getReference(uid_user)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                key_current_course = dataSnapshot.child("CURRENT_COURSE").getValue(String.class);
                key_current_lesson = dataSnapshot.child("CURRENT_LESSON").getValue(String.class);
                updateCurrentLesson();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

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

        imgButtonSettings.setOnClickListener(view1 -> {
            FragmentTransaction ft = Objects.requireNonNull(getActivity())
                    .getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                    R.anim.enter_left_to_right, R.anim.exit_left_to_right);
            ft.replace(R.id.MainConstraintLayout, SettingsFragment.newInstance()).commit();
        });

        return view;
    }

    private static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;

        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) return true;

        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) return true;

        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) return true;

        return false;
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
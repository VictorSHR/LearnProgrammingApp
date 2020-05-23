package com.mop.learnprogrammingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static com.mop.learnprogrammingapp.FragmentMain.key_current_course;
import static com.mop.learnprogrammingapp.FragmentMain.key_current_lesson;
import static com.mop.learnprogrammingapp.FragmentMain.key_num_current_lesson;
import static com.mop.learnprogrammingapp.FragmentMain.uid_user;

public class AdapterCardViewLesson extends RecyclerView.Adapter<AdapterCardViewLesson.CardViewHolder> {
    private Context mContext;
    private List<lessons_cards> cards;

    AdapterCardViewLesson(Context mContext, List<lessons_cards> cards) {
        this.mContext = mContext;
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.item_lesson_card, parent, false);

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder cardViewHolder, final int position) {
        switch(cards.get(position).getCourse()) {
            case "PYTHON":
                cardViewHolder.linLayoutCard.setBackground(cardViewHolder.itemView.getResources()
                        .getDrawable(R.drawable.corner_radius_item_python));
                break;
            case "CPLUS":
                cardViewHolder.linLayoutCard.setBackground(cardViewHolder.itemView.getResources()
                        .getDrawable(R.drawable.corner_radius_item_cplus));
                break;
            case "CSHARP":
                cardViewHolder.linLayoutCard.setBackground(cardViewHolder.itemView.getResources()
                        .getDrawable(R.drawable.corner_radius_item_csharp));
                break;
        }

        cardViewHolder.img.setImageDrawable(cards.get(position).getImg());
        cardViewHolder.lesson.setText(cards.get(position).getLesson());

        cardViewHolder.itemView.setOnClickListener(view -> {
            key_current_course = cards.get(position).getCourse();
            key_current_lesson = String.valueOf(position);
            key_num_current_lesson = position;

            DatabaseReference tmpDB = FirebaseDatabase.getInstance().getReference(uid_user);
            tmpDB.child("CURRENT_COURSE").setValue(key_current_course);
            tmpDB.child("CURRENT_LESSON").setValue(key_current_lesson);

            FragmentTransaction ft = ((ActivityMain) mContext).getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                    R.anim.enter_left_to_right, R.anim.exit_left_to_right);
            ft.replace(R.id.MainConstraintLayout, FragmentCurrentLesson.newInstance()).commit();
        });
    }

    @Override
    public int getItemCount() { return cards.size(); }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView lesson;
        LinearLayout linLayoutCard;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgItemCourse);
            lesson = itemView.findViewById(R.id.textViewItemCourse);
            linLayoutCard = itemView.findViewById(R.id.nestedLinLayoutItemCourse);
        }
    }
}

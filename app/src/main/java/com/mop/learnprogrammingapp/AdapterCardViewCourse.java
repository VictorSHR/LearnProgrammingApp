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

import java.util.List;

import static com.mop.learnprogrammingapp.MainFragment.databaseFirebase;
import static com.mop.learnprogrammingapp.MainFragment.db_key_current_course;

public class AdapterCardViewCourse extends RecyclerView.Adapter<AdapterCardViewCourse.CardViewHolder> {
    private Context mContext;
    private List<ModelCardViewCourse> cards;

    AdapterCardViewCourse(Context mContext, List<ModelCardViewCourse> cards) {
        this.mContext = mContext;
        this.cards = cards;
    }

    AdapterCardViewCourse(List<ModelCardViewCourse> cards){ this.cards = cards; }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.item_cardview, parent, false);

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

        cardViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db_key_current_course.setValue(cards.get(position).getCourse());

                FragmentTransaction ft = ((MainActivity) mContext).getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
                ft.replace(R.id.MainConstraintLayout, SettingsFragment.newInstance()).commit();
            }
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

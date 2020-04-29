package com.mop.learnprogrammingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(final CardViewHolder cardViewHolder, final int position) {
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

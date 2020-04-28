package com.mop.learnprogrammingapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterCardViewCourse extends RecyclerView.Adapter<AdapterCardViewCourse.CardViewHolder> {
    private List<ModelCardViewCourse> cards;

    class CardViewHolder extends RecyclerView.ViewHolder {
        Button cardView;
        int currentCardPosition;
        Context mContext;

        CardViewHolder(Button cv, Context context) {
            super(cv);
            cardView = cv;
            mContext = context;
        }
    }

    AdapterCardViewCourse(List<ModelCardViewCourse> cards){ this.cards = cards; }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button CardViewSite = (Button) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview, parent, false);
        return new CardViewHolder(CardViewSite, CardViewSite.getContext());
    }

    @Override
    public void onBindViewHolder(final CardViewHolder cardViewHolder, final int position) {
        Button cardView = cardViewHolder.cardView;

        cardView.setCompoundDrawablesWithIntrinsicBounds(null, cards.get(position).getImg(), null, null);
        cardView.setText(cards.get(position).getLesson());

        cardViewHolder.currentCardPosition = position;

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(cardViewHolder.mContext, "OK!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { return cards.size(); }
}

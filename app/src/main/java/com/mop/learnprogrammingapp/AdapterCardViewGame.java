package com.mop.learnprogrammingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterCardViewGame extends RecyclerView.Adapter<AdapterCardViewGame.GameViewHolder>  {
    private Context mContext;
    private List<game_cards> cards;

    AdapterCardViewGame(Context mContext, List<game_cards> cards){
        this.mContext = mContext;
        this.cards = cards;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_game_card, parent,false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.game_text.setText(cards.get(position).getGame_text());
        holder.img.setImageDrawable(cards.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView game_text;

         GameViewHolder(@NonNull View itemView) {
            super(itemView);
             game_text = itemView.findViewById(R.id.textViewItemGame);
             img = itemView.findViewById(R.id.imgItemGame);
        }
    }
}

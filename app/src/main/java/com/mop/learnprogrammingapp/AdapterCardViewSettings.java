package com.mop.learnprogrammingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class AdapterCardViewSettings extends RecyclerView.Adapter<AdapterCardViewSettings.SettingsViewHolder>{
    private Context mContext;
    private List<settings_cards> cards;

    AdapterCardViewSettings(Context mContext, List<settings_cards> cards){
        this.mContext = mContext;
        this.cards = cards;
    }


    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_settings_card, parent,false);
        return new SettingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        holder.ImgSettings.setImageDrawable(cards.get(position).getImg());
        holder.TextSettings.setText(cards.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class SettingsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView ImgSettings;
        TextView TextSettings;
        SettingsViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            ImgSettings = itemView.findViewById(R.id.img_settings);
            TextSettings = itemView.findViewById(R.id.text_settings);
        }
    }
}

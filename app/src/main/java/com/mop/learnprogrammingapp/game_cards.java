package com.mop.learnprogrammingapp;

import android.graphics.drawable.Drawable;

public class game_cards {
    private Drawable img;
    private String game_text;

    game_cards(String game_text, Drawable img) {
        this.game_text = game_text;
        this.img = img;
    }

    String getGame_text() {
        return game_text;
    }

    Drawable getImg() {
        return img;
    }

}
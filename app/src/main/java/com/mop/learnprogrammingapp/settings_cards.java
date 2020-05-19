package com.mop.learnprogrammingapp;

import android.graphics.drawable.Drawable;

class settings_cards {
        private String text;
        private Drawable img;

        settings_cards(String name, Drawable idImg) {
            this.text = name;
            this.img = idImg;
        }

    String getText() {
        return text;
    }

    Drawable getImg() {
        return img;
    }
}
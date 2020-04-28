package com.mop.learnprogrammingapp;

import android.graphics.drawable.Drawable;

class ModelCardViewCourse {
    private Drawable img;
    private String lesson;

    ModelCardViewCourse(Drawable img, String lesson) {
        this.img = img;
        this.lesson = lesson;
    }

    Drawable getImg() { return img; }

    String getLesson() { return lesson; }
}

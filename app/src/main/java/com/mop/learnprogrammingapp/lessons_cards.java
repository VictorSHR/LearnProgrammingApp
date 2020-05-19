package com.mop.learnprogrammingapp;

import android.graphics.drawable.Drawable;

class lessons_cards {
    private Drawable img;
    private String lesson;
    private String course;

    lessons_cards(Drawable img, String lesson, String course) {
        this.img = img;
        this.lesson = lesson;
        this.course = course;
    }

    Drawable getImg() { return img; }

    String getLesson() { return lesson; }

    String getCourse() { return course; }
}
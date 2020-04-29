package com.mop.learnprogrammingapp;

import android.graphics.drawable.Drawable;

class ModelCardViewCourse {
    private Drawable img;
    private String lesson;
    private String course;

    ModelCardViewCourse(Drawable img, String lesson, String course) {
        this.img = img;
        this.lesson = lesson;
        this.course = course;
    }

    Drawable getImg() { return img; }

    String getLesson() { return lesson; }

    String getCourse() { return course; }
}
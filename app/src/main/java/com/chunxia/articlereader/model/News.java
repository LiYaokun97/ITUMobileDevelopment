package com.chunxia.articlereader.model;

import android.graphics.drawable.Drawable;

public class News {

    public int image;
    public Drawable imageDrw;
    public String title;
    public String subtitle;
    public String date;

    public News(int image, String title, String subtitle, String date) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
    }

    public News() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Drawable getImageDrw() {
        return imageDrw;
    }

    public void setImageDrw(Drawable imageDrw) {
        this.imageDrw = imageDrw;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

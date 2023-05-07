package com.chunxia.articlereader.model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

    public int image;
    public String title;
    public String subtitle;
    public String date;

    public String content;

    public News(int image, String title, String subtitle, String date, String content) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public News() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.image);
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeString(this.date);
        dest.writeString(this.content);
    }

    public void readFromParcel(Parcel source) {
        this.image = source.readInt();
        this.title = source.readString();
        this.subtitle = source.readString();
        this.date = source.readString();
        this.content = source.readString();
    }

    protected News(Parcel in) {
        this.image = in.readInt();
        this.title = in.readString();
        this.subtitle = in.readString();
        this.date = in.readString();
        this.content = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}

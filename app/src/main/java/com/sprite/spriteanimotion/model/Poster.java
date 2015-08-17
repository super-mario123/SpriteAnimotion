package com.sprite.spriteanimotion.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dings on 2015/8/5.
 */
public class Poster implements Parcelable {

    /**
     * 小图
     */
    private String small;
    /**
     * 中图
     */
    private String middle;
    /**
     * 大图
     */
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }


    @Override
    public String toString() {
        return "Poster{" +
                "small='" + small + '\'' +
                ", middle='" + middle + '\'' +
                ", large='" + large + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.middle);
        dest.writeString(this.large);
    }

    public Poster() {
    }

    private Poster(Parcel in) {
        this.small = in.readString();
        this.middle = in.readString();
        this.large = in.readString();
    }

    public static final Parcelable.Creator<Poster> CREATOR = new Parcelable.Creator<Poster>() {
        public Poster createFromParcel(Parcel source) {
            return new Poster(source);
        }

        public Poster[] newArray(int size) {
            return new Poster[size];
        }
    };
}

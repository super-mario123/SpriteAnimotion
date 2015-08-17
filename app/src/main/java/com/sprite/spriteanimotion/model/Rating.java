package com.sprite.spriteanimotion.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dings on 2015/8/5.
 */
public class Rating implements Parcelable {

    /**
     * 最低评分
     */
    private int min;
    /**
     * 最高评分
     */
    private int max;
    /**
     * 评分
     */
    private float average;
    /**
     * 评星数
     */
    private int stars;



    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "min=" + min +
                ", max=" + max +
                ", average=" + average +
                ", stars=" + stars +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.min);
        dest.writeInt(this.max);
        dest.writeFloat(this.average);
        dest.writeInt(this.stars);
    }

    public Rating() {
    }

    private Rating(Parcel in) {
        this.min = in.readInt();
        this.max = in.readInt();
        this.average = in.readFloat();
        this.stars = in.readInt();
    }

    public static final Parcelable.Creator<Rating> CREATOR = new Parcelable.Creator<Rating>() {
        public Rating createFromParcel(Parcel source) {
            return new Rating(source);
        }

        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };
}

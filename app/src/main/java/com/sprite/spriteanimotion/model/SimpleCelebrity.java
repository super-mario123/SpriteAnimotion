package com.sprite.spriteanimotion.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dings on 2015/8/5.
 */
public class SimpleCelebrity implements Parcelable {
    /**
     * 影人条目id
     */
    private String id;
    /**
     * 中文名
     */
    private String name;
    /**
     * 影人头像
     */
    private Poster avatars;

    /**
     * 艺人类型，1.导演 2.演员
     */
    private int type;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Poster getAvatars() {
        return avatars;
    }

    public void setAvatars(Poster avatars) {
        this.avatars = avatars;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SimpleCelebrity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", avatars=" + avatars +
                ", type=" + type +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.avatars, 0);
        dest.writeInt(this.type);
    }

    public SimpleCelebrity() {
    }

    private SimpleCelebrity(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.avatars = in.readParcelable(Poster.class.getClassLoader());
        this.type = in.readInt();
    }

    public static final Parcelable.Creator<SimpleCelebrity> CREATOR = new Parcelable.Creator<SimpleCelebrity>() {
        public SimpleCelebrity createFromParcel(Parcel source) {
            return new SimpleCelebrity(source);
        }

        public SimpleCelebrity[] newArray(int size) {
            return new SimpleCelebrity[size];
        }
    };
}

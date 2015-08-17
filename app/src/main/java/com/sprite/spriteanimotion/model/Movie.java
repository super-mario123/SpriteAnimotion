package com.sprite.spriteanimotion.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by dings on 2015/8/5.
 */
    @Table(name="Movie")
public class Movie extends Model implements Parcelable{
    /**
     * 电影条目id
     */
    @Column(name="movieId")
    @SerializedName(value = "id")
    private String movieId;
    /**
     * 中文名
     */
    @Column(name="title")
    private String title;
    /**
     * 原名
     */
    @Column(name="original_title")
    private String original_title;
    /**
     * 又名
     */
    @Column(name="aka")
    private String[] aka;
    /**
     * 评分
     */
    @Column(name="rating")
    private Rating rating;
    /**
     * 评分人数
     */
    @Column(name="ratings_count")
    private int ratings_count;
    /**
     * 想看人数
     */
    @Column(name="wish_count")
    private int wish_count;
    /**
     * 看过人数
     */
    @Column(name="collect_count")
    private int collect_count;
    /**
     * 电影海报
     */
    @Column(name="images")
    private Poster images;
    /**
     * 条目分类, movie或者tv
     */
    @Column(name="subtype")
    private String subtype;
    /**
     * 导演
     */
    @Column(name="directors")
    private SimpleCelebrity[] directors;
    /**
     * 主演
     */
    @Column(name="casts")
    private SimpleCelebrity[] casts;
    /**
     * 编剧
     */
    @Column(name="writers")
    private SimpleCelebrity[] writers;
    /**
     * 官方网站
     */
    @Column(name="website")
    private String website;
    /**
     * 年代
     */
    @Column(name="year")
    private String year;
    /**
     * 影片类型
     */
    @Column(name="genres")
    private String[] genres;
    /**
     * 制片国家
     */
    @Column(name="countries")
    private String[] countries;
    /**
     * 简介
     */
    @Column(name="summary")
    private String summary;
    /**
     * 短评论数
     */
    @Column(name="comments_count")
    private int comments_count;
    /**
     * 影评数
     */
    @Column(name="reviews_count")
    private int reviews_count;

//    public String getId() {
//        return movieId;
//    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieId(){return movieId;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String[] getAka() {
        return aka;
    }

    public void setAka(String[] aka) {
        this.aka = aka;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Poster getImages() {
        return images;
    }

    public void setImages(Poster images) {
        this.images = images;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public SimpleCelebrity[] getDirectors() {
        return directors;
    }

    public void setDirectors(SimpleCelebrity[] directors) {
        this.directors = directors;
    }

    public SimpleCelebrity[] getCasts() {
        return casts;
    }

    public void setCasts(SimpleCelebrity[] casts) {
        this.casts = casts;
    }

    public SimpleCelebrity[] getWriters() {
        return writers;
    }

    public void setWriters(SimpleCelebrity[] writers) {
        this.writers = writers;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", aka=" + Arrays.toString(aka) +
                ", rating=" + rating +
                ", ratings_count=" + ratings_count +
                ", wish_count=" + wish_count +
                ", collect_count=" + collect_count +
                ", images=" + images +
                ", subtype='" + subtype + '\'' +
                ", directors=" + Arrays.toString(directors) +
                ", casts=" + Arrays.toString(casts) +
                ", writers=" + Arrays.toString(writers) +
                ", website='" + website + '\'' +
                ", year='" + year + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", countries=" + Arrays.toString(countries) +
                ", summary='" + summary + '\'' +
                ", comments_count=" + comments_count +
                ", reviews_count=" + reviews_count +
                '}';
    }

    public Movie(){}

    public Movie(String movieId, String title, String original_title, String[] aka, Rating rating, int ratings_count, int wish_count, int collect_count, Poster images, String subtype, SimpleCelebrity[] directors, SimpleCelebrity[] casts, SimpleCelebrity[] writers, String website, String year, String[] genres, String[] countries, String summary, int comments_count, int reviews_count) {
        this.movieId = movieId;
        this.title = title;
        this.original_title = original_title;
        this.aka = aka;
        this.rating = rating;
        this.ratings_count = ratings_count;
        this.wish_count = wish_count;
        this.collect_count = collect_count;
        this.images = images;
        this.subtype = subtype;
        this.directors = directors;
        this.casts = casts;
        this.writers = writers;
        this.website = website;
        this.year = year;
        this.genres = genres;
        this.countries = countries;
        this.summary = summary;
        this.comments_count = comments_count;
        this.reviews_count = reviews_count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieId);
        dest.writeString(this.title);
        dest.writeString(this.original_title);
        dest.writeStringArray(this.aka);
        dest.writeParcelable(this.rating, flags);
        dest.writeInt(this.ratings_count);
        dest.writeInt(this.wish_count);
        dest.writeInt(this.collect_count);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.subtype);
        dest.writeString(this.website);
        dest.writeString(this.year);
        dest.writeStringArray(this.genres);
        dest.writeStringArray(this.countries);
        dest.writeString(this.summary);
        dest.writeInt(this.comments_count);
        dest.writeInt(this.reviews_count);
    }

    private Movie(Parcel in) {
        this.movieId = in.readString();
        this.title = in.readString();
        this.original_title = in.readString();
        this.aka = in.createStringArray();
        this.rating = in.readParcelable(Rating.class.getClassLoader());
        this.ratings_count = in.readInt();
        this.wish_count = in.readInt();
        this.collect_count = in.readInt();
        this.images = in.readParcelable(Poster.class.getClassLoader());
        this.subtype = in.readString();
//        this.directors = in.readParcelable(SimpleCelebrity[].class.getClassLoader());
//        this.casts = in.readParcelable(SimpleCelebrity[].class.getClassLoader());
//        this.writers = in.readParcelable(SimpleCelebrity[].class.getClassLoader());
        this.website = in.readString();
        this.year = in.readString();
        this.genres = in.createStringArray();
        this.countries = in.createStringArray();
        this.summary = in.readString();
        this.comments_count = in.readInt();
        this.reviews_count = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}

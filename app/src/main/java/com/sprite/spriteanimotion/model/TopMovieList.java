package com.sprite.spriteanimotion.model;

import java.util.List;

/**
 * Created by dings on 2015/8/5.
 */
public class TopMovieList {
    /**
     * 起始位置
     */
    private int start;
    /**
     * 相对起始位置的偏移量
     */
    private int count;
    /**
     * 总数
     */
    private int total;
    /**
     * 排行榜名称
     */
    private String title;
    /**
     * 电影列表
     */
    private List<Movie> subjects;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Movie> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Movie> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "TopMovieList{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}

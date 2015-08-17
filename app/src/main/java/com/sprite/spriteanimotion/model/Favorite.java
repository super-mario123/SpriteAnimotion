package com.sprite.spriteanimotion.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by dings on 2015/8/16.
 */
@Table(name="Favorite")
public class Favorite {

    @Column(name="movieId")
    public final String movieId;

    public Favorite(Movie movie){
        movieId = movie.getMovieId();
    }
}

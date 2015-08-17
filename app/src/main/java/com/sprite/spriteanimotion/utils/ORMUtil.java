package com.sprite.spriteanimotion.utils;

import com.activeandroid.ActiveAndroid;
import com.sprite.spriteanimotion.model.Movie;

import java.util.List;

/**
 * Created by dings on 2015/8/15.
 */
public class ORMUtil {

    public static void saveMvoies(List<Movie> movies){
        try{

            for (Movie movie: movies) {
                movie.save();
            }
            ActiveAndroid.setTransactionSuccessful();

        }finally {
            ActiveAndroid.endTransaction();
        }
    }

    public static void saveMovie(Movie movie){
        try{
            movie.save();
            ActiveAndroid.setTransactionSuccessful();
        }finally {
            ActiveAndroid.endTransaction();
        }
    }

}

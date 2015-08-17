package com.sprite.spriteanimotion.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sprite.spriteanimotion.db.MovieDB;
import com.sprite.spriteanimotion.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dings on 2015/8/17.
 */
public class DBManager {

    private MovieDB mMovieDB;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        mMovieDB = new MovieDB(context, MovieDB.NAME, null, MovieDB.VERSION);
        db = mMovieDB.getWritableDatabase();
    }

    public void addMovie(Movie movie) {
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO Movie VALUES(null,?,?,?)",
                    new Object[]{movie.getMovieId(), movie.getTitle(), movie.getOriginal_title()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void addMovies(List<Movie> movies) {
        db.beginTransaction();
        try {
            for (Movie movie : movies) {
                db.execSQL("INSERT INTO Movie VALUES(null,?,?,?)",
                        new Object[]{movie.getMovieId(), movie.getTitle(), movie.getOriginal_title()});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    public void updateMovie(Movie movie) {

        ContentValues cv = new ContentValues();
        cv.put("title", movie.getTitle());
        db.update("Movie", cv, "id = ?", new String[]{movie.getMovieId()});
    }

    public void deleteMovie(Movie movie) {
        db.delete("Movie", "id = ?", new String[]{movie.getMovieId()});
    }

    public List<Movie> query() {
        ArrayList<Movie> movies = new ArrayList<>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            Movie movie = new Movie();
            movie.setMovieId(c.getString(c.getColumnIndex("id")));
            movie.setTitle(c.getString(c.getColumnIndex("title")));
            movie.setOriginal_title(c.getString(c.getColumnIndex("original_title")));
            movies.add(movie);
        }
        c.close();
        return movies;
    }

    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM Movie", null);
        return c;
    }

    public void closeDB() {
        db.close();
    }

}

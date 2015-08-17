package com.sprite.spriteanimotion.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dings on 2015/8/16.
 */
public class MovieDB extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String NAME = "MovieDB";
    private static final String TABLE_MOVIE_NAME = "Movie";
    private static final String TABLE_FAV_NAME = "fav";

    private static final String DATABASE_MOVIE_CREATE = "create table Movie(_id integer primary key autoincrement,"
            + "id text not null UNIQUE,"
            + "title text not null,"
            + "original_title text not null"
            + ");";
    private static final String DATABASE_FAV_CREATE = "create table fav(_id integer primary key autoincrement,"
            + "vid text not null UNIQUE"
            + ");";

    public MovieDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_MOVIE_CREATE);
        db.execSQL(DATABASE_FAV_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

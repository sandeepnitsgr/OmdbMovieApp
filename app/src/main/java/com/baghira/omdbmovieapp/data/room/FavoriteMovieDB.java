package com.baghira.omdbmovieapp.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.baghira.omdbmovieapp.data.room.dao.FavoriteMovieDAO;
import com.baghira.omdbmovieapp.data.room.entities.FavoriteMovie;

@Database(entities = {FavoriteMovie.class}, version = FavoriteMovieDB.VERSION)
public abstract class FavoriteMovieDB extends RoomDatabase {

    static final int VERSION = 1;

    public abstract FavoriteMovieDAO getFavoriteMovieDao();

}
package com.Sunflower.myapplication.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertmovie(MovieRoom movie);

    @Query("SELECT * FROM tbmovie where type =1")
    MovieRoom[] select_movie_favorit();

    @Query("SELECT * FROM tbmovie where type =2")
    MovieRoom[] select_tv_favorit();

    @Delete
    int deleteMovie(MovieRoom movie);

}

package com.Sunflower.myapplication.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieRoom.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

}
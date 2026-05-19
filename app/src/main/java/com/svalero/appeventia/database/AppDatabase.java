package com.svalero.appeventia.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Favorito.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoritoDao favoritoDao();
}
package com.svalero.appeventia.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoritoDao {

    @Query("SELECT * FROM favoritos")
    List<Favorito> findAll();

    @Query("SELECT * FROM favoritos WHERE id = :id")
    Favorito findById(long id);

    @Insert
    void insert(Favorito favorito);

    @Update
    void update(Favorito favorito);

    @Delete
    void delete(Favorito favorito);
}
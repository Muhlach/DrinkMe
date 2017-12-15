package com.drinkme.sdm.myapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.drinkme.sdm.myapplication.entity.Bebida;
import com.drinkme.sdm.myapplication.entity.Categoria;

import java.util.List;

/**
 * Created by javie on 18/11/2017.
 */

@Dao
public interface CategoriaDAO {

    @Query("SELECT * FROM categoria")
    List<Categoria> getAll();

    @Insert
    void insertAll(Categoria... categoria);

    @Update
    void update(Categoria categoria);

    @Delete
    void delete(Categoria categoria);

    @Insert
    void insertCollection(List<Categoria> cats);
}
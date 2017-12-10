package com.drinkme.sdm.myapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.drinkme.sdm.myapplication.entity.Bebida;
import com.drinkme.sdm.myapplication.entity.Usuario;

import java.util.List;

/**
 * Created by javie on 18/11/2017.
 */

@Dao
public interface BebidaDAO {

    @Query("SELECT * FROM bebida")
    List<Bebida> getAll();

    @Query("SELECT * FROM bebida WHERE nombre LIKE :nombre LIMIT 1")
    Bebida findByNombre(String nombre);

    @Insert
    void insertAll(Bebida... bebida);

    @Update
    void update(Bebida bebida);

    @Delete
    void delete(Bebida bebida);
}

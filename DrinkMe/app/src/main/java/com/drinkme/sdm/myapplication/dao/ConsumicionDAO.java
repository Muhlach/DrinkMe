package com.drinkme.sdm.myapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.drinkme.sdm.myapplication.entity.Bebida;
import com.drinkme.sdm.myapplication.entity.Consumicion;

import java.util.List;

/**
 * Created by javie on 18/11/2017.
 */

@Dao
public interface ConsumicionDAO {

    @Query("SELECT * FROM consumicion")
    List<Consumicion> getAll();

    @Query("SELECT * FROM consumicion WHERE idUsuario LIKE :idUsuario")
    List<Consumicion> findByUsuario(String idUsuario);

    @Query("SELECT * FROM consumicion WHERE idBebida LIKE :idBebida")
    List<Consumicion> findByBebida(String idBebida);

    @Insert
    void insertAll(Consumicion... consumicion);

    @Update
    void update(Consumicion consumicion);

    @Delete
    void delete(Consumicion consumicion);

    @Insert
    void insertCollection(List<Consumicion> consumicion);
}

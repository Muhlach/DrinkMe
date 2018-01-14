package com.drinkme.sdm.myapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.drinkme.sdm.myapplication.entity.LogrosSuperados;

import java.util.List;

/**
 * Created by ssant on 29/12/2017.
 */

@Dao
public interface LogrosDAO {

    @Query("SELECT * FROM logros")
    List<LogrosSuperados> getAll();

    @Query("SELECT * FROM logros WHERE idUsuario LIKE :idUsuario")
    List<LogrosSuperados> getByUserId(int idUsuario);

    @Insert
    void insertAll(LogrosSuperados logro);
}

package com.drinkme.sdm.myapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.drinkme.sdm.myapplication.entity.Usuario;

/**
 * Created by javie on 18/11/2017.
 */

@Dao
public interface UsuarioDAO {

    @Query("SELECT * FROM usuarios")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuarios WHERE nombre LIKE :nombre LIMIT 1")
    Usuario findByNombre(String nombre);

    @Insert
    void insertAll(Usuario... usuarios);

    @Update
    void update(Usuario usuario);

    @Delete
    void delete(Usuario usuario);
}
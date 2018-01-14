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

    /*
    @Query("SELECT * FROM usuarios")
    List<Usuario> getAll();
*/

    /**
     * En shared preferences se guarda el nombre real (DNI) ya que este nunca cambia
     * @param nombre
     * @return
     */
    @Query("SELECT * FROM usuarios WHERE nombre LIKE :nombre LIMIT 1")
    Usuario findByNombreReal(String nombre);

    /*
    @Query("SELECT * FROM usuarios WHERE userID LIKE :nombre LIMIT 1")
    Usuario findByNombre(String nombre);*/

    @Query("SELECT * FROM usuarios WHERE userID LIKE :nombre and contrasena LIKE :contrasena LIMIT 1")
    Usuario findByNombreAndContraseña(String nombre, String contrasena);

    @Query("UPDATE usuarios SET puntuacion = puntuacion + :puntos WHERE id LIKE :id")
    void actualizaPuntosUsuario(int id, int puntos);

    @Query("UPDATE usuarios SET email = :correo, altura = :altura, peso = :peso, contrasena = :password, userID = :userID  WHERE nombre LIKE :nombre")
    void updateUser(String nombre, String correo, int altura, int peso, String password, String userID);

    @Insert
    void insertAll(Usuario... usuarios);

/*
    @Update
    void update(Usuario usuario);
    @Delete
    void delete(Usuario usuario);
    */
}
package com.drinkme.sdm.myapplication.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by javie on 10/12/2017.
 */
@Entity(tableName = "categoria",foreignKeys = @ForeignKey(entity = Bebida.class,
        parentColumns = "id",
        childColumns = "idCategoria"))
public class Categoria {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "imagen")
    private String imagen;
    @ColumnInfo(name = "puntuacion")
    private String puntuacion;

    public Categoria(String descripcion, String imagen, String puntuacion) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

}

package com.drinkme.sdm.myapplication.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by javie on 10/12/2017.
 */
@Entity(tableName = "categoria")
public class Categoria {
    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "imagen")
    private String imagen;

    public Categoria(){}

    public Categoria(String descripcion, String imagen) {
        this.descripcion = descripcion;
        this.imagen = imagen;
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


}

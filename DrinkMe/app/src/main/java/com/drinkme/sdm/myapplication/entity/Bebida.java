package com.drinkme.sdm.myapplication.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by javie on 10/12/2017.
 */
@Entity(tableName = "bebida",foreignKeys = @ForeignKey(entity = Categoria.class,
        parentColumns = "id",
        childColumns = "idCategoria"))
public class Bebida {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "graduacion")
    private String graduacion;
    @ColumnInfo(name = "alcohol")
    private String alcohol;
    @ColumnInfo(name = "kcal")
    private int kcal;
    @ColumnInfo(name = "idCategoria")
    private int idCategoria;

    public Bebida(String nombre, String graduacion, String alcohol, int kcal, int idCategoria) {
        this.nombre = nombre;
        this.graduacion = graduacion;
        this.alcohol = alcohol;
        this.kcal = kcal;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(String graduacion) {
        this.graduacion = graduacion;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

}

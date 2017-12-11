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
    @ColumnInfo(name = "volumenTotal")
    private int volumenTotal;
    @ColumnInfo(name = "volumenAlcohol")
    private int volumenAlcohol;
    @ColumnInfo(name = "alcohol")
    private double alcohol;
    @ColumnInfo(name = "kcal")
    private int kcal;
    @ColumnInfo(name = "azucar")
    private int azucar;
    @ColumnInfo(name="puntos")
    private int puntos;
    @ColumnInfo(name = "idCategoria")
    private int idCategoria;

    public Bebida(String nombre, int volumenTotal, int volumenAlcohol, double alcohol, int kcal, int azucar, int puntos, int idCategoria) {
        this.nombre = nombre;
        this.volumenTotal = volumenTotal;
        this.volumenAlcohol = volumenAlcohol;
        this.alcohol = alcohol;
        this.kcal = kcal;
        this.azucar = azucar;
        this.puntos = puntos;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVolumenTotal() {
        return volumenTotal;
    }

    public int getVolumenAlcohol() {
        return volumenAlcohol;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public int getKcal() {
        return kcal;
    }

    public int getAzucar() {
        return azucar;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getIdCategoria() {
        return idCategoria;
    }
}

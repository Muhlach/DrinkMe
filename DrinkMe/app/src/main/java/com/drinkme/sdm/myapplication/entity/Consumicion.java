package com.drinkme.sdm.myapplication.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by javie on 24/12/2017.
 */
@Entity(tableName = "consumicion",foreignKeys = {
        @ForeignKey(entity = Usuario.class,
                parentColumns = "id",
                childColumns = "idUsuario"),
        @ForeignKey(entity = Bebida.class,
                parentColumns = "id",
                childColumns = "idBebida")
})
public class Consumicion {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "idUsuario")
    private int idUsuario;
    @ColumnInfo(name = "idBebida")
    private int idBebida;
    @ColumnInfo(name = "precio")
    private double  precio;
    @ColumnInfo(name = "fecha")
    private int fecha;

    public Consumicion(int idUsuario, int idBebida, double precio, int fecha) {
        this.idUsuario = idUsuario;
        this.idBebida = idBebida;
        this.precio = precio;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }


}

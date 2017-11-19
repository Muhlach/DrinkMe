package com.drinkme.sdm.myapplication;

import android.graphics.drawable.Drawable;

/**
 * Created by ssant on 01/11/2017.
 */

public class Logro {
    String nombre, descripcion;
    Drawable imagen;

    public Logro (String nombre, String descripcion, Drawable imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Drawable getImagen() {
        return imagen;
    }
}

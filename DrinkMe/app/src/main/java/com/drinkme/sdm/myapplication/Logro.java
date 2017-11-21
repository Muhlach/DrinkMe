package com.drinkme.sdm.myapplication;

import android.graphics.drawable.Drawable;

/**
 * Created by ssant on 01/11/2017.
 */

public class Logro {
    String nombre, descripcion;
    boolean superado;
    Drawable imagen, imagenSuperado;

    public Logro (String nombre, String descripcion, Drawable imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.superado = false;
        imagenSuperado = null;
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

    public boolean isSuperado() {
        return superado;
    }

    public void setSuperado(boolean superado) {
        this.superado = superado;
    }

    public Drawable getImagenSuperado() {
        return imagenSuperado;
    }

    public void setImagenSuperado(Drawable imagenSuperado) {
        this.imagenSuperado = imagenSuperado;
    }
}

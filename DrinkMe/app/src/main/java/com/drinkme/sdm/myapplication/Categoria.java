package com.drinkme.sdm.myapplication;

import android.graphics.drawable.Drawable;

/**
 * Created by ssant on 01/11/2017.
 */

public class Categoria {

    String categoria;
    Drawable imagen;

    public Categoria(String categoria, Drawable imagen) {
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable img) {
        this.imagen = img;
    }
}

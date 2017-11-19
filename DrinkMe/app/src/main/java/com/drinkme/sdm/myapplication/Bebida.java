package com.drinkme.sdm.myapplication;

/**
 * Created by ssant on 09/11/2017.
 */

public class Bebida {
    String nombre;
    int kcal;
    double alcohol, precio;

    public Bebida (String nombre, int kcal, double alcohol) {
        this.nombre = nombre;
        this.kcal = kcal;
        this.alcohol = alcohol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}

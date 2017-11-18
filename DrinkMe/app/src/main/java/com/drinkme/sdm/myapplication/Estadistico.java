package com.drinkme.sdm.myapplication;

/**
 * Created by ssant on 01/11/2017.
 */

public class Estadistico {

    String nombre;
    double valor;

    public Estadistico (String nombre, double valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }
}

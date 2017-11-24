package com.drinkme.sdm.myapplication.logic;

/**
 * Created by ssant on 01/11/2017.
 */

public class Estadistico {

    private int estID;
    private String nombre;
    private double valor;

    public Estadistico (int estID, String nombre, double valor) {
        this.estID = estID;
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

    public int getEstID() {
        return estID;
    }

    public void setEstID(int estID) {
        this.estID = estID;
    }
}

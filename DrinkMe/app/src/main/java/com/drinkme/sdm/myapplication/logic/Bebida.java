package com.drinkme.sdm.myapplication.logic;

/**
 * Created by ssant on 09/11/2017.
 */

public class Bebida {
    private String bebName;
    private int kcal, azucar;
    private double alcohol, precio;
    private int volumenTotal, volumenAlcohol; //el volumen se medirá en mL


    public Bebida(){}

    public Bebida(String bebName, int kcal, int azucar, double alcohol, int volumenTotal, int volumenAlcohol) {
        this.bebName = bebName;
        this.kcal = kcal;
        this.azucar = azucar;
        this.alcohol = alcohol;
        this.volumenTotal = volumenTotal;
        this.volumenAlcohol = volumenAlcohol;
    }

    public String getBebName() {
        return bebName;
    }

    public void setBebName(String bebName) {
        this.bebName = bebName;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getAzucar() {
        return azucar;
    }

    public void setAzucar(int azucar) {
        this.azucar = azucar;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getVolumenTotal() {
        return volumenTotal;
    }

    public void setVolumenTotal(int volumenTotal) {
        this.volumenTotal = volumenTotal;
    }

    public int getVolumenAlcohol() {
        return volumenAlcohol;
    }

    public void setVolumenAlcohol(int volumenAlcohol) {
        this.volumenAlcohol = volumenAlcohol;
    }

    @Override
    public String toString() {
        return bebName + "(" + volumenTotal + " mL)";
    }
}

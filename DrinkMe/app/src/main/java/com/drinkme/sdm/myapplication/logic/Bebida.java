package com.drinkme.sdm.myapplication.logic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ssant on 09/11/2017.
 */

public class Bebida implements Parcelable{
    private String bebName;
    private int kcal, azucar;
    private double alcohol, precio;
    private int volumenTotal, volumenAlcohol; //el volumen se medirá en mL
    private int puntosBebida;


    public Bebida(){}

    public Bebida(String bebName, int kcal, int azucar, double alcohol, int volumenTotal, int volumenAlcohol, int puntosBebida) {
        this.bebName = bebName;
        this.kcal = kcal;
        this.azucar = azucar;
        this.alcohol = alcohol;
        this.volumenTotal = volumenTotal;
        this.volumenAlcohol = volumenAlcohol;
        this.puntosBebida = puntosBebida;

    }

    protected Bebida(Parcel in) {
        bebName = in.readString();
        kcal = in.readInt();
        azucar = in.readInt();
        alcohol = in.readDouble();
        precio = in.readDouble();
        volumenTotal = in.readInt();
        volumenAlcohol = in.readInt();
        puntosBebida = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bebName);
        dest.writeInt(kcal);
        dest.writeInt(azucar);
        dest.writeDouble(alcohol);
        dest.writeDouble(precio);
        dest.writeInt(volumenTotal);
        dest.writeInt(volumenAlcohol);
        dest.writeInt(puntosBebida);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bebida> CREATOR = new Creator<Bebida>() {
        @Override
        public Bebida createFromParcel(Parcel in) {
            return new Bebida(in);
        }

        @Override
        public Bebida[] newArray(int size) {
            return new Bebida[size];
        }
    };

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

    public int getPuntosBebida() {
        return puntosBebida;
    }

    public void setPuntosBebida(int puntosBebida) {
        this.puntosBebida = puntosBebida;
    }


    @Override
    public String toString() {
        return bebName + " (" + volumenTotal + "ml)";
    }
}

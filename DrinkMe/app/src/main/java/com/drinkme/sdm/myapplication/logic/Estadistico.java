package com.drinkme.sdm.myapplication.logic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ssant on 01/11/2017.
 */

public class Estadistico implements Parcelable {

    private int estID;
    private String nombre;
    private double valor;

    public Estadistico (int estID, String nombre, double valor) {
        this.estID = estID;
        this.nombre = nombre;
        this.valor = valor;
    }

    protected Estadistico(Parcel in) {
        estID = in.readInt();
        nombre = in.readString();
        valor = in.readDouble();
    }

    public static final Creator<Estadistico> CREATOR = new Creator<Estadistico>() {
        @Override
        public Estadistico createFromParcel(Parcel in) {
            return new Estadistico(in);
        }

        @Override
        public Estadistico[] newArray(int size) {
            return new Estadistico[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(estID);
        parcel.writeString(nombre);
        parcel.writeDouble(valor);
    }
}

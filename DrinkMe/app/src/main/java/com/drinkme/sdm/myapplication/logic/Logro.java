package com.drinkme.sdm.myapplication.logic;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ssant on 01/11/2017.
 */

public class Logro implements Parcelable{
    private int logroID, puntos;
    private String logroName, logroDescripcion;
    private boolean superado;
    private Drawable logroImg, logroSuperadoImg;

    public Logro (int logroID, String logroName, String logroDescripcion, int puntos) {
        this.logroID = logroID;
        this.logroName = logroName;
        this.logroDescripcion = logroDescripcion;
        this.puntos = puntos;
        this.superado = false;
    }

    protected Logro(Parcel in) {
        logroID = in.readInt();
        logroName = in.readString();
        logroDescripcion = in.readString();
        superado = in.readByte() != 0;
    }

    public static final Creator<Logro> CREATOR = new Creator<Logro>() {
        @Override
        public Logro createFromParcel(Parcel in) {
            return new Logro(in);
        }

        @Override
        public Logro[] newArray(int size) {
            return new Logro[size];
        }
    };

    public int getLogroID() {
        return logroID;
    }

    public void setLogroID(int logroID) {
        this.logroID = logroID;
    }

    public String getLogroName() {
        return logroName;
    }

    public void setLogroName(String logroName) {
        this.logroName = logroName;
    }

    public String getLogroDescripcion() {
        return logroDescripcion;
    }

    public void setLogroDescripcion(String logroDescripcion) {
        this.logroDescripcion = logroDescripcion;
    }

    public boolean isSuperado() {
        return superado;
    }

    public void setSuperado(boolean superado) {
        this.superado = superado;
    }

    public Drawable getLogroImg() {
        return logroImg;
    }

    public void setLogroImg(Drawable logroImg) {
        this.logroImg = logroImg;
    }

    public Drawable getLogroSuperadoImg() {
        return logroSuperadoImg;
    }

    public void setLogroSuperadoImg(Drawable logroSuperadoImg) {
        this.logroSuperadoImg = logroSuperadoImg;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(logroID);
        parcel.writeString(logroName);
        parcel.writeString(logroDescripcion);
        parcel.writeByte((byte) (superado ? 1 : 0));
    }
}

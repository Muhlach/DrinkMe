package com.drinkme.sdm.myapplication.logic;

import android.graphics.drawable.Drawable;

/**
 * Created by ssant on 01/11/2017.
 */

public class Logro {
    private int logroID;
    private String logroName, logroDescripcion;
    private boolean superado;
    private Drawable logroImg, logroSuperadoImg;

    public Logro (int logroID, String logroName, String logroDescripcion) {
        this.logroID = logroID;
        this.logroName = logroName;
        this.logroDescripcion = logroDescripcion;
        this.superado = false;
    }

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
}

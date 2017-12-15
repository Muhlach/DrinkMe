package com.drinkme.sdm.myapplication.logic;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ssant on 01/11/2017.
 */

public class CategoriaBin implements Parcelable{

    private int id;
    private String catName;
    private Drawable catImg;
    private ArrayList<BebidaBin> bebidas;

    public CategoriaBin() {

    }

    public CategoriaBin(int id, String catName, Drawable catImg, ArrayList<BebidaBin> bebidas) {
        this.id = id;
        this.catName = catName;
        this.catImg = catImg;
        this.bebidas = bebidas;
    }

    protected CategoriaBin(Parcel in) {
        catName = in.readString();
    }

    public static final Creator<CategoriaBin> CREATOR = new Creator<CategoriaBin>() {
        @Override
        public CategoriaBin createFromParcel(Parcel in) {
            return new CategoriaBin(in);
        }

        @Override
        public CategoriaBin[] newArray(int size) {
            return new CategoriaBin[size];
        }
    };

    public String getCatName() {
        return catName;
    }
    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Drawable getCatImg() {
        return catImg;
    }

    public void setCatImg(Drawable img) {
        this.catImg = img;
    }

    public ArrayList<BebidaBin> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<BebidaBin> bebidas) {
        this.bebidas = bebidas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo que añade una nueva bebida a la lista de bebidas
     * @param bebida que se va a añadir
     * @return true si se añade correctamente, false de lo contrario
     */
    public boolean añadirBebida(BebidaBin bebida) {
        if(bebida==null || bebidas==null || existeBebida(bebida))
            return false;
        else{
            bebidas.add(bebida);
            return true;
        }
    }


    /**
     * Comprueba si existe una bebida en la lista de bebidas
     * @param bebida que se comprueba
     * @return true si existe, falso de lo contrario
     */
    private boolean existeBebida(BebidaBin bebida) {
        for(BebidaBin b : bebidas) {
            if(b.getBebName().toLowerCase().equals(bebida.getBebName().toLowerCase()))
                return true;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(catName);
        parcel.writeArray(bebidas.toArray());
    }
}

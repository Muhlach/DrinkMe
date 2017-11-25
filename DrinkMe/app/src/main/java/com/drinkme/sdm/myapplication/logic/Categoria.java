package com.drinkme.sdm.myapplication.logic;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.res.TypedArrayUtils;

import java.util.ArrayList;

/**
 * Created by ssant on 01/11/2017.
 */

public class Categoria implements Parcelable{

    private String catName;
    private Drawable catImg;
    private ArrayList<Bebida> bebidas;

    public Categoria() {

    }

    public Categoria(String catName, Drawable catImg, ArrayList<Bebida> bebidas) {
        this.catName = catName;
        this.catImg = catImg;
        this.bebidas = bebidas;
    }

    protected Categoria(Parcel in) {
        catName = in.readString();
    }

    public static final Creator<Categoria> CREATOR = new Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
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

    public ArrayList<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Bebida> bebidas) {
        this.bebidas = bebidas;
    }

    /**
     * Metodo que añade una nueva bebida a la lista de bebidas
     * @param bebida que se va a añadir
     * @return true si se añade correctamente, false de lo contrario
     */
    public boolean añadirBebida(Bebida bebida) {
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
    private boolean existeBebida(Bebida bebida) {
        for(Bebida b : bebidas) {
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

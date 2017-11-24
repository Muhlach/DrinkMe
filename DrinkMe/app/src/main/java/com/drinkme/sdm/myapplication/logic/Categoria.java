package com.drinkme.sdm.myapplication.logic;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.TypedArrayUtils;

import java.util.ArrayList;

/**
 * Created by ssant on 01/11/2017.
 */

public class Categoria {

    private String catName;
    private Drawable catImg;
    private ArrayList<Bebida> bebidas;
    private int puntosCategoria;

    public Categoria() {

    }

    public Categoria(String catName, Drawable catImg, ArrayList<Bebida> bebidas, int puntosCategoria) {
        this.catName = catName;
        this.catImg = catImg;
        this.bebidas = bebidas;
        this.puntosCategoria = puntosCategoria;
    }

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
}

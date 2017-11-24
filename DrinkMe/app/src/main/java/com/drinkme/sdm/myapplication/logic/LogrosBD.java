package com.drinkme.sdm.myapplication.logic;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ssant on 24/11/2017.
 */

public class LogrosBD {
    private ArrayList<Logro> todosLogros, logrosSuperados;

    public LogrosBD() {}

    public LogrosBD(ArrayList<Logro> todosLogros) {
        this.todosLogros = todosLogros;
        this.logrosSuperados = null;
    }

    public LogrosBD(ArrayList<Logro> todosLogros, ArrayList<Logro> logrosSuperados) {
        this.todosLogros = todosLogros;
        this.logrosSuperados = logrosSuperados;
    }

    public ArrayList<Logro> getTodosLogros() {
        return todosLogros;
    }

    public void setTodosLogros(ArrayList<Logro> todosLogros) {
        this.todosLogros = todosLogros;
    }

    public ArrayList<Logro> getLogrosSuperados() {
        return logrosSuperados;
    }

    public void setLogrosSuperados(ArrayList<Logro> logrosSuperados) {
        this.logrosSuperados = logrosSuperados;
    }

    /**
     * Añade nuevo logro a la una lista de logros
     * @param logro para añadir
     * @param lista en la que se quiere añadir
     * @return true si se ha añadido, false en caso contrario
     */
    public boolean añadirLogro(ArrayList<Logro> lista, Logro logro) {
        if(todosLogros==null || logro==null || existeLogro(lista, logro))
            return false;

        todosLogros.add(logro);
        return true;
    }


    /**
     * Comprueba si existe un logro en una lista de logros
     * @param logro que se comprueba
     * @param lista en la que se comprueba
     * @return true si el logro existe , false en caso contrario
     */
    private boolean existeLogro(ArrayList<Logro> lista, Logro logro) {
        for(Logro l : lista) {
            if(l.getLogroID() == logro.getLogroID())
                return true;
        }
        return false;
    }

    /**
     * Método que comprueba si se ha cumplido algún logro
     * @param puntos experiencia actuales del usuario
     * @return lista de logros cumplidos
     */
    public ArrayList<Logro> comprobarLogros(int puntos) {
        //TODO: Aquí va la implementación de la comprobación de logros
        return null;
    }


}

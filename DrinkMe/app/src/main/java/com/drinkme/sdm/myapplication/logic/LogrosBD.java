package com.drinkme.sdm.myapplication.logic;

import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ssant on 24/11/2017.
 */

public class LogrosBD {
    private ArrayList<Logro> todosLogros, logrosSuperados;

    public LogrosBD(int[] ids, String[] nombres, String[] descripciones, int[] puntos) {
        this.todosLogros = creaTodosLogros(ids, nombres, descripciones, puntos);
        this.logrosSuperados = new ArrayList<Logro>();
    }

    public LogrosBD(ArrayList<Logro> logrosSuperados, int[] ids, String[] nombres,
                    String[] descripciones, int[] puntos) {
        this.todosLogros = creaTodosLogros(ids, nombres, descripciones, puntos);
        this.logrosSuperados = logrosSuperados;
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
     * Metodo que crea un ArrayList con todos los logros definidos por el administrador
     * @return ArrayList con todos los logros de la aplicacion
     */
    private ArrayList<Logro> creaTodosLogros(int[] ids, String[] nombres, String[] descripciones, int[] puntos) {
        ArrayList<Logro> result = new  ArrayList<Logro>();
        Logro l = null;
        for(int i=0; i<ids.length; i++) {
            l = new Logro(ids[i], nombres[i], descripciones[i], puntos[i]);
            result.add(l);
        }
        return result;
    }


    /**
     * Añade nuevo logro a la una lista de logros
     * @param logro para añadir
     * @param lista en la que se quiere añadir
     * @return true si se ha añadido, false en caso contrario
     */
    public boolean añadirLogro(ArrayList<Logro> lista, Logro logro) {
        if(lista==null || logro==null || existeLogro(lista, logro))
            return false;

        lista.add(logro);
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


    public ArrayList<Logro> comprobarLogros(int numeroCervezas, int numeroVinos, int numeroChupitos,
                                            int numeroCopas, int consumicionesHoy,
                                            Date horaConsumicion) {

        //TODO: Aquí va la implementación de la comprobación de logros
        ArrayList<Logro> logrosSuperados = new ArrayList<Logro>();

        /**Comprobamos en primer lugar los logros de cada categoria**/

        /**Comprobamos si se han cumplido los logros de alcanzar cierto nivel en cada categoria**/

        /**Comprobamos si se han cumplido los logros de cierto numero de consumiciones el mismo dia**/

        /**Comprobamos si se han cumplido los logros de la hora pi**/

        /**Comprobamos si se han cumplido los logros de las franjas horarias**/


        return logrosSuperados;
    }




}

package com.drinkme.sdm.myapplication.logic;

import android.content.Context;
import android.util.Log;

import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.utils.FechaUtils;

import java.util.ArrayList;

/**
 * Created by ssant on 24/11/2017.
 */

public class LogrosBD {

    private final static int CERVEZA_ID = 1;
    private final static int VINO_ID = 2;
    private final static int COPA_ID = 3;
    private final static int CHUPITO_ID = 4;
    private final static int FECHA_MIN = 0;
    private final static int FECHA_MAX = 99999999;

    private ArrayList<Logro> todosLogros, logrosSuperados;

    public LogrosBD(int[] ids, String[] nombres, String[] descripciones, int[] puntos) {
        this.todosLogros = creaTodosLogros(ids, nombres, descripciones, puntos);
        this.logrosSuperados = new ArrayList<Logro>();
    }

    public LogrosBD(ArrayList<Logro> logrosSuperados, int[] ids, String[] nombres, String[] descripciones, int[] puntos) {
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

    public Logro getLogroById(int id) {
        for(Logro l : todosLogros ) {
            if(l.getLogroID() == id)
                return l;
        }
        return null;
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

    /**
     *
     * @return
     */
    public ArrayList<Logro> comprobarLogros(Context context) {
        //TODO: Aquí va la implementación de la comprobación de logros
        ArrayList<Logro> logrosSuperados = new ArrayList<Logro>();
        MyDatabase bd = MyDatabase.getDatabase(context);
        int cervezasActuales = bd.consumicionDAO().getAllPorCategoria(CERVEZA_ID, FECHA_MIN, FECHA_MAX).size();
        int vinosActuales = bd.consumicionDAO().getAllPorCategoria(VINO_ID, FECHA_MIN, FECHA_MAX).size();
        int copasActuales = bd.consumicionDAO().getAllPorCategoria(COPA_ID, FECHA_MIN, FECHA_MAX).size();
        int chupuitosActuales = bd.consumicionDAO().getAllPorCategoria(CHUPITO_ID, FECHA_MIN, FECHA_MAX).size();
        int hoy = FechaUtils.getToday();
        int consumicionesHoy = bd.consumicionDAO().getAllPorFecha(hoy, hoy).size();
        int hora = FechaUtils.getHora();

        /**Comprobamos en primer lugar los logros de cada categoria **/
        if(cervezasActuales==20)
            logrosSuperados.add(getLogroById(1));
        else if(cervezasActuales==100)
            logrosSuperados.add(getLogroById(2));
        else if(cervezasActuales==500)
            logrosSuperados.add(getLogroById(3));
        else if(cervezasActuales==1000)
            logrosSuperados.add(getLogroById(4));

        if(vinosActuales==20)
            logrosSuperados.add(getLogroById(5));
        else if(vinosActuales==100)
            logrosSuperados.add(getLogroById(6));
        else if(vinosActuales==500)
            logrosSuperados.add(getLogroById(7));
        else if(vinosActuales==1000)
            logrosSuperados.add(getLogroById(8));

        if(copasActuales==10)
            logrosSuperados.add(getLogroById(9));
        else if(copasActuales==50)
            logrosSuperados.add(getLogroById(10));
        else if(copasActuales==200)
            logrosSuperados.add(getLogroById(11));
        else if(copasActuales==500)
            logrosSuperados.add(getLogroById(12));

        if(chupuitosActuales==20)
            logrosSuperados.add(getLogroById(13));
        else if(chupuitosActuales==100)
            logrosSuperados.add(getLogroById(14));
        else if(chupuitosActuales==500)
            logrosSuperados.add(getLogroById(15));
        else if(chupuitosActuales==1000)
            logrosSuperados.add(getLogroById(16));

        /**Comprobamos si se han cumplido los logros de alcanzar cierto nivel en cada categoria**/

        /**Comprobamos si se han cumplido los logros de cierto numero de consumiciones el mismo dia**/
        if(consumicionesHoy == 10)
            logrosSuperados.add(getLogroById(19));
        else if(consumicionesHoy == 15)
            logrosSuperados.add(getLogroById(20));
        else if(consumicionesHoy == 20)
            logrosSuperados.add(getLogroById(21));

        /**Comprobamos si se han cumplido los logros de la hora pi**/
        if(hora==314){
            //Comprobar que categoria ha sido la bebida y desbloquar el logro correspondiente
        }

        /**Comprobamos si se han cumplido los logros de las franjas horarias**/
        if(hora>=500 && hora<530) {
            //Comprobar que categoria ha sido la bebida y desbloquar el logro correspondiente
        }
        else if (hora>=530 && hora<630) {
            //Comprobar que categoria ha sido la bebida y desbloquar el logro correspondiente
        }
        else if (hora>=630 && hora<730) {
            //Comprobar que categoria ha sido la bebida y desbloquar el logro correspondiente
        }
        else if (hora>=730 && hora<830) {
            //Comprobar que categoria ha sido la bebida y desbloquar el logro correspondiente
        }


        return logrosSuperados;
    }

    private ArrayList<Logro> creaTodosLogros(int[] ids, String[] nombres, String[] descripciones, int[] puntos) {
        ArrayList<Logro> result = new  ArrayList<Logro>();
        Logro l = null;
        for(int i=0; i<ids.length; i++) {
            l = new Logro(ids[i], nombres[i], descripciones[i], puntos[i]);
            result.add(l);
        }
        return result;
    }


}

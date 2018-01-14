package com.drinkme.sdm.myapplication.logic;

import android.content.Context;

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

    public void superarLogros(ArrayList<Logro> logros) {
        for(Logro logro : logros ){
            if(existeLogro(todosLogros, logro) && !logro.isSuperado() &&!logrosSuperados.contains(logro)) {
                logro.setSuperado(true);
                logrosSuperados.add(logro);
            }
        }
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
    public ArrayList<Logro> comprobarLogros(Context context, int categoriaId,
                                            ArrayList<Logro> logrosYaSuperados, String nombreusuario) {
        ArrayList<Logro> logrosSuperados = new ArrayList<Logro>();
        MyDatabase bd = MyDatabase.getDatabase(context);
        int userId = bd.usuarioDAO().findByNombre(nombreusuario).getId();
        int consumicionesActualesPorCategoria = bd.consumicionDAO().getAllPorCategoria(categoriaId, FECHA_MIN, FECHA_MAX, userId).size();
        int hoy = FechaUtils.getToday();
        int consumicionesHoy = bd.consumicionDAO().getAllPorFecha(hoy, hoy, userId).size();
        int hora = FechaUtils.getHora();

        /**Comprobamos en primer lugar los logros de cada categoria **/
        //TODO: Modificar los valores de las consumiciones cuando terminemos de debugear
        switch (categoriaId) {
            case CERVEZA_ID:
                if(consumicionesActualesPorCategoria==20)
                    logrosSuperados.add(getLogroById(0));
                else if(consumicionesActualesPorCategoria==100) {
                    logrosSuperados.add(getLogroById(1));
                }
                else if(consumicionesActualesPorCategoria==500)
                    logrosSuperados.add(getLogroById(2));
                else if(consumicionesActualesPorCategoria==100)
                    logrosSuperados.add(getLogroById(3));
                break;

            case VINO_ID:
                if(consumicionesActualesPorCategoria==20)
                    logrosSuperados.add(getLogroById(4));
                else if(consumicionesActualesPorCategoria==100) {
                    logrosSuperados.add(getLogroById(5));
                }
                else if(consumicionesActualesPorCategoria==500)
                    logrosSuperados.add(getLogroById(6));
                else if(consumicionesActualesPorCategoria==1000)
                    logrosSuperados.add(getLogroById(7));
                break;

            case COPA_ID:
                if(consumicionesActualesPorCategoria==10)
                    logrosSuperados.add(getLogroById(8));
                else if(consumicionesActualesPorCategoria==50) {
                    logrosSuperados.add(getLogroById(9));
                }
                else if(consumicionesActualesPorCategoria==200)
                    logrosSuperados.add(getLogroById(10));
                else if(consumicionesActualesPorCategoria==500)
                    logrosSuperados.add(getLogroById(11));
                break;

            case CHUPITO_ID:
                if(consumicionesActualesPorCategoria==20)
                    logrosSuperados.add(getLogroById(12));
                else if(consumicionesActualesPorCategoria==100) {
                    logrosSuperados.add(getLogroById(13));
                }
                else if(consumicionesActualesPorCategoria==500)
                    logrosSuperados.add(getLogroById(14));
                else if(consumicionesActualesPorCategoria==1000)
                    logrosSuperados.add(getLogroById(15));
        }

        /**Comprobamos si se han cumplido los logros de cierto numero de consumiciones el mismo dia**/
        if(consumicionesHoy == 10)
            compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(18));
        else if(consumicionesHoy == 15)
            compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(19));
        else if(consumicionesHoy == 20)
            compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(20));

        /**Comprobamos si se han cumplido los logros de la hora pi**/
        if(hora==314){
            switch (categoriaId) {
                case CERVEZA_ID:
                    compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(21));
                    break;
                case VINO_ID:
                    compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(22));
                    break;
                case COPA_ID:
                    compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(23));
                    break;
                case CHUPITO_ID:
                    compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(24));
                    break;
            }
        }

        /**Comprobamos si se han cumplido los logros de las franjas horarias**/
        if(hora>=500 && hora<=830) {
            if(categoriaId==CERVEZA_ID || categoriaId==COPA_ID) {
                if(hora>=500 && hora<=530) {
                    compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(25));
                }
                else if (hora>530 && hora<=630) {
                    compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(26));
                }
                else if (hora>630 && hora<=730) {
                    compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(27));
                }
                else if (hora>730 && hora<=830) {
                    compruebaAnadeLogro(logrosSuperados, logrosYaSuperados, getLogroById(28));
                }
            }
        }


        return logrosSuperados;
    }

    private boolean isArrayCompleto(boolean[] logros) {
        for(int i=0; i<logros.length; i++) {
            if(!logros[i])
                return false;
        }
        return true;
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


    private void compruebaAnadeLogro(ArrayList<Logro> lista, ArrayList<Logro> logrosSuperados, Logro logro) {
        if(!logrosSuperados.contains(logro))
            lista.add(logro);
    }
}

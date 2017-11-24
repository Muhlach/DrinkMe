package com.drinkme.sdm.myapplication.logic;

import com.drinkme.sdm.myapplication.EstadisticasFragment;

import java.util.ArrayList;

/**
 * Created by ssant on 24/11/2017.
 */

public class EstadisticosBD {
    ArrayList<Estadistico> estadisticos;

    public EstadisticosBD() {}

    public EstadisticosBD(ArrayList<Estadistico> estadisticos) {
        this.estadisticos = estadisticos;
    }

    /**
     * Metodo que añade un nuevo estadístico a la lista de estadisticos
     * @param est que se quiere insertar
     * @return true si se ha insertado correctamente, false de lo contrario
     */
    public boolean añadirEstadistico(Estadistico est) {
        if(est==null || estadisticos==null || existeEstadistico(est))
            return false;
        else{
            estadisticos.add(est);
            return true;
        }
    }

    /**
     * Comprueba que si existe un estadístico en una lista de estadisticos
     * @param est que se comprueba si existe
     * @return true si ya existe y falso en caso contrario
     */
    private boolean existeEstadistico(Estadistico est) {
        for(Estadistico e : estadisticos) {
            if(e.getEstID() == est.getEstID())
                return true;
        }
        return false;
    }

    public ArrayList<Estadistico> getEstadisticos() {
        return estadisticos;
    }

    public void setEstadisticos(ArrayList<Estadistico> estadisticos) {
        this.estadisticos = estadisticos;
    }
}

package com.drinkme.sdm.myapplication.logic;

import java.util.ArrayList;

/**
 * Created by ssant on 24/11/2017.
 */

public class NivelBD {

    private static final int AUMENTO_NIVELES = 50;

    private ArrayList<Nivel> niveles;

    public NivelBD() {
        niveles = new ArrayList<Nivel>();
        generarNiveles();
    }

    /**
     * Genera todos los niveles posibles en el juego
     */
    private void generarNiveles() {
        //Añadimos los demás niveles
        int puntosMinimo=0;
        int puntosMaximo=30;
        int aumentoPuntos = 50;

        for (int i=1; i<=Nivel.NIVEL_MAXIMO; i++) {
            if(i==Nivel.NIVEL_MAXIMO) {
                Nivel nivel = new Nivel(i, puntosMinimo, Integer.MAX_VALUE);
                niveles.add(nivel);
            }
            else{
                Nivel nivel = new Nivel(i, puntosMinimo, puntosMaximo);
                niveles.add(nivel);
                puntosMinimo = puntosMaximo+1;
                puntosMaximo += aumentoPuntos;
                aumentoPuntos += AUMENTO_NIVELES;
            }
        }
    }


    public ArrayList<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(ArrayList<Nivel> niveles) {
        this.niveles = niveles;
    }

    /**
     * Devuelve el nivel del usuario en funcion de sus puntos de experiencia
     * @param puntosActuales del usuario
     * @return Nivel en el que se encuentra
     */
    public Nivel getNivelUsuario(int puntosActuales) {
        for(Nivel n : niveles) {
            if(puntosActuales >= n.getPuntosMinimos() && puntosActuales<=n.getPuntosMaximos())
                return n;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Nivel n : niveles) {
            sb.append(n.getNivelID() + " [" + n.getPuntosMinimos() + " - " + n.getPuntosMaximos() + "]\n");
        }
        return sb.toString();
    }
}

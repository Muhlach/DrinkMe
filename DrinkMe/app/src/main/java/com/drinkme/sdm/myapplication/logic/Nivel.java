package com.drinkme.sdm.myapplication.logic;

/**
 * Created by ssant on 24/11/2017.
 */

public class Nivel {
    public static final String[] RANGOS_BEBEDOR = {"Bebedor Principiante", "Bebedor Medio",
            "Bebedor Avanzado", "Bebedor Experto", "Dios Alcohólico"};
    public static final int NIVEL_MAXIMO = 50;
    private int nivelID;
    private String rangoBebedor;
    private int puntosMaximos, puntosMinimos;

    public Nivel(int nivelID, int puntosMinimos, int puntosMaximos) {
        this.nivelID = nivelID;
        this.puntosMaximos = puntosMaximos;
        this.puntosMinimos = puntosMinimos;
        asignaRango(nivelID);
    }

    /**
     * Asigna el nivel de bebedorcorrespondiente a cada nivel
     * @param nivelID el nivel del usuario
     */
    private void asignaRango(int nivelID) {
        if(nivelID>=0 && nivelID<5) {
            rangoBebedor = RANGOS_BEBEDOR[0];
        }
        else if(nivelID>=5 && nivelID<10) {
            rangoBebedor = RANGOS_BEBEDOR[1];
        }
        else if(nivelID>=10 && nivelID<25) {
            rangoBebedor = RANGOS_BEBEDOR[2];
        }
        else if(nivelID>=25 && nivelID<40) {
            rangoBebedor = RANGOS_BEBEDOR[3];
        }
        else
            rangoBebedor = RANGOS_BEBEDOR[4];
    }

    public int getNivelID() {
        return nivelID;
    }

    public void setNivelID(int nivelID) {
        this.nivelID = nivelID;
    }

    public String getRangoBebedor() {
        return rangoBebedor;
    }

    public void setRangoBebedor(String rangoBebedor) {
        this.rangoBebedor = rangoBebedor;
    }

    public int getPuntosMaximos() {
        return puntosMaximos;
    }

    public void setPuntosMaximos(int puntosMaximos) {
        this.puntosMaximos = puntosMaximos;
    }

    public int getPuntosMinimos() {
        return puntosMinimos;
    }

    public void setPuntosMinimos(int puntosMinimos) {
        this.puntosMinimos = puntosMinimos;
    }
}

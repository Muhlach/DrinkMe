package com.drinkme.sdm.myapplication.logic;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ssant on 24/11/2017.
 */

public class Usuario {

    public final static boolean HOMBRE = true;
    public final static boolean MUJER = false;

    private String userID, nombre, apellidos;
    private String correo, contraseña;
    private boolean sexo;
    private Date nacimiento;
    private int altura, peso;
    private int puntosExperiencia;
    private NivelBD nivelBD;
    private Nivel nivel;
    private LogrosBD logros;


    public Usuario() {
        this.userID = userID;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contraseña = contraseña;
        this.sexo = sexo;
        this.nacimiento = nacimiento;
        this.altura = altura;
        this.peso = peso;
        this.puntosExperiencia = puntosExperiencia;
        nivelBD = new NivelBD();
        nivel = nivelBD.getNivelUsuario(puntosExperiencia);
    }

    public int getPuntosExperiencia() {
        return puntosExperiencia;
    }

    public void setPuntosExperiencia(int puntosExperiencia) {
        this.puntosExperiencia = puntosExperiencia;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public LogrosBD getLogros() {
        return logros;
    }

    public void setLogros(LogrosBD logros) {
        this.logros = logros;
    }

    public ArrayList<Logro> getLogrosSuperadosDelUsuario() {
        return logros.getLogrosSuperados();
    }


    /**
     * Comprueba si en con el estado actual se ha cumplido algún logro nuevo
     * @return
     */
    public void actualizarLogros() {
        logros.comprobarLogros(getPuntosExperiencia());
    }

    public void actualizarPuntosExperiencia(int puntosRecibidos) {
        puntosExperiencia += puntosRecibidos;
    }

    public boolean actualizarNivel() {
        Nivel nivelNuevo = nivelBD.getNivelUsuario(puntosExperiencia);
        if (nivelNuevo.getNivelID() != nivel.getNivelID()) {
            nivel = nivelNuevo;
            return true;
        }
        return false;
    }

}

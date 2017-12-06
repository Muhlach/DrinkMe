package com.drinkme.sdm.myapplication.logic;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ssant on 24/11/2017.
 */

public class UsuarioBin implements Parcelable {

    public final static boolean HOMBRE = true;
    public final static boolean MUJER = false;

    private String userID, nombre, apellidos;
    private String correo, contraseña;
    private boolean sexo;
    private int nacimiento;
    private int altura, peso;
    private int puntosExperiencia;
    private NivelBD nivelBD;
    private Nivel nivel;
    private LogrosBD logros;


    public UsuarioBin() {

    }

    protected UsuarioBin(Parcel in) {
        userID = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        correo = in.readString();
        contraseña = in.readString();
        sexo = in.readByte() != 0;
        altura = in.readInt();
        peso = in.readInt();
        puntosExperiencia = in.readInt();
        nacimiento = in.readInt();
    }

    public static final Creator<UsuarioBin> CREATOR = new Creator<UsuarioBin>() {
        @Override
        public UsuarioBin createFromParcel(Parcel in) {
            return new UsuarioBin(in);
        }

        @Override
        public UsuarioBin[] newArray(int size) {
            return new UsuarioBin[size];
        }
    };

    public int getPuntosExperiencia() {
        return puntosExperiencia;
    }

    public void setPuntosExperiencia(int puntosExperiencia) {
        this.puntosExperiencia = puntosExperiencia;
        nivelBD = new NivelBD();
        nivel = nivelBD.getNivelUsuario(puntosExperiencia);
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

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
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
        //TODO:logros.comprobarLogros(getPuntosExperiencia());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userID);
        parcel.writeString(nombre);
        parcel.writeString(apellidos);
        parcel.writeString(correo);
        parcel.writeString(contraseña);
        parcel.writeByte((byte) (sexo ? 1 : 0));
        parcel.writeInt(altura);
        parcel.writeInt(peso);
        parcel.writeInt(puntosExperiencia);
        parcel.writeInt(nacimiento);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userID='" + userID + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", sexo=" + sexo +
                ", nacimiento=" + nacimiento +
                ", altura=" + altura +
                ", peso=" + peso +
                ", puntosExperiencia=" + puntosExperiencia +
                ", nivelBD=" + nivelBD +
                ", nivel=" + nivel +
                ", logros=" + logros +
                '}';
    }
}

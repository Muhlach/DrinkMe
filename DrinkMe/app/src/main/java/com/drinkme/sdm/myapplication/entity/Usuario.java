package com.drinkme.sdm.myapplication.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by javie on 18/11/2017.
 */

@Entity(tableName = "usuarios")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "apellidos")
    private String apellidos;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "contrasena")
    private String contrasena;
    @ColumnInfo(name = "fecha")
    private int fecha;
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(name = "altura")
    private int altura;
    @ColumnInfo(name = "peso")
    private int peso;
    @ColumnInfo(name = "nivel")
    private int nivel;
    @ColumnInfo(name = "puntuacion")
    private int puntuacion;

    public Usuario(String nombre, String apellidos, String email, String contrasena, int fecha, String sexo, int altura, int peso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasena = contrasena;
        this.fecha = fecha;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
    }
    public Usuario(){}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {

        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getFecha() {
        return fecha;
    }

    public String getSexo() {
        return sexo;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPuntuacion() {
        return puntuacion;
    }


    @Override
    public String toString() {
        return "UsuarioBin{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", fecha=" + fecha +
                ", sexo='" + sexo + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                '}';
    }
}

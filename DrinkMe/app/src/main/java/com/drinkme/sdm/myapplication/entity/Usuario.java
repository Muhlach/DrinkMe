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
    @ColumnInfo(name = "userID")
    private String userID;
    @ColumnInfo(name = "apellidos")
    private String apellidos;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "contrasena")
    private String contrasena;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(name = "altura")
    private int altura;
    @ColumnInfo(name = "peso")
    private int peso;
    @ColumnInfo(name = "puntuacion")
    private int puntuacion;

    public Usuario(String nombre, String apellidos, String email, String contrasena,
                   String fecha, String sexo, int altura, int peso, int puntuacion, String userID) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasena = contrasena;
        this.fecha = fecha;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
        this.puntuacion = puntuacion;
        this.userID = userID;
    }

    public Usuario() {
    }

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

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
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

    public String getFecha() {
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


    public int getPuntuacion() {
        return puntuacion;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", userID='" + userID + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", fecha='" + fecha + '\'' +
                ", sexo='" + sexo + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", puntuacion=" + puntuacion +
                '}';
    }
}

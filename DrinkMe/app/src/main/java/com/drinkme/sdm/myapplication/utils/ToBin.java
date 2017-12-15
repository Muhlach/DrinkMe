package com.drinkme.sdm.myapplication.utils;

import com.drinkme.sdm.myapplication.entity.Bebida;
import com.drinkme.sdm.myapplication.entity.Categoria;
import com.drinkme.sdm.myapplication.entity.Usuario;
import com.drinkme.sdm.myapplication.logic.BebidaBin;
import com.drinkme.sdm.myapplication.logic.CategoriaBin;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;

/**
 * Created by ssant on 11/12/2017.
 */

public class ToBin {

    public ToBin(){}

    public UsuarioBin usuarioToBin(Usuario usuario) {
        UsuarioBin result = new UsuarioBin();
        result.setNombre(usuario.getNombre());
        result.setApellidos(usuario.getApellidos());
        result.setCorreo(usuario.getEmail());
        result.setContraseña(usuario.getContrasena());
        result.setAltura(usuario.getAltura());
        result.setPeso(usuario.getPeso());
        result.setPuntosExperiencia(usuario.getPuntuacion());
        result.setNacimiento(usuario.getFecha());
        return result;
    }

    public BebidaBin bebidaToBin(Bebida bebida){
        BebidaBin result = new BebidaBin();
        result.setBebName(bebida.getNombre());
        result.setKcal(bebida.getKcal());
        result.setAzucar(bebida.getAzucar());
        result.setAlcohol(bebida.getAlcohol());
        result.setVolumenAlcohol(bebida.getVolumenAlcohol());
        result.setVolumenTotal(bebida.getVolumenTotal());
        result.setPuntosBebida(bebida.getPuntos());
        return result;
    }

    public CategoriaBin categoriaToBin(Categoria categoria) {
        CategoriaBin result = new CategoriaBin();
        result.setCatName(categoria.getDescripcion());
        return result;
    }

}

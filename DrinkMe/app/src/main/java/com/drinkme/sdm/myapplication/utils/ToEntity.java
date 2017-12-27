package com.drinkme.sdm.myapplication.utils;

import com.drinkme.sdm.myapplication.entity.Usuario;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;

/**
 * Created by alex on 27/12/2017.
 */

public class ToEntity {


    public Usuario convertUser(UsuarioBin usuarioBin){
        Usuario result = new Usuario();
        result.setNombre(usuarioBin.getNombre());
        result.setApellidos( usuarioBin.getApellidos());
        result.setEmail(usuarioBin.getCorreo());

        //TODO
        //  result.setFecha(usuarioBin.getNacimiento());
        // result.setSexo(usuarioBin.getSexo());


        result.setAltura(usuarioBin.getAltura());
        result.setPuntuacion(usuarioBin.getPuntosExperiencia());
        result.setPeso(usuarioBin.getPeso());
        return result;
    }

}

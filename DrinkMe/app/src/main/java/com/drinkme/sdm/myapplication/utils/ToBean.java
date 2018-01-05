package com.drinkme.sdm.myapplication.utils;

import com.drinkme.sdm.myapplication.entity.Bebida;
import com.drinkme.sdm.myapplication.entity.Categoria;
import com.drinkme.sdm.myapplication.entity.Usuario;
import com.drinkme.sdm.myapplication.logic.BebidaBin;
import com.drinkme.sdm.myapplication.logic.CategoriaBin;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssant on 11/12/2017.
 */

public class ToBean {

    public ToBean(){}

    public UsuarioBin usuarioToBean(Usuario usuario) {
        UsuarioBin result = new UsuarioBin();
        result.setNombre(usuario.getNombre());
        result.setApellidos(usuario.getApellidos());
        result.setCorreo(usuario.getEmail());
        result.setContraseña(usuario.getContrasena());
        result.setAltura(usuario.getAltura());
        result.setPeso(usuario.getPeso());
        result.setPuntosExperiencia(usuario.getPuntuacion());
        result.setUserID(usuario.getUserID());
        result.setNacimiento(usuario.getFecha());
        return result;
    }

    public BebidaBin bebidaToBean(Bebida bebida){
        BebidaBin result = new BebidaBin();
        result.setBebName(bebida.getNombre());
        result.setKcal(bebida.getKcal());
        result.setAzucar(bebida.getAzucar());
        result.setAlcohol(bebida.getAlcohol());
        result.setVolumenAlcohol(bebida.getVolumenAlcohol());
        result.setVolumenTotal(bebida.getVolumenTotal());
        result.setPuntosBebida(bebida.getPuntos());
        result.setIdCategoria(bebida.getIdCategoria());
        return result;
    }

    public CategoriaBin categoriaToBean(Categoria categoria) {
        CategoriaBin result = new CategoriaBin();
        result.setId(categoria.getId());
        result.setCatName(categoria.getDescripcion());
        return result;
    }

    public ArrayList<CategoriaBin> obtenerCategoriasBean(List<Categoria> cats, List<Bebida> bebs) {
        ArrayList<CategoriaBin> categoriasBean = new ArrayList<CategoriaBin>();
        for(Categoria c : cats)
            categoriasBean.add(categoriaToBean(c));

        ArrayList<BebidaBin> bebidasBean = new ArrayList<BebidaBin>();
        for(Bebida b : bebs)
            bebidasBean.add(bebidaToBean(b));

        for(int i=1; i<=categoriasBean.size(); i++){
            ArrayList<BebidaBin> bebsPorCategoria = new ArrayList<BebidaBin>();

            for(BebidaBin b : bebidasBean){
                if(b.getIdCategoria() == i)
                    bebsPorCategoria.add(b);
            } //Fin del bucle que carga bebidas

            for(CategoriaBin c : categoriasBean) {
                if(c.getId() == i)
                    c.setBebidas(bebsPorCategoria);
            } //Fin del bucle que carga las bebidas a la categoria
        } //Fin del main bucle

        return categoriasBean;
    }
}

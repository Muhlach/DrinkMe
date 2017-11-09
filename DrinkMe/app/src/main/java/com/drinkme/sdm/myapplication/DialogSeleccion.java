package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ssant on 09/11/2017.
 */

public class DialogSeleccion extends DialogFragment{

    Spinner spinnerBebida;
    View view;

    public DialogSeleccion(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        view = inflater.inflate(R.layout.dialog_seleccion_layout, container);

        spinnerBebida = (Spinner) view.findViewById(R.id.cmbxDialogBebidas);
        cargaBebidas();
        return view;
    }

    private void cargaBebidas() {
        Bebida b = new Bebida("Caña Rubia", 0, 0);
        Bebida b1 = new Bebida("Caña Tostada", 0, 0);
        Bebida b2 = new Bebida("Caña de Trigo", 0, 0);
        Bebida b3 = new Bebida("Caña con Limón", 0, 0);
        Bebida b4 = new Bebida("Botellin Pilsen", 0, 0);
        Bebida b5 = new Bebida("Botellin Premium", 0, 0);
        Bebida b7 = new Bebida("Botellin de Trigo", 0, 0);
        Bebida b8 = new Bebida("Desperados", 0, 0);

        String[] bebidas = {"Caña Rubia","Caña Tostada","Caña de Trigo","Caña con Limón",
                "Botellin Pilsen","Botellin Premium","Botellin de Trigo","Desperados"};

        ArrayAdapter<String> adapterBebida = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, bebidas);
        adapterBebida.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBebida.setAdapter(adapterBebida);


    }
}

package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.logic.Bebida;

/**
 * Created by ssant on 09/11/2017.
 */

public class DialogSeleccion extends DialogFragment{

    Spinner spinnerBebida;
    TextView txPrecio;
    View view;
    Button guardar, cancelar;
    Bebida bebidaSeleccionada;
    double precio;

    public DialogSeleccion(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        view = inflater.inflate(R.layout.dialog_seleccion_layout, container);

        spinnerBebida = (Spinner) view.findViewById(R.id.cmbxDialogBebidas);
        txPrecio = (TextView) view.findViewById(R.id.txDialogPrecio);

        guardar = (Button) view.findViewById(R.id.btnDialogGuardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "Has guardado", Toast.LENGTH_SHORT).show();
                Object b = spinnerBebida.getSelectedItem();
                precio = Double.valueOf(txPrecio.getText().toString());
                String r = b.toString() + "  " + precio;
                Toast.makeText(getActivity(), r, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        cancelar = (Button) view.findViewById(R.id.btnDialogCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Has cancelado", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });
        cargaBebidas();
        return view;
    }


    private void cargaBebidas() {
        Bebida b = new Bebida("Caña Rubia", 0, 0, 0, 0, 0);
        Bebida b1 = new Bebida("Caña Tostada", 0, 0, 0, 0, 0);
        Bebida b2 = new Bebida("Caña de Trigo", 0, 0, 0, 0, 0);
        Bebida b3 = new Bebida("Caña con Limón", 0, 0, 0, 0, 0);
        Bebida b4 = new Bebida("Botellin Pilsen", 0, 0, 0, 0, 0);
        Bebida b5 = new Bebida("Botellin Premium", 0, 0, 0, 0, 0);
        Bebida b7 = new Bebida("Botellin de Trigo", 0, 0, 0, 0, 0);
        Bebida b8 = new Bebida("Desperados", 0, 0, 0, 0, 0);

        String[] bebidas = {"Caña Rubia","Caña Tostada","Caña de Trigo","Caña con Limón",
                "Botellin Pilsen","Botellin Premium","Botellin de Trigo","Desperados"};

        ArrayAdapter<String> adapterBebida = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, bebidas);
        adapterBebida.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBebida.setAdapter(adapterBebida);


    }
}

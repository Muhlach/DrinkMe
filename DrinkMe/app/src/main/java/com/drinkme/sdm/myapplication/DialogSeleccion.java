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

import java.util.ArrayList;

/**
 * Created by ssant on 09/11/2017.
 */

public class DialogSeleccion extends DialogFragment{

    Spinner spinnerBebida;
    TextView txPrecio;
    View view;
    Button guardar, cancelar;
    Bebida bebidaSeleccionada;
    ArrayList<Bebida> bebidasArrayList;
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

                //Cogemos la bebida seleccionada y el precio introducido
                Bebida b = (Bebida) spinnerBebida.getSelectedItem();
                String precioStr = txPrecio.getText().toString();

                //Comprobamos que se haya seleccionado una bebida y que se haya introducido precio
                if(precioStr.isEmpty())
                    Toast.makeText(getActivity(), "Debes introducir un precio", Toast.LENGTH_SHORT).show();
                else {
                    precio = Double.valueOf(precioStr);
                    //TODO: Aquí se debe implementar el registro de la consumición
                    String r = b.toString() + "  " + precio;
                    Toast.makeText(getActivity(), r, Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });

        cancelar = (Button) view.findViewById(R.id.btnDialogCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Consumición cancelada", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });


        Bundle bundleRecibido = getArguments();
        bebidasArrayList = bundleRecibido.getParcelableArrayList(MainActivity.BEBIDAS_KEY);
        cargaBebidas();
        return view;
    }


    private void cargaBebidas() {
        Bebida[] bebidas = creaArray();
        ArrayAdapter<Bebida> adapterBebida = new ArrayAdapter<Bebida>(getActivity(),
                android.R.layout.simple_spinner_item, bebidas);
        adapterBebida.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBebida.setAdapter(adapterBebida);
    }

    private Bebida[] creaArray() {
        Bebida[] result = new Bebida[bebidasArrayList.size()];
        int counter = 0;
        for(Bebida beb : bebidasArrayList) {
            result[counter] = beb;
            counter++;
        }

        return result;
    }
}

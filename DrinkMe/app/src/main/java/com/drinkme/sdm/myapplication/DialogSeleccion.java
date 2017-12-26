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

import com.drinkme.sdm.myapplication.dao.BebidaDAO;
import com.drinkme.sdm.myapplication.dao.ConsumicionDAO;
import com.drinkme.sdm.myapplication.dao.UsuarioDAO;
import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.entity.Consumicion;
import com.drinkme.sdm.myapplication.logic.BebidaBin;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;
import com.drinkme.sdm.myapplication.utils.FechaUtils;

import java.util.ArrayList;

/**
 * Created by ssant on 09/11/2017.
 */

public class DialogSeleccion extends DialogFragment{

    Spinner spinnerBebida;
    TextView txPrecio;
    View view;
    Button guardar, cancelar;
    BebidaBin bebidaSeleccionada;
    ArrayList<BebidaBin> bebidasArrayList;
    UsuarioBin user;
    double precio;

    public DialogSeleccion(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        view = inflater.inflate(R.layout.dialog_seleccion_layout, container);

        spinnerBebida = (Spinner) view.findViewById(R.id.cmbxDialogBebidas);
        txPrecio = (TextView) view.findViewById(R.id.txDialogPrecio);

        guardar = (Button) view.findViewById(R.id.btnDialogGuardar);

        Bundle bundleRecibido = this.getArguments();
        bebidasArrayList = bundleRecibido.getParcelableArrayList(MainActivity.BEBIDAS_KEY);
        user = bundleRecibido.getParcelable(MainActivity.USER_KEY);
        cargaBebidas();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Cogemos la bebida seleccionada y el precio introducido
                BebidaBin b = (BebidaBin) spinnerBebida.getSelectedItem();
                String precioStr = txPrecio.getText().toString();

                //Comprobamos que se haya seleccionado una bebida y que se haya introducido precio
                if(precioStr.isEmpty())
                    Toast.makeText(getActivity(), "Debes introducir un precio", Toast.LENGTH_SHORT).show();
                else {
                    precio = Double.valueOf(precioStr);
                    //TODO: Aquí se debe implementar el registro de la consumición
                    int registros = guardarConsumicion(b, precio);
                    String r = b.toString() + "  " + precio + ". Hay: " + registros + " bebidas registradas.";
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

        return view;
    }

    /**
     * Metodo que registra en la base de datos una consumición
     * @param b bebida que selecciona el usuario
     * @param precio precio de la consumicion
     */
    private int guardarConsumicion(BebidaBin b, double precio) {
        MyDatabase db = MyDatabase.getDatabase(getActivity());
        UsuarioDAO usuarioDAO = db.usuarioDAO();
        BebidaDAO bebidaDAO = db.bebidaDAO();
        ConsumicionDAO consumicionDAO = db.consumicionDAO();
        int bebidaId = bebidaDAO.findByNombre(b.getBebName()).getId();
        int usuarioId = usuarioDAO.findByNombre(user.getNombre()).getId();
        int fecha = FechaUtils.getToday();

        Consumicion consumicion = new Consumicion(usuarioId, bebidaId, precio, fecha);
        consumicionDAO.insertAll(consumicion);
        return consumicionDAO.cuentaRegistros(usuarioId);
    }


    private void cargaBebidas() {
        BebidaBin[] bebidas = creaArray();
        ArrayAdapter<BebidaBin> adapterBebida = new ArrayAdapter<BebidaBin>(getActivity(),
                android.R.layout.simple_spinner_item, bebidas);
        adapterBebida.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBebida.setAdapter(adapterBebida);
    }

    private BebidaBin[] creaArray() {
        BebidaBin[] result = new BebidaBin[bebidasArrayList.size()];
        int counter = 0;
        for(BebidaBin beb : bebidasArrayList) {
            result[counter] = beb;
            counter++;
        }

        return result;
    }
}

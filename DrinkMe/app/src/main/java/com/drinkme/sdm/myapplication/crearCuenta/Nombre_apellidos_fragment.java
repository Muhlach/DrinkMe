package com.drinkme.sdm.myapplication.crearCuenta;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.CrearCuentaActivity;
import com.drinkme.sdm.myapplication.R;

/**
 * Created by alex on 26/12/2017.
 */

public class Nombre_apellidos_fragment extends Fragment {

    View view;
    Button aceptar;
    CrearCuentaActivity activity;

    Spinner spinnerSex;
    private EditText nombre_et, apellidos_et;
    String nombre, apellidos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nombre_apellidos, container,false);
        activity = (CrearCuentaActivity) getActivity();
        aceptar = (Button) view.findViewById(R.id.buttonNext);

        nombre_et = (EditText) view.findViewById(R.id.editTextNombre);
        apellidos_et = (EditText) view.findViewById(R.id.editTextApellidos);
        spinnerSex = (Spinner) view.findViewById(R.id.spinnerSex);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity.getApplicationContext(), R.array.sex_array, R.layout.my_spinner_item);
        adapter.setDropDownViewResource(R.layout.my_list_items);
        spinnerSex.setAdapter(adapter);


        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = nombre_et.getText().toString();
                apellidos = apellidos_et.getText().toString();
                if (check()){
                    activity.setNombre(nombre);
                    activity.setApellidos(apellidos);
                    activity.setSexo(spinnerSex.getSelectedItem().toString());
                    activity.nextFragmet();
                }
            }
        });
        return view;
    }

    private boolean check() {
        if (nombre.isEmpty() || apellidos.isEmpty()) {
            Toast.makeText(activity.getApplicationContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}

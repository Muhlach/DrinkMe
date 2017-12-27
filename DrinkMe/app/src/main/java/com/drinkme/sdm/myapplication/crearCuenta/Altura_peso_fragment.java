package com.drinkme.sdm.myapplication.crearCuenta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.R;

/**
 * Created by alex on 26/12/2017.
 */

public class Altura_peso_fragment extends Fragment {

    private View view;
    private Button aceptar;
    private CrearCuentaActivity crearCuentaActivity;

    private EditText altura_et, peso_et;

    private String altura, peso;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_altura_peso, container, false);
        crearCuentaActivity = (CrearCuentaActivity) getActivity();
        aceptar = (Button) view.findViewById(R.id.buttonNext45);


        altura_et = (EditText) view.findViewById(R.id.editTextlaaltura);
        peso_et = (EditText) view.findViewById(R.id.editTextelpeso);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                altura = altura_et.getText().toString();
                peso = peso_et.getText().toString();

                if (check()) {
                    crearCuentaActivity.setAltura(Integer.parseInt(altura));
                    crearCuentaActivity.setPeso(Integer.parseInt(peso));
                    crearCuentaActivity.createUser();
                    crearCuentaActivity.nextFragmet();
                }
            }
        });
        return view;
    }

    private boolean check() {
        if (altura.isEmpty() || peso.isEmpty()) {
            Toast.makeText(crearCuentaActivity.getApplicationContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            int altura_i = Integer.parseInt(altura);
            int peso_i = Integer.parseInt(peso);
            if (altura_i < 100 || altura_i > 250 || peso_i < 30 || peso_i > 150) {
                Toast.makeText(crearCuentaActivity.getApplicationContext(), "Comprueba los datos", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }
}

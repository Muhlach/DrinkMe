package com.drinkme.sdm.myapplication.crearCuenta;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.CrearCuentaActivity;
import com.drinkme.sdm.myapplication.R;

/**
 * Created by alex on 26/12/2017.
 */

public class Altura_peso_fragment extends DialogFragment {

    View view;
    Button aceptar;
    CrearCuentaActivity activity;

    private EditText altura_et, peso_et;
    String altura, peso;


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_altura_peso, container);
        activity = (CrearCuentaActivity) getActivity();
        aceptar = (Button) view.findViewById(R.id.buttonNext45);


        altura_et = (EditText) view.findViewById(R.id.editTextlaaltura);
        peso_et = (EditText) view.findViewById(R.id.editTextelpeso);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altura = altura_et.getText().toString();
                peso = peso_et.getText().toString();
                if (check()) {
                    activity.setAltura(Integer.parseInt(altura));
                    activity.setPeso(Integer.parseInt(peso));
                    activity.nextFragmet();
                    activity.createUser();
                    dismiss();
                }
            }
        });
        return view;
    }

    private boolean check() {
        if (altura.isEmpty() || peso.isEmpty()) {
            Toast.makeText(activity.getApplicationContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            int altura_i = Integer.parseInt(altura);
            int peso_i = Integer.parseInt(peso);
            if (altura_i < 100 || altura_i > 250 || peso_i < 30 || peso_i > 150) {
                Toast.makeText(activity.getApplicationContext(), "Comprueba los datos", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }
}

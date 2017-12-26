package com.drinkme.sdm.myapplication.crearCuenta;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class Dia_mes_fragment extends DialogFragment {

    View view;
    Button aceptar;
    CrearCuentaActivity activity;

    private Spinner spinnerMes;
    private EditText dia_et, ano_et;
    String dia, ano;

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
        view = inflater.inflate(R.layout.fragment_dia_mes, container);
        activity = (CrearCuentaActivity) getActivity();
        aceptar = (Button) view.findViewById(R.id.buttonNext3);

        spinnerMes = (Spinner) view.findViewById(R.id.spinnerMes11);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(activity.getApplicationContext(), R.array.mes_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMes.setAdapter(adapter5);
        dia_et = (EditText) view.findViewById(R.id.editTexteldia);
        ano_et = (EditText) view.findViewById(R.id.editTextelano);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia = dia_et.getText().toString();
                ano = ano_et.getText().toString();
                if (check()) {
                    activity.setFecha(dia.concat("/").concat(String.valueOf(spinnerMes.getSelectedItemPosition() + 1)).concat("/").concat(ano));
                    activity.nextFragmet();
                    dismiss();
                }
            }
        });
        return view;
    }

    private boolean check() {
        if (dia.isEmpty() || ano.isEmpty()) {
            Toast.makeText(activity.getApplicationContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            int dia_i = Integer.parseInt(dia);
            int ano_i = Integer.parseInt(ano);
            if (dia_i < 0 || dia_i > 31 || ano_i < 1936 || ano_i > 2009) {
                Toast.makeText(activity.getApplicationContext(), "Comprueba los datos", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }

}

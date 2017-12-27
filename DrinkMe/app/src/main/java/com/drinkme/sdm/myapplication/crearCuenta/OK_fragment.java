package com.drinkme.sdm.myapplication.crearCuenta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.drinkme.sdm.myapplication.R;

/**
 * Created by alex on 26/12/2017.
 */



public class OK_fragment extends Fragment {

    private Button aceptar;
    private View view;
    private CrearCuentaActivity crearCuentaActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_ok, container,false);
        aceptar = (Button) view.findViewById(R.id.buttonNext69);
        crearCuentaActivity = (CrearCuentaActivity) getActivity();

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              crearCuentaActivity.finish();
            }
        });
        return view;
    }
}

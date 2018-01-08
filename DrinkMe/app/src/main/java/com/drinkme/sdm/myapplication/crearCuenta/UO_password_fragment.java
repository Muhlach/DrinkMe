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

public class UO_password_fragment extends Fragment {

    private View view;
    private Button aceptar;
    private CrearCuentaActivity crearCuentaActivity;

    private EditText nombreDeUo_et, correo_et, password_et, repassword_et;
    private String usuario, password, repassword, correo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_uo_password, container, false);
        crearCuentaActivity = (CrearCuentaActivity) getActivity();
        aceptar = (Button) view.findViewById(R.id.buttonNext2);

        correo_et = (EditText) view.findViewById(R.id.editTextCorreo);
        password_et = (EditText) view.findViewById(R.id.editTextPassword);
        repassword_et = (EditText) view.findViewById(R.id.editTextRePassword);
        nombreDeUo_et = (EditText) view.findViewById(R.id.editTextNombreUsuario);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = nombreDeUo_et.getText().toString();
                password = password_et.getText().toString();
                repassword = repassword_et.getText().toString();
                correo = correo_et.getText().toString();
                if (check()){
                    crearCuentaActivity.setContrasena(password);
                    crearCuentaActivity.setEmail(correo);
                    crearCuentaActivity.setUsuarioID(usuario);
                    crearCuentaActivity.nextFragmet();
                }
            }
        });
        return view;
    }

    private boolean check() {
        if (usuario.isEmpty() || password.isEmpty() || repassword.isEmpty() || correo.isEmpty()) {
            Toast.makeText(crearCuentaActivity.getApplicationContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (!password.equalsIgnoreCase(repassword)){
                Toast.makeText(crearCuentaActivity.getApplicationContext(), "Comprueba tu password", Toast.LENGTH_SHORT).show();
            return false;
            }
            return true;
        }
    }
}

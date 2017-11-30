package com.drinkme.sdm.myapplication;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alex on 28/11/2017.
 */

public class PerfilActivity_password_fragment extends DialogFragment {

    TextView oldPassword, newPassword;
    Button aceptar;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_perfil_activity_password, container);

        oldPassword = (EditText) view.findViewById(R.id.editTextPasswordActual);
        oldPassword.setText(getArguments().getString("password", null));

        newPassword = (TextView) view.findViewById(R.id.editTextNewPass);

        aceptar = (Button) view.findViewById(R.id.buttonFPAAceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAceptar(view);
            }
        });
        return view;
    }

    void onClickAceptar(View v){

        String password = newPassword.getText().toString();

        PerfilActivity perfilActivity = (PerfilActivity) getActivity();

        if(!password.isEmpty()){
            perfilActivity.updateUserPassword(password);
            Toast.makeText(perfilActivity.getApplicationContext(),"Cambio aplicado correctamente", Toast.LENGTH_SHORT).show();
            dismiss();
        }else{
            Toast.makeText(perfilActivity.getApplicationContext(),"Introduce la nueva password", Toast.LENGTH_SHORT).show();
        }

    }
}

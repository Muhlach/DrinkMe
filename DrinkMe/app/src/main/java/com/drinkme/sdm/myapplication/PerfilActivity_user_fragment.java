package com.drinkme.sdm.myapplication;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alex on 29/11/2017.
 */

public class PerfilActivity_user_fragment extends DialogFragment {

    TextView oldUser, newUser;
    Button aceptar;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_perfil_activity_user, container);

        oldUser = (TextView) view.findViewById(R.id.textViewUsuarioActual);
        oldUser.setText(getArguments().getString("user", null));

        newUser = (TextView) view.findViewById(R.id.editTextNewUO);

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

        String user = newUser.getText().toString();

        PerfilActivity perfilActivity = (PerfilActivity) getActivity();

        if(!user.isEmpty()){
            perfilActivity.updateUserUO(user);
            Toast.makeText(perfilActivity.getApplicationContext(),"Cambio aplicado correctamente", Toast.LENGTH_SHORT).show();
            dismiss();
        }else{
            Toast.makeText(perfilActivity.getApplicationContext(),"Introduce el nuevo usuario", Toast.LENGTH_SHORT).show();
        }

    }
}

package com.drinkme.sdm.myapplication;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ssant on 01/01/2018.
 */

public class DialogSubeNivel extends android.support.v4.app.DialogFragment {

    View view;
    TextView txNivel;
    Button btnOk;

    public DialogSubeNivel() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_nuevo_nivel, container);

        txNivel = view.findViewById(R.id.txNuevoNivel);
        btnOk = view.findViewById(R.id.btnNivelNuevoOK);
        Bundle bundleRecibido = this.getArguments();
        int nivel = bundleRecibido.getInt(MainActivity.NUEVO_NIVEL);
        txNivel.setText(String.valueOf(nivel));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }
}

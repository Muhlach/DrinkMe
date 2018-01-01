package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by ssant on 01/01/2018.
 */

public class DialogoSuperarLogro extends DialogFragment {
    View view;
    TextView titulo, puntos;
    Button btnOk;
    ListView listView;

    public DialogoSuperarLogro() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_logro_superado, container);

        Bundle bundleRecibido = this.getArguments();
        String[] nombres = bundleRecibido.getStringArray("nombrelogrossuperados");
        int recompensa = bundleRecibido.getInt("puntoslogrossuperados");
        String titulostr = "";
        if(nombres.length==1)
            titulostr = "¡Nuevo logro superado!";
        else
            titulostr = "¡Nuevos logros superados!";

        String recompensastr = String.valueOf(recompensa)+" xp";

        titulo = view.findViewById(R.id.txTituloLogroSuperado);
        puntos = view.findViewById(R.id.txRecompensaLogroSuperado);
        btnOk = view.findViewById(R.id.btnLogroSuperadoOK);
        listView = view.findViewById(R.id.listViewLogrosSuperados);

        titulo.setText(titulostr);
        puntos.setText(recompensastr);
        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, nombres));
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

}

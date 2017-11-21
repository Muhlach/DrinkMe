package com.drinkme.sdm.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.drinkme.sdm.myapplication.Adapters.AdapterLogros;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class LogrosFragment extends Fragment {

    View view;
    ArrayList<Logro> logros;
    ListView listViewLogros;
    Button btnMasLogros;

    public LogrosFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_logros, container, false);

        btnMasLogros = (Button) view.findViewById(R.id.btnMasLogros);
        btnMasLogros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent masLogrosIntent = new Intent(getActivity(), MasLogrosActivity.class);
                startActivity(masLogrosIntent);
            }
        });

        cargarLogros();

        return view;
    }

    private void cargarLogros() {
        Logro l= new Logro("Cervecero Principiante", "", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l1= new Logro("Cervecero Avanzado", "", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l2= new Logro("Coctelero Principiante", "", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l3= new Logro("Fin de Semana Cervecero", "", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l4= new Logro("Vamos de Tranquis", "", getResources().getDrawable(R.drawable.ic_logros_24_lista));

        logros = new ArrayList<Logro>();
        logros.add(l);
        logros.add(l1);
        logros.add(l2);
        logros.add(l3);
        logros.add(l4);

        listViewLogros = view.findViewById(R.id.listViewMisLogros);
        AdapterLogros adapter = new AdapterLogros(getActivity(), logros);
        listViewLogros.setAdapter(adapter);
    }

}

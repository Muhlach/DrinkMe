package com.drinkme.sdm.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.drinkme.sdm.myapplication.Adapters.AdapterEstadisticas;

import java.util.ArrayList;


public class EstadisticasFragment extends Fragment {

    View view;
    ArrayList<Estadistico> estadisticos;
    ListView listViewEstadisticos;

    public EstadisticasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        cargaEstadisticos();

        return view;
    }

    private void cargaEstadisticos() {
        Estadistico e = new Estadistico("Total L bebidos: ", 100);
        Estadistico e1 = new Estadistico("Total L alcohol: ", 21.8);
        Estadistico e2 = new Estadistico("Total kcal: ", 15000);
        Estadistico e3 = new Estadistico("Total € gastados: ", 115.87);
        estadisticos = new ArrayList<Estadistico>();
        estadisticos.add(e);
        estadisticos.add(e1);
        estadisticos.add(e2);
        estadisticos.add(e3);

        listViewEstadisticos = view.findViewById(R.id.listViewEstadisticas);
        AdapterEstadisticas adapter = new AdapterEstadisticas(getActivity(), estadisticos);
        listViewEstadisticos.setAdapter(adapter);
    }
}

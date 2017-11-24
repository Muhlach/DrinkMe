package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.drinkme.sdm.myapplication.Adapters.AdapterEstadisticas;
import com.drinkme.sdm.myapplication.logic.Estadistico;

import java.util.ArrayList;


public class EstadisticasFragment extends Fragment {

    View view;
    ArrayList<Estadistico> estadisticos;
    ListView listViewEstadisticos;

    Spinner spinnerCategoria, spinnerBebida, spinnerTiempo;

    public EstadisticasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        spinnerCategoria = (Spinner) view.findViewById(R.id.cmbxCategoria);
        spinnerBebida = (Spinner) view.findViewById(R.id.cmbxBebida);
        spinnerTiempo = (Spinner) view.findViewById(R.id.cmbxTiempo);

        cargaEstadisticos();
        cargaSpinners();

        return view;
    }

    /**
     * Método que carga los valores de los estadísticos en el list view
     */
    private void cargaEstadisticos() {
        Estadistico e = new Estadistico(1, "Total L bebidos: ", 100);
        Estadistico e1 = new Estadistico(2, "Total L alcohol: ", 21.8);
        Estadistico e2 = new Estadistico(3, "Total kcal: ", 15000);
        Estadistico e3 = new Estadistico(4, "Total € gastados: ", 115.87);
        estadisticos = new ArrayList<Estadistico>();
        estadisticos.add(e);
        estadisticos.add(e1);
        estadisticos.add(e2);
        estadisticos.add(e3);

        listViewEstadisticos = view.findViewById(R.id.listViewEstadisticas);
        AdapterEstadisticas adapter = new AdapterEstadisticas(getActivity(), estadisticos);
        listViewEstadisticos.setAdapter(adapter);
    }

    private void cargaSpinners() {
        String[] categorias = {"Todas", "Vino", "Cerveza", "Cóctel", "Chupito"};
        String[] bebidas = {"Todas", "Botellin Pilsner", "Caña Rubia"};
        String[] tiempos = {"Última semana", "Último mes", "Último año"};

        ArrayAdapter<String> adCategorias = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, categorias);
        adCategorias.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerCategoria.setAdapter(adCategorias);

        ArrayAdapter<String> adBebidas = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, bebidas);
        adBebidas.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBebida.setAdapter(adBebidas);

        ArrayAdapter<String> adTiempo = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, tiempos);
        adTiempo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTiempo.setAdapter(adTiempo);

    }
}

package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.drinkme.sdm.myapplication.Adapters.AdapterEstadisticas;
import com.drinkme.sdm.myapplication.logic.Bebida;
import com.drinkme.sdm.myapplication.logic.Categoria;
import com.drinkme.sdm.myapplication.logic.Estadistico;

import java.util.ArrayList;


public class EstadisticasFragment extends Fragment {


    private static final String TODAS = "Todas";
    View view;
    ArrayList<Estadistico> estadisticos;
    ArrayList<Categoria> categoriasArrayList;
    ListView listViewEstadisticos;

    Spinner spinnerCategoria, spinnerBebida, spinnerTiempo;

    public EstadisticasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundleRecibido = getArguments();
        estadisticos = bundleRecibido.getParcelableArrayList(MainActivity.ESTADISTICOS_KEY);
        categoriasArrayList = bundleRecibido.getParcelableArrayList(MainActivity.KEY_CATEGORIAS);

        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        spinnerCategoria = (Spinner) view.findViewById(R.id.cmbxCategoria);
        spinnerBebida = (Spinner) view.findViewById(R.id.cmbxBebida);
        spinnerTiempo = (Spinner) view.findViewById(R.id.cmbxTiempo);

        cargaSpinners();
        cargaEstadisticos();

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String categoriaSeleccionada = (String) adapterView.getItemAtPosition(i);
                if(categoriaSeleccionada.equals(TODAS)){
                    ArrayList<Bebida> todasBebidas = new ArrayList<Bebida>();
                    for(Categoria c:categoriasArrayList) {
                        todasBebidas.addAll(c.getBebidas());
                    }
                    cargaArrayBebidas(todasBebidas);
                }
                else{
                    Categoria categoria = findCategoriaPorNombre(categoriaSeleccionada);
                    cargaArrayBebidas(categoria.getBebidas());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    /**
     * Método que carga los valores de los estadísticos en el list view
     */
    private void cargaEstadisticos() {
        listViewEstadisticos = view.findViewById(R.id.listViewEstadisticas);
        AdapterEstadisticas adapter = new AdapterEstadisticas(getActivity(), estadisticos);
        listViewEstadisticos.setAdapter(adapter);
    }

    private void cargaSpinners() {
        /**Definición de los arrays**/
        String[] arrayCategorias = cargaArrayCategorias();
        String[] bebidas = {"Todas"};
        String[] tiempos = {"Última semana", "Último mes", "Último año"};

        /**Asignacion de adapter de las categorias**/
        ArrayAdapter<String> adCategorias = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arrayCategorias);
        adCategorias.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerCategoria.setAdapter(adCategorias);

        /**Asignacion de adapter de las bebidas**/
        ArrayAdapter<String> adBebidas = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, bebidas);
        adBebidas.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBebida.setAdapter(adBebidas);

        /**Asignacion de adapter del tiempo**/
        ArrayAdapter<String> adTiempo = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, tiempos);
        adTiempo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTiempo.setAdapter(adTiempo);

    }

    private String[] cargaArrayCategorias() {
        String[] result = new String[categoriasArrayList.size()+1];
        result[0] = TODAS;
        for(int i=1; i<result.length; i++){
            result[i] = categoriasArrayList.get(i-1).getCatName();
        }
        return result;
    }
    private void cargaArrayBebidas(ArrayList<Bebida> listaBebidas) {
        String[] result = new String[listaBebidas.size()+1];
        result[0] = TODAS;
        for(int i=1; i<result.length; i++){
            result[i] = listaBebidas.get(i-1).getBebName();
        }

        ArrayAdapter<String> adBebidas = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, result);
        adBebidas.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBebida.setAdapter(adBebidas);

    }

    private Categoria findCategoriaPorNombre(String categoriaSeleccionada) {
        Categoria result = null;

        for(Categoria c : categoriasArrayList) {
            if(c.getCatName().equals(categoriaSeleccionada))
                result = c;
        }

        return result;
    }


}

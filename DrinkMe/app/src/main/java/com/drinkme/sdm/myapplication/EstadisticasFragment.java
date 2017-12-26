package com.drinkme.sdm.myapplication;

import android.hardware.ConsumerIrManager;
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
import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.entity.Consumicion;
import com.drinkme.sdm.myapplication.logic.BebidaBin;
import com.drinkme.sdm.myapplication.logic.CategoriaBin;
import com.drinkme.sdm.myapplication.logic.Estadistico;
import com.drinkme.sdm.myapplication.utils.FechaUtils;

import java.util.ArrayList;


public class EstadisticasFragment extends Fragment {


    private static final String TODAS = "Todas";
    private static final int TODAS_ID = -1;
    public static final int SEMANA = 0;
    public static final int MES = 1;
    public static final int ANIO = 2;

    View view;
    ArrayList<Estadistico> estadisticos;
    ArrayList<CategoriaBin> categoriasArrayList;
    ListView listViewEstadisticos;

    Spinner spinnerCategoria, spinnerBebida, spinnerTiempo;

    MyDatabase db;

    public EstadisticasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundleRecibido = getArguments();
        estadisticos = bundleRecibido.getParcelableArrayList(MainActivity.ESTADISTICOS_KEY);
        categoriasArrayList = bundleRecibido.getParcelableArrayList(MainActivity.KEY_CATEGORIAS);

        db = MyDatabase.getDatabase(getActivity());

        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        spinnerCategoria = (Spinner) view.findViewById(R.id.cmbxCategoria);
        spinnerBebida = (Spinner) view.findViewById(R.id.cmbxBebida);
        spinnerTiempo = (Spinner) view.findViewById(R.id.cmbxTiempo);

        cargaSpinners();
        cargaEstadisticos(TODAS_ID, TODAS_ID, SEMANA);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String categoriaSeleccionada = (String) adapterView.getItemAtPosition(i);
                if(categoriaSeleccionada.equals(TODAS)){
                    ArrayList<BebidaBin> todasBebidas = new ArrayList<BebidaBin>();
                    for(CategoriaBin c:categoriasArrayList) {
                        todasBebidas.addAll(c.getBebidas());
                    }
                    cargaArrayBebidas(todasBebidas);
                }
                else{
                    CategoriaBin categoria = findCategoriaPorNombre(categoriaSeleccionada);
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
    private void cargaEstadisticos(int categoriaId, int bebidaId, int fechaId) {
        listViewEstadisticos = view.findViewById(R.id.listViewEstadisticas);
        ArrayList<Consumicion> consumicionesFiltradas = obtenConsumicionesFiltradasBD(categoriaId, bebidaId, fechaId);
        calculaEstadisticos(consumicionesFiltradas, estadisticos);
        AdapterEstadisticas adapter = new AdapterEstadisticas(getActivity(), estadisticos);
        listViewEstadisticos.setAdapter(adapter);
    }

    private ArrayList<Consumicion> obtenConsumicionesFiltradasBD(int categoriaId, int bebidaId, int fechaId) {
        ArrayList<Consumicion> result = new ArrayList<Consumicion>();
        int[] fechas = FechaUtils.getRango(fechaId);

        if(categoriaId==TODAS_ID && bebidaId==TODAS_ID) {
            result = db.consumicionDAO().getAllPorFecha(fechas[0], fechas[1]);
        }

        else if (bebidaId==TODAS_ID){
            //TODO
//            int categoria = 0;
//            result = db.consumicionDAO().getAllPorCategoria(categoria, fechas[0], fechas[1]);
        }

        else {
            //TODO
//            int bebida = 0;
//            result = db.consumicionDAO().getAllPorBebida(bebida, fechas[0], fechas[1]);
        }

        return result;
    }

    private void calculaEstadisticos(ArrayList<Consumicion> consumicionesFiltradas, ArrayList<Estadistico> estadisticos) {
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
    private void cargaArrayBebidas(ArrayList<BebidaBin> listaBebidas) {
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

    private CategoriaBin findCategoriaPorNombre(String categoriaSeleccionada) {
        CategoriaBin result = null;

        for(CategoriaBin c : categoriasArrayList) {
            if(c.getCatName().equals(categoriaSeleccionada))
                result = c;
        }

        return result;
    }


}

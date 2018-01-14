package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.drinkme.sdm.myapplication.Adapters.AdapterEstadisticas;
import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.entity.Bebida;
import com.drinkme.sdm.myapplication.entity.Consumicion;
import com.drinkme.sdm.myapplication.logic.BebidaBin;
import com.drinkme.sdm.myapplication.logic.CategoriaBin;
import com.drinkme.sdm.myapplication.logic.Estadistico;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;
import com.drinkme.sdm.myapplication.utils.FechaUtils;

import java.util.ArrayList;
import java.util.List;


public class EstadisticasFragment extends Fragment {


    private static final String TODAS = "Todas";
    private static final int TODAS_ID = 0;
    public static final int SEMANA = 0;
    public static final int MES = 1;
    public static final int ANIO = 2;

    View view;
    ArrayList<Estadistico> estadisticos;
    ArrayList<CategoriaBin> categoriasArrayList;
    ListView listViewEstadisticos;

    Spinner spinnerCategoria, spinnerBebida, spinnerTiempo;
    Button btnAplicarFiltro;
    UsuarioBin user;

    MyDatabase db;

    public EstadisticasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundleRecibido = getArguments();
        estadisticos = bundleRecibido.getParcelableArrayList(MainActivity.ESTADISTICOS_KEY);
        categoriasArrayList = bundleRecibido.getParcelableArrayList(MainActivity.KEY_CATEGORIAS);
        user = bundleRecibido.getParcelable(MainActivity.USER_KEY);

        db = MyDatabase.getDatabase(getActivity());

        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        spinnerCategoria = (Spinner) view.findViewById(R.id.cmbxCategoria);
        spinnerBebida = (Spinner) view.findViewById(R.id.cmbxBebida);
        spinnerTiempo = (Spinner) view.findViewById(R.id.cmbxTiempo);
        btnAplicarFiltro = (Button) view.findViewById(R.id.btnAplicar);

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

        btnAplicarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int categoria = spinnerCategoria.getSelectedItemPosition();
                int bebida = spinnerBebida.getSelectedItemPosition();
                int fecha = spinnerTiempo.getSelectedItemPosition();

                cargaEstadisticos(categoria, bebida, fecha);
            }
        });

        return view;
    }

    /**
     * Método que carga los valores de los estadísticos en el list view
     */
    private void cargaEstadisticos(int categoriaId, int bebidaId, int fechaId) {
        listViewEstadisticos = view.findViewById(R.id.listViewEstadisticas);
        List<Consumicion> consumicionesFiltradas = obtenConsumicionesFiltradasBD(categoriaId, bebidaId, fechaId);
        calculaEstadisticos(consumicionesFiltradas, estadisticos);
        AdapterEstadisticas adapter = new AdapterEstadisticas(getActivity(), estadisticos);
        listViewEstadisticos.setAdapter(adapter);
    }

    private List<Consumicion> obtenConsumicionesFiltradasBD(int categoriaId, int bebidaId, int fechaId) {
        List<Consumicion> result = new ArrayList<Consumicion>();
        int[] fechas = FechaUtils.getRango(fechaId);
        int userId = db.usuarioDAO().findByNombreReal(user.getNombre()).getId();

        if(categoriaId==TODAS_ID && bebidaId==TODAS_ID) {
            result = db.consumicionDAO().getAllPorFecha(fechas[0], fechas[1], userId);
        }

        else if (bebidaId==TODAS_ID){
            result = db.consumicionDAO().getAllPorCategoria(categoriaId, fechas[0], fechas[1], userId);
        }

        else {
            String nombreBebida = (String) spinnerBebida.getItemAtPosition(bebidaId);
            int bebida = db.bebidaDAO().findByNombre(nombreBebida).getId();
            result = db.consumicionDAO().getAllPorBebida(bebida, fechas[0], fechas[1]);
        }

        return result;
    }

    private void calculaEstadisticos(List<Consumicion> consumicionesFiltradas, ArrayList<Estadistico> estadisticos) {
        //Calculo nuero de consumiciones
        double result = consumicionesFiltradas.size();
        estadisticos.get(0).setValor(result);

        //Calculo de L total
        result = calculaLitrosTotales(consumicionesFiltradas);
        estadisticos.get(1).setValor(result/1000);

        //Calculo de L de alcohol
        result = calculaLitrosAlcohol(consumicionesFiltradas);
        estadisticos.get(2).setValor(result/1000);

        //Calcuo de kcal ingeridas
        int kal = calculaKcal(consumicionesFiltradas);
        estadisticos.get(3).setValor(result);

        //Calculo de dinero gastado
        result = calculaDinero(consumicionesFiltradas);
        estadisticos.get(4).setValor(result);
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

    /**
     * Calcula el total de los litros en una lista de consumiciones
     * @param consumiciones
     * @return
     */
    private double calculaLitrosAlcohol (List<Consumicion> consumiciones) {
        int result = 0;
        for (Consumicion c : consumiciones) {
            Bebida b = db.bebidaDAO().findById(c.getIdBebida());
            result = result + b.getVolumenAlcohol();
        }
        return result;
    }

    /**
     * Calcula los litros de alcohol en una lista de consumiciones
     * @param consumiciones
     * @return
     */
    private double calculaLitrosTotales (List<Consumicion> consumiciones) {
        int result = 0;
        for (Consumicion c : consumiciones) {
            Bebida b = db.bebidaDAO().findById(c.getIdBebida());
            result = result + b.getVolumenTotal();
        }
        return result;
    }

    /**
     * Calcula las Kcal en una lista de consumiciones
     * @param consumiciones
     * @return
     */
    private int calculaKcal(List<Consumicion> consumiciones) {
        int result = 0;
        for (Consumicion c : consumiciones) {
            Bebida b = db.bebidaDAO().findById(c.getIdBebida());
            result = result + b.getKcal();
        }
        return result;
    }

    /**
     * Calcula el dinero gastado en una lista de consumiciones
     * @param consumiciones
     * @return
     */
    private double calculaDinero(List<Consumicion> consumiciones) {
        double result = 0;
        for(Consumicion c : consumiciones) {
            result = result + c.getPrecio();
        }
        return result;
    }



}

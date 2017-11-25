package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.drinkme.sdm.myapplication.Adapters.AdapterCategorias;
import com.drinkme.sdm.myapplication.logic.Categoria;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;


public class BeberFragment extends Fragment{


    ListView listViewCategorias;
    ArrayList<Categoria> categorias;
    View view;

    public BeberFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_beber, container, false);
        Bundle bundle = getArguments();
        categorias = bundle.getParcelableArrayList(MainActivity.KEY_CATEGORIAS);
        cargarCategorías();
        return view;
    }

    private void cargarCategorías() {
        listViewCategorias = (ListView) view.findViewById(R.id.listViewCategorias);
        AdapterCategorias adapter = new AdapterCategorias(getActivity(), categorias);
        listViewCategorias.setAdapter(adapter);
        listViewCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Categoria categoriaSeleccionada = (Categoria) adapterView.getItemAtPosition(i);
                Bundle bundleBebidas = new Bundle();
                bundleBebidas.putParcelableArrayList(MainActivity.BEBIDAS_KEY, categoriaSeleccionada.getBebidas());
                DialogSeleccion dialog = new DialogSeleccion();
                dialog.setArguments(bundleBebidas);
                dialog.show(getFragmentManager(), "personal");
            }
        });
    }


}

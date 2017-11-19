package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.drinkme.sdm.myapplication.Adapters.AdapterCategorias;

import java.util.ArrayList;
import java.util.List;


public class BeberFragment extends Fragment{

    ListView listViewCategorias;
    ArrayList<Categoria> categorias;
    View view;

    public BeberFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_beber, container, false);
        cargarCategorías();
        return view;
    }

    private void cargarCategorías() {
        categorias = new ArrayList<Categoria>();
        Categoria vino = new Categoria("Vino", getResources().getDrawable(R.drawable.ic_vino_64));
        Categoria cerveza = new Categoria("Cerveza", getResources().getDrawable(R.drawable.ic_cerveza_64));
        Categoria copa = new Categoria("Copa", getResources().getDrawable(R.drawable.ic_copa_64));
        Categoria chupito = new Categoria("Chupito", getResources().getDrawable(R.drawable.ic_chupito_64));
        categorias.add(vino);
        categorias.add(cerveza);
        categorias.add(copa);
        categorias.add(chupito);


        listViewCategorias = (ListView) view.findViewById(R.id.listViewCategorias);
        AdapterCategorias adapter = new AdapterCategorias(getActivity(), categorias);
        listViewCategorias.setAdapter(adapter);
        listViewCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogSeleccion dialog = new DialogSeleccion();
                dialog.show(getFragmentManager(), "personal");
            }
        });
    }


}

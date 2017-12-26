package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.drinkme.sdm.myapplication.Adapters.AdapterCategorias;
import com.drinkme.sdm.myapplication.logic.CategoriaBin;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;

import java.util.ArrayList;


public class BeberFragment extends Fragment{

    ListView listViewCategorias;
    ArrayList<CategoriaBin> categorias;
    UsuarioBin user;
    View view;

    public BeberFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_beber, container, false);
        Bundle bundle = getArguments();
        categorias = bundle.getParcelableArrayList(MainActivity.KEY_CATEGORIAS);
        user = bundle.getParcelable(MainActivity.USER_KEY);
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
                CategoriaBin categoriaSeleccionada = (CategoriaBin) adapterView.getItemAtPosition(i);
                Bundle bundleBebidas = new Bundle();
                bundleBebidas.putParcelableArrayList(MainActivity.BEBIDAS_KEY, categoriaSeleccionada.getBebidas());
                bundleBebidas.putParcelable(MainActivity.USER_KEY, user);
                DialogSeleccion dialog = new DialogSeleccion();
                dialog.setArguments(bundleBebidas);
                dialog.show(getFragmentManager(), "personal");
            }
        });
    }


}

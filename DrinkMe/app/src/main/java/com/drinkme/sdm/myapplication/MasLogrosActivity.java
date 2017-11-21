package com.drinkme.sdm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.drinkme.sdm.myapplication.Adapters.AdapterLogros;
import com.drinkme.sdm.myapplication.Adapters.AdapterTodosLogros;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MasLogrosActivity extends AppCompatActivity {

    ListView listaLogros;
    TextView txDescripcion;
    TextView txNombre;
    ArrayList<Logro> logros;
    Logro logroSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_logros);

        listaLogros = (ListView) findViewById(R.id.listViewTodosLogros);
        txDescripcion = (TextView) findViewById(R.id.txDescripcionLogro);
        txNombre = (TextView) findViewById(R.id.txNombreDescripcion);

        cargarTodosLogros();
        listaLogros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                logroSeleccionado = (Logro) adapterView.getItemAtPosition(position);
                txNombre.setText(logroSeleccionado.getNombre());
                txDescripcion.setText(logroSeleccionado.getDescripcion());
            }
        });
    }

    private void cargarTodosLogros() {
        Logro l= new Logro("Cervecero Principiante", "Descripcion1", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l1= new Logro("Cervecero Avanzado", "Descripcion2", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l2= new Logro("Coctelero Principiante", "Descripcion3", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l3= new Logro("Fin de Semana Cervecero", "Descripcion4", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l4= new Logro("Vamos de Tranquis", "Descripcion5", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l5= new Logro("Cervecero Principiante", "Descripcion1", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l6= new Logro("Cervecero Avanzado", "Descripcion2", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l7= new Logro("Coctelero Principiante", "Descripcion3", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l8= new Logro("Fin de Semana Cervecero", "Descripcion4", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        Logro l9= new Logro("Vamos de Tranquis", "Descripcion5", getResources().getDrawable(R.drawable.ic_logros_24_lista));
        l2.setSuperado(true);
        l4.setSuperado(true);
        l5.setSuperado(true);
        l7.setSuperado(true);
        l9.setSuperado(true);


        logros = new ArrayList<Logro>();
        logros.add(l);
        logros.add(l1);
        logros.add(l2);
        logros.add(l3);
        logros.add(l4);
        logros.add(l5);
        logros.add(l6);
        logros.add(l7);
        logros.add(l8);
        logros.add(l9);

        for (Logro logro:logros) {
            logro.setImagenSuperado(getResources().getDrawable(R.drawable.ic_logrosuperado_32));
        }

        AdapterTodosLogros adapter = new AdapterTodosLogros(this, logros);
        listaLogros.setAdapter(adapter);
    }
}

package com.drinkme.sdm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.drinkme.sdm.myapplication.Adapters.AdapterTodosLogros;
import com.drinkme.sdm.myapplication.logic.Logro;

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
                txNombre.setText(logroSeleccionado.getLogroName());
                txDescripcion.setText(logroSeleccionado.getLogroDescripcion());
            }
        });
    }

    private void cargarTodosLogros() {
        Logro l= new Logro(1, "Cervecero Principiante", "");
        Logro l1= new Logro(2, "Cervecero Avanzado", "");
        Logro l2= new Logro(3, "Coctelero Principiante", "");
        Logro l3= new Logro(4, "Fin de Semana Cervecero", "");
        Logro l4= new Logro(5, "Vamos de Tranquis", "");
        Logro l5= new Logro(1, "Cervecero Principiante", "");
        Logro l6= new Logro(2, "Cervecero Avanzado", "");
        Logro l7= new Logro(3, "Coctelero Principiante", "");
        Logro l8= new Logro(4, "Fin de Semana Cervecero", "");
        Logro l9= new Logro(5, "Vamos de Tranquis", "");
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
            logro.setLogroSuperadoImg(getResources().getDrawable(R.drawable.ic_logrosuperado_32));
        }

        AdapterTodosLogros adapter = new AdapterTodosLogros(this, logros);
        listaLogros.setAdapter(adapter);
    }
}

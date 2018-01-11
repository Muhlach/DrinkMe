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

        Bundle bundleRecibido = getIntent().getExtras();
        logros = bundleRecibido.getParcelableArrayList(MainActivity.LOGROS_KEY );

        for(Logro l : logros) {
            l.setLogroImg(getResources().getDrawable(R.drawable.ic_logros_32, null));
            if(l.isSuperado())
                l.setLogroSuperadoImg(getResources().getDrawable(R.drawable.ic_logrosuperado_32, null));
        }

        AdapterTodosLogros adapter = new AdapterTodosLogros(this, logros);
        listaLogros.setAdapter(adapter);
    }
}

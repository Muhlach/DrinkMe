package com.drinkme.sdm.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.drinkme.sdm.myapplication.Adapters.AdapterLogros;
import com.drinkme.sdm.myapplication.logic.Logro;
import com.drinkme.sdm.myapplication.logic.Usuario;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class LogrosFragment extends Fragment {

    View view;
    ArrayList<Logro> logros;
    ListView listViewLogros;
    Button btnMasLogros;
    Usuario currentUser;
    TextView txNivel, txNombre, txRango, txProgreso;
    ProgressBar progressBar;

    public LogrosFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_logros, container, false);

        btnMasLogros = (Button) view.findViewById(R.id.btnMasLogros);
        btnMasLogros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent masLogrosIntent = new Intent(getActivity(), MasLogrosActivity.class);
                masLogrosIntent.putParcelableArrayListExtra(MainActivity.LOGROS_KEY,
                        currentUser.getLogros().getTodosLogros());
                startActivity(masLogrosIntent);
            }
        });

        Bundle bundleRecibido = getArguments();
        currentUser = bundleRecibido.getParcelable(MainActivity.USER_KEY);

        //Cargar los datos del usuario
        txNombre = (TextView) view.findViewById(R.id.lblNombreUsuarioLogros);
        txRango = (TextView) view.findViewById(R.id.lblRangoBebedorLogros);
        txNivel = (TextView) view.findViewById(R.id.lblNivelUsuario);
        txProgreso = (TextView) view.findViewById(R.id.lblProgresoNivel);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarNivel);

        txNombre.setText(currentUser.getNombre());
        txRango.setText(currentUser.getNivel().getRangoBebedor());
        txNivel.setText(String.valueOf(currentUser.getNivel().getNivelID()));
        txProgreso.setText(currentUser.getPuntosExperiencia() + " / " + currentUser.getNivel().getPuntosMaximos());
        progressBar.setMax(currentUser.getNivel().getPuntosMaximos());
        progressBar.setProgress(currentUser.getPuntosExperiencia());

        cargarLogros();

        return view;
    }

    private void cargarLogros() {
        logros = currentUser.getLogrosSuperadosDelUsuario();
        for(Logro l : logros) {
            l.setLogroImg(getResources().getDrawable(R.drawable.ic_logros_24_lista));
        }

        listViewLogros = view.findViewById(R.id.listViewMisLogros);
        AdapterLogros adapter = new AdapterLogros(getActivity(), logros);
        listViewLogros.setAdapter(adapter);
    }

}

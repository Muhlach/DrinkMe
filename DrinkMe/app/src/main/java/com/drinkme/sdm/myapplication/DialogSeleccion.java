package com.drinkme.sdm.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.dao.BebidaDAO;
import com.drinkme.sdm.myapplication.dao.ConsumicionDAO;
import com.drinkme.sdm.myapplication.dao.LogrosDAO;
import com.drinkme.sdm.myapplication.dao.UsuarioDAO;
import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.entity.Consumicion;
import com.drinkme.sdm.myapplication.entity.LogrosSuperados;
import com.drinkme.sdm.myapplication.logic.BebidaBin;
import com.drinkme.sdm.myapplication.logic.Logro;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;
import com.drinkme.sdm.myapplication.utils.FechaUtils;

import java.util.ArrayList;

/**
 * Created by ssant on 09/11/2017.
 */

public class DialogSeleccion extends DialogFragment{

    private static final int NO_SUPERA_LOGROS = 0;
    Spinner spinnerBebida;
    TextView txPrecio;
    View view;
    Button guardar, cancelar;
    BebidaBin bebidaSeleccionada;
    ArrayList<BebidaBin> bebidasArrayList;
    UsuarioBin user;
    double precio;
    int categoria;
    FragmentManager fragmentManager;
    private MediaPlayer mediaPlayer;
    Context context;

    public DialogSeleccion(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        view = inflater.inflate(R.layout.dialog_seleccion_layout, container);
        fragmentManager = getFragmentManager();
        context = getContext();
        spinnerBebida = (Spinner) view.findViewById(R.id.cmbxDialogBebidas);
        txPrecio = (TextView) view.findViewById(R.id.txDialogPrecio);

        mediaPlayer = MediaPlayer.create(getActivity(),R.raw.drinkme_sample);

        guardar = (Button) view.findViewById(R.id.btnDialogGuardar);

        Bundle bundleRecibido = this.getArguments();
        bebidasArrayList = bundleRecibido.getParcelableArrayList(MainActivity.BEBIDAS_KEY);
        user = bundleRecibido.getParcelable(MainActivity.USER_KEY);
        categoria = bundleRecibido.getInt("categoriaseleccionada");
        cargaBebidas();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Cogemos la bebida seleccionada y el precio introducido
                BebidaBin b = (BebidaBin) spinnerBebida.getSelectedItem();
                String precioStr = txPrecio.getText().toString();

                //Comprobamos que se haya seleccionado una bebida y que se haya introducido precio
                if(precioStr.isEmpty())
                    Toast.makeText(getActivity(), "Debes introducir un precio", Toast.LENGTH_SHORT).show();
                else {
                    precio = Double.valueOf(precioStr);
                    /** Guarda en la base de datos la consumicion y actualiza experiencia usuario **/
                    int registros = guardarConsumicion(b, precio);
                    /** Gestiona los logros superados **/
                    ArrayList<Logro> logrosSuperados = user.actualizarLogros(getContext(), categoria);
                    int recompensaLogros = accionSuperarLogros(logrosSuperados);

                    /** Gestiona las subidas de nivel **/
                    user.actualizarPuntosExperiencia(b.getPuntosBebida()+recompensaLogros);
                    actualizaPuntosBD(b.getPuntosBebida()+recompensaLogros);
                    boolean subeNivel = user.actualizarNivel();
                    if(subeNivel) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(MainActivity.NUEVO_NIVEL, user.getNivel().getNivelID());
                        DialogSubeNivel dialog = new DialogSubeNivel();
                        dialog.setArguments(bundle);
                        dialog.show(fragmentManager, "tag");
                    }
                    String r = b.toString() + "  " + precio;
                    Toast.makeText(getActivity(), r, Toast.LENGTH_SHORT).show();
                    soundEffect();
                    dismiss();
                }
            }
        });

        cancelar = (Button) view.findViewById(R.id.btnDialogCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Consumición cancelada", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });

        return view;
    }

    private void soundEffect() {
        try{
            mediaPlayer.prepare();

        } catch (Exception e) {
            Log.wtf("MediaPlayer", "MediaPlayer Fail");
        }

        try{
            mediaPlayer.start();

        } catch (Exception e) {
            Log.wtf("MediaPlayer", "MediaPlayer Fail");
        }

    }

    private void actualizaPuntosBD(int puntos) {
        MyDatabase db = MyDatabase.getDatabase(getActivity());
        UsuarioDAO usuarioDAO = db.usuarioDAO();
        int usuarioId = usuarioDAO.findByNombre(user.getUserID()).getId();
        usuarioDAO.actualizaPuntosUsuario(usuarioId, puntos);
    }

    /**
     * Metodo que registra los logros superados, notifica al usuario y actualiza las vistas
     * @param logrosSuperados
     */
    private int accionSuperarLogros(ArrayList<Logro> logrosSuperados) {
        //Primero comprobamos si se ha superado algún logro, en caso de no haberse superado ninguno terminamo.
        if(logrosSuperados.size()>0) {
            //Actualizamos los logros del objeto User
            for(Logro l : user.getLogros().getTodosLogros()) {
                if(logrosSuperados.contains(l)) {
                    user.getLogros().superarLogros(logrosSuperados);
                }
            }
            //Actualizamos los logros superados en la base de datos
            MyDatabase db = MyDatabase.getDatabase(getActivity());
            UsuarioDAO usuarioDAO = db.usuarioDAO();
            LogrosDAO logrosDAO = db.logrosDAO();
            int usuarioId = usuarioDAO.findByNombre(user.getUserID()).getId();
            for(Logro l : logrosSuperados) {
                LogrosSuperados ls = new LogrosSuperados(l.getLogroID(), usuarioId);
                logrosDAO.insertAll(ls);
            }

            //Calculamos los puntos de experiencia que gana el usuario
            int puntos = 0;
            for(Logro l : logrosSuperados) {
                puntos = puntos + l.getPuntos();
            }

            //Mostramos dialogo de logros superados
            String[] logros = new String[logrosSuperados.size()];
            for(int i = 0; i<logrosSuperados.size(); i++) {
                logros[i] = logrosSuperados.get(i).getLogroName();
            }
            DialogoSuperarLogro dialog = new DialogoSuperarLogro();
            Bundle bundle = new Bundle();
            bundle.putStringArray("nombrelogrossuperados", logros);
            bundle.putInt("puntoslogrossuperados", puntos);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "logros");

            //Devolvemos los puntos que gana el usuario
            return puntos;
        }
        return NO_SUPERA_LOGROS;
    }

    /**
     * Metodo que registra en la base de datos una consumición
     * @param b bebida que selecciona el usuario
     * @param precio precio de la consumicion
     */
    private int guardarConsumicion(BebidaBin b, double precio) {
        MyDatabase db = MyDatabase.getDatabase(getActivity());
        UsuarioDAO usuarioDAO = db.usuarioDAO();
        BebidaDAO bebidaDAO = db.bebidaDAO();
        ConsumicionDAO consumicionDAO = db.consumicionDAO();
        int bebidaId = bebidaDAO.findByNombre(b.getBebName()).getId();
        int usuarioId = usuarioDAO.findByNombre(user.getUserID()).getId();
        int fecha = FechaUtils.getToday();

        Consumicion consumicion = new Consumicion(usuarioId, bebidaId, precio, fecha);
        consumicionDAO.insertAll(consumicion);
        return consumicionDAO.cuentaRegistros(usuarioId);
    }


    private void cargaBebidas() {
        BebidaBin[] bebidas = creaArray();
        ArrayAdapter<BebidaBin> adapterBebida = new ArrayAdapter<BebidaBin>(getActivity(),
                android.R.layout.simple_spinner_item, bebidas);
        adapterBebida.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBebida.setAdapter(adapterBebida);
    }

    private BebidaBin[] creaArray() {
        BebidaBin[] result = new BebidaBin[bebidasArrayList.size()];
        int counter = 0;
        for(BebidaBin beb : bebidasArrayList) {
            result[counter] = beb;
            counter++;
        }

        return result;
    }


//    private class GuardaBebidaAsync extends AsyncTask <Void, Void, Boolean> {
//
//        ProgressDialog progressDialog;
//        BebidaBin b;
//        Context c;
//
//        public GuardaBebidaAsync (BebidaBin b, Context c) {
//            this.b = b;
//            this.c = c;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... voids) {
//            /** Guarda en la base de datos la consumicion y actualiza experiencia usuario **/
//            int registros = guardarConsumicion(b, precio);
//            /** Gestiona los logros superados **/
//            ArrayList<Logro> logrosSuperados = user.actualizarLogros(getContext(), categoria);
//            int recompensaLogros = accionSuperarLogros(logrosSuperados);
//
//            /** Gestiona las subidas de nivel **/
//            user.actualizarPuntosExperiencia(b.getPuntosBebida()+recompensaLogros);
//            actualizaPuntosBD(b.getPuntosBebida()+recompensaLogros);
//            return true;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            progressDialog = new ProgressDialog(c);
//            progressDialog.setTitle("Guardando consumición");
//            progressDialog.setMessage("Por favor espere...");
//            progressDialog.show();
//        }
//
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            progressDialog.dismiss();
//        }
//    }
}

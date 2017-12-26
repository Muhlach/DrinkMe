package com.drinkme.sdm.myapplication;

import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.R;
import com.drinkme.sdm.myapplication.crearCuenta.*;
import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.entity.Usuario;

import java.util.ArrayList;

/**
 * Created by alex on 26/12/2017.
 */

public class CrearCuentaActivity extends AppCompatActivity {

    boolean debug = true;
    MyDatabase database;
    private int indexCurretFragment = 0;
    ArrayList<DialogFragment> fragments;
    Usuario user;
    String nombre, apellidos, contrasena, email, fecha, sexo;
    int altura, peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta_fondo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        user = new Usuario();

        fragments = new ArrayList<>();
        fragments.add(new Nombre_apellidos_fragment());
        fragments.add(new UO_password_fragment());
        fragments.add(new Dia_mes_fragment());
        fragments.add(new Altura_peso_fragment());
        fragments.add(new OK_fragment());

        getSupportActionBar().hide();

        nextFragmet();

        database = MyDatabase.getDatabase(getApplicationContext());
    }


    public void nextFragmet() {
        Log.wtf("-------------", String.valueOf(indexCurretFragment));
        fragments.get(indexCurretFragment).show(getFragmentManager(), "Numero " + indexCurretFragment);
        ++indexCurretFragment;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void createUser() {
        Usuario user = new Usuario(nombre, apellidos, email, contrasena, 1, sexo, altura,
                peso, 0);

        if (debug)
            Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_LONG).show();

        /**
         *
         * El objeto usuario ya está creado, debería insertarse en la BBDD
         */
        database.usuarioDAO().insertAll(user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.wtf("----", "wtf");
    }
}

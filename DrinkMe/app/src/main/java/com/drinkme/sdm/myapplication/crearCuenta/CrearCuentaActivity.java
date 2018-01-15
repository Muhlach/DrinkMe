package com.drinkme.sdm.myapplication.crearCuenta;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.drinkme.sdm.myapplication.R;
import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.entity.Usuario;

import java.util.ArrayList;

/**
 * Created by alex on 26/12/2017.
 */

public class CrearCuentaActivity extends AppCompatActivity {

    private boolean debug = false;
    private MyDatabase database;
    private int indexCurretFragment = 0;
    private ArrayList<Fragment> fragments;

    /**
     * Creamos un usuario; cada fragment inicializa los fields del usuario
     */
    private Usuario user;
    private String nombre, apellidos, contrasena, email, fecha, sexo, userID;
    private int altura, peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_crear_cuenta);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        user = new Usuario();

        /**
         * Rellenamos el arrayList de fragments
         */
        fragments = new ArrayList<>();
        fragments.add(new Nombre_apellidos_fragment());
        fragments.add(new UO_password_fragment());
        fragments.add(new Dia_mes_fragment());
        fragments.add(new Altura_peso_fragment());
        fragments.add(new OK_fragment());

        getSupportActionBar().setTitle("Crear cuenta");

        nextFragmet();

        database = MyDatabase.getDatabase(getApplicationContext());
    }


    /**
     * Metodo encargado de cambiar el fragment
     */
    public void nextFragmet() {
        Fragment fragment = fragments.get(indexCurretFragment);
        ++indexCurretFragment;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.commit();
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

    public void setUsuarioID(String uo) {
        this.userID = uo;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Este método es llamado por el último fragment
     */
    public void createUser() {
        Usuario user = new Usuario(nombre, apellidos, email, contrasena, fecha, sexo, altura,
                peso, 0, userID);

        database.usuarioDAO().insertAll(user);

    }
}

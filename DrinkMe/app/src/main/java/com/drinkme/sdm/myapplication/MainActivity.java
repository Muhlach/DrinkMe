package com.drinkme.sdm.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.logic.Bebida;
import com.drinkme.sdm.myapplication.logic.Categoria;
import com.drinkme.sdm.myapplication.logic.Estadistico;
import com.drinkme.sdm.myapplication.logic.EstadisticosBD;
import com.drinkme.sdm.myapplication.logic.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Usuario currentUser;
    EstadisticosBD estadisticosBD;
    ArrayList<Categoria> categorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        /** Carga los datos de usuario, bebidas y estadisticos **/
        currentUser = new Usuario();
        categorias = cargarCategorias();
        estadisticosBD = cargarEstadisticos();

        /** Carga el fragment inicial **/
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_beber);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setInitialFragment();
    }

    /**
     * Metodo que te carga al fragment en funcion de lo que seleccionas en la navigation bar
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_logros:
                    fragment = new LogrosFragment();
                    break;
                case R.id.navigation_beber:
                    fragment = new BeberFragment();
                    break;
                case R.id.navigation_estadisticas:
                    fragment = new EstadisticasFragment();
                    break;
            }
            replaceFragment(fragment);
            return true;
        }

    };

    /**
     * Carga el fragment principal (el de beber)
     */
    private void setInitialFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_fragment_placeholder, new BeberFragment());
        fragmentTransaction.commit();
    }

    /**
     * Recibe un fragmento que cargará en la pantalla que se visualiza
     * @param fragment que mostrará en pantalla
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_placeholder, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Metodo que asigna el menú principal a la activity
     * @param menu que asignamos
     * @return true si se ha añadido correctamente. False en caso contrario
     */
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Método que asigna la funcionalidad a cada elemento del meú
     * @param item del menú que pulsa el usuario
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.perfil){
            Intent perfilIntent = new Intent(this, PerfilActivity.class);
            startActivity(perfilIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo que carga todas las categorias con sus correspondientes bebeidas en la aplicacion.
     *
     * ACTUALMENTE ES UN METODO DE PRUEBA QUE CREA LOS OBJETOS
     *
     * @return
     */
    private ArrayList<Categoria> cargarCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        //Creamos las categorias
        Categoria vino = new Categoria("Vino", getResources().getDrawable(R.drawable.ic_vino_64), null);
        Categoria cerveza = new Categoria("Cerveza", getResources().getDrawable(R.drawable.ic_cerveza_64), null);
        Categoria copa = new Categoria("Copa", getResources().getDrawable(R.drawable.ic_copa_64), null);
        Categoria chupito = new Categoria("Chupito", getResources().getDrawable(R.drawable.ic_chupito_64), null);

        //Creamos las bebidas
        Bebida b = new Bebida("Caña Rubia", 0, 0, 0, 0, 0, 1);
        Bebida b1 = new Bebida("Caña Tostada", 0, 0, 0, 0, 0, 1);
        Bebida b2 = new Bebida("Caña de Trigo", 0, 0, 0, 0, 0, 1);

        Bebida b3 = new Bebida("Copa de Tinto", 0, 0, 0, 0, 0, 1);
        Bebida b4 = new Bebida("Copa de Blanco", 0, 0, 0, 0, 0, 1);
        Bebida b5 = new Bebida("Copa de Espumoso", 0, 0, 0, 0, 0, 1);

        Bebida b7 = new Bebida("Copa de Vodka", 0, 0, 0, 0, 0, 1);
        Bebida b8 = new Bebida("Copa de Ginebra", 0, 0, 0, 0, 0, 1);
        Bebida b9 = new Bebida("Copa de Ron", 0, 0, 0, 0, 0, 1);

        Bebida b10 = new Bebida("Chupito de Jagger", 0, 0, 0, 0, 0, 1);
        Bebida b11 = new Bebida("Chupito de Whiskey", 0, 0, 0, 0, 0, 1);
        Bebida b12 = new Bebida("Chupito de Absenta", 0, 0, 0, 0, 0, 1);

        //Creamos los arrays de bebidas
        ArrayList<Bebida> bebidasCerveza = new ArrayList<Bebida>();
        ArrayList<Bebida> bebidasVino = new ArrayList<Bebida>();
        ArrayList<Bebida> bebidasCoctel = new ArrayList<Bebida>();
        ArrayList<Bebida> bebidasChupito = new ArrayList<Bebida>();

        bebidasCerveza.add(b);bebidasCerveza.add(b1);bebidasCerveza.add(b2);
        bebidasVino.add(b3);bebidasVino.add(b4);bebidasVino.add(b5);
        bebidasCoctel.add(b7);bebidasCoctel.add(b8);bebidasCoctel.add(b9);
        bebidasChupito.add(b10);bebidasChupito.add(b11);bebidasChupito.add(b12);

        //Asignamos los arrays a las categorias
        vino.setBebidas(bebidasVino);
        cerveza.setBebidas(bebidasCerveza);
        copa.setBebidas(bebidasCoctel);
        chupito.setBebidas(bebidasChupito);


        //Añadimos las categorias al array y hacemos return
        categorias.add(vino);
        categorias.add(cerveza);
        categorias.add(copa);
        categorias.add(chupito);

        return categorias;
    }

    /**
     * Metodo que crea la lista de estadisticos.
     * @return
     */
    private EstadisticosBD cargarEstadisticos() {
        Estadistico e = new Estadistico(1, "Total L bebidos: ", -1);
        Estadistico e1 = new Estadistico(2, "Total L alcohol: ", -1);
        Estadistico e2 = new Estadistico(3, "Total kcal: ", -1);
        Estadistico e3 = new Estadistico(4, "Total € gastados: ", -1);
        ArrayList<Estadistico> estadisticos = new ArrayList<Estadistico>();
        estadisticos.add(e);
        estadisticos.add(e1);
        estadisticos.add(e2);
        estadisticos.add(e3);

        return new EstadisticosBD(estadisticos);
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

}

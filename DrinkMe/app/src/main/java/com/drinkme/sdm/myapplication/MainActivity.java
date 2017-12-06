package com.drinkme.sdm.myapplication;

import android.content.Intent;
import android.content.res.Resources;
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
import com.drinkme.sdm.myapplication.logic.Logro;
import com.drinkme.sdm.myapplication.logic.LogrosBD;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_CATEGORIAS = "lista_categorias";
    public static final String BEBIDAS_KEY = "lista_bebidas";
    public static final String USER_KEY = "usuario";
    public static final String LOGROS_KEY = "lista_logros";
    public static final String ESTADISTICOS_KEY = "estadisticos";
    public static final int REQUEST_CODE_FOR_PERFIL_ACTIVITY = 1;

    private boolean debug = true;
    UsuarioBin currentUser;
    EstadisticosBD estadisticosBD;
    ArrayList<Categoria> categorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        /** Carga los datos de usuario, bebidasArrayList y estadisticos **/
        Bundle bundleRecibido = getIntent().getExtras();
        currentUser = bundleRecibido.getParcelable(LoginActivity.KEY_USUARIO_LOGEADO);
        int aux = currentUser.getPuntosExperiencia();
        currentUser.setPuntosExperiencia(aux);
        LogrosBD logros = cargaLogros();
        currentUser.setLogros(logros);
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
            Bundle b = new Bundle();
            switch (item.getItemId()) {
                case R.id.navigation_logros:
                    b.putParcelable(USER_KEY, currentUser);
                    fragment = new LogrosFragment();
                    fragment.setArguments(b);
                    break;
                case R.id.navigation_beber:

                    b.putParcelableArrayList(KEY_CATEGORIAS, categorias);
                    fragment = new BeberFragment();
                    fragment.setArguments(b);
                    break;
                case R.id.navigation_estadisticas:
                    b.putParcelableArrayList(ESTADISTICOS_KEY, estadisticosBD.getEstadisticos());
                    b.putParcelableArrayList(KEY_CATEGORIAS, categorias);
                    fragment = new EstadisticasFragment();
                    fragment.setArguments(b);
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
        Bundle b = new Bundle();
        b.putParcelableArrayList(KEY_CATEGORIAS, categorias);
        BeberFragment fragment = new BeberFragment();
        fragment.setArguments(b);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_fragment_placeholder, fragment);
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
            perfilIntent.putExtra(USER_KEY, currentUser);
            startActivityForResult(perfilIntent, REQUEST_CODE_FOR_PERFIL_ACTIVITY);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final Bundle mBundle = data.getExtras();
            currentUser = mBundle.getParcelable(PerfilActivity.KEY_FOR_USER_IN_PA);
            if (debug)
                Toast.makeText(getApplicationContext(),currentUser.toString(),Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Metodo que carga todas las categoriasArrayList con sus correspondientes bebeidas en la aplicacion.
     *
     * ACTUALMENTE ES UN METODO DE PRUEBA QUE CREA LOS OBJETOS
     *
     * @return
     */
    private ArrayList<Categoria> cargarCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        //Creamos las categoriasArrayList
        Categoria vino = new Categoria("Vino", getResources().getDrawable(R.drawable.ic_vino_64), null);
        Categoria cerveza = new Categoria("Cerveza", getResources().getDrawable(R.drawable.ic_cerveza_64), null);
        Categoria copa = new Categoria("Copa", getResources().getDrawable(R.drawable.ic_copa_64), null);
        Categoria chupito = new Categoria("Chupito", getResources().getDrawable(R.drawable.ic_chupito_64), null);

        //Creamos las bebidasArrayList
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

        //Creamos los arrays de bebidasArrayList
        ArrayList<Bebida> bebidasCerveza = new ArrayList<Bebida>();
        ArrayList<Bebida> bebidasVino = new ArrayList<Bebida>();
        ArrayList<Bebida> bebidasCoctel = new ArrayList<Bebida>();
        ArrayList<Bebida> bebidasChupito = new ArrayList<Bebida>();

        bebidasCerveza.add(b);bebidasCerveza.add(b1);bebidasCerveza.add(b2);
        bebidasVino.add(b3);bebidasVino.add(b4);bebidasVino.add(b5);
        bebidasCoctel.add(b7);bebidasCoctel.add(b8);bebidasCoctel.add(b9);
        bebidasChupito.add(b10);bebidasChupito.add(b11);bebidasChupito.add(b12);

        //Asignamos los arrays a las categoriasArrayList
        vino.setBebidas(bebidasVino);
        cerveza.setBebidas(bebidasCerveza);
        copa.setBebidas(bebidasCoctel);
        chupito.setBebidas(bebidasChupito);


        //Añadimos las categoriasArrayList al array y hacemos return
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


    /**
     * Metodo que carga los logros del usuario
     * @return
     */
    private LogrosBD cargaLogros() {
        Resources res = getResources();
        int[] ids = res.getIntArray(R.array.logros_id);
        String[] nombres = res.getStringArray(R.array.logros_nombres);
        String[] descripciones = res.getStringArray(R.array.logros_descripciones);
        int[] puntos = res.getIntArray(R.array.logros_puntos);
        LogrosBD result = new LogrosBD(ids, nombres, descripciones, puntos);

        ArrayList superados = new ArrayList();
        superados.add(1);
        superados.add(25);
        superados.add(2);
        superados.add(27);
        superados.add(14);
        superados.add(10);
        superados.add(9);


        for (Logro l : result.getTodosLogros()) {
            if (superados.contains(l.getLogroID())) {
                l.setSuperado(true);
                result.añadirLogro(result.getLogrosSuperados(), l);
            }
        }
        return result;
    }
}

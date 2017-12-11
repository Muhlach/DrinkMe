package com.drinkme.sdm.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.entity.Categoria;
import com.drinkme.sdm.myapplication.logic.BebidaBin;
import com.drinkme.sdm.myapplication.logic.CategoriaBin;
import com.drinkme.sdm.myapplication.logic.Estadistico;
import com.drinkme.sdm.myapplication.logic.EstadisticosBD;
import com.drinkme.sdm.myapplication.logic.Logro;
import com.drinkme.sdm.myapplication.logic.LogrosBD;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;

import java.util.ArrayList;

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
    ArrayList<CategoriaBin> categorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        /** Carga los datos de usuario, bebidasArrayList y estadisticos **/
        Bundle bundleRecibido = getIntent().getExtras();
        currentUser = bundleRecibido.getParcelable(LoginActivity.KEY_USUARIO_LOGEADO);
        int aux = currentUser.getPuntosExperiencia();
        currentUser.setPuntosExperiencia(aux);
        cargaLogros();
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


    private ArrayList<CategoriaBin> cargarDesdeBD() {
        ArrayList<CategoriaBin> categorias = new ArrayList<CategoriaBin>();
        return categorias;
    }

    /**
     * Metodo que carga todas las categoriasArrayList con sus correspondientes bebeidas en la aplicacion.
     *
     * ACTUALMENTE ES UN METODO DE PRUEBA QUE CREA LOS OBJETOS
     *
     * @return
     */
    private ArrayList<CategoriaBin> cargarCategorias() {
        ArrayList<CategoriaBin> categorias = new ArrayList<CategoriaBin>();
        //Creamos las categoriasArrayList
        CategoriaBin vino = new CategoriaBin("Vino", getResources().getDrawable(R.drawable.ic_vino_64), null);
        CategoriaBin cerveza = new CategoriaBin("Cerveza", getResources().getDrawable(R.drawable.ic_cerveza_64), null);
        CategoriaBin copa = new CategoriaBin("Copa", getResources().getDrawable(R.drawable.ic_copa_64), null);
        CategoriaBin chupito = new CategoriaBin("Chupito", getResources().getDrawable(R.drawable.ic_chupito_64), null);

        //Creamos las bebidasArrayList
        BebidaBin b = new BebidaBin("Caña Rubia", 0, 0, 0, 0, 0, 1);
        BebidaBin b1 = new BebidaBin("Caña Tostada", 0, 0, 0, 0, 0, 1);
        BebidaBin b2 = new BebidaBin("Caña de Trigo", 0, 0, 0, 0, 0, 1);

        BebidaBin b3 = new BebidaBin("Copa de Tinto", 0, 0, 0, 0, 0, 1);
        BebidaBin b4 = new BebidaBin("Copa de Blanco", 0, 0, 0, 0, 0, 1);
        BebidaBin b5 = new BebidaBin("Copa de Espumoso", 0, 0, 0, 0, 0, 1);

        BebidaBin b7 = new BebidaBin("Copa de Vodka", 0, 0, 0, 0, 0, 1);
        BebidaBin b8 = new BebidaBin("Copa de Ginebra", 0, 0, 0, 0, 0, 1);
        BebidaBin b9 = new BebidaBin("Copa de Ron", 0, 0, 0, 0, 0, 1);

        BebidaBin b10 = new BebidaBin("Chupito de Jagger", 0, 0, 0, 0, 0, 1);
        BebidaBin b11 = new BebidaBin("Chupito de Whiskey", 0, 0, 0, 0, 0, 1);
        BebidaBin b12 = new BebidaBin("Chupito de Absenta", 0, 0, 0, 0, 0, 1);

        //Creamos los arrays de bebidasArrayList
        ArrayList<BebidaBin> bebidasCerveza = new ArrayList<BebidaBin>();
        ArrayList<BebidaBin> bebidasVino = new ArrayList<BebidaBin>();
        ArrayList<BebidaBin> bebidasCoctel = new ArrayList<BebidaBin>();
        ArrayList<BebidaBin> bebidasChupito = new ArrayList<BebidaBin>();

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

    public ArrayList<CategoriaBin> getCategorias() {
        return categorias;
    }


    /**
     * Metodo que carga los logros del usuario
     * @return
     */
    public void cargaLogros() {
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

        ArrayList<Logro> todos = new ArrayList<Logro>();
        todos.add(l);todos.add(l1);todos.add(l2);todos.add(l3);todos.add(l4);todos.add(l5);
        todos.add(l6);todos.add(l7);todos.add(l8);todos.add(l9);

        ArrayList<Logro> superados = new ArrayList<Logro>();
        superados.add(l2);superados.add(l4);superados.add(l5);superados.add(l7);superados.add(l9);

        LogrosBD result = new LogrosBD(todos, superados);

        currentUser.setLogros(result);
    }


}

package com.drinkme.sdm.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.entity.Bebida;
import com.drinkme.sdm.myapplication.entity.Categoria;
import com.drinkme.sdm.myapplication.logic.CategoriaBin;
import com.drinkme.sdm.myapplication.logic.Estadistico;
import com.drinkme.sdm.myapplication.logic.EstadisticosBD;
import com.drinkme.sdm.myapplication.logic.Logro;
import com.drinkme.sdm.myapplication.logic.LogrosBD;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;
import com.drinkme.sdm.myapplication.utils.ToBean;
import com.drinkme.sdm.myapplication.utils.ToEntity;

import java.util.ArrayList;
import java.util.List;

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
    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        //Instanciar base de datos
        database = MyDatabase.getDatabase(getApplicationContext());

        /** Carga los datos de usuario**/
        Bundle bundleRecibido = getIntent().getExtras();
        currentUser = bundleRecibido.getParcelable(LoginActivity.KEY_USUARIO_LOGEADO);
        int aux = currentUser.getPuntosExperiencia();
        currentUser.setPuntosExperiencia(aux);

        /** Carga los logros**/
        cargaLogros();

        /** Carga las categorias**/
        categorias = cargarCategoriasyBebidas();

        /** Carga los estadisticos**/
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
     * Carga las bebidas y categorias en la aplicacion desde la base de datos. En caso de que la
     * base de datos no tenga ninguna bebida cargada, las genera
     * @return ArrayList de categorias con respectivas bebidas
     */
    public ArrayList<CategoriaBin> cargarCategoriasyBebidas(){
        /** Si la base de datos no tiene bebidas ni categorias cargadas, las cargamos */
        if(database.bebidaDAO().getAll().isEmpty() && database.categoriaDAO().getAll().isEmpty()){
            generarCategoriasBD();
            generarBebidasBD();
        }

        /** Obtenemos la arraylist de Bean para nuestra app */
        ToBean worker = new ToBean();
        ArrayList<CategoriaBin> categorias = worker.obtenerCategoriasBean(database.categoriaDAO().getAll(),
                database.bebidaDAO().getAll());

        /** Cargamos las imagenes */
        for(CategoriaBin c : categorias) {
            switch (c.getId()){
                case 1:
                    c.setCatImg(getResources().getDrawable(R.drawable.ic_cerveza_128, null));
                    break;
                case 2:
                    c.setCatImg(getResources().getDrawable(R.drawable.ic_vino_128, null));
                    break;
                case 3:
                    c.setCatImg(getResources().getDrawable(R.drawable.ic_copa_128, null));
                    break;
                case 4:
                    c.setCatImg(getResources().getDrawable(R.drawable.ic_chupito_128, null));
                    break;
            }
        }


        return categorias;
    }

    /**
     * Genera y añade a la base de datos las bebidas en funcion de los ficheros XML para que
     * sea facil añadir nuevas
     */
    private void generarBebidasBD() {
        Resources res = getResources();
        String[] nombres = res.getStringArray(R.array.nombres_bebidas);
        int[] volumenTotal = res.getIntArray(R.array.volumen_total);
        int[] volumenAlcohol = res.getIntArray(R.array.volumen_alcohol);
        String[] alcohol = res.getStringArray(R.array.alcohol);
        int[] kcal = res.getIntArray(R.array.kcal);
        int[] azucar = res.getIntArray(R.array.azucar);
        int[] puntos = res.getIntArray(R.array.puntos);
        int[] idCategoria = res.getIntArray(R.array.id_categoria);

        List<Bebida> bebs = new ArrayList<Bebida>();
        Bebida b;
        for(int i=0; i<nombres.length; i++) {
            b = new Bebida(nombres[i], volumenTotal[i], volumenAlcohol[i], Double.valueOf(alcohol[i]), kcal[i],
                    azucar[i], puntos[i], idCategoria[i]);
            bebs.add(b);
        }
        database.bebidaDAO().insertCollection(bebs);

    }

    /**
     * Genera y añade a la base de datos las categoriasen funcion de los ficheros XML para que
     * sea facil añadir nuevas
     */
    private void generarCategoriasBD() {
        String[] nombres = getResources().getStringArray(R.array.nombres_categorias);
        List<Categoria> cats = new ArrayList<Categoria>();
        Categoria c;
        for(int i = 0; i<nombres.length; i++){
            c = new Categoria(nombres[i], null);
            cats.add(c);
        }
        database.categoriaDAO().insertCollection(cats);
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
        Logro  l= new Logro(1, "Cervecero Principiante", "");
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




    //TODO debería actualizar el usuario en la base de datos pero no lo hace?
    @Override
    protected void onDestroy() {
        Toast.makeText(this,"Fin",Toast.LENGTH_LONG).show();
        database.usuarioDAO().update(new ToEntity().convertUser(currentUser));
        super.onDestroy();
    }
}

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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

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
                    Bundle newBundle = new Bundle();
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

}

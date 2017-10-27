package com.drinkme.sdm.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

}

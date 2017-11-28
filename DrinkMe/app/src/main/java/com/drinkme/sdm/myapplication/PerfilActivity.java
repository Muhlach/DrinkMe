package com.drinkme.sdm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import static com.drinkme.sdm.myapplication.R.id.itemEditarPassword;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_perfil);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.perfil_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case itemEditarPassword:
                PerfilActivity_password_fragment perfilActivity_password_fragment = new PerfilActivity_password_fragment();
                perfilActivity_password_fragment.show(getFragmentManager(),"miTag");
                break;

        }


        return true;
    }
}

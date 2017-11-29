package com.drinkme.sdm.myapplication;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.logic.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.drinkme.sdm.myapplication.R.id.itemEditarPassword;

public class PerfilActivity extends AppCompatActivity {


    private Spinner spinnerAltura;
    private Spinner spinnerPeso;
    private Usuario user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_perfil);

        Bundle bundle = getIntent().getExtras();

        user = bundle.getParcelable(MainActivity.USER_KEY);

        findViews();

      //  Toast.makeText(getApplicationContext(),String.valueOf(user_p.getAltura()),Toast.LENGTH_LONG).show();

    }


    private void findViews(){

        spinnerAltura = (Spinner) findViewById(R.id.spinnerAlturaP);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.altura_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAltura.setAdapter(adapter2);

      List<String> list = Arrays.asList(getResources().getStringArray(R.array.altura_array));


       int i = 0;
       for(i = 0; i < list.size(); i++){
           if(list.get(i).equalsIgnoreCase(String.valueOf(user.getAltura())))
               break;
       }

        spinnerAltura.setSelection(i);
        spinnerAltura.setEnabled(true);


              spinnerAltura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                      Toast.makeText(getApplicationContext(),"ke",Toast.LENGTH_LONG).show();
                  }

                  @Override
                  public void onNothingSelected(AdapterView<?> adapterView) {

                  }
              });




        spinnerPeso = (Spinner) findViewById(R.id.spinnerPesoP);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.peso_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeso.setAdapter(adapter3);


        List<String> list2 = Arrays.asList(getResources().getStringArray(R.array.peso_array));

        int j = 0;
        for(j = 0; j < list2.size(); j++){
            if(list2.get(j).equalsIgnoreCase(String.valueOf(user.getPeso())))
                break;
        }

        spinnerPeso.setSelection(j);
        spinnerPeso.setEnabled(false);
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

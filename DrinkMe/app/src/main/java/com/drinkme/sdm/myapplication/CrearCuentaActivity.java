package com.drinkme.sdm.myapplication;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CrearCuentaActivity extends AppCompatActivity {

    Spinner spinnerSex;
    Spinner spinnerAltura;
    Spinner spinnerPeso;
    Spinner spinnerDia;
    Spinner spinnerMes;
    Spinner spinnerAno;
    EditText nombre_et, apellidos_et, nombreDeUo_et, correo_et, password_et, repassword_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nombre_et = (EditText) findViewById(R.id.editTextNombre);
        apellidos_et = (EditText) findViewById(R.id.editTextApellidos);
        correo_et = (EditText) findViewById(R.id.editTextCorreo);
        password_et = (EditText) findViewById(R.id.editTextPassword);
        repassword_et = (EditText) findViewById(R.id.editTextRePassword);
        nombreDeUo_et = (EditText) findViewById(R.id.editTextNombreUsuario);


        spinnerSex = (Spinner) findViewById(R.id.spinnerSex);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sex_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapter);

        spinnerAltura = (Spinner) findViewById(R.id.spinnerAltura);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.altura_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAltura.setAdapter(adapter2);

        spinnerPeso = (Spinner) findViewById(R.id.spinnerPeso);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.peso_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeso.setAdapter(adapter3);

        spinnerDia = (Spinner) findViewById(R.id.spinnerDia);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.dia_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDia.setAdapter(adapter4);

        spinnerMes = (Spinner) findViewById(R.id.spinnerMes);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.mes_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMes.setAdapter(adapter5);

        spinnerAno = (Spinner) findViewById(R.id.spinnerAno);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,R.array.ano_array, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAno.setAdapter(adapter6);

         }

    public void onClickCrearCuentas(View v){

             String nombre, apellidos, usuario, password, repassword, correo;
             nombre = nombre_et.getText().toString();
             apellidos = apellidos_et.getText().toString();
             usuario = nombreDeUo_et.getText().toString();
             password = password_et.getText().toString();
             repassword = repassword_et.getText().toString();
             correo = correo_et.getText().toString();

             if(nombre.isEmpty() || apellidos.isEmpty() || usuario.isEmpty() || password.isEmpty() || repassword.isEmpty() || correo.isEmpty())
                 Toast.makeText(getApplicationContext(),"Completa todos los campos",Toast.LENGTH_SHORT).show();

             if(!password.equalsIgnoreCase(repassword))
                 Toast.makeText(getApplicationContext(),"Comprueba tu password",Toast.LENGTH_SHORT).show();



        Toast.makeText(getApplicationContext(),String.valueOf(spinnerMes.getSelectedItemPosition()+1),Toast.LENGTH_SHORT).show();

         }
}

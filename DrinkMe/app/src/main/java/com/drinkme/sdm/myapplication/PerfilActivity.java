package com.drinkme.sdm.myapplication;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.logic.UsuarioBin;

import java.nio.channels.InterruptedByTimeoutException;

import static com.drinkme.sdm.myapplication.R.id.itemCerrarSesion;
import static com.drinkme.sdm.myapplication.R.id.itemEditarPassword;
import static com.drinkme.sdm.myapplication.R.id.itemGuardar;
import static com.drinkme.sdm.myapplication.R.id.itemLapiz;

public class PerfilActivity extends AppCompatActivity {

    private UsuarioBin user;
    private EditText email_et, altura_et, peso_et;
    private TextView nombre, fecha, nombreUO_et;
    public static final String KEY_FOR_USER_IN_PA = "keyPA";
    MenuItem guardar, lapiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_perfil);

        Bundle bundle = getIntent().getExtras();

        /**
         * Obtenemos el usuario del bundle
         */
        user = bundle.getParcelable(MainActivity.USER_KEY);

        findViews();

    }


    /**
     * Metodo encargado de inicializar todas las vistas
     */
    private void findViews() {

        altura_et = findViewById(R.id.et_altura);
        altura_et.setText(String.valueOf(user.getAltura()));

        peso_et = findViewById(R.id.et_peso);
        peso_et.setText(String.valueOf(user.getPeso()));

        nombreUO_et = findViewById(R.id.et_uo);
        nombreUO_et.setText(user.getUserID());

        email_et = (EditText) findViewById(R.id.editTextEmail);
        email_et.setText(user.getCorreo().toString());

        nombre = findViewById(R.id.tv_nombre);
        nombre.setText(user.getNombre().concat(" ").concat(user.getApellidos()));

        fecha = findViewById(R.id.textViewFechaNaci);
        fecha.setText(fecha.getText().toString().concat(" ").concat(user.getNacimiento()));

        email_et.setEnabled(false);
        altura_et.setEnabled(false);
        peso_et.setEnabled(false);
        nombreUO_et.setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.perfil_activity_menu, menu);
        guardar = menu.findItem(R.id.itemGuardar);
        lapiz = menu.findItem(R.id.itemLapiz);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case itemEditarPassword:
                PerfilActivity_password_fragment perfilActivity_password_fragment = new PerfilActivity_password_fragment();
                Bundle bundle = new Bundle();
                bundle.putString("password", user.getContraseña());
                perfilActivity_password_fragment.setArguments(bundle);
                perfilActivity_password_fragment.show(getFragmentManager(), "miTagPassword");
                break;

            case itemCerrarSesion:
                LoginActivity.deleteSharedPreferences();
                finishAffinity();
                break;

            case itemLapiz:
                email_et.setEnabled(true);
                altura_et.setEnabled(true);
                peso_et.setEnabled(true);
                guardar.setVisible(true);
                lapiz.setVisible(false);
                break;

            case itemGuardar:
                email_et.setEnabled(false);
                altura_et.setEnabled(false);
                peso_et.setEnabled(false);
                actualizarUsuario();
                lapiz.setVisible(true);
                guardar.setVisible(false);
                break;


        }


        return true;
    }

    void updateUserPassword(String password) {
        user.setContraseña(password);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onClickVolver(null);
        }
        return (super.onKeyDown(keyCode, event));
    }

    /**
     * Metodo encargado de comunicar a main activity que la perfilActivity ha terminado
     */
    private void actualizarUsuario() {


        if (!altura_et.getText().toString().isEmpty() && !peso_et.getText().toString().isEmpty() &&
                !email_et.getText().toString().isEmpty() && !nombreUO_et.getText().toString().isEmpty()) {

            user.setAltura(Integer.parseInt(altura_et.getText().toString()));
            user.setPeso(Integer.parseInt(peso_et.getText().toString()));
            user.setCorreo(email_et.getText().toString());
            user.setUserID(nombreUO_et.getText().toString());

//            try {
//                final Intent resultIntent = new Intent();
//                Bundle resultBundle = new Bundle();
//                resultBundle.putParcelable(KEY_FOR_USER_IN_PA, user);
//                resultIntent.putExtras(resultBundle);
//                setResult(RESULT_OK, resultIntent);
//                finish();
//            } catch (Exception e) {
//                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
//                setResult(RESULT_CANCELED);
//                finish();
//            }

        }else
            Toast.makeText(this,"Comprueba los datos", Toast.LENGTH_LONG).show();

    }

    private void setResultToMainActivity() {
        try {
                final Intent resultIntent = new Intent();
                Bundle resultBundle = new Bundle();
                resultBundle.putParcelable(KEY_FOR_USER_IN_PA, user);
                resultIntent.putExtras(resultBundle);
                setResult(RESULT_OK, resultIntent);
                finish();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED);
                finish();
            }
    }

    void onClickVolver(View v) {
        if(!lapiz.isVisible()) {
         crearDialogo().show();
        }
        else {
            setResultToMainActivity();
        }

    }

    public AlertDialog crearDialogo(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Los cambios no se guardarán, ¿quieres salir?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setResultToMainActivity();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

          return builder.create();

    }
}

package com.drinkme.sdm.myapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
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

import static com.drinkme.sdm.myapplication.R.id.itemCerrarSesion;
import static com.drinkme.sdm.myapplication.R.id.itemEditarPassword;
import static com.drinkme.sdm.myapplication.R.id.itemLapiz;

public class PerfilActivity extends AppCompatActivity {

    private UsuarioBin user;
    private EditText nombreUO_et, email_et, altura_et, peso_et;
    TextView nombre, fecha;
    public static final String KEY_FOR_USER_IN_PA = "keyPA";


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
                Toast.makeText(getApplicationContext(), "Tu sesión finalizará cuando cierres la app", Toast.LENGTH_SHORT).show();
                break;

            case itemLapiz:
                email_et.setEnabled(true);
                altura_et.setEnabled(true);
                peso_et.setEnabled(true);
                nombreUO_et.setEnabled(true);
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
            setResultToMainActivity();
        }
        return (super.onKeyDown(keyCode, event));
    }

    /**
     * Metodo encargado de comunicar a main activity que la perfilActivity ha terminado
     */
    private void setResultToMainActivity() {


        if (!altura_et.getText().toString().isEmpty() && !peso_et.getText().toString().isEmpty() && !email_et.getText().toString().isEmpty() && !nombreUO_et.getText().toString().isEmpty()) {

            user.setAltura(Integer.parseInt(altura_et.getText().toString()));
            user.setPeso(Integer.parseInt(peso_et.getText().toString()));
            user.setCorreo(email_et.getText().toString());
            user.setUserID(nombreUO_et.getText().toString());

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

        }else
            Toast.makeText(this,"Comprueba los datos", Toast.LENGTH_LONG).show();

    }

    void onClickVolver(View v) {
        setResultToMainActivity();
    }
}

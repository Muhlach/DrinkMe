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

import com.drinkme.sdm.myapplication.logic.Usuario;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.drinkme.sdm.myapplication.R.id.itemCerrarSesion;
import static com.drinkme.sdm.myapplication.R.id.itemEditarPassword;
import static com.drinkme.sdm.myapplication.R.id.itemEditarUO;
import static com.drinkme.sdm.myapplication.R.id.itemLapiz;
import static com.drinkme.sdm.myapplication.R.id.visible;

public class PerfilActivity extends AppCompatActivity {


    private Spinner spinnerAltura;
    private Spinner spinnerPeso;
    private Usuario user;
    private RadioButton rbAltura, rbPeso;
    private TextView nombreUO;
    private EditText email_t;
    private RadioButton rbEmail;
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

        rbAltura = (RadioButton) findViewById(R.id.radioButtonAltura);
        rbPeso = (RadioButton) findViewById(R.id.radioButtonPeso);
        rbAltura.setChecked(true);
        rbPeso.setChecked(true);


        spinnerAltura = (Spinner) findViewById(R.id.spinnerAlturaP);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.altura_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAltura.setAdapter(adapter2);

        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.altura_array));


        int i = 0;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).equalsIgnoreCase(String.valueOf(user.getAltura())))
                break;
        }

        spinnerAltura.setSelection(i);
        spinnerAltura.setEnabled(true);


        spinnerAltura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rbAltura.setChecked(false);
                spinnerAltura.setEnabled(false);
                user.setAltura(Integer.parseInt(list.get(i)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerPeso = (Spinner) findViewById(R.id.spinnerPesoP);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.peso_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeso.setAdapter(adapter3);


        final List<String> list2 = Arrays.asList(getResources().getStringArray(R.array.peso_array));

        int j = 0;
        for (j = 0; j < list2.size(); j++) {
            if (list2.get(j).equalsIgnoreCase(String.valueOf(user.getPeso())))
                break;
        }

        spinnerPeso.setSelection(j);
        spinnerPeso.setEnabled(false);

        spinnerPeso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rbPeso.setChecked(false);
                spinnerPeso.setEnabled(false);
                user.setPeso(Integer.parseInt(list2.get(i)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rbAltura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerAltura.setEnabled(true);
                spinnerAltura.setSelected(true);
            }
        });

        rbPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerPeso.setEnabled(true);
                spinnerPeso.setSelected(true);
            }
        });

        try {
            nombreUO = (TextView) findViewById(R.id.textViewNombreUsuario);

            email_t = (EditText) findViewById(R.id.editTextEmail);
            email_t.setEnabled(false);
            updateUserEmailForView();

            rbEmail = (RadioButton) findViewById(R.id.radioButtonEmail);
            rbEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        email_t.setEnabled(true);
                        email_t.setSelected(true);
                    }


            });


            updateViewForUO();

            TextView nombre = (TextView) findViewById(R.id.textViewNombreApellidos);
            nombre.setText(user.getNombre().concat(" ").concat(user.getApellidos()));

            TextView fecha = (TextView) findViewById(R.id.textViewFechaNaci);

            String date_s = String.valueOf(user.getNacimiento());

            fecha.setText(fecha.getText().toString().concat(" ").concat(date_s.substring(0, 2).concat("/").concat(date_s.substring(2, 4).concat("/").concat(date_s.substring(4)))));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            email_t.setEnabled(false);
            email_t.setSelected(false);
            rbEmail.setChecked(false);
            return true;
        }
        return super.dispatchKeyEvent(e);
    }

    private void updateUserEmailForView() {
        email_t.setHint(user.getCorreo());
    }

    private void updateViewForUO() {
        nombreUO.setText(user.getUserID());
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

            case itemEditarUO:
                PerfilActivity_user_fragment perfilActivity_user_fragment = new PerfilActivity_user_fragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("user", user.getUserID());
                perfilActivity_user_fragment.setArguments(bundle2);
                perfilActivity_user_fragment.show(getFragmentManager(), "miTagUO");
                break;

            case itemLapiz:
                rbPeso.setVisibility(View.VISIBLE);
                rbAltura.setVisibility(View.VISIBLE);
                rbEmail.setVisibility(View.VISIBLE);
                break;
        }


        return true;
    }

    void updateUserPassword(String password) {
        user.setContraseña(password);
    }

    void updateUserUO(String userID) {
        user.setUserID(userID);
        updateViewForUO();
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
        setResultToMainActivity();
    }
}

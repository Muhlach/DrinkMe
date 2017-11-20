package com.drinkme.sdm.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText user_et;
    private EditText password_et;
    private SharedPreferences mSharedPreferences;

    /**
     * Indica si se mantiene la sesión iniciada
     */
    private boolean holdSesion;
    private CheckBox checkBox;

    /**
     * Para debugear
     */
    private boolean debug = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Antes de lanzar el login observamos si el usuario y contrasenia están ya guardados en shared preferences
         */

         mSharedPreferences =
                getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        String user = mSharedPreferences.getString ("user", null);

        String password = mSharedPreferences.getString ("password", null);

        super.onCreate(savedInstanceState);


        /**
         *
         * Si usuario o contrasenia están null, quiere decir que la sesión no está iniciada
         */

        if(user == null || password == null){
            setContentView(R.layout.activity_login);
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            user_et = (EditText) findViewById(R.id.editTextUser);
            password_et = (EditText) findViewById(R.id.editTextPassword);
            checkBox = (CheckBox) findViewById(R.id.checkBoxMantenerSesion);
        }else {
            if(debug)
            deleteSharedPreferences();
            finish();
            launchMainActivity();
        }
    }

    private void launchMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }


    public void botonCrearCuentaOnClick(View v){
        Intent intent = new Intent(LoginActivity.this, CrearCuentaActivity.class);
        startActivity(intent);
    }

    public void botonEntrarOnClick(View v){

        String user = user_et.getText().toString();
        String password = password_et.getText().toString();
        holdSesion = checkBox.isChecked();

        if(checkUserAndPassword(user, password)){
            if(holdSesion)
            saveInSharedPreferences(user, password);
            launchMainActivity();
            finish();
        }
        else
            Toast.makeText(getApplicationContext(),"Datos incorrectos", Toast.LENGTH_SHORT);
    }

    /**
     *
     * @param user
     * @param password
     * @return true sí y solo sí el usuario ya está en la BBDD
     */
    private boolean checkUserAndPassword(String user, String password){


        //si el usuario y su contrasenia está en la bbdd return true

        //si no return false
        
        return true;
    }

    private void saveInSharedPreferences(String user, String password){
        final SharedPreferences.Editor mEditor =
                mSharedPreferences.edit();
        mEditor.putString("user", user);
        mEditor.putString("password",password);
        mEditor.commit();
    }

    private void deleteSharedPreferences(){
        final SharedPreferences.Editor mEditor =
                mSharedPreferences.edit();
        mEditor.putString("user", null);
        mEditor.putString("password",null);
        mEditor.commit();
    }
}

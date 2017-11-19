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
    private boolean holdSesion;
    private CheckBox checkBox;
    private boolean debug = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

         mSharedPreferences =
                getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        String user = mSharedPreferences.getString ("user", null);

        String password = mSharedPreferences.getString ("password", null);

        super.onCreate(savedInstanceState);

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
        }
        else
            Toast.makeText(getApplicationContext(),"Datos incorrectos", Toast.LENGTH_SHORT);
    }

    private boolean checkUserAndPassword(String user, String password){
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

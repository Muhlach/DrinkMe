package com.drinkme.sdm.myapplication;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.entity.Usuario;
import com.drinkme.sdm.myapplication.logic.UsuarioBin;
import com.drinkme.sdm.myapplication.utils.ToBean;

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_USUARIO_LOGEADO = "usuarioLogueado";
    private EditText user_et;
    private EditText password_et;
    private static SharedPreferences mSharedPreferences;
    private Usuario usuario;
    MyDatabase database;

    /**
     * Indica si se mantiene la sesión iniciada
     */
    private boolean holdSesion;
    private CheckBox checkBox;


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

        database = MyDatabase.getDatabase(getApplicationContext());

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
            hiloDeAnimacion();
        }else {
            finish();
            usuario = database.usuarioDAO().findByNombreAndContraseña(user,password);
            launchMainActivity();
        }
    }

    private void launchMainActivity(){
        ToBean worker = new ToBean();
        UsuarioBin usuarioLogeado = worker.usuarioToBean(usuario);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(KEY_USUARIO_LOGEADO,usuarioLogeado);
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
        else {
            Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
            password_et.requestFocus();
            password_et.setText("");
        }
    }

    /**
     *
     * @param user
     * @param password
     * @return true sí y solo sí el usuario ya está en la BBDD
     */
    private boolean checkUserAndPassword(String user, String password){

        Usuario usuarioActivo = database.usuarioDAO().findByNombreAndContraseña(user,password);
        if(usuarioActivo!=null){
            usuario = usuarioActivo;
            return true;
        }
        return false;


    }

    private void hiloDeAnimacion() {
        final ImageView imageView = (ImageView) findViewById(R.id.imageView4);

        //https://developer.android.com/guide/components/processes-and-threads.html
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {

                            imageView.animate()
                                    .scaleXBy(0.5f)
                                    .scaleYBy(0.5f)
                                    // .translationX(0)
                                    // .translationY(0)
                                    .setDuration(500)
                                    .setListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            imageView.animate()
                                                    .scaleXBy(-0.5f).scaleYBy(-0.5f)
                                                    .setDuration(500)
                                                    .setListener(new Animator.AnimatorListener() {
                                                        @Override
                                                        public void onAnimationStart(Animator animation) {
                                                        }

                                                        @Override
                                                        public void onAnimationEnd(Animator animation) {

                                                        }

                                                        @Override
                                                        public void onAnimationCancel(Animator animation) {
                                                        }

                                                        @Override
                                                        public void onAnimationRepeat(Animator animation) {
                                                        }
                                                    }).start();
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {
                                        }
                                    }).start();
                        }
                    });
                    try {
                        Thread.sleep(2200);
                    } catch (Exception e) {
                    }
                }
            }
        });
        thread.start();
    }

    private void saveInSharedPreferences(String user, String password){
        final SharedPreferences.Editor mEditor =
                mSharedPreferences.edit();
        mEditor.putString("user", user);
        mEditor.putString("password",password);
        mEditor.commit();
    }


    public static void deleteSharedPreferences(){
        final SharedPreferences.Editor mEditor =
                mSharedPreferences.edit();
        mEditor.putString("user", null);
        mEditor.putString("password",null);
        mEditor.commit();
    }
    @Override
    protected void onDestroy() {
        MyDatabase.destroyInstance();
        super.onDestroy();
    }

}

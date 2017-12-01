package com.drinkme.sdm.myapplication.utils;


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.drinkme.sdm.myapplication.database.MyDatabase;
import com.drinkme.sdm.myapplication.entity.Usuario;

import java.util.List;


public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final MyDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final MyDatabase db) {
        populateWithTestData(db);
    }

    private static Usuario addUser(final MyDatabase db, Usuario usuario) {
        db.usuarioDAO().insertAll(usuario);
        return usuario;
    }

    private static void populateWithTestData(MyDatabase db) {
/*       UsuarioBin user = new UsuarioBin();
        user.setNombre("Ajay");
        user.setApellidos("Saini");
        addUser(db, user);

        List<UsuarioBin> userList = db.usuarioDAO().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count: " + userList.size());*/
    }
    public static boolean comprobarLogin(String nombre, String contraseña){


        return false;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MyDatabase mDb;

        PopulateDbAsync(MyDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}

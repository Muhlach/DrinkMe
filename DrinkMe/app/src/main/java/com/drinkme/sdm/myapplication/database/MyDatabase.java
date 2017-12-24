package com.drinkme.sdm.myapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Parcelable;

import com.drinkme.sdm.myapplication.dao.BebidaDAO;
import com.drinkme.sdm.myapplication.dao.CategoriaDAO;
import com.drinkme.sdm.myapplication.dao.ConsumicionDAO;
import com.drinkme.sdm.myapplication.dao.UsuarioDAO;
import com.drinkme.sdm.myapplication.entity.Bebida;
import com.drinkme.sdm.myapplication.entity.Categoria;
import com.drinkme.sdm.myapplication.entity.Consumicion;
import com.drinkme.sdm.myapplication.entity.Usuario;

/**
 * Created by javie on 18/11/2017.
 */
@Database(entities = {Usuario.class, Categoria.class, Bebida.class, Consumicion.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UsuarioDAO usuarioDAO();
    public abstract BebidaDAO bebidaDAO();
    public abstract CategoriaDAO categoriaDAO();
    public abstract ConsumicionDAO consumicionDAO();

    private static MyDatabase INSTANCE;


    public static MyDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, MyDatabase.class, "userdatabase")
//Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the exercise, allow queries on the main thread.
                            // Don't do this on a real app!
                            .allowMainThreadQueries()
                            // recreate the database if necessary
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

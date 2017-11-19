package com.drinkme.sdm.myapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.drinkme.sdm.myapplication.dao.UsuarioDAO;
import com.drinkme.sdm.myapplication.entity.Usuario;

/**
 * Created by javie on 18/11/2017.
 */
@Database(entities = {Usuario.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase{
    public abstract UsuarioDAO usuarioDAO();

    private static MyDatabase INSTANCE;


    public static MyDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "user-persistence.entity.database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

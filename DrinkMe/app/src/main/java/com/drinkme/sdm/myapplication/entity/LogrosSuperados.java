package com.drinkme.sdm.myapplication.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by ssant on 29/12/2017.
 */

@Entity(primaryKeys = {"id", "idUsuario"},
        tableName = "logros", foreignKeys = @ForeignKey(entity =  Usuario.class,
                                                        parentColumns = "id",
                                                        childColumns =  "idUsuario"))
public class LogrosSuperados {
    @NonNull
    private int id;
    @NonNull
    private int idUsuario;

    public LogrosSuperados (int id, int idUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}

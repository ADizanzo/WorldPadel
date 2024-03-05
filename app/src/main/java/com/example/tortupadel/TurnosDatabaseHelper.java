package com.example.tortupadel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TurnosDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "turnos.db";
    private static final int DATABASE_VERSION = 1;

    // Sentencia SQL para crear la tabla de turnos reservados
    private static final String CREATE_TABLE_TURNOS_RESERVADOS =
            "CREATE TABLE " + TurnoReservadoContract.TurnoReservadoEntry.TABLE_NAME + " (" +
                    TurnoReservadoContract.TurnoReservadoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TurnoReservadoContract.TurnoReservadoEntry.COLUMN_TURNO + " TEXT," +
                    TurnoReservadoContract.TurnoReservadoEntry.COLUMN_FECHA + " TEXT);";

    public TurnosDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla de turnos reservados
        db.execSQL(CREATE_TABLE_TURNOS_RESERVADOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Manejar la actualización de la base de datos
    }


}
package com.example.tortupadel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class TurnoReservadoDataSource {

    private SQLiteDatabase database;
    private TurnosDatabaseHelper dbHelper;

    public TurnoReservadoDataSource(Context context) {
        dbHelper = new TurnosDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long agregarTurnoReservado(String turno) {
        ContentValues values = new ContentValues();
        values.put(TurnoReservadoContract.TurnoReservadoEntry.COLUMN_TURNO, turno);
        return database.insert(TurnoReservadoContract.TurnoReservadoEntry.TABLE_NAME, null, values);
    }

    public void eliminarTurnoReservado(String turno) {

    }

    // Implementa métodos para modificar, eliminar y obtener turnos reservados según sea necesario

}
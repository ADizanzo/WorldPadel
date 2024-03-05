package com.example.tortupadel;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TurnoReservadoData {

    private SQLiteDatabase database;
    private final TurnosDatabaseHelper dbHelper;

    public TurnoReservadoData(Context context) {
        dbHelper = new TurnosDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void agregarTurnoReservado(String turno) {
        ContentValues values = new ContentValues();
        values.put(TurnoReservadoContract.TurnoReservadoEntry.COLUMN_TURNO, turno);
        database.insert(TurnoReservadoContract.TurnoReservadoEntry.TABLE_NAME, null, values);
    }

    public void eliminarTurnoReservado(String turno) {
        database.delete(TurnoReservadoContract.TurnoReservadoEntry.TABLE_NAME,
                TurnoReservadoContract.TurnoReservadoEntry.COLUMN_TURNO + " = ?",
                new String[]{turno});
    }

    public void actualizarTurnoReservado(String turnoExistente, String nuevoTurno) {
        ContentValues values = new ContentValues();
        values.put(TurnoReservadoContract.TurnoReservadoEntry.COLUMN_TURNO, nuevoTurno);

        // Actualizar el turno en la base de datos
        database.update(
                TurnoReservadoContract.TurnoReservadoEntry.TABLE_NAME,
                values,
                TurnoReservadoContract.TurnoReservadoEntry.COLUMN_TURNO + " = ?",
                new String[]{turnoExistente}
        );
    }


}
package com.example.tortupadel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TurnoReservadoData {

    private SQLiteDatabase database;
    private TurnosDatabaseHelper dbHelper;

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
        // Eliminar el turno de la base de datos
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


    // Método para obtener todos los turnos reservados
    public List<String> obtenerTodosLosTurnosReservados() {
        List<String> turnosReservados = new ArrayList<>();
        Cursor cursor = database.query(
                TurnoReservadoContract.TurnoReservadoEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            @SuppressLint("Range") String turno = cursor.getString(cursor.getColumnIndex(TurnoReservadoContract.TurnoReservadoEntry.COLUMN_TURNO));
            turnosReservados.add(turno);
            cursor.moveToNext();
        }
        cursor.close();
        return turnosReservados;
    }


}
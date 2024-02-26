package com.example.tortupadel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
            String turno = cursor.getString(cursor.getColumnIndex(TurnoReservadoContract.TurnoReservadoEntry.COLUMN_TURNO));
            turnosReservados.add(turno);
            cursor.moveToNext();
        }
        cursor.close();
        return turnosReservados;
    }

    // Método para obtener los turnos reservados para una fecha específica
    public List<String> obtenerTurnosReservadosParaFecha(int dayOfMonth, int month, int year) {
        // Este método se puede implementar de manera similar al obtenerTodosLosTurnosReservados
        // utilizando una consulta SQL adecuada para obtener los turnos reservados para la fecha dada
        // Dependiendo de la estructura de tu base de datos, puedes ajustar la consulta.
        return new ArrayList<>(); // Por ahora, devolvemos una lista vacía
    }

    // Método para actualizar un turno reservado existente
    public void actualizarTurnoReservado(int id, String nuevoTurno) {
        ContentValues values = new ContentValues();
        values.put(TurnoReservadoContract.TurnoReservadoEntry.COLUMN_TURNO, nuevoTurno);
        database.update(TurnoReservadoContract.TurnoReservadoEntry.TABLE_NAME,
                values,
                TurnoReservadoContract.TurnoReservadoEntry._ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}

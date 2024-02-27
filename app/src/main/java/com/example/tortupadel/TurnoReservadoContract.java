package com.example.tortupadel;

import android.provider.BaseColumns;

public class TurnoReservadoContract {

    // Constructor privado para evitar instanciación
    private TurnoReservadoContract() {}

    // Definir la estructura de la tabla de turnos reservados
    public static class TurnoReservadoEntry implements BaseColumns {
        public static final String TABLE_NAME = "turnos_reservados";
        public static final String COLUMN_TURNO = "turno";
    }


}

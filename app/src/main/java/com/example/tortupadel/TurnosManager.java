package com.example.tortupadel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TurnosManager {
    private static TurnosManager instance;
    private List<String> turnosReservados;
    private List<String> turnosDisponibles;

    private TurnosManager() {
        turnosReservados = new ArrayList<>();
        // Inicializar la lista de turnos disponibles
        turnosDisponibles = new ArrayList<>();
        turnosDisponibles.add("Turno 15:30hs");
        turnosDisponibles.add("Turno 17:00hs");
        turnosDisponibles.add("Turno 18:30hs");
        turnosDisponibles.add("Turno 20:00hs");
        turnosDisponibles.add("Turno 21:30hs");
    }

    public static synchronized TurnosManager getInstance() {
        if (instance == null) {
            instance = new TurnosManager();
        }
        return instance;
    }

    public List<String> getTurnosReservados() {
        return turnosReservados;
    }

    public void agregarTurnoReservado(String turno) {
        turnosReservados.add(turno);
    }


    // Método para obtener los turnos disponibles
    public List<String> getTurnosDisponibles() {
        return turnosDisponibles;
    }


    // Método para obtener los turnos disponibles del día actual
    public List<String> getTurnosDisponiblesDelDia( String fechaSeleccionada) {
        List<String> turnosDisponibles = new ArrayList<>();
        // Suponiendo que los turnos disponibles son para hoy
        // Puedes ajustar esta lógica según tus necesidades específicas
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String fechaActual = dateFormat.format(calendar.getTime());

        // Ejemplo de turnos disponibles
        turnosDisponibles.add("Turno 15:30hs - " + fechaActual);
        turnosDisponibles.add("Turno 17:00hs - " + fechaActual);
        turnosDisponibles.add("Turno 18:30hs - " + fechaActual);
        turnosDisponibles.add("Turno 20:00hs - " + fechaActual);
        turnosDisponibles.add("Turno 21:30hs - " + fechaActual);

        return turnosDisponibles;
    }


}
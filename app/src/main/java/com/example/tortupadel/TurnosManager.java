package com.example.tortupadel;

import java.util.ArrayList;
import java.util.List;

public class TurnosManager {
    private static TurnosManager instance;
    private List<String> turnosReservados;

    private TurnosManager() {
        turnosReservados = new ArrayList<>();
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

    public void eliminarTurnoReservado(String turno) {
        turnosReservados.remove(turno);
    }
}
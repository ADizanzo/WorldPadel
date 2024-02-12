package com.example.tortupadel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservasActivity extends AppCompatActivity {
    CalendarView calendarView;
    Calendar calendar;
    ListView listView;
    ArrayAdapter<String> adapter;
    List<String> turnosDisponibles;
    List<String> turnosReservados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        // Obtén la referencia al ImageView del icono de retroceso
        ImageView backIconImageView = findViewById(R.id.backIcon);

        // Establece un OnClickListener para el icono de retroceso
        backIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono de retroceso, finaliza la actividad actual
                finish();
            }
        });

        // Obtén la referencia al ImageView del icono home
        ImageView homeIconImageView = findViewById(R.id.home);

        // Establece un OnClickListener para el icono home
        homeIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono home, inicia la HomeActivity
                Intent intent = new Intent(ReservasActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Obtén la referencia al ImageView del icono Mis Reservas
        ImageView reservasIconImage = findViewById(R.id.mis_reservas);

        // Establece un OnClickListener para el icono reservas
        reservasIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono reservas, inicia MisReservasActivity
                Intent intent = new Intent(ReservasActivity.this, MisReservasActivity.class);
                startActivity(intent);
            }
        });

        // Obtén la referencia al ImageView del icono usuarios
        ImageView userIconImage = findViewById(R.id.user);

        // Establece un OnClickListener para el icono usuarios
        userIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono usuarios, inicia la UserActivity
                Intent intent = new Intent(ReservasActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        calendarView = findViewById(R.id.calenderView);
        calendar = Calendar.getInstance();

        // Inicializa el ListView y el ArrayAdapter
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        // Establece un listener para cuando se seleccione una fecha en el calendario
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                // Mostrar los turnos disponibles para la fecha seleccionada
                mostrarTurnosDisponibles(year, month, dayOfMonth);
            }
        });

        // Inicializa la lista de turnos disponibles y reservados
        turnosDisponibles = new ArrayList<>();
        turnosDisponibles.add("Turno 17:00hs");
        turnosDisponibles.add("Turno 18:30hs");
        turnosDisponibles.add("Turno 20:00hs");
        turnosDisponibles.add("Turno 21:30hs");

        turnosReservados = new ArrayList<>();

        // Establece un listener para cuando se hace clic en un turno disponible
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarDialogoReservaTurno(adapter.getItem(position));
            }
        });

        // En la inicialización de la actividad
        TurnosManager turnosManager = TurnosManager.getInstance();
        // Obtener la lista de turnos reservados
        turnosReservados = turnosManager.getTurnosReservados();
    }

    private void mostrarTurnosDisponibles(int year, int month, int dayOfMonth) {
        // Lógica para mostrar los turnos disponibles en el ListView
        // Aquí puedes obtener los datos de los turnos disponibles para la fecha seleccionada
        // y agregarlos al ArrayAdapter para que se muestren en el ListView
        // Por ahora, simplemente mostramos los turnos disponibles que tenemos en la lista
        adapter.clear();
        adapter.addAll(turnosDisponibles);
    }

    private void mostrarDialogoReservaTurno(final String turno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Reservar ahora?")
                .setMessage("¿Desea reservar \"" + turno + "\"?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Mostrar mensaje de reserva exitosa
                        Toast.makeText(ReservasActivity.this, "Turno Reservado", Toast.LENGTH_SHORT).show();
                        // Agregar el turno a la lista de turnos reservados
                        String turnoReservado = turno + " - " + obtenerFechaSeleccionada();
                        TurnosManager turnosManager = TurnosManager.getInstance();
                        turnosManager.agregarTurnoReservado(turnoReservado);
                        // Eliminar el turno de la lista de turnos disponibles
                        turnosDisponibles.remove(turno);
                        // Actualizar la lista de turnos disponibles en el ListView
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // No hacer nada
                    }
                })
                .show();
    }

    // Método para obtener la fecha seleccionada en el calendario
    private String obtenerFechaSeleccionada() {
        long selectedDateMillis = calendarView.getDate();
        Calendar selectedDateCalendar = Calendar.getInstance();
        selectedDateCalendar.setTimeInMillis(selectedDateMillis);
        int year = selectedDateCalendar.get(Calendar.YEAR);
        int month = selectedDateCalendar.get(Calendar.MONTH);
        int dayOfMonth = selectedDateCalendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month + 1) + "-" + dayOfMonth;
    }
}

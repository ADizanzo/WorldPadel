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
import java.util.Locale;

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
        ImageView misReservasIconImage = findViewById(R.id.mis_reservas);

        // Establece un OnClickListener para el icono reservas
        misReservasIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservasActivity.this, MisReservasActivity.class);
                startActivity(intent);
            }
        });

        // Obtén la referencia al ImageView del icono torneos
        ImageView torneoIconImage = findViewById(R.id.torneo);

        // Establece un OnClickListener para el icono torneos
        torneoIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservasActivity.this, TorneosActivity.class);
                startActivity(intent);
            }
        });

        // Obtén la referencia al ImageView del icono usuarios
        ImageView userIconImage = findViewById(R.id.user);

        // Establece un OnClickListener para el icono usuarios
        userIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int dayOfMonth, int month, int year) {
                // Actualizar el objeto calendar con la fecha seleccionada
                calendar.set(dayOfMonth, month, year);

                // Mostrar los turnos disponibles para la fecha seleccionada
                mostrarTurnosDisponibles(dayOfMonth, month, year);
            }

        });

        // Inicializa la lista de turnos disponibles y reservados
        turnosDisponibles = new ArrayList<>();
        turnosDisponibles.add("Turno 15:30hs");
        turnosDisponibles.add("Turno 17:00hs");
        turnosDisponibles.add("Turno 18:30hs");
        turnosDisponibles.add("Turno 20:00hs");
        turnosDisponibles.add("Turno 21:30hs");

        turnosReservados = new ArrayList<>();

        // Establece un listener para cuando se hace clic en un turno disponible
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fechaSeleccionada = obtenerFechaSeleccionada();
                mostrarDialogoReservaTurno(adapter.getItem(position), obtenerFechaSeleccionada());
            }
        });

        // En la inicialización de la actividad
        TurnosManager turnosManager = TurnosManager.getInstance();
        // Obtener la lista de turnos reservados
        turnosReservados = turnosManager.getTurnosReservados();
    }

    private void mostrarTurnosDisponibles(int dayOfMonth, int month, int year) {
        // Obtener la fecha actual
        Calendar fechaActual = Calendar.getInstance();

        // Crear un objeto Calendar para la fecha seleccionada
        Calendar fechaSeleccionada = Calendar.getInstance();
        fechaSeleccionada.set(dayOfMonth, month, year);

        // Verificar si la fecha seleccionada es igual o posterior a la fecha actual
        if (fechaSeleccionada.compareTo(fechaActual) >= 0) {
            // La fecha seleccionada es igual o posterior a la fecha actual
            // Mostrar los turnos disponibles en el ListView
            // Aquí puedes obtener los datos de los turnos disponibles para la fecha seleccionada
            // y agregarlos al ArrayAdapter para que se muestren en el ListView
            // Por ahora, simplemente mostramos los turnos disponibles que tenemos en la lista
            adapter.clear();
            adapter.addAll(turnosDisponibles);
        } else {
            // La fecha seleccionada es anterior a la fecha actual
            // No hay turnos disponibles para esta fecha
            Toast.makeText(this, "No hay turnos disponibles", Toast.LENGTH_SHORT).show();
            adapter.clear();
        }
    }


    private void mostrarDialogoReservaTurno(final String turno, final String fechaSeleccionada) {
        // Verificar si el turno está reservado para la fecha seleccionada
        if (turnosReservados.contains(turno + " - " + fechaSeleccionada)) {
            // Si el turno ya está reservado, mostrar un mensaje
            Toast.makeText(this, "Este turno ya está reservado.", Toast.LENGTH_SHORT).show();
        } else {
            // Si el turno no está reservado, permitir la reserva
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¿Reservar ahora?")
                    .setMessage("¿Desea reservar \"" + turno + "\" para el " + fechaSeleccionada + "?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Mostrar mensaje de reserva exitosa
                            Toast.makeText(ReservasActivity.this, "Turno Reservado", Toast.LENGTH_SHORT).show();

                            // Agregar el turno y la fecha a la lista de turnos reservados
                            String turnoReservado = turno + " - " + fechaSeleccionada;
                            TurnosManager turnosManager = TurnosManager.getInstance();
                            turnosManager.agregarTurnoReservado(turnoReservado);

                            // Agregar el turno a la base de datos
                            TurnoReservadoData dataSource = new TurnoReservadoData(ReservasActivity.this);
                            dataSource.open();
                            dataSource.agregarTurnoReservado(turnoReservado);
                            dataSource.close();

                            // Eliminar el turno de la lista de turnos disponibles
                            turnosDisponibles.remove(turno);

                            // Actualizar la lista de turnos disponibles en el ListView
                            adapter.remove(turno);
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
    }


    @NonNull
    private List<String> obtenerTodosLosTurnosReservados(String fechaSeleccionada) {
        // Lista para almacenar los turnos reservados para la fecha seleccionada
        List<String> turnosReservados = new ArrayList<>();

        // Crear una instancia de la clase TurnoReservadoData para acceder a la base de datos
        TurnoReservadoData dataSource = new TurnoReservadoData(this);
        dataSource.open();

        // Obtener todos los turnos reservados de la base de datos para la fecha seleccionada
        List<String> turnosReservadosDB = dataSource.obtenerTodosLosTurnosReservados();

        // Cerrar la conexión con la base de datos
        dataSource.close();

        // Filtrar los turnos reservados para la fecha seleccionada
        for (String turnoReservado : turnosReservadosDB) {
            // Comprobar si el turno reservado contiene la fecha seleccionada
            if (turnoReservado.contains(fechaSeleccionada)) {
                turnosReservados.add(turnoReservado);
            }
        }

        return turnosReservados;
    }


    // Método para obtener la fecha seleccionada en el calendario
    private String obtenerFechaSeleccionada() {
        // Obtener el año, mes y día del mes de la fecha seleccionada del objeto calendar
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Sumar 1 al mes (enero es 0)
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Formatear la fecha seleccionada en el formato deseado
        return String.format(Locale.getDefault(), "%02d-%02d-%04d", dayOfMonth, month, year);
    }


}
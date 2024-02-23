package com.example.tortupadel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.List;

public class MisReservasActivity extends AppCompatActivity {
    ListView listView;
    TurnosAdapter adapter;
    List<String> turnosReservados;
    private static final int EDITAR_TURNO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_reservas);

        listView = findViewById(R.id.listViewMisReservas);

        // Obtener la lista de turnos reservados
        TurnosManager turnosManager = TurnosManager.getInstance();
        turnosReservados = turnosManager.getTurnosReservados();

        // Inicializar el adaptador con la lista de turnos reservados
        adapter = new TurnosAdapter(this, turnosReservados);

        // Establecer el adaptador en la ListView
        listView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TurnosAdapter.OnItemClickListener() {
            @Override
            public void onModificarClick(int position) {
                // Obtener el turno seleccionado
                String turnoSeleccionado = turnosReservados.get(position);
                // Iniciar la actividad para editar el turno
                Intent intent = new Intent(MisReservasActivity.this, EditarTurnoActivity.class);
                intent.putExtra("turno", turnoSeleccionado);
                intent.putExtra("posicion", position); // Pasar la posición del turno
                startActivityForResult(intent, EDITAR_TURNO_REQUEST);
            }

            @Override
            public void onEliminarClick(int position) {
                eliminarTurno(position);
            }
        });

        // Obtén la referencia al ImageView del icono de retroceso
        ImageView backIconImageView = findViewById(R.id.backIcon);
        // Establece un OnClickListener para el icono de retroceso
        backIconImageView.setOnClickListener(v -> finish());

        // Obtén la referencia al ImageView del icono de añadir
        ImageView addIconImageView = findViewById(R.id.addIcon);
        // Establece un OnClickListener para el icono de añadir
        addIconImageView.setOnClickListener(v -> {
            Intent intent = new Intent(MisReservasActivity.this, ReservasActivity.class);
            startActivity(intent);
        });

        // Obtén la referencia al ImageView del icono home
        ImageView homeIconImageView = findViewById(R.id.home);

        // Establece un OnClickListener para el icono home
        homeIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono home, inicia la HomeActivity
                Intent intent = new Intent(MisReservasActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        // Obtén la referencia al ImageView del icono Mis Reservas
        ImageView misReservasIconImage = findViewById(R.id.mis_reservas);

        // Establece un OnClickListener para el icono reservas
        misReservasIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono reservas, inicia MisReservasActivity
                Intent intent = new Intent(MisReservasActivity.this, MisReservasActivity.class);
                startActivity(intent);
            }
        });


        // Obtén la referencia al ImageView del icono torneos
        ImageView torneoIconImage = findViewById(R.id.torneo);

        // Establece un OnClickListener para el icono torneos
        torneoIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MisReservasActivity.this, TorneosActivity.class);
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
                Intent intent = new Intent(MisReservasActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDITAR_TURNO_REQUEST && resultCode == RESULT_OK) {
            // Obtener la posición del turno modificado
            int position = data.getIntExtra("posicion", -1);
            if (position != -1) {
                // Actualizar la lista de turnos reservados con el nuevo turno
                String nuevoTurno = data.getStringExtra("nuevoTurno");
                turnosReservados.set(position, nuevoTurno);
                // Notificar al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void eliminarTurno(int position) {
        // Eliminar el turno de la base de datos
        TurnoReservadoDataSource dataSource = new TurnoReservadoDataSource(MisReservasActivity.this);
        try {
            dataSource.open();
            dataSource.eliminarTurnoReservado(adapter.getItem(position));
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción
        }

        // Eliminar el turno de la lista
        turnosReservados.remove(position);

        // Notificar al adaptador que los datos han cambiado
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Turno dado de baja", Toast.LENGTH_SHORT).show();
    }
}

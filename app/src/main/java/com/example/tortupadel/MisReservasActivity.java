package com.example.tortupadel;

import android.content.Intent;
import android.os.Bundle;
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
                // Lógica para modificar el turno
                // Puedes implementar lo que necesites aquí, como abrir una actividad de edición del turno
                // Por ejemplo:
                // Intent intent = new Intent(MisReservasActivity.this, EditarTurnoActivity.class);
                // intent.putExtra("turno", turnosReservados.get(position));
                // startActivity(intent);
                Toast.makeText(MisReservasActivity.this, "Modificar turno en posición: " + position, Toast.LENGTH_SHORT).show();
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


        // Obtén la referencia al ImageView del icono home
        ImageView homeIconImageView = findViewById(R.id.home);
        // Establece un OnClickListener para el icono home
        homeIconImageView.setOnClickListener(v -> {
            // Cuando se hace clic en el icono home, inicia la HomeActivity
            Intent intent = new Intent(MisReservasActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // Obtén la referencia al ImageView del icono Mis Reservas
        ImageView misReservasIconImage = findViewById(R.id.mis_reservas);
        // Establece un OnClickListener para el icono reservas
        misReservasIconImage.setOnClickListener(v ->
                Toast.makeText(MisReservasActivity.this, "Mis Reservas", Toast.LENGTH_SHORT).show()
        );

        // Obtén la referencia al ImageView del icono usuarios
        ImageView userIconImage = findViewById(R.id.user);
        // Establece un OnClickListener para el icono usuarios
        userIconImage.setOnClickListener(v -> {
            // Cuando se hace clic en el icono usuarios, inicia la UserActivity
            Intent intent = new Intent(MisReservasActivity.this, UserActivity.class);
            startActivity(intent);
        });
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

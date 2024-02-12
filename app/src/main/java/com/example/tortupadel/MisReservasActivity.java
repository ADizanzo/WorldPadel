package com.example.tortupadel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.List;

public class MisReservasActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, turnosReservados);

        // Establecer el adaptador en la ListView
        listView.setAdapter(adapter);

        // Configurar el OnClickListener para el botón "Dar de Baja"
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MisReservasActivity.this);
                builder.setTitle("¿Dar de Baja?")
                        .setMessage("¿Desea dar de baja este turno?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Eliminar el turno de la base de datos y de la lista
                                eliminarTurno(position);
                                // Agregar este bloque
                                Intent intent = new Intent();
                                intent.putExtra("turno_dado_de_baja", adapter.getItem(position));
                                setResult(RESULT_OK, intent);
                                finish();
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
        });

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
                // Aquí podrías implementar alguna lógica adicional, como actualizar la lista de reservas
                Toast.makeText(MisReservasActivity.this, "Mis Reservas", Toast.LENGTH_SHORT).show();
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

    private void eliminarTurno(int position) {
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

package com.example.tortupadel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                Intent intent = new Intent(MisReservasActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });


    }
}
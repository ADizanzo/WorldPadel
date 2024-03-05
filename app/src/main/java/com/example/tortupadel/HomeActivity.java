package com.example.tortupadel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Obtén la referencia al ImageView del botón RESERVAR AHORA
        Button reservaButton = findViewById(R.id.buttonReservar);

        // Establece un OnClickListener para el botón RESERVAR AHORA
        reservaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el botón RESERVAR AHORA, inicia ReservasActivity
                Intent intent = new Intent(HomeActivity.this, ReservasActivity.class);
                startActivity(intent);
            }
        });


        // Obtén la referencia al ImageView del icono home
        ImageView homeIconImageView = findViewById(R.id.home);

        // Establece un OnClickListener para el icono home
        homeIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono home, inicia la HomeActivity
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(HomeActivity.this, MisReservasActivity.class);
                startActivity(intent);
            }
        });


        // Obtén la referencia al ImageView del icono torneos
        ImageView torneoIconImage = findViewById(R.id.torneo);

        // Establece un OnClickListener para el icono torneos
        torneoIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TorneosActivity.class);
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
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });


    }

}
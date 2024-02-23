package com.example.tortupadel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TorneoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo_detail);


        // Obtén los datos del Intent
        Intent intent = getIntent();
        String torneoTitle = intent.getStringExtra("torneoTitle");
        int torneoImage = intent.getIntExtra("torneoImage", 0); // 0 es el valor predeterminado si no se encuentra la clave
        String torneoDescription = intent.getStringExtra("torneoDescription");

        // Mostrar los datos en los elementos de la interfaz de usuario
        ImageView torneoImageView = findViewById(R.id.torneoImage);
        torneoImageView.setImageResource(torneoImage);

        TextView torneoTitleTextView = findViewById(R.id.torneoTitle);
        torneoTitleTextView.setText(torneoTitle);

        TextView torneoDescriptionTextView = findViewById(R.id.torneoDescription);
        torneoDescriptionTextView.setText(torneoDescription);


        // Obtén la referencia al ImageView del icono de retroceso
        ImageView backIconImageView = findViewById(R.id.backIcon);
        // Establece un OnClickListener para el icono de retroceso
        backIconImageView.setOnClickListener(v -> finish());



        // Obtén la referencia al ImageView del icono home
        ImageView homeIconImageView = findViewById(R.id.home);

        // Establece un OnClickListener para el icono home
        homeIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono home, inicia la HomeActivity
                Intent intent = new Intent(TorneoDetailActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(TorneoDetailActivity.this, MisReservasActivity.class);
                startActivity(intent);
            }
        });


        // Obtén la referencia al ImageView del icono torneos
        ImageView torneoIconImage = findViewById(R.id.torneo);

        // Establece un OnClickListener para el icono torneos
        torneoIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TorneoDetailActivity.this, TorneosActivity.class);
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
                Intent intent = new Intent(TorneoDetailActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });


    }




}
package com.example.tortupadel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TorneosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneos);

        // Obtén la referencia al ImageView del icono de retroceso
        ImageView backIconImageView = findViewById(R.id.backIcon);
        // Establece un OnClickListener para el icono de retroceso
        backIconImageView.setOnClickListener(v -> finish());



        // Obtén la referencia al ImageView del icono "Más" en las tarjetas
        ImageView addIconImageView1 = findViewById(R.id.add_icon1);
        ImageView addIconImageView2 = findViewById(R.id.add_icon2);
        ImageView addIconImageView3 = findViewById(R.id.add_icon3);
        ImageView addIconImageView4 = findViewById(R.id.add_icon4);


        // Establece un OnClickListener para el icono "Más" en la tarjeta 1
        addIconImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar TorneoDetailActivity
                Intent intent = new Intent(TorneosActivity.this, TorneoDetailActivity.class);
                // Pasa los datos relevantes a través del Intent
                intent.putExtra("torneoTitle", getString(R.string.torneo_rosario));
                intent.putExtra("torneoImage", R.drawable.ros);
                intent.putExtra("torneoDescription", getString(R.string.rosario_description));
                startActivity(intent);
            }
        });


        // Establece un OnClickListener para el icono "Más" en la tarjeta 2
        addIconImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar TorneoDetailActivity
                Intent intent = new Intent(TorneosActivity.this, TorneoDetailActivity.class);
                // Pasa los datos relevantes a través del Intent
                intent.putExtra("torneoTitle", getString(R.string.torneo_mendoza));
                intent.putExtra("torneoImage", R.drawable.mza);
                intent.putExtra("torneoDescription", getString(R.string.mza_description));
                startActivity(intent);
            }
        });

        // Establece un OnClickListener para el icono "Más" en la tarjeta 3
        addIconImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar TorneoDetailActivity
                Intent intent = new Intent(TorneosActivity.this, TorneoDetailActivity.class);
                // Pasa los datos relevantes a través del Intent
                intent.putExtra("torneoTitle", getString(R.string.torneo_bsas));
                intent.putExtra("torneoImage", R.drawable.bsas);
                intent.putExtra("torneoDescription", getString(R.string.bsas_description));
                startActivity(intent);
            }
        });


        // Establece un OnClickListener para el icono "Más" en la tarjeta 4
        addIconImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar TorneoDetailActivity
                Intent intent = new Intent(TorneosActivity.this, TorneoDetailActivity.class);
                // Pasa los datos relevantes a través del Intent
                intent.putExtra("torneoTitle", getString(R.string.torneo_cordoba));
                intent.putExtra("torneoImage", R.drawable.cba);
                intent.putExtra("torneoDescription", getString(R.string.cba_description));
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
                Intent intent = new Intent(TorneosActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(TorneosActivity.this, MisReservasActivity.class);
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
                Intent intent = new Intent(TorneosActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

    }





}
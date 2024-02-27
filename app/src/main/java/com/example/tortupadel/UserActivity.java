package com.example.tortupadel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

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
                Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Obtén la referencia al ImageView del icono reservas
        ImageView misReservasIconImage = findViewById(R.id.mis_reservas);

        // Establece un OnClickListener para el icono reservas
        misReservasIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono reservas, inicia MisReservasActivity
                Intent intent = new Intent(UserActivity.this, MisReservasActivity.class);
                startActivity(intent);
            }
        });

        // Obtén la referencia al ImageView del icono torneos
        ImageView torneoIconImage = findViewById(R.id.torneo);

        // Establece un OnClickListener para el icono torneos
        torneoIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, TorneosActivity.class);
                startActivity(intent);
            }
        });

        // Obtén la referencia al ImageView del icono usuarios
        ImageView userIconImage = findViewById(R.id.user);

        // Establece un OnClickListener para el icono usuarios
        userIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el icono usuarios, inicia UserActivity
                Intent intent = new Intent(UserActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });



        // Obtén la referencia al EditText de disponibilidad
        final EditText disponibilidadEditText = findViewById(R.id.disponibilidadEditText);

        // Establece un OnClickListener para el EditText de disponibilidad
        disponibilidadEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra un cuadro de diálogo con las opciones de disponibilidad
                showDisponibilidadDialog();
            }
        });

        // Obtén la referencia al EditText de categoría
        final EditText categoriaEditText = findViewById(R.id.categoriaEditText);

        // Establece un OnClickListener para el EditText de categoría
        categoriaEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra un cuadro de diálogo con las opciones de categoría
                showCategoriaDialog();
            }
        });
    }

    // Método para mostrar el cuadro de diálogo de disponibilidad
    private void showDisponibilidadDialog() {
        final String[] opcionesDisponibilidad = {"Mañana", "Mañana/Tarde", "Tarde", "Tarde/Noche", "Noche"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona Disponibilidad");
        builder.setItems(opcionesDisponibilidad, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Establecer el texto seleccionado en el EditText de disponibilidad
                EditText disponibilidadEditText = findViewById(R.id.disponibilidadEditText);
                disponibilidadEditText.setText(opcionesDisponibilidad[which]);

                // Cerrar el cuadro de diálogo
                dialog.dismiss();
            }
        });
        builder.show();
    }


    // Método para mostrar el cuadro de diálogo de categoría
    private void showCategoriaDialog() {
        final String[] opcionesCategoria = {"8va", "7ma", "6ta", "5ta", "4ta", "3ra", "2da", "1ra"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona Categoría");
        builder.setItems(opcionesCategoria, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Establecer el texto seleccionado en el EditText de categoría
                EditText categoriaEditText = findViewById(R.id.categoriaEditText);
                categoriaEditText.setText(opcionesCategoria[which]);

                // Cerrar el cuadro de diálogo
                dialog.dismiss();
            }
        });
        builder.show();


    }



}
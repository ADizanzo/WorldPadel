package com.example.tortupadel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class TorneoDetailActivity extends AppCompatActivity {

    private int[] torneoImages; // Arreglo de recursos de imágenes
    private int currentImageIndex = 0; // Índice de la imagen actual
    private int torneoVideo; // Recurso del video del torneo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo_detail);

        // Obtén los datos del Intent
        Intent intent = getIntent();
        String torneoTitle = intent.getStringExtra("torneoTitle");
        torneoImages = intent.getIntArrayExtra("torneoImages");
        String torneoDescription = intent.getStringExtra("torneoDescription");
        torneoVideo = intent.getIntExtra("torneoVideo", 0);

        // Mostrar los datos iniciales en los elementos de la interfaz de usuario
        ImageView torneoImageView = findViewById(R.id.torneoImage);
        torneoImageView.setImageResource(torneoImages[currentImageIndex]);

        TextView torneoTitleTextView = findViewById(R.id.torneoTitle);
        torneoTitleTextView.setText(torneoTitle);

        TextView torneoDescriptionTextView = findViewById(R.id.torneoDescription);
        torneoDescriptionTextView.setText(torneoDescription);

        // Inicializa las ImageView de las flechas de navegación
        ImageView arrowLeft = findViewById(R.id.arrowLeft);
        ImageView arrowRight = findViewById(R.id.arrowRight);

        // Configura el OnClickListener para la flecha izquierda
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica si hay una imagen anterior disponible
                if (currentImageIndex > 0) {
                    currentImageIndex--; // Disminuye el índice para mostrar la imagen anterior
                    updateTorneoImage(); // Actualiza la imagen mostrada
                }
            }
        });

        // Configura el OnClickListener para la flecha derecha
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica si hay una siguiente imagen disponible
                if (currentImageIndex < torneoImages.length - 1) {
                    currentImageIndex++; // Aumenta el índice para mostrar la siguiente imagen
                    updateTorneoImage(); // Actualiza la imagen mostrada
                }
            }
        });


        VideoView mivideo=findViewById(R.id.videoView);

        String videoPath="android.resource://" + getPackageName() + "/" + R.raw.videoros;
        Uri uri=Uri.parse(videoPath);
        mivideo.setVideoURI(uri);

        MediaController mediaController=new MediaController(this);
        mivideo.setMediaController(mediaController);
        mediaController.setAnchorView(mivideo);



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
                Intent intent = new Intent(TorneoDetailActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

    }


    // Método para actualizar la imagen o el video mostrado
    private void updateTorneoImage() {
        // Obtén la referencia al ImageView de la imagen del torneo
        ImageView torneoImageView = findViewById(R.id.torneoImage);

        // Verifica si la imagen actual es la última
        if (currentImageIndex == torneoImages.length - 1) {
            // Si es la última, oculta la ImageView y muestra el VideoView
            torneoImageView.setVisibility(View.GONE);

            // Obtén la referencia al VideoView
            VideoView videoView = findViewById(R.id.videoView);
            videoView.setVisibility(View.VISIBLE);

            // Determina el recurso de video correspondiente al índice actual
            int videoResource = torneoVideo;

            // Configura el URI del video
            String videoPath = "android.resource://" + getPackageName() + "/" + videoResource;
            Uri videoUri = Uri.parse(videoPath);

            // Configura el video para el VideoView
            videoView.setVideoURI(videoUri);
            videoView.start();
        } else {
            // Si no es la última, la ImageView está visible y el VideoView está oculto
            torneoImageView.setVisibility(View.VISIBLE);

            // Obtén la referencia al VideoView y asegúrate de que esté oculto
            VideoView videoView = findViewById(R.id.videoView);
            videoView.setVisibility(View.GONE);

            // Establece la imagen correspondiente al índice actual
            torneoImageView.setImageResource(torneoImages[currentImageIndex]);
        }
    }


    // Método para obtener el recurso de video correspondiente al índice



}
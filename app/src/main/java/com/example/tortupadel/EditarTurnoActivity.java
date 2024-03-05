package com.example.tortupadel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EditarTurnoActivity extends AppCompatActivity {
    private EditText editTextTurno;
    private List<String> turnosDisponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_turno);


        editTextTurno = findViewById(R.id.editTextTurno);
        Button buttonGuardar = findViewById(R.id.buttonGuardar);

        // Obtener el turno a editar
        Intent intent = getIntent();
        String turno = intent.getStringExtra("turno");


        // Mostrar el turno en el EditText
        editTextTurno.setText(turno);

        // Deshabilitar la edición del EditText
        editTextTurno.setFocusable(false);
        editTextTurno.setClickable(true);

        // Obtener los turnos disponibles para la fecha seleccionada
        turnosDisponibles = TurnosManager.getInstance().getTurnosDisponiblesDelDia();

        // Configurar el OnClickListener para el EditText para mostrar los turnos disponibles
        editTextTurno.setOnClickListener(v -> mostrarTurnosDisponibles());

        buttonGuardar.setOnClickListener(v -> guardarCambios());
    }

    private void mostrarTurnosDisponibles() {
        // Crear un array de strings con los turnos disponibles
        String[] arrayTurnos = turnosDisponibles.toArray(new String[0]);

        // Crear un cuadro de diálogo para mostrar los turnos disponibles
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Turnos Disponibles")
                .setItems(arrayTurnos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Obtener el turno seleccionado y mostrarlo en el EditText
                        editTextTurno.setText(turnosDisponibles.get(which));
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void guardarCambios() {
        // Obtener el nuevo turno editado
        String nuevoTurno = editTextTurno.getText().toString();

        // Si el nuevo turno no está vacío, enviarlo de vuelta a la actividad anterior
        if (!nuevoTurno.isEmpty()) {
            // Obtener el turno existente que se está editando
            String turnoExistente = getIntent().getStringExtra("turno");

            // Crear un nuevo intent para enviar los datos de vuelta
            Intent resultadoIntent = new Intent();
            resultadoIntent.putExtra("nuevoTurno", nuevoTurno);
            resultadoIntent.putExtra("posicion", getIntent().getIntExtra("posicion", -1));
            setResult(RESULT_OK, resultadoIntent);

            // Llamar al método actualizarTurnoReservado para actualizar el turno en la base de datos
            TurnoReservadoData dataSource = new TurnoReservadoData(EditarTurnoActivity.this);
            dataSource.open();
            dataSource.actualizarTurnoReservado(turnoExistente, nuevoTurno);
            dataSource.close();

            finish();
        } else {
            // Mostrar un mensaje de error si el campo está vacío
            editTextTurno.setError("Debe ingresar un turno");
        }
    }




}
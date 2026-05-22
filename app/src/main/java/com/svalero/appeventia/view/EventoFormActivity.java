package com.svalero.appeventia.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appeventia.R;
import com.svalero.appeventia.contract.EventoFormContract;
import com.svalero.appeventia.model.Artista;
import com.svalero.appeventia.model.Evento;
import com.svalero.appeventia.model.Recinto;
import com.svalero.appeventia.presenter.EventoFormPresenter;

import java.util.ArrayList;
import java.util.List;

public class EventoFormActivity extends AppCompatActivity implements EventoFormContract.View {

    private EventoFormContract.Presenter presenter;

    private EditText nombreInput;
    private EditText descripcionInput;
    private EditText fechaInput;
    private EditText horaInput;
    private EditText precioInput;
    private EditText aforoInput;
    private EditText entradasInput;
    private EditText categoriaInput;
    private CheckBox presencialCheckBox;
    private CheckBox canceladoCheckBox;
    private Spinner artistaSpinner;
    private Spinner recintoSpinner;

    private List<Artista> artistas = new ArrayList<>();
    private List<Recinto> recintos = new ArrayList<>();

    private long eventoId;
    private boolean modoEdicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_form);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter = new EventoFormPresenter(this);

        nombreInput = findViewById(R.id.nombreEventoInput);
        descripcionInput = findViewById(R.id.descripcionEventoInput);
        fechaInput = findViewById(R.id.fechaEventoInput);
        horaInput = findViewById(R.id.horaEventoInput);
        precioInput = findViewById(R.id.precioEventoInput);
        aforoInput = findViewById(R.id.aforoEventoInput);
        entradasInput = findViewById(R.id.entradasEventoInput);
        categoriaInput = findViewById(R.id.categoriaEventoInput);
        presencialCheckBox = findViewById(R.id.presencialCheckBox);
        canceladoCheckBox = findViewById(R.id.canceladoCheckBox);
        artistaSpinner = findViewById(R.id.artistaSpinner);
        recintoSpinner = findViewById(R.id.recintoSpinner);

        Button saveEventoButton = findViewById(R.id.saveEventoButton);

        cargarDatosEdicion();

        saveEventoButton.setOnClickListener(v -> guardarEvento());

        presenter.cargarDatosFormulario();
    }

    private void cargarDatosEdicion() {
        eventoId = getIntent().getLongExtra("id", 0);
        modoEdicion = eventoId != 0;

        if (modoEdicion) {
            nombreInput.setText(getIntent().getStringExtra("nombre"));
            descripcionInput.setText(getIntent().getStringExtra("descripcion"));
            fechaInput.setText(getIntent().getStringExtra("fecha"));
            horaInput.setText(getIntent().getStringExtra("hora"));
            precioInput.setText(String.valueOf(getIntent().getFloatExtra("precio", 0)));
            categoriaInput.setText(getIntent().getStringExtra("categoria"));

            aforoInput.setText("100");
            entradasInput.setText("100");
        }
    }

    private void guardarEvento() {
        String nombre = nombreInput.getText().toString();
        String descripcion = descripcionInput.getText().toString();
        String fecha = fechaInput.getText().toString();
        String hora = horaInput.getText().toString();
        String categoria = categoriaInput.getText().toString();

        float precio = Float.parseFloat(precioInput.getText().toString());
        int aforo = Integer.parseInt(aforoInput.getText().toString());
        int entradas = Integer.parseInt(entradasInput.getText().toString());

        Evento evento = new Evento(
                nombre,
                descripcion,
                fecha,
                hora,
                precio,
                aforo,
                entradas,
                canceladoCheckBox.isChecked(),
                presencialCheckBox.isChecked(),
                categoria
        );

        if (!artistas.isEmpty()) {
            evento.setArtista((Artista) artistaSpinner.getSelectedItem());
        }

        if (!recintos.isEmpty()) {
            evento.setRecinto((Recinto) recintoSpinner.getSelectedItem());
        }

        if (modoEdicion) {
            presenter.editarEvento(eventoId, evento);
        } else {
            presenter.crearEvento(evento);
        }
    }

    @Override
    public void mostrarArtistas(List<Artista> artistas) {
        this.artistas.clear();
        this.artistas.addAll(artistas);

        ArrayAdapter<Artista> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                this.artistas
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artistaSpinner.setAdapter(adapter);
    }

    @Override
    public void mostrarRecintos(List<Recinto> recintos) {
        this.recintos.clear();
        this.recintos.addAll(recintos);

        ArrayAdapter<Recinto> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                this.recintos
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recintoSpinner.setAdapter(adapter);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void volverAlListado() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
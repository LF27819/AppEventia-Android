package com.svalero.appeventia.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appeventia.R;
import com.svalero.appeventia.contract.ArtistaFormContract;
import com.svalero.appeventia.model.Artista;
import com.svalero.appeventia.presenter.ArtistaFormPresenter;

public class ArtistaFormActivity extends AppCompatActivity implements ArtistaFormContract.View {

    private EditText nombreArtisticoInput;
    private EditText nombreRealInput;
    private EditText generoMusicalInput;
    private EditText fechaNacimientoInput;
    private EditText cacheInput;
    private EditText eventosRealizadosInput;
    private CheckBox activoCheckbox;

    private ArtistaFormContract.Presenter presenter;

    private boolean editMode = false;
    private long artistaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artista_form);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter = new ArtistaFormPresenter(this);

        nombreArtisticoInput = findViewById(R.id.nombreArtisticoInput);
        nombreRealInput = findViewById(R.id.nombreRealInput);
        generoMusicalInput = findViewById(R.id.generoMusicalInput);
        fechaNacimientoInput = findViewById(R.id.fechaNacimientoInput);
        cacheInput = findViewById(R.id.cacheInput);
        eventosRealizadosInput = findViewById(R.id.eventosRealizadosInput);
        activoCheckbox = findViewById(R.id.activoCheckbox);

        Button guardarArtistaButton = findViewById(R.id.guardarArtistaButton);

        cargarDatosSiEsEdicion();

        guardarArtistaButton.setOnClickListener(v -> guardarArtista());
    }

    private void cargarDatosSiEsEdicion() {
        if (getIntent().hasExtra("id")) {
            editMode = true;

            artistaId = getIntent().getLongExtra("id", 0);

            nombreArtisticoInput.setText(getIntent().getStringExtra("nombreArtistico"));
            nombreRealInput.setText(getIntent().getStringExtra("nombreReal"));
            generoMusicalInput.setText(getIntent().getStringExtra("generoMusical"));
            fechaNacimientoInput.setText(getIntent().getStringExtra("fechaNacimiento"));
            cacheInput.setText(String.valueOf(getIntent().getFloatExtra("cache", 0)));
            eventosRealizadosInput.setText(String.valueOf(getIntent().getIntExtra("eventosRealizados", 0)));
            activoCheckbox.setChecked(getIntent().getBooleanExtra("activo", true));
        }
    }

    private void guardarArtista() {
        String nombreArtistico = nombreArtisticoInput.getText().toString().trim();
        String nombreReal = nombreRealInput.getText().toString().trim();
        String generoMusical = generoMusicalInput.getText().toString().trim();
        String fechaNacimiento = fechaNacimientoInput.getText().toString().trim();
        String cacheTexto = cacheInput.getText().toString().trim();
        String eventosTexto = eventosRealizadosInput.getText().toString().trim();

        if (nombreArtistico.isEmpty()) {
            mostrarError("El nombre artístico es obligatorio");
            return;
        }

        if (nombreReal.isEmpty()) {
            mostrarError("El nombre real es obligatorio");
            return;
        }

        if (fechaNacimiento.isEmpty()) {
            mostrarError("La fecha de nacimiento es obligatoria");
            return;
        }

        if (cacheTexto.isEmpty()) {
            mostrarError("El caché es obligatorio");
            return;
        }

        if (eventosTexto.isEmpty()) {
            mostrarError("Los eventos realizados son obligatorios");
            return;
        }

        float cache;
        int eventosRealizados;

        try {
            cache = Float.parseFloat(cacheTexto);
            eventosRealizados = Integer.parseInt(eventosTexto);
        } catch (NumberFormatException e) {
            mostrarError("Revisa los campos numéricos");
            return;
        }

        Artista artista = new Artista();
        artista.setNombreArtistico(nombreArtistico);
        artista.setNombreReal(nombreReal);
        artista.setGeneroMusical(generoMusical);
        artista.setFechaNacimiento(fechaNacimiento);
        artista.setCache(cache);
        artista.setEventosRealizados(eventosRealizados);
        artista.setActivo(activoCheckbox.isChecked());

        if (editMode) {
            presenter.editarArtista(artistaId, artista);
        } else {
            presenter.crearArtista(artista);
        }
    }

    @Override
    public void artistaGuardado() {
        Toast.makeText(this, "Artista guardado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
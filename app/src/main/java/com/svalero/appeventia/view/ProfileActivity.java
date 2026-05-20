package com.svalero.appeventia.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appeventia.R;
import com.svalero.appeventia.contract.ProfileContract;
import com.svalero.appeventia.presenter.ProfilePresenter;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {

    private ProfileContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter = new ProfilePresenter(this);

        CheckBox notificationsCheckBox = findViewById(R.id.notificationsCheckBox);
        Button saveProfileButton = findViewById(R.id.saveProfileButton);

        saveProfileButton.setOnClickListener(v -> {
            boolean notificacionesActivas = notificationsCheckBox.isChecked();
            presenter.guardarPreferencias(notificacionesActivas);
        });
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
package com.svalero.appeventia.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.svalero.appeventia.R;
import com.svalero.appeventia.contract.MapContract;
import com.svalero.appeventia.presenter.MapPresenter;

public class MapActivity extends AppCompatActivity implements MapContract.View {

    private MapView mapView;
    private MapContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mapView = findViewById(R.id.mapView);
        presenter = new MapPresenter(this);

        double latitud = getIntent().getDoubleExtra("latitud", 41.6488);
        double longitud = getIntent().getDoubleExtra("longitud", -0.8891);

        presenter.prepararMapa(latitud, longitud);
    }

    @Override
    public void mostrarMapa(Point punto) {
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, style -> {
            mapView.getMapboxMap().setCamera(
                    new CameraOptions.Builder()
                            .center(punto)
                            .zoom(13.0)
                            .build()
            );
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
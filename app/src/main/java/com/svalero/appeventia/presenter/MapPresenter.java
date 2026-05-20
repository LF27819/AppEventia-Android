package com.svalero.appeventia.presenter;

import com.mapbox.geojson.Point;
import com.svalero.appeventia.contract.MapContract;

public class MapPresenter implements MapContract.Presenter {

    private final MapContract.View view;

    public MapPresenter(MapContract.View view) {
        this.view = view;
    }

    @Override
    public void prepararMapa(double latitud, double longitud) {
        Point punto = Point.fromLngLat(longitud, latitud);
        view.mostrarMapa(punto);
    }
}
package com.svalero.appeventia.contract;

import com.mapbox.geojson.Point;

public interface MapContract {

    interface View {
        void mostrarMapa(Point punto);
    }

    interface Presenter {
        void prepararMapa(double latitud, double longitud);
    }
}
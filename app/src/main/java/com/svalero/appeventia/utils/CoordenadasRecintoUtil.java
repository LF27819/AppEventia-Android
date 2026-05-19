package com.svalero.appeventia.utils;

public class CoordenadasRecintoUtil {

    public static double[] obtenerCoordenadas(String recinto) {

        if (recinto == null) {
            return new double[]{41.6488, -0.8891};
        }

        String nombre = recinto.toLowerCase();

        if (nombre.contains("wizink")) {
            return new double[]{40.423889, -3.671944};
        }

        if (nombre.contains("palau sant jordi")) {
            return new double[]{41.363467, 2.152568};
        }

        if (nombre.contains("ciudad de las artes")) {
            return new double[]{39.455300, -0.352400};
        }

        if (nombre.contains("cartuja center")) {
            return new double[]{37.410370, -6.000690};
        }

        if (nombre.contains("bilbao arena")) {
            return new double[]{43.253028, -2.923617};
        }

        if (nombre.contains("príncipe felipe") || nombre.contains("principe felipe")) {
            return new double[]{41.635721, -0.883831};
        }

        if (nombre.contains("cortijo de torres")) {
            return new double[]{36.699395, -4.479382};
        }

        if (nombre.contains("granada")) {
            return new double[]{37.177336, -3.606747};
        }

        if (nombre.contains("alicante")) {
            return new double[]{38.345214, -0.480997};
        }

        if (nombre.contains("artillería") || nombre.contains("artilleria")) {
            return new double[]{37.983786, -1.121496};
        }

        // Zaragoza centro por defecto
        return new double[]{41.6488, -0.8891};
    }
}
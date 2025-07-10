package org.example.SERVICES;

import org.example.INTERFACES.Servicio;

public class ServicioBase implements Servicio {

    @Override
    public double getCosto() {
        return 0.0;
    }

    @Override
    public String getDescripcion() {
        return "Servicio Base de hospedaje";
    }

    @Override
    public String toString() {
        return "Servicio Base + ";
    }
}

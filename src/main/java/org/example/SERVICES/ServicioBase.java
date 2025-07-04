package org.example.SERVICES;

import org.example.INTERFACES.Servicio;

public class ServicioBase implements Servicio {

    @Override
    public String getDescripcion() {
        return "Servicio Base de hospedaje";
    }

    @Override
    public double getCosto() {
        return 50.0;
    }
}

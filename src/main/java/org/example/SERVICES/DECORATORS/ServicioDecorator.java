package org.example.SERVICES.DECORATORS;

import org.example.INTERFACES.Servicio;

public class ServicioDecorator implements Servicio {
    public Servicio servicio;

    public ServicioDecorator(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion(){
        return servicio.getDescripcion();
    }

    public double getCosto() {
        return servicio.getCosto();
    }

}

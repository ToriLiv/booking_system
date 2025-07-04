package org.example.SERVICES.DECORATORS;

import org.example.INTERFACES.Servicio;

public class SpaDecorator extends ServicioDecorator{

    public SpaDecorator(Servicio servicio) {
        super(servicio);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() +  " spa incluido";
    }

    @Override
    public double getCosto() {
        return super.getCosto() + 75.0;
    }
}

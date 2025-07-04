package org.example.SERVICES.DECORATORS;

import org.example.INTERFACES.Servicio;

public class DesayunoDecorator extends ServicioDecorator{

    public DesayunoDecorator(Servicio servicio) {
        super(servicio);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " desayuno incluido";
    }

    @Override
    public double getCosto() {
        return super.getCosto() + 25.0;
    }
}

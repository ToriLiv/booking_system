package org.example.SERVICES.DECORATORS;

import org.example.INTERFACES.Servicio;

public class ParkingDecorator extends ServicioDecorator{

    public ParkingDecorator(Servicio servicio) {
        super(servicio);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " parqueo incluido";
    }

    @Override
    public double getCosto() {
        return super.getCosto() + 25.0;
    }
}

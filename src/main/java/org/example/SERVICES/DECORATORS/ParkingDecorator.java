package org.example.SERVICES.DECORATORS;

import org.example.INTERFACES.Servicio;


/*-------------------------DECORATOR-------------------------
 * Permite agregar funcionalidades a un objeto de tipo Servicio
 * -----------------------------------------------------------
 * */

public class ParkingDecorator extends ServicioDecorator{

    public ParkingDecorator(Servicio servicio) {
        super(servicio);
    }

    public String toString() {
        return servicio.toString() + " Parqueo";
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

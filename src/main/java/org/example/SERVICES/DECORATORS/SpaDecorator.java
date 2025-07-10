package org.example.SERVICES.DECORATORS;

import org.example.INTERFACES.Servicio;


/*-------------------------DECORATOR-------------------------
 * Permite agregar funcionalidades a un objeto de tipo Servicio
 * -----------------------------------------------------------
 * */

public class SpaDecorator extends ServicioDecorator{

    public SpaDecorator(Servicio servicio) {
        super(servicio);
    }

    public String toString() {
        return servicio.toString() + " Spa";
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

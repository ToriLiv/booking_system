package org.example.SERVICES.DECORATORS;

import org.example.INTERFACES.Servicio;

/*-------------------------DECORATOR-------------------------
* Permite agregar funcionalidades a un objeto de tipo Servicio
* -----------------------------------------------------------
* */
public class DesayunoDecorator extends ServicioDecorator {

    public DesayunoDecorator(Servicio servicio) {
        super(servicio);
    }

    public String toString() {
        return servicio.toString() + " Desayuno";
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

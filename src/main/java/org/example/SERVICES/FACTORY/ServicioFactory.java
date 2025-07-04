package org.example.SERVICES.FACTORY;

import org.example.INTERFACES.Servicio;
import org.example.SERVICES.DECORATORS.*;

public class ServicioFactory {
    public static Servicio crearServicio(String tipo, Servicio servicioBase){
        switch(tipo.toLowerCase()) {
            case "spa":
                return new SpaDecorator(servicioBase);
            case "desayuno":
                return new DesayunoDecorator(servicioBase);
            case "transporte":
                return new ParkingDecorator(servicioBase);
            default:
                throw new IllegalArgumentException("Tipo de servicio no reconocido: " + tipo);
        }
    }
}

package org.example.SERVICES.FACTORY;

import org.example.ENTITIES.HABITACIONES.Habitacion;
import org.example.ENTITIES.HABITACIONES.HabitacionDoble;
import org.example.ENTITIES.HABITACIONES.HabitacionSimple;
import org.example.ENTITIES.HABITACIONES.Suite;

public class HabitacionFactory {
    public static Habitacion crearHabitacion(String tipo, String numero, double precio){
        switch (tipo.toLowerCase()) {
            case "simple":
                return new HabitacionSimple(numero, precio, true);
            case "doble":
                return new HabitacionDoble(numero, precio, true);
            case "suite":
                return new Suite(numero, precio, true);
            default:
                throw new IllegalArgumentException("Tipo de habitacion no reconocido: " + tipo);
        }
    }
}

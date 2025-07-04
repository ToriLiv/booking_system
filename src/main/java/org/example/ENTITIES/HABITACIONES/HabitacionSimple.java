package org.example.ENTITIES.HABITACIONES;

public class HabitacionSimple extends Habitacion{

    public HabitacionSimple(String numero, double precioPorNoche, boolean disponible) {
        super(numero, "Simple", precioPorNoche, disponible, 1);
    }

    @Override
    public String getDescription() {
        return "Habitacion individual, cama individual, television, baño privado.";
    }
}

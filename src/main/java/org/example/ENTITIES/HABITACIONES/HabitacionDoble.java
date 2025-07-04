package org.example.ENTITIES.HABITACIONES;

public class HabitacionDoble extends Habitacion{

    public HabitacionDoble(String numero, double precioPorNoche, boolean disponible) {
        super(numero, "Doble", precioPorNoche, disponible, 2);
    }

    @Override
    public String getDescription() {
        return "Habitacion doble, cama matrimonial, television, baño privado.";
    }
}

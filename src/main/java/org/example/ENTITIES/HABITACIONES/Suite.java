package org.example.ENTITIES.HABITACIONES;

public class Suite extends Habitacion{

    public Suite(String numero, double precioPorNoche, boolean disponible) {
        super(numero, "Suite", precioPorNoche, disponible, 4);
    }

    @Override
    public String getDescription() {
        return "Habitacion suite, cama king size, jacuzzi, television, minibar, baño privado.";
    }
}

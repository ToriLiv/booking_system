package org.example.ENTITIES.HABITACIONES;

public abstract class Habitacion {

protected String numero;

protected String tipo;

protected double precioPorNoche;

protected boolean disponible;

protected int capacidad;

    public Habitacion(String numero, String tipo, double precioPorNoche, boolean disponible, int capacidad) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.disponible = disponible;
        this.capacidad = capacidad;
    }

    public Habitacion(String numero, String tipo, double precioPorNoche, int capacidad) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.capacidad = capacidad;
        this.disponible = true; //Por defecto, una habitacion nueva esta disponible
    }

    public abstract String getDescription();

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }


    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return  "numero: " + numero +
                ", tipo: " + tipo  +
                ", precio por noche: " + precioPorNoche +
                ", disponible: " + disponible +
                ", capacidad: " + capacidad;
    }
}

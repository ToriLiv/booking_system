package org.example.ENTITIES;


import org.example.INTERFACES.ClienteObserver;

import java.util.ArrayList;
import java.util.List;

public class Client implements ClienteObserver {


private String id;

private String nombre;

private String email;

private String telefono;

private List<Reserva> reservas;

    public Client(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.reservas = new ArrayList<>();
    }

    @Override
    public void actualizar(String message) {
        System.out.println("Notificacion para el cliente " + nombre + ": " + message);
    }

    public void agregarReserva(Reserva reserva){
        reservas.add(reserva);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return  "id=" + id  +
                ", nombre=" + nombre  +
                ", email=" + email +
                ", telefono=" + telefono  +
                ", reservas=" + reservas;
    }


}

package org.example.ENTITIES;

import org.example.ENTITIES.HABITACIONES.Habitacion;
import org.example.INTERFACES.Servicio;
import org.example.SERVICES.FACTORY.ServicioFactory;
import org.example.SERVICES.ServicioBase;

import java.util.Date;

public class Reserva {

private String id;

private Client cliente;

private Habitacion habitacion;

private Date fechaInicio;

private Date fechaFin;

private Servicio servicios;

private String estado;

private double costoTotal;

    public Reserva(String id, Client cliente, Habitacion habitacion, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.servicios = new ServicioBase();
        this.estado = "Pendiente";
        this.habitacion.setDisponible(false);
        this.costoTotal = calcularCostoTotal();
    }

    public void agregarServicio(String tipoServicio){
        this.servicios = ServicioFactory.crearServicio(tipoServicio, this.servicios );
        this.costoTotal = calcularCostoTotal();
    }

    public double calcularCostoTotal() {
        long diasEstancia = Math.max(1, (fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24));
        return (habitacion.getPrecioPorNoche() * diasEstancia) + (servicios != null ? servicios.getCosto() : 0.0);
    }

    public void cancelarReserva(){
        this.estado = "Cancelada";
        habitacion.setDisponible(true);

    }

    public void confirmarReserva() {
        this.estado = "Confirmada";
        habitacion.setDisponible(true);
    }

    public String getId() {
        return id;
    }

    public Client getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Servicio getServicios() {
        return servicios;
    }

    public String getEstado() {
        return estado;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    @Override
    public String toString() {
        return "\n======================================================================================================" +
                "\nId reserva: " + id  +
                "\nCliente:" + cliente.getNombre() + " Id: " + cliente.getId() +
                "\nHabitacion:" + habitacion +
                "\nFecha Inicio:" + fechaInicio +
                "\nFecha Fin:" + fechaFin +
                "\nServicios:" + ((servicios != null && !(servicios instanceof ServicioBase)) ? servicios.toString() : "Sin servicios adicionales") +
                "\nCosto Total:" + getCostoTotal() +
                "\nEstado:" + estado +
                "\n=====================================================================================================";
    }
}

package org.example.ENTITIES;

import org.example.ENTITIES.HABITACIONES.Habitacion;
import org.example.INTERFACES.Servicio;
import org.example.SERVICES.FACTORY.ServicioFactory;

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
    }

    public void agregarServicio(String tipoServicio){
        this.servicios = ServicioFactory.crearServicio(tipoServicio, this.servicios );
        this.costoTotal = calcularCostoTotal();

    }

    private double calcularCostoTotal() {
        long diasEstancia = (fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24);
        return (habitacion.getPrecioPorNoche() * diasEstancia) + servicios.getCosto();
    }

    public void confirmarReserva(){
        this.estado = "Confirmada";
        habitacion.setDisponible(false);
    }

    public void cancelarReserva(){
        this.estado = "Cancelada";
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
        return "Reserva{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente +
                ", habitacion=" + habitacion +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", servicios=" + servicios +
                ", estado='" + estado + '\'' +
                ", costoTotal=" + costoTotal +
                '}';
    }
}

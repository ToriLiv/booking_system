package org.example;


import org.example.ENTITIES.Client;
import org.example.ENTITIES.HABITACIONES.Habitacion;
import org.example.ENTITIES.Reserva;
import org.example.EXCEPTIONS.ClienteDuplicadoException;
import org.example.EXCEPTIONS.HabitacionNoDisponibleException;
import org.example.EXCEPTIONS.ReservaInvalidaException;
import org.example.OBSERVER.NotificadorReservas;
import org.example.SERVICES.FACTORY.HabitacionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaReserva {
    private static SistemaReserva instancia;
    private final List<Client> clientes;
    private final List<Reserva> reservas;
    private final List<Habitacion> habitaciones = new ArrayList<>();
    private final NotificadorReservas notificadorReservas;

    public SistemaReserva() {
        inicializarHabitacion();

        this.notificadorReservas = new NotificadorReservas();
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    /*
    * -------------------SINGLETON-------------------------
    * asegura que solo haya una instancia de SistemaReserva
    * proporciona un punto de acceso global a ella.
    * */
    public static SistemaReserva getInstancia() {
        if (instancia == null) {
            instancia = new SistemaReserva();
        }
        return instancia;
    }

    public void inicializarHabitacion() {
        habitaciones.add(HabitacionFactory.crearHabitacion("simple", "101", 50.0));
        habitaciones.add(HabitacionFactory.crearHabitacion("doble", "102", 100.0));
        habitaciones.add(HabitacionFactory.crearHabitacion("suite", "103", 200.0));
        habitaciones.add(HabitacionFactory.crearHabitacion("simple", "104", 50.0));
        habitaciones.add(HabitacionFactory.crearHabitacion("doble", "105", 100.0));

    }

    public void registrarCliente(Client cliente) throws ClienteDuplicadoException {
        for (Client c : clientes) {
            if (c.getEmail().equals(cliente.getEmail())) {
                throw new ClienteDuplicadoException("El cliente con email " + cliente.getEmail() + " ya está registrado.");
            }
        }
        clientes.add(cliente);
        notificadorReservas.agregarObservador(cliente);
        System.out.print("Cliente registrado: " + cliente.getNombre() + " con el id: " + cliente.getId());
    }


    public Reserva crearReserva(String idReserva, String idCliente, String tipoHabitacion, Date fechaInicio, Date fechaFin, String tipoServicio) throws ReservaInvalidaException, HabitacionNoDisponibleException {
        Client cliente = BuscarCliente(idCliente);
        Habitacion habitacion = BuscarHabitacionDisponible(tipoHabitacion);
        List<Habitacion> BuscarHabitacionesDisponibles = BuscarHabitacionesDisponibles(tipoHabitacion);

        Reserva reserva = new Reserva(idReserva, cliente, habitacion, fechaInicio, fechaFin);
        reserva.agregarServicio(tipoServicio);
        reservas.add(reserva);
        reserva.calcularCostoTotal();
        notificadorReservas.notificarObservador("Nueva reserva creada: " + idReserva + " para el cliente: " + cliente.getNombre());
        return reserva;

    }

    private Habitacion BuscarHabitacionDisponible(String tipoHabitacion) throws HabitacionNoDisponibleException {
        List<Habitacion> habitacionesDisponibles = BuscarHabitacionesDisponibles(tipoHabitacion);
        if (habitacionesDisponibles.isEmpty()) {
            throw new HabitacionNoDisponibleException("No hay habitaciones disponibles del tipo: " + tipoHabitacion);
        }
        return habitacionesDisponibles.get(0); // Retorna la primera habitación disponible del tipo solicitado
    }


    public void cancelarReserva(String idReserva) throws ReservaInvalidaException {
        Reserva reserva = BuscarReserva(idReserva);
        if (reserva == null) {
            throw new ReservaInvalidaException("Reserva no encontrada: " + idReserva);
        }
        reserva.cancelarReserva();
        notificadorReservas.notificarObservador("Reserva cancelada: " + idReserva + " para el cliente: " + reserva.getCliente().getNombre());;
    }

    public void confirmarReserva(String idReserva) throws ReservaInvalidaException {
        Reserva reserva = BuscarReserva(idReserva);
        if (reserva == null) {
            throw new ReservaInvalidaException("Reserva no encontrada: " + idReserva);
        }
        reserva.confirmarReserva();
        notificadorReservas.notificarObservador("Reserva confirmada: " + idReserva + " para el cliente: " + reserva.getCliente().getNombre());
    }

    public Client BuscarCliente(String idCliente) {
        return clientes.stream()
                .filter(c -> c.getId().equals(idCliente))
                .findFirst()
                .orElse(null);
    }

    public List<Habitacion> BuscarHabitacionesDisponibles(String tipo){
        return habitaciones.stream()
                .filter(h -> h.getTipo().equalsIgnoreCase(tipo) && h.isDisponible())
                .collect(Collectors.toList());
    }

    public Reserva BuscarReserva(String idReserva) {
        return reservas.stream()
                .filter(r -> r.getId().equals(idReserva))
                .findFirst()
                .orElse(null);
    }

    public List<Habitacion> habitacionesDisponibles(){
        return habitaciones.stream()
                .filter(Habitacion::isDisponible)
                .collect(Collectors.toList());
    }

    public List<Reserva> reservasCliente(String idCliente){
        return reservas.stream()
                .filter(r -> r.getCliente().getId().equals(idCliente))
                .toList();

    }

    public void mostrarClientes() {
        System.out.println("Lista de clientes registrados:");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (Client cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void mostrarReservas() {
        System.out.println("Lista de reservas:");
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    public void mostrarEstadoSistema() {
        System.out.println("=========================================");
        System.out.println("Estado del sistema de reservas:");
        System.out.println("Clientes registrados: " + clientes.size());
        System.out.println("Reservas realizadas: " + reservas.size());
        System.out.println("Habitaciones disponibles: " + habitacionesDisponibles().size());
        System.out.println("Habitaciones totales: " + habitaciones.size());
        System.out.println("Reservas por cliente:");
        for (Client cliente : clientes) {
            List<Reserva> reservasCliente = reservasCliente(cliente.getId());
            System.out.println(cliente.getNombre() + " tiene " + reservasCliente.size() + " reservas.");
        }
        System.out.println("=========================================");

    }



}

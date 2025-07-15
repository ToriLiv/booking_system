package org.example;

import org.example.ENTITIES.HABITACIONES.Habitacion;
import org.example.ENTITIES.Reserva;
import org.example.EXCEPTIONS.*;
import org.example.ENTITIES.Client;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static SistemaReserva sistemaReserva = new SistemaReserva();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            menu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion < 0 || opcion > 10) {
                System.out.println("Opción no válida. Por favor, intente de nuevo.");
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarCliente(scanner);
                    break;
                case 2:
                    buscarCliente(scanner);
                    break;
                case 3:
                    sistemaReserva.mostrarClientes();
                    break;
                case 4:
                    mostrarHabitaciones();
                    break;
                case 5:
                    buscarHabitacionesDisponibles(scanner);
                    break;
                case 6:
                    registrarReserva(scanner);
                    break;
                case 7:
                    cancelarReserva(scanner);
                    break;
                case 8:
                    sistemaReserva.mostrarReservas();
                    break;
                case 9:
                    buscarReserva(scanner);
                    break;
                case 10:
                    sistemaReserva.mostrarEstadoSistema();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }


    private static void menu() {
        System.out.println("\n");
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│           BOOKING SYSTEM            │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ 1-> Registrar cliente               │");
        System.out.println("│ 2-> Buscar cliente                  │");
        System.out.println("│ 3-> Mostrar clientes                │");
        System.out.println("│ 4-> Habitaciones disponibles        │");
        System.out.println("│ 5-> Buscar habitaciones disponibles │");
        System.out.println("│ 6-> Crear reserva                   │");
        System.out.println("│ 7-> Cancelar reserva                │");
        System.out.println("│ 8-> Mostrar reservas                │");
        System.out.println("│ 9-> Buscar reserva                  │");
        System.out.println("│ 10->Estado del sistema              │");
        System.out.println("│ 0-> Salir                           │");
        System.out.println("└─────────────────────────────────────┘");
    }

    //=====================METODOS CLIENTE========================

    private static void registrarCliente(Scanner scanner) {
        System.out.print("Ingrese ID del cliente: ");
        String id = scanner.nextLine();
        if (sistemaReserva.BuscarCliente(id) != null) {
            System.out.println("El cliente con ID " + id + " ya existe. Por favor, ingrese un ID diferente.");
            return;
        }
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) {
            System.out.println("El nombre del cliente no puede estar vacío.");
            return;
        }
        System.out.print("Ingrese email del cliente: ");
        String email = scanner.nextLine();
        if (email.isEmpty()) {
            System.out.println("El email del cliente no puede estar vacío.");
            return;
        }
        System.out.print("Ingrese telefono del cliente: ");
        String telefono = scanner.nextLine();
        if (telefono.isEmpty()) {
            System.out.println("El teléfono del cliente no puede estar vacío.");
            return;
        }
        Client cliente = new Client(id, nombre, email, telefono);
        try {
            sistemaReserva.registrarCliente(cliente);
            System.out.println("\nCliente registrado exitosamente.");
        } catch (ClienteDuplicadoException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarCliente(Scanner scanner) {
        System.out.print("Ingrese ID del cliente a buscar: ");
        String id = scanner.nextLine();
        Client cliente = sistemaReserva.BuscarCliente(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }



    //=====================METODOS RESERVA========================

    private static void registrarReserva(Scanner scanner) {
        System.out.print("Ingrese ID de la reserva: ");
        String id = scanner.nextLine();
        if (sistemaReserva.BuscarReserva(id) != null) {
            System.out.println("La reserva con ID " + id + " ya existe. Por favor, ingrese un ID diferente.");
            return;
        }
        if (sistemaReserva.habitacionesDisponibles().isEmpty()) {
            System.out.println("No hay habitaciones disponibles para crear una reserva.");
            return;
        }
        System.out.print("Ingrese ID del cliente: ");
        String clienteId = scanner.nextLine();
        if (clienteId.isEmpty()) {
            System.out.println("El ID del cliente no puede estar vacío.");
            return;
        }
        if(sistemaReserva.BuscarCliente(clienteId) == null) {
            System.out.println("Cliente no encontrado. Por favor, registre al cliente primero.");
            return;
        }
        System.out.print("Ingrese tipo de la habitacion: ");
        System.out.println("Seleccione 1, 2 o 3 para el tipo de habitación:");
        System.out.println("1. Simple");
        System.out.println("2. Doble");
        System.out.println("3. Suite");
        System.out.print("Seleccione el tipo de habitación: ");
        String tipoHabitacion = scanner.nextLine();
        if(tipoHabitacion.equals("1")){
            tipoHabitacion = "Simple";
        } else if(tipoHabitacion.equals("2")) {
            tipoHabitacion = "Doble";
        } else if(tipoHabitacion.equals("3")) {
            tipoHabitacion = "Suite";
        } else {
            System.out.println("Tipo de habitación no reconocido. No se creará la reserva.");
            return;
        }
        System.out.print("Ingrese fecha de inicio (dd/MM/yyyy): ");
        String Inicio = scanner.nextLine();
        if (Inicio.isEmpty()) {
            System.out.println("La fecha de inicio no puede estar vacía.");
            return;
        }
        System.out.print("Ingrese fecha de fin (dd/MM/yyyy): ");
        String Fin = scanner.nextLine();
        if (Fin.isEmpty()) {
            System.out.println("La fecha de fin no puede estar vacía.");
            return;
        }
        try {
            Date fechaInicio = new SimpleDateFormat("dd/MM/yyyy").parse(Inicio);
            Date fechaFin = new SimpleDateFormat("dd/MM/yyyy").parse(Fin);
            System.out.print("Desea agregar un servicio a la reserva? (si/no): ");
            String respuesta = scanner.nextLine();
            String tipoServicio = "";
            if(respuesta.equalsIgnoreCase("si")){
                System.out.println("Tipos de servicios disponibles: ");
                System.out.println("1. spa");
                System.out.println("2. desayuno");
                System.out.println("3. parqueo");
                System.out.println("4. todo incluido");
                System.out.print("Seleccione el tipo de servicio: ");
                tipoServicio = scanner.nextLine();
                if(tipoServicio.isEmpty()) {
                    System.out.println("El tipo de servicio no puede estar vacío. No se agregará ningún servicio.");
                    tipoServicio = "";
                }
                if(tipoServicio.equals("1")) {
                    tipoServicio = "spa";
                } else if(tipoServicio.equals("2")) {
                    tipoServicio = "desayuno";
                } else if(tipoServicio.equals("3")) {
                    tipoServicio = "parqueo";
                } else if(tipoServicio.equals("4")) {
                    tipoServicio = "todo incluido";
                } else {
                    System.out.println("Tipo de servicio no reconocido. No se agregará ningún servicio.");
                    tipoServicio = "";
                }
            }
            Reserva reserva = sistemaReserva.crearReserva(id, clienteId, tipoHabitacion, fechaInicio, fechaFin, tipoServicio);
            System.out.println("\nReserva registrada exitosamente.");
        } catch (ParseException e) {
            System.err.println("\nError al parsear las fechas: " + e.getMessage());
        } catch (ReservaInvalidaException | HabitacionNoDisponibleException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void cancelarReserva(Scanner scanner) {
        System.out.print("Ingrese ID de la reserva a cancelar: ");
        String idReserva = scanner.nextLine();
        try {
            sistemaReserva.cancelarReserva(idReserva);
            System.out.println("\nReserva cancelada exitosamente.");
        } catch (ReservaInvalidaException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarReserva(Scanner scanner) {
        System.out.print("Ingrese ID de la reserva a buscar: ");
        String idReserva = scanner.nextLine();
        Reserva reserva = sistemaReserva.BuscarReserva(idReserva);
        if (reserva != null) {
            System.out.println("Reserva encontrada: " + reserva);
        } else {
            System.out.println("Reserva no encontrada.");
        }
    }


    //=====================METODOS HABITACION========================
    private static void buscarHabitacionesDisponibles(Scanner scanner) {
        System.out.println("Tipos de habitaciones disponibles: ");
        System.out.println("Ingrese simple, doble o suite");
        System.out.print("Ingrese tipo de habitación a buscar: ");
        String tipo = scanner.nextLine();
        if (tipo.isEmpty()) {
            System.out.println("Tipo de habitación no puede estar vacío.");
            return;
        }
        var habitacionesDisponibles = sistemaReserva.BuscarHabitacionesDisponibles(tipo);
        if (habitacionesDisponibles.isEmpty()) {
            System.out.println("No hay habitaciones disponibles del tipo: " + tipo);
        } else {
            System.out.println("Habitaciones disponibles del tipo " + tipo + ":");
            for (Habitacion habitacion : habitacionesDisponibles) {
                System.out.println(habitacion);
            }
        }
    }

    private static void mostrarHabitaciones() {
        List<Habitacion> habitacions = sistemaReserva.habitacionesDisponibles();
        habitacions.forEach(System.out::println);
    }


}
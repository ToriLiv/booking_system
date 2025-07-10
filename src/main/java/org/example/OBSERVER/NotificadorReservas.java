package org.example.OBSERVER;

import org.example.INTERFACES.ClienteObserver;

import java.util.ArrayList;
import java.util.List;

/*---------------------------------OBSERVER-----------------------------
* permite que un objeto notifique a otros objetos cuando ocurra un evento
* ----------------------------------------------------------------------
* */
public class NotificadorReservas {
    private List<ClienteObserver> observadores  = new ArrayList<>();

    public void agregarObservador(ClienteObserver observador) {
        observadores.add(observador);
    }

    public void removerObservador(ClienteObserver observador){
        observadores.remove(observador);
    }

    public void notificarObservador(String message){
        // Extrae el nombre del cliente del mensaje (ej: "para el cliente: chel")
        String nombreCliente = message.split("para el cliente: ")[1].trim();

        for (ClienteObserver observador : observadores) {
            if (observador.toString().contains(nombreCliente)) {
                observador.actualizar(message); //solo al cliente que corresponde
                break;
            }
        }
    }




}



package org.example.OBSERVER;

import org.example.INTERFACES.ClienteObserver;

import java.util.ArrayList;
import java.util.List;

public class NotificadorReservas {
    private List<ClienteObserver> observadores  = new ArrayList<>();

    public void agregarObservador(ClienteObserver observador) {
        observadores.add(observador);
    }

    public void removerObservador(ClienteObserver observador){
        observadores.remove(observador);
    }

    public void notificarObservador(String message){
        for (ClienteObserver observador : observadores){
            observador.actualizar(message);
        }
    }




}



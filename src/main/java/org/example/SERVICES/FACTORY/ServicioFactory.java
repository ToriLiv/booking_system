package org.example.SERVICES.FACTORY;

import org.example.INTERFACES.Servicio;
import org.example.SERVICES.DECORATORS.*;
import org.example.SERVICES.ServicioBase;

/*------------------------------------------FACTORY-------------------------------------------
* * Clase que crea instancias de servicios decorados segun el tipo de servicio solicitado.
* --------------------------------------------------------------------------------------------
* */
public class ServicioFactory {
    ServicioBase servicioBase;
    public static Servicio crearServicio(String tipo, Servicio servicioBase){
        if (servicioBase == null) {
            servicioBase = new ServicioBase();
        }
        switch(tipo.toLowerCase()) {
            case "spa":
                return new SpaDecorator(servicioBase);
            case "desayuno":
                return new DesayunoDecorator(servicioBase);
            case "parqueo":
                return new ParkingDecorator(servicioBase);
            case "todo incluido":
                return new SpaDecorator(new DesayunoDecorator(new ParkingDecorator(servicioBase)));
            default:
                return servicioBase;
        }
    }
}

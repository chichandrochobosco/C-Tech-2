
package core;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transaccion {
    static int idGlobal;
    int id;
    TipoTransaccion tipo;
    Camion camion;
    Conductor conductor;
    ArrayList<Palet> mercaderia;
    LocalDate fecha = LocalDate.now();
    String origen;
    String destino;

    public Transaccion(TipoTransaccion tipo, Camion camion, Conductor conductor, ArrayList<Palet> mercaderia, String origen, String destino) {
        this.id = idGlobal;
        this.tipo = tipo;
        this.camion = camion;
        this.conductor = conductor;
        this.mercaderia = mercaderia;
        this.origen = origen;
        this.destino = destino;
        idGlobal++;
    }
    
    public void emitirInforme(){
        
    }    
    
}


package core;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transaccion {
    private static int idGlobal;
    private int id;
    private TipoTransaccion tipo;
    private Camion camion;
    private Conductor conductor;
    private ArrayList<Palet> mercaderia;
    private LocalDate fecha = LocalDate.now();
    private String origen;
    private String destino;

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

    public LocalDate getFecha() {
        return fecha;
    }
    
    
    
}

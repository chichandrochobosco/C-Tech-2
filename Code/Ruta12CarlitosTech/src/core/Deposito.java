
package core;

import java.time.LocalDate;
import java.util.ArrayList;

public class Deposito {
    Pasillo [] pasillos = new Pasillo[8];
    ArrayList<Transaccion> transacciones = new ArrayList<>();

    public Deposito() {
        for(Pasillo p: pasillos){
            p = new Pasillo();
        }
    }
    
    //Muestra la lista de la mercaderia en todos los palets ocupados
    public void verStock(){
        for(Pasillo p: pasillos){
            Estanteria[] estanterias = p.getEstanterias();
            for(Estanteria e: estanterias){
                Palet[] palets = e.getPalets();
                for(Palet palet: palets){
                    if(palet!=null){
                        palet.mostrarDatos();
                    }
                }
            }
        }
    }
    
    //Muestra las unidades de palet que se encuentran vacias (Su pasillo, estanteria y numero de palet. Todo por el indice interno)
    public ArrayList<Integer[]> verEspaciosVacios(){ //devuelve un arraylist de arrays de enteros, cada array tiene tres coordenadas {pasillo, estanteria y palet}
        ArrayList<Integer[]>espaciosVacios = new ArrayList<>();
        for (int i = 0; i < pasillos.length; i++) {
            Estanteria[] estanterias = pasillos[i].getEstanterias();
            for (int j = 0; j < estanterias.length; j++) {
                Palet[] palets = estanterias[j].getPalets();
                for (int k = 0; k < palets.length; k++) {
                    if(palets[k]==null){
                        Integer[] coordenadaVacia = {i,j,k};
                        espaciosVacios.add(coordenadaVacia);
                    }
                }
            }
        }
        
        return espaciosVacios;
    }
    
    //Agrega una transaccion a la lista y ejecuta el metodo de la transaccion necesario
    public void realizarTransaccion(){
        //preguntar que transaccion hacer y en que coordenadas
        //licha
    }
    
    //Borrar los palets indicados en el egreso
    public void egreso(/*que palets borrar*/){
        //licha
    }
    
    //Asigna a un palet indicado los valores de ingreso
    public void ingreso(){
        //lolo
    }
    
    //muestra todas las transacciones
    public void generarInformeTotal(){
        for(Transaccion t: transacciones){
            t.emitirInforme();
        }
    }
    
    //muestra las fechas entre determinadas fechas, terminado, commitear
     public void generarInformeFecha(LocalDate f1, LocalDate f2) {

        for (Transaccion transaccion : transacciones) {
            if (transaccion.fecha.isAfter(f1) && transaccion.fecha.isBefore(f2)) {

                transaccion.emitirInforme();
            }
        }

    }
    
}

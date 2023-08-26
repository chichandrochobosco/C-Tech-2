
package core;

public class Main {
    public static void main(String[] args) {
        
        Deposito deposito = inicializarDeposito();
        
        deposito.realizarTransaccion();    
    }
    
    public static Deposito inicializarDeposito(){
        Pasillo[] pasillos = new Pasillo[8];
        for(int i=0;i<pasillos.length;i++){
            Estanteria estanterias[] = new Estanteria[2];
            for(int j=0;j<estanterias.length;j++){
                Palet palets[] = new Palet[16];
                estanterias[j] = new Estanteria(palets);
            }
            
            pasillos[i] = new Pasillo(estanterias);
        }
        return new Deposito(pasillos);
    }
}

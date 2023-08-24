
package core;

public class Pasillo {
    Estanteria estanterias[]= new Estanteria[2];

    public Pasillo() {
        for(Estanteria e: estanterias){
            e = new Estanteria();
        }
    }
}

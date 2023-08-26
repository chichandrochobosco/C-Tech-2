
package core;

public class Pasillo {
    private Estanteria estanterias[];

    public Pasillo(Estanteria estanterias[]) {
        this.estanterias = estanterias;
        /*
        for(Estanteria e: estanterias){
            e = new Estanteria();
        }
        */
        
    }

    public Estanteria[] getEstanterias() {
        return estanterias;
    }
    
    
}

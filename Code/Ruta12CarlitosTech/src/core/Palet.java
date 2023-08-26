
package core;


public class Palet {
    private Mercaderia mercaderia;

    public Palet(Mercaderia mercaderia) {
        this.mercaderia = mercaderia;
    }
    
    public Palet(){
        
    }

    public Mercaderia getMercaderia() {
        return mercaderia;
    }
    
    public void mostrarDatos(){
        System.out.println("Palet: "+mercaderia.getNombre()+"- marca:"+mercaderia.getMarca()+"- Peso:"+mercaderia.getPeso()+"kg");
    }
}

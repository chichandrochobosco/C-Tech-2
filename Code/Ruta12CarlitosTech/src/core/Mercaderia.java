package core;
        
public class Mercaderia {
    private String marca;
    private String nombre;
    private Double peso;

    public Mercaderia(String marca, String nombre, Double peso) {
        this.marca = marca;
        this.peso = peso;
        this.nombre=nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPeso() {
        return peso;
    }
    
    
}

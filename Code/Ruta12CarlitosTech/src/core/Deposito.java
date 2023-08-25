
package core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
        ArrayList<Integer[]>coordenadas = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String destino, origen, marca, modelo, nombre, apellido, marcaMercaderia, nombreMercaderia;
        double peso;
        int entrada, pasillo, estanteria, palet, matricula, dni;
        System.out.println("Ingrese 1-Egreso 2-Ingreso: ");
        entrada = in.nextInt();
        in.nextLine();
        if(entrada==1){
            int carga = 1;
            ArrayList<Palet> palets = new ArrayList<>();
            verStock();
            System.out.println("Ingrese coordenadas del palet a retirar: ");
            do{
                System.out.println("Numero de pasillo");
                pasillo = in.nextInt();
                in.nextLine();
                System.out.println("Numero de estantería");
                estanteria = in.nextInt();
                in.nextLine();                
                System.out.println("Numero de palet");
                palet = in.nextInt();
                in.nextLine();
                
                Integer[] coordenadaEgreso = {pasillo, estanteria, palet};
                coordenadas.add(coordenadaEgreso);
                
                palets.add(pasillos[pasillo].getEstanterias()[estanteria].getPalets()[palet]);
                
                System.out.println("Ingrese 1-seguir retirando cualquier numero-finalizar egreso: ");
                carga = in.nextInt();
                in.nextLine();
            }while(carga==1);
            
            System.out.println("Ingrese destino: ");
            destino = in.nextLine();
            System.out.println("Ingrese origen: ");
            origen = in.nextLine();
            System.out.println("Ingrese marca: ");
            marca = in.nextLine();
            System.out.println("Ingrese modelo: ");
            modelo = in.nextLine();
            System.out.println("Ingrese matricula: ");
            matricula = in.nextInt();
            in.nextLine();
            Camion camion = new Camion(matricula, marca, modelo);
            
            System.out.println("Ingrese Nombre: ");
            nombre = in.nextLine();
            System.out.println("Ingrese Apellido: ");
            apellido = in.nextLine();
            System.out.println("Ingrese dni: ");
            dni = in.nextInt();
            in.nextLine();
            Conductor conductor = new Conductor(dni, nombre, apellido);
            
            egreso(coordenadas);
            Transaccion t = new Transaccion(TipoTransaccion.EGRESO, camion, conductor, palets, origen, destino);
            
        }else if(entrada==2){
            int carga = 1;
            ArrayList<Integer[]> espaciosVacios = verEspaciosVacios();
            ArrayList<Palet> mercaderia = new ArrayList<>();
            System.out.println("Espacios vacios: ");
            for(Integer[] p :  espaciosVacios){
                System.out.println(p);
            }
            System.out.println("Ingrese coordenadas del palet a ingresar: ");
            do{
                System.out.println("Numero de pasillo");
                pasillo = in.nextInt();
                in.nextLine();
                System.out.println("Numero de estantería");
                estanteria = in.nextInt();
                in.nextLine();                
                System.out.println("Numero de palet");
                palet = in.nextInt();
                in.nextLine();
                System.out.println("Numero de pasillo");
                pasillo = in.nextInt();
                in.nextLine();
                marca
                        nombre
                        peso
                
                
                Integer[] coordenadaIngreso = {pasillo, estanteria, palet};
                coordenadas.add(coordenadaIngreso);
                
                System.out.println("Ingrese 1-seguir retirando cualquier numero-finalizar egreso: ");
                carga = in.nextInt();
                
            }while(carga==1);
            
            
            System.out.println("Ingrese destino: ");
            destino = in.nextLine();
            System.out.println("Ingrese origen: ");
            origen = in.nextLine();
            System.out.println("Ingrese marca: ");
            marca = in.nextLine();
            System.out.println("Ingrese modelo: ");
            modelo = in.nextLine();
            System.out.println("Ingrese matricula: ");
            matricula = in.nextInt();
            in.nextLine();
            Camion camion = new Camion(matricula, marca, modelo);
            
            System.out.println("Ingrese Nombre: ");
            nombre = in.nextLine();
            System.out.println("Ingrese Apellido: ");
            apellido = in.nextLine();
            System.out.println("Ingrese dni: ");
            dni = in.nextInt();
            in.nextLine();
            Conductor conductor = new Conductor(dni, nombre, apellido);
            
            ingreso(coordenadas);
            Transaccion t = new Transaccion(TipoTransaccion.INGRESO, camion, conductor, mercaderia, origen, destino);
            
        }
        
        
        
    }
    
    //Borrar los palets indicados en el egreso
    public void egreso(ArrayList<Integer[]> coordenadas){
        
    }
    
    //Asigna a un palet indicado los valores de ingreso
    public void ingreso(ArrayList<Integer[]> coordenadas, ArrayList<Palet>palets){
        for(int i=0;i<coordenadas.size();i++){
            
            int pasillo = coordenadas.get(i)[1];
            int estanteria = coordenadas.get(i)[2];
            int palet = coordenadas.get(i)[3];
            
            pasillos[pasillo].getEstanterias()[estanteria].getPalets()[palet] = palets.get(i);
        }
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

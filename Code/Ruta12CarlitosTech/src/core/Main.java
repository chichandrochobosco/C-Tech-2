
package core;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        Deposito deposito = inicializarDeposito();
        
        menu(deposito);
    }
    
    public static void menu(Deposito deposito){
        int opc;
        do{
            System.out.println(":-------------------------------------------------------------:");
            System.out.println("OPCIONES");
            System.out.println("1.- Ver stock");
            System.out.println("2.- Ver espacios vacios del deposito");
            System.out.println("3.- Realizar una transaccion de Ingreso o Egreso");
            System.out.println("4.- Generar informe total");
            System.out.println("5.- Generar informe de determinadas fechas");
            System.out.println("6.- SALIR");
            opc = in.nextInt();
            in.nextLine();

            switch (opc) {
                case 1:
                    deposito.verStock();
                    break;
                case 2:
                    deposito.verEspaciosVacios();
                    break;
                case 3:
                    deposito.realizarTransaccion();
                    break;
                case 4:
                    deposito.generarInformeTotal();
                    break;
                case 5:
                    LocalDate f1,f2;
                    System.out.println("Ingrese la primera fecha: ");
                    f1 = ingresarFecha();
                    System.out.println("Ingrese la segunda fecha: ");
                    f2 = ingresarFecha();

                    deposito.generarInformeFecha(f1, f2);
                    break;
                case 6:
                    System.out.println("chauchis");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while(opc!=6);
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
    
    public static LocalDate ingresarFecha(){
        LocalDate fecha;
        
        System.out.println("Ingrese el dia ");
        int dia = in.nextInt();
        in.nextLine();
        System.out.println("Ingrese el mes");
        int mes = in.nextInt();
        in.nextLine();
        System.out.println("Ingrese el año");
        int ano = in.nextInt();
        in.nextLine();
        
        fecha = LocalDate.of(ano, mes, dia);
        
        return fecha;
    }
}

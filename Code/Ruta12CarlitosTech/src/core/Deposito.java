package core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Deposito {

    private final Pasillo[] pasillos;
    private ArrayList<Transaccion> transacciones = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public Deposito(Pasillo[] pasillos) {
        this.pasillos = pasillos;
    }

    //Muestra la lista de la mercaderia en todos los palets ocupados
    public boolean verStock() {
        int cont = 0, i = 0;
        for (Pasillo p : pasillos) {
            int j = 0;
            Estanteria[] estanterias = p.getEstanterias();
            for (Estanteria e : estanterias) {
                int k = 0;
                Palet[] palets = e.getPalets();
                for (Palet palet : palets) {
                    if (palet != null) {
                        palet.mostrarDatos();
                        System.out.println("Ubicacion = Pasillo:" + i + " Estanteria:" + j + " Palet:" + k);
                        cont++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }

        if (cont == 0) {
            System.out.println("El stock se encuentra totalmente vacio.");
            return false;
        }

        return true;
    }

    //Muestra las unidades de palet que se encuentran vacias (Su pasillo, estanteria y numero de palet. Todo por el indice interno)
    private ArrayList<Integer[]> devolverEspaciosVacios() { //devuelve un arraylist de arrays de enteros, cada array tiene tres coordenadas {pasillo, estanteria y palet}
        ArrayList<Integer[]> espaciosVacios = new ArrayList<>();
        for (int i = 0; i < pasillos.length; i++) {
            Estanteria[] estanterias = pasillos[i].getEstanterias();
            for (int j = 0; j < estanterias.length; j++) {
                Palet[] palets = estanterias[j].getPalets();
                for (int k = 0; k < palets.length; k++) {
                    if (palets[k] == null) {
                        Integer[] coordenadaVacia = {i, j, k};
                        espaciosVacios.add(coordenadaVacia);
                    }
                }
            }
        }

        return espaciosVacios;
    }

    public boolean verEspaciosVacios() {
        ArrayList<Integer[]> espaciosVacios = devolverEspaciosVacios();
        if (espaciosVacios.isEmpty()) {
            System.out.println("No hay espacios libres");
            return false;
        }
        System.out.println("Espacios vacios: ");
        for (Integer[] p : espaciosVacios) {
            System.out.println("Pasillo:" + p[0] + " Estanteria:" + p[1] + " Palet:" + p[2]);
            
        }
        return true;
    }

    //Agrega una transaccion a la lista y ejecuta el metodo de la transaccion necesario
    public void realizarTransaccion() {
        //preguntar que transaccion hacer y en que coordenadas
        int entrada;
        System.out.println("Ingrese 1-Egreso 2-Ingreso: ");
        entrada = in.nextInt();
        while(entrada <1 || entrada >2 ){
            System.out.println("Reingrese la operacion: 1-Egreso 2-Ingreso");
            entrada = in.nextInt();
        }
        in.nextLine();

        if (entrada == 1) {
            int carga = 1;
            ArrayList<Integer[]> coordenadas = new ArrayList<>();
            ArrayList<Palet> palets = new ArrayList<>();

            if (!verStock()) {
                carga = 0;
            }

            while (carga == 1) {
                System.out.println("Ingrese coordenadas del palet a retirar: ");
                Integer[] coordenadaEgreso = ingresarCoordenada();
                coordenadas.add(coordenadaEgreso);
                palets.add(getPaletByCoordenada(coordenadaEgreso));

                System.out.println("Ingrese 1-seguir retirando cualquier numero-finalizar egreso: ");
                carga = in.nextInt();
                in.nextLine();
            }

            if (!coordenadas.isEmpty()) {
                egreso(coordenadas);
                Transaccion t = ingresarTransaccion(TipoTransaccion.EGRESO, palets);
                transacciones.add(t);
            }

        } else if (entrada == 2) {
            int carga = 1;
            ArrayList<Integer[]> coordenadas = new ArrayList<>();
            ArrayList<Palet> palets = new ArrayList<>();

            if (!verEspaciosVacios()) {
                carga = 0;
            }

            while (carga == 1) {
                System.out.println("Ingrese coordenadas del palet a ingresar: ");
                Integer[] coordenadaIngreso = ingresarCoordenada();

                Mercaderia mercaderia = ingresarMercaderia();
                Palet palet = new Palet(mercaderia);
                palets.add(palet);

                coordenadas.add(coordenadaIngreso);

                System.out.println("Ingrese 1-seguir ingresando cualquier numero-finalizar egreso: ");
                carga = in.nextInt();
                in.nextLine();
            }

            if (!coordenadas.isEmpty()) {
                ingreso(coordenadas, palets);
                Transaccion t = ingresarTransaccion(TipoTransaccion.INGRESO, palets);
                transacciones.add(t);
            }
        }
    }

    //Borrar los palets indicados en el egreso
    public void egreso(ArrayList<Integer[]> coordenadas) throws ArrayIndexOutOfBoundsException{
        for (int i = 0; i < coordenadas.size(); i++) {
            
            Integer[] coordenada = coordenadas.get(i);

            int pasillo = coordenada[0];
            int estanteria = coordenada[1];
            int palet = coordenada[2];
            
            pasillos[pasillo].getEstanterias()[estanteria].getPalets()[palet] = null;
        }
    }

    //Asigna a un palet indicado los valores de ingreso
    public void ingreso(ArrayList<Integer[]> coordenadas, ArrayList<Palet> palets) throws ArrayIndexOutOfBoundsException{
        for (int i = 0; i < coordenadas.size(); i++) {
            
            Integer[] coordenada = coordenadas.get(i);
            int pasillo = coordenada[0];
            int estanteria = coordenada[1];
            int palet = coordenada[2];
            
            pasillos[pasillo].getEstanterias()[estanteria].getPalets()[palet] = palets.get(i);
        }
    }

    //muestra todas las transacciones
    public void generarInformeTotal() {
        for (Transaccion t : transacciones) {
            t.emitirInforme();
        }
    }

    //muestra las transacciones entre determinadas fechas
    public void generarInformeFecha(LocalDate f1, LocalDate f2) {

        for (Transaccion transaccion : transacciones) {
            if (transaccion.getFecha().isAfter(f1) && transaccion.getFecha().isBefore(f2)) {

                transaccion.emitirInforme();
            }
        }
    }

    private Integer[] ingresarCoordenada() {

        int pasillo, estanteria, paletIngreso;

        System.out.println("Numero de pasillo");
        pasillo = in.nextInt();
        in.nextLine();
        System.out.println("Numero de estantería");
        estanteria = in.nextInt();
        in.nextLine();
        System.out.println("Numero de palet");
        paletIngreso = in.nextInt();
        in.nextLine();

        Integer[] coordenada = {pasillo, estanteria, paletIngreso};

        return coordenada;

    }

    private Palet getPaletByCoordenada(Integer[] coordenada) {
        int pasillo = coordenada[0];
        int estanteria = coordenada[1];
        int palet = coordenada[2];

        return pasillos[pasillo].getEstanterias()[estanteria].getPalets()[palet];
    }

    private Camion ingresarCamion() {
        String marca, modelo;
        int matricula;
        System.out.println("Ingrese marca del camion: ");
        marca = in.nextLine();
        System.out.println("Ingrese modelo del camion: ");
        modelo = in.nextLine();
        System.out.println("Ingrese matricula del camion: ");
        matricula = in.nextInt();
        in.nextLine();
        return new Camion(matricula, marca, modelo);
    }

    private Conductor ingresarConductor() {
        String nombre, apellido;
        int dni;

        System.out.println("Ingrese Nombre del conductor: ");
        nombre = in.nextLine();
        System.out.println("Ingrese Apellido del conductor: ");
        apellido = in.nextLine();
        System.out.println("Ingrese dni del conductor: ");
        dni = in.nextInt();
        in.nextLine();
        return new Conductor(dni, nombre, apellido);
    }

    private Mercaderia ingresarMercaderia() {
        String marcaMercaderia, nombreMercaderia;
        double peso;

        System.out.println("Ingrese marca de mercaderia: ");
        marcaMercaderia = in.nextLine();
        System.out.println("Ingrese nombre de mercaderia: ");
        nombreMercaderia = in.nextLine();
        System.out.println("Ingrese peso de mercaderia: ");
        peso = in.nextDouble();
        in.nextLine();
        return new Mercaderia(marcaMercaderia, nombreMercaderia, peso);
    }

    private Transaccion ingresarTransaccion(TipoTransaccion tipoTransaccion, ArrayList<Palet> palets) {
        String destino, origen;
        System.out.println("Ingrese destino: ");
        destino = in.nextLine();
        System.out.println("Ingrese origen: ");
        origen = in.nextLine();
        Camion camion = ingresarCamion();
        Conductor conductor = ingresarConductor();

        return new Transaccion(tipoTransaccion, camion, conductor, palets, origen, destino);
    }

    public Pasillo[] getPasillos() {
        return pasillos;
    }

}

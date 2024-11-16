package zona_fit;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

public class ZonaFit {

    static Scanner in = new Scanner(System.in);
    static final String[] opciones = {"LISTAR CLIENTES","AGREGAR CLIENTE","MODIFICAR CLIENTE","ELIMINAR CLIENTE","SALIR"};
    static final IClienteDAO iClienteDao = new ClienteDAO();

    public static void main(String[] args) {

        boolean salir = false;

        while(!salir) {

            int option = pintarMenu();
            switch (option) {
                case 1:
                    listarClientes();
                    break;
                case 2:
                    agregarCliente();
                    break;
                case 3:
                    modificarCliente();
                    break;
                case 4:
                    eliminarCliente();
                    break;
                case 5:
                    System.out.println("\n ** Vuelve pronto!! =) **");
                    salir = true;
                    break;
            }
        }
    }

    private static void eliminarCliente() {
        System.out.println("Introduce el id del cliente: ");
        int id = checkEntradaNumero();

        if(iClienteDao.findCliente(new Cliente(id))) {
            iClienteDao.deleteCliente(new Cliente(id));
        }else{
            System.out.println("El id introducido no existe.");
        }
    }

    private static void modificarCliente() {

        System.out.println("Introduce el id del cliente: ");
        int id = checkEntradaNumero();

        if(iClienteDao.findCliente(new Cliente(id))) {
            System.out.println("Introduce el nombre: ");
            String nombre = in.next();
            System.out.println("Introduce el apellido: ");
            String apellido = in.next();
            System.out.println("Introduce la membresia: ");
            int membresia = checkEntradaNumero();

            iClienteDao.updateCliente(new Cliente(id,nombre, apellido, membresia));
        }else{
            System.out.println("El id introducido no existe.");
        }
    }

    private static void agregarCliente() {
        System.out.println("Introduce el nombre: ");
        String nombre = in.next();
        System.out.println("Introduce el apellido: ");
        String apellido = in.next();
        System.out.println("Introduce la membresia: ");
        int membresia = checkEntradaNumero();

        iClienteDao.insertCliente(new Cliente(nombre,apellido,membresia));
    }

    private static int checkEntradaNumero() {
        boolean correcto = false;
        int result = 0;
        while (!correcto) {
            try {
                in = new Scanner(System.in);
                result = in.nextInt();
                correcto = true;
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("\nOpcion: ");
            }
        }

        return result;
    }

    private static void listarClientes() {
        List<Cliente> clientes = iClienteDao.getClientes();

        for(Cliente cliente : clientes){
            System.out.println(cliente.toString());
        }

        if(clientes.size() == 0)
            System.out.println("No existen clientes en la base de datos.");
    }

    private static int pintarMenu() {
        int option = 0;
        try{
            while(option <= 0 || option > opciones.length){
                System.out.println("\n");
                System.out.println(""" 
                *** Zona Fit App ***""" + "\n" +
                "1. " + opciones[0] + "\n" +
                "2. " + opciones[1] + "\n" +
                "3. " + opciones[2] + "\n" +
                "4. " + opciones[3] + "\n" +
                "5. " + opciones[4] + "\n\n" +
                "Opcion: ");

                option = checkEntradaNumero();

                if(option <= 0 || option > opciones.length)
                    System.out.println("Opcion incorrecta !!");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return option;
    }
}

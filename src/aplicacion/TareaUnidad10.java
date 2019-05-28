/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Antonio Luis
 */
public class TareaUnidad10 {

    private static ConectorBD db;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        db = new ConectorBD();
        do {
            mostrarMenu();
            System.out.print("Introduce la opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();
            tratarOpcionMenu(opcion);
        } while (opcion != 0);
    }

    public static void mostrarMenu() {

        System.out.println("1.- Dar de alta una persona ");
        System.out.println("2.- Dar de baja una persona ");
        System.out.println("3.- Modificar los datos de una persona ");
        System.out.println("4.- Modificar los datos de un departamento");
        System.out.println("5.- Dar de baja un departamento");
        System.out.println("6.- Dar de alta un departamento");
        System.out.println("7.- Mostrar datos de las personas");

    }

    public static void tratarOpcionMenu(int opcion) {
        Scanner teclado = new Scanner(System.in);
        switch (opcion) {

            case 1:
                altaPersona();
                break;
            case 2:
                bajaPersona();
                break;
            case 3:
                modificarPers();
                break;
            case 4:
                modificarDatosDep();
                break;
            case 5:
                bajaDep();
                break;
            case 6:
                System.out.println("Introduce el número del departamento");
                int num = teclado.nextInt();
                altaDep(num);
                break;
            case 7:
                mostrarDatosPerso();
                break;

        }

    }

    private static void altaPersona() {
        Persona p = new Persona();
        Utilidades.borrarPantalla();

        p.setNomPerso(Utilidades.leerCadena("Nombre de la persona: "));

        if (db.buscarPersona(p)) {

            System.out.println("ERROR: YA EXISTE ESA PERSONA");

        } else {

            p.setNumDep(Utilidades.leerEntero("Numero del departamento: "));
            p.setId(Utilidades.leerEntero("Identificador de la persona: "));
            if (db.buscarDp(p.getNumDep())) {
                System.out.println("Se inserto la persona correctamente.");
                db.insertarPersona(p);

            } else {
                Departamento aux;
                aux = altaDep(p.getNumDep());
                if (aux != null) {

                    db.insertarPersona(p);

                }

            }

        }

    }

    private static Departamento altaDep(int num) {
        Departamento d = new Departamento();
        Utilidades.borrarPantalla();

        if (db.buscarDp(num)) {

            System.out.println("ERROR: EL DEPARTAMENTO YA EXISTE");

        } else {

            d.setNumDep(num);
            d.setDireccion(Utilidades.leerDireccion("Dirección del departamento: "));
            d.setNomDep(Utilidades.leerCadena("Nombre del departamento: "));
            db.insertarDepartamento(d);
            System.out.println("Departamento dado de alta correctamente");

        }

        return d;
    }

    private static void bajaPersona() {
        Persona p = new Persona();
        Utilidades.borrarPantalla();
        System.out.println("Introduce el nombre de la persona a borrar");
        p.setNomPerso(Utilidades.leerCadena("Nombre de la persona: "));
        if (db.buscarPersona(p)) {
            db.borrarPersona(p);
            System.out.println("Contacto borrado de la base de datos...");
        } else {
            System.out.println("Contacto no encontrado en la base de datos...");
        }
        Utilidades.esperar(5);
    }

    private static void modificarDatosDep() {

        Departamento d = new Departamento();
        int numDep;
        mostrarDepartamentos();
        numDep = Utilidades.leerEntero("Introduce el número de departamento que desea modificar: ");
        if (db.buscarDp(numDep)) {

            d.setDireccion(Utilidades.leerDireccion("Introduce la dirección nueva del departamento: "));
            d.setNomDep(Utilidades.leerCadena("Introduce el nombre nuevo del departamento: "));

            if (db.actualizarDep(d, numDep)) {
                System.out.println("El departamento se actualizo correctamente.");
            }

        } else {

            System.out.println("El departamento introducido, no existe en la base de datos.");

        }

    }

    public static void mostrarDepartamentos() {

        ArrayList<Departamento> departamentos;
        departamentos = db.obtenerDepartamentos();
        System.out.println("Departamentos disponibles en la base de datos...");
        System.out.println("");
        String cadena = "";
        cadena += Utilidades.formatoColumna("Nº Departamento", 50);
        cadena += Utilidades.formatoColumna("Dirección", 50);
        cadena += Utilidades.formatoColumna("Nombre departamento", 50);
        System.out.println(cadena);
        System.out.println("");

        for (Departamento d : departamentos) {

            System.out.println(d);

        }
        System.out.println("");
        Utilidades.esperar(5);
    }

    private static void bajaDep() {
        Departamento d = new Departamento();
        mostrarDepartamentos();
        Utilidades.borrarPantalla();
        System.out.println("Introduce el número de departamento que quiere  borrar");
        d.setNumDep(Utilidades.leerEntero("Número de departamento: "));
        if (db.buscarDp(d.getNumDep())) {

            if (db.personasDep(d.getNumDep())) {

                System.out.println("No se puede borrar el departamento porque hay personas asignadas a dicho departamento");

            } else {
                db.borrarDepartamento(d);
                System.out.println("Departamento borrado con éxito.");
            }

        } else {
            System.out.println("Contacto no encontrado en la base de datos...");
        }
        Utilidades.esperar(5);

    }

    private static void mostrarDatosPerso() {

        String cadena = "";
        cadena += Utilidades.formatoColumna("ID", 50);
        cadena += Utilidades.formatoColumna("Nombre de la Persona", 50);
        cadena += Utilidades.formatoColumna("Número de Departamento", 50);
        ArrayList<Persona> personas = new ArrayList<>();
        personas = db.obtenerPersonas();
        System.out.println("");
        System.out.println(cadena);
        for (Persona p : personas) {

            System.out.println(p);

        }
        System.out.println("");

    }

    public static void modificarPers() {
        
        int numAntiguo;
        Persona p = new Persona();
        String numDep;
        String nombreAntiguo = "";
        p.setNomPerso(Utilidades.leerCadena("Introduce el nombre de la persona a modificar: "));

        if (db.buscarPersona(p)) {

            nombreAntiguo = p.getNomPerso();
            p.setNomPerso(Utilidades.leerCadena("Introduce el nuevo nombre de la persona (INTRO PARA NO MODIFICAR): "));

            if (p.getNomPerso().equalsIgnoreCase("")) {

                System.out.println("Campo omitido para actualización.");
                p.setNomPerso(nombreAntiguo);
               

            } else {

                if (db.buscarPersona(p)) {

                    System.out.println("ERROR: YA EXISTE EL NOMBRE EN LA BASE DE DATOS.");

                }

            }

        }

        numDep = Utilidades.leerCadena("Introduce el nuevo número del departamento: ");
        
        if (numDep.equalsIgnoreCase("")) {
            
            System.out.println("Campo omitido para actualización.");
        } else {

            int dp;

            dp = Integer.parseInt(numDep);

            if (db.buscarDp(dp)) {

                p.setNumDep(dp);

            } else {
                System.out.println("No existe el departamento en la base de datos.");

            }

        }

        if (db.actualizarPersona(p, nombreAntiguo)) {
            System.out.println("Se actualizaron los campos.");

        }

    }
}

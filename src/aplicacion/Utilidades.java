package aplicacion;

/*
 Class Utilidades
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaime M. Botonero Morillo
 */
public class Utilidades {

    /**
     * Creado por: jbotonero Fecha: 11-mar-2005
     *
     * @param string
     * @return
     */
    public static double leerDoble(String Cadena) {
        // Lee un n√∫mero double mostrando una cadena que se pasa como par√°metro

        //	Creamos el objeto de entrada de datos de tipo BufferedReader
        BufferedReader Entrada = new BufferedReader(new InputStreamReader(System.in));

        double Numero = 0;
        boolean continuar = false;
        do {
            System.out.print(Cadena);
            try {
                Numero = Double.parseDouble(Entrada.readLine());
            } catch (NumberFormatException ex) {
                System.out.println("No has introducido un n√∫mero double correcto.");
                continuar = true;
                esperar(2);
            } catch (IOException ex) {
                System.out.println("Se ha producido un error de entrada/salida gen√©rico.");
                continuar = true;
                esperar(2);
            }
        } while (continuar);
        return Numero;
    }

    /**
     * Creado por: jbotonero Fecha: 11-mar-2005
     *
     * @param string
     * @return
     */
    public static float leerFloat(String Cadena) {
        // Lee un n√∫mero float mostrando una cadena que se pasa como par√°metro

        //	Creamos el objeto de entrada de datos de tipo BufferedReader
        BufferedReader Entrada = new BufferedReader(new InputStreamReader(System.in));

        Float Numero = 0.0F;
        boolean continuar = false;
        do {
            System.out.print(Cadena);
            try {
                Numero = Float.parseFloat(Entrada.readLine());
            } catch (NumberFormatException ex) {
                System.out.println("No has introducido un n√∫mero float correcto.");
                continuar = true;
                esperar(2);
            } catch (IOException ex) {
                System.out.println("Se ha producido un error de entrada/salida gen√©rico.");
                continuar = true;
                esperar(2);
            }
        } while (continuar);
        return Numero;
    }

    /**
     * Creado por: jbotonero Fecha: 11-mar-2005
     *
     * @param string
     * @return
     */
    public static int leerEntero(String Cadena) {
        // Lee un n√∫mero entero mostrando una cadena que se pasa como par√°metro

        //	Creamos el objeto de entrada de datos de tipo BufferedReader
        BufferedReader Entrada = new BufferedReader(new InputStreamReader(System.in));

        int Numero = 0;
        boolean continuar = false;
        do {
            System.out.print(Cadena);
            try {
                Numero = Integer.parseInt(Entrada.readLine());
            } catch (NumberFormatException ex) {
                System.out.println("No has introducido un n˙mero entero correcto.");
                continuar = true;
                esperar(2);
            } catch (IOException ex) {
                System.out.println("Se ha producido un error de entrada/salida gen√©rico.");
                continuar = true;
                esperar(2);
            }
        } while (continuar);
        return Numero;
    }

    /**
     * Creado por: jbotonero Fecha: 11-mar-2005
     *
     * @param string
     * @return
     */
    public static String leerDireccion(String cadena) {
        Scanner teclado = new Scanner(System.in);

        String pedirDato = "";
        boolean continuar = false;
        do {
            System.out.print(cadena);

            continuar = true;
            pedirDato = teclado.nextLine();

            if (!pedirDato.matches("([A-Za-z¡…Õ”⁄·ÈÌÛ˙0-9∫ /' '/]){0,20}")) {
                System.out.println("ERROR, NOMBRE INTRODUCIDO NO VALIDO");

                continuar = false;

            }

        } while (!continuar);

        return pedirDato;

    }

    public static String leerCadena(String cadena) {

        Scanner teclado = new Scanner(System.in);

        String pedirDato = "";
        boolean continuar;
        do {
            continuar = false;
            System.out.print(cadena);

            pedirDato = teclado.nextLine();

            if (!pedirDato.matches("([A-Za-z¡…Õ”⁄·ÈÌÛ˙0-9 /' '/]){0,50}")) {
                System.out.println("ERROR, NOMBRE INTRODUCIDO NO VALIDO");

                continuar = true;

            }

        } while (continuar);

        return pedirDato;
    }

    /**
     * Creado por: jbotonero Fecha: 11-mar-2005
     *
     * @param
     * @return
     */
    public static void borrarPantalla() {
        //Imprimimos 30 l√≠neas en blanco para "borrar" la pantalla.
        for (int i = 0; i < 29; i++) {
            System.out.println();
        }
    }

    /**
     * Creado por: jbotonero Fecha: 11-mar-2005
     *
     * @param int
     * @return
     */
    public static void esperar(int n) {

        //Espera un n√∫mero de segundos antes de continuar
        long Tiempo0, Tiempo1;

        Tiempo0 = System.currentTimeMillis();

        do {
            Tiempo1 = System.currentTimeMillis();
        } while ((Tiempo1 - Tiempo0) < (n * 1000));
    }

    public static int MCD(int a, int b) {
        int r;
        if (a < b) {
            r = a;
            a = b;
            b = r;
        }
        if (b == 0) {
            return a;
        }
        return MCD(b, a % b);
    }

    public static String formatoColumna(String texto, int largo) {
        int restaLargo = largo - texto.length();
        for (int indice = 0; indice < restaLargo; indice++) {
            texto = texto + " ";
        }
        return texto;
    }

}

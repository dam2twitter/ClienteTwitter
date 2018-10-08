package interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 *
 * @author gnord
 */
public class BufferLeerTeclado {

//leer cadenas
    public static String leerCadena() {
        String cadena = "";

//defino el objeto de stream de caracteres de entrada
        InputStreamReader v_str = new InputStreamReader(System.in);
//defino el buffer por donde se va a leer, el teclado (estandar)
        BufferedReader teclado = new BufferedReader(v_str);
        try {
            //aplico el metodo de leer una linea hasta que pulso el intro

            cadena = teclado.readLine();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(BufferLeerTeclado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cadena;
    }

    public static char leerCaracter() {
        char c = '\0';
        try {
            c = (char) System.in.read();
// limpiar el buffer del flujo de entrada
            System.in.skip(System.in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static short leerShort() {
        String cadena;
        short numero;
        cadena = leerCadena(); //leeemos una cadena
        try {
            numero = Short.parseShort(cadena);
            return numero;
        } catch (NumberFormatException e) {
            return Short.MIN_VALUE; // valor más pequeño
        }
    }

    public static int leerInt() {
        String cadena;
        int numero;
        cadena = leerCadena(); //leeemos una cadena
        try {
            numero = Integer.parseInt(cadena); //convertimos el string en int
            return numero;
        } catch (NumberFormatException ex) {
            System.out.println("error al introducir dato");
            return Integer.MIN_VALUE;
        }

    }

    public static long leerLong() {
        String cadena;
        long numero;
        cadena = leerCadena(); //leeemos una cadena
        try {
            numero = Long.parseLong(cadena);
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("error al introducir dato");
            return Long.MIN_VALUE; // valor más pequeño
        }
    }

    public static double leerDouble() {
        String cadena;
        double numero;
        cadena = leerCadena(); //leeemos una cadena
        try {
            numero = Double.parseDouble(cadena);
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("error al introducir dato");
            return Double.MIN_VALUE; // valor más pequeño
        }
    }

    public static float leerFloat() {
        String cadena;
        float numero;
        cadena = leerCadena(); //leeemos una cadena
        try {
            numero = Float.parseFloat(cadena);
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("error al introducir dato");
            return Float.MIN_VALUE; // valor más pequeño
        }
    }

    public static byte leerByte() {
        String cadena;
        byte numero;
        cadena = leerCadena(); //leeemos una cadena
        try {
            numero = Byte.parseByte(cadena);
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("error al introducir dato");
            return Byte.MIN_VALUE; // valor más pequeño
        }
    }

}

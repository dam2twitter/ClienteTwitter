package logica;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GestorCsv {

    // ATRIBUTOS
    private File fichero;
    private PrintWriter pw;
    private BufferedReader br;

    // METODOS
    // CONSTRUCTOR    
    /**
     * Crea un objeto de tipo FicheroDeTexto y asigna el parametro al atributo
     * fichero.
     *
     * @param fichero Con este fichero se abrira el flujo de transferencia de
     * caracteres para leer o grabar en el.
     */
    public GestorCsv(File fichero) {
        this.fichero = fichero;
    }

    /**
     * Abre el flujo para leer del fichero situando el puntero en la posicion
     * inicial. Devuelve un mensaje si el fichero no existe.
     */
    public void abrirLector() {
        try {
            br = new BufferedReader(new FileReader(fichero));
        } catch (FileNotFoundException ex) {
            System.out.println("El fichero no existe.");
        }
    }

    public void cerrarLector() {
        try {
            br.close();
        } catch (IOException ex) {
            System.out.println("No se ha podido cerrar el fichero.");
        }
    }

    /**
     * Lee una linea del fichero y la devuelve. Si llega al final de fichero o
     * tiene un error devuelve null y muestra un mensaje.
     *
     * @return El String que ha leido del fichero.
     */
    public String leerString() {
        String linea = "";
        try {
            linea = br.readLine();
            return linea;
        } catch (IOException ex) {
            System.out.println("Error al leer la linea del fichero.");
            return null;
        }
    }

    /**
     * Lee el contenido de un fichero CSV guardando en una coleccion de tipo
     * List el contenido de cada linea en el orden en el que lo va leyendo.
     *
     * @return de tipo LinkedList, que contiene un String con cada linea del
     * fichero en cada posicion.
     */
    public List guardarLineasCSVEnColeccion() {
        LinkedList coleccion = new LinkedList();
        abrirLector();
        String linea;
        while ((linea = leerString()) != null) {
            coleccion.add(linea);
        }
        cerrarLector();
        return coleccion;
    }

    public void abrirEscritor() {
        try {
            pw = new PrintWriter(new FileWriter(fichero, false), true);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero.");
        } catch (IOException ex) {
            System.out.println("No se pudo acceder al fichero.");
        }
    }

    public void cerrarEscritor() {
        pw.flush();
        pw.close();
    }

    public void println(String x) {
        pw.println(x);
        pw.flush();
    }
}

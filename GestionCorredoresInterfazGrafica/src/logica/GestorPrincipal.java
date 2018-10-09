/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Corredor;

/**
 *
 * @author Daniel Regueiro
 */
public class GestorPrincipal {

    private static GestorPrincipal gestor = null;
    private Map<String, Corredor> corredores;
    
    private GestorCsv gestorFichero;

    private GestorPrincipal() {
        corredores = new TreeMap();
        File archivo = new File("corredores.csv");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                System.out.println("no se puede crear el csv");
            }
        }
        gestorFichero = new GestorCsv(archivo);
    }

    private synchronized static void createInstance() {
        if (gestor == null) {
            gestor = new GestorPrincipal(); //creauna instance para llamar solo a un objeto de esta clase desde cualquier jdialog
        }
    }

    public static GestorPrincipal getInstance() {
        if (gestor == null) {
            createInstance();
        }
        return gestor;
    }

    /**
     * pasa de String en una coleccion a una coleccion Map de corredores con key
     * dni
     */
    public void volcarCSVaColeccion() {
        gestorFichero.abrirLector();
        List<String> datosCorredoresCsv = gestorFichero.guardarLineasCSVEnColeccion();
        gestorFichero.cerrarLector();
        Corredor runner = null;
        for (String linea : datosCorredoresCsv) {
            try {
                runner = tokenizar(linea);
            } catch (ParseException ex) {
                Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            corredores.put(runner.getDni(), runner);
        }
    }
   /**
     * Metodo que recorre la coleccion pasando cada corredor a una linea y lo
     * graba en un csv
     */
    public void grabarColeccionAcsv() {
        gestorFichero.abrirEscritor();
        for (Corredor corredores : corredores.values()) {
            gestorFichero.println(destokenitator(corredores));
        }
        gestorFichero.cerrarEscritor();
    }
    public Corredor tokenizar(String linea) throws ParseException {
        StringTokenizer tokenizador = new StringTokenizer(linea, ";");
        if (tokenizador.countTokens() < 5) {
            throw new IllegalArgumentException("No hay suficientes tokens para crear el corredor");
        }
        String nombre = tokenizador.nextToken().trim();
        String apellido = tokenizador.nextToken().trim();
        Date fecha = (new SimpleDateFormat("dd/MM/yyyy")).parse(tokenizador.nextToken().trim());
        String dni = tokenizador.nextToken().trim();
        String direccion = tokenizador.nextToken().trim();
        return new Corredor(nombre, apellido, fecha, dni, direccion);
    }

    /**
     * metodo para pasar un corredor a una linea para grabar en el csv
     *
     * @param c le pasamos un corredor
     * @return nos devuelve la linea
     */
    public String destokenitator(Corredor c) {
        String linea = null;
        linea = c.getNombre() + ";" + c.getApellidos() + ";"
                + (new SimpleDateFormat("dd/mm/yyyy")).format(c.getFechaNacimiento())
                + ";" + c.getDni() + ";" + c.getDireccion();
        return linea;
    }

    /**
     * Recorro la coleccion mostrando todos los corredores por consola
     * ,deprecated em interfaz grafica
     */
    public void visualizarTodos() {
        for (Corredor runner : corredores.values()) {
            System.out.println(runner.toString());
        }
    }

    /**
     * Añade un corredor a una coleccion
     *
     * @param nombre string
     * @param apellidos string
     * @param fechaNacimiento date
     * @param dni string
     * @param direccion string
     */
    public void anadirCorredor(String nombre, String apellidos, Date fechaNacimiento, String dni, String direccion) {
        Corredor c = new Corredor(nombre, apellidos, fechaNacimiento, dni, direccion);
        corredores.put(c.getDni(), c);
    }

    public Corredor buscarUnCorredorDni(String dni) {
        return corredores.get(dni);
    }

    public Corredor eleminarUnCorredor(String dni) {
        return corredores.remove(dni);
    }

    public Corredor modificarNombre(String dni, String nombre) {
        Corredor corredor = buscarUnCorredorDni(dni);
        corredor.setNombre(nombre);
        corredores.replace(dni, corredor);
        return corredor;
    }

    public Corredor modificarFecha(String dni, String nombre) throws ParseException {
        Corredor corredor = buscarUnCorredorDni(dni);
        corredor.setFechaNacimiento((new SimpleDateFormat("dd/mm/yyyy")).parse(nombre));
        corredores.replace(dni, corredor);
        return corredor;
    }

    public Corredor modificarApellido(String dni, String apellido) {
        Corredor corredor = buscarUnCorredorDni(dni);
        corredor.setApellidos(apellido);
        corredores.replace(dni, corredor);
        return corredor;
    }

    public Corredor modificarDni(String dni, String dniNuevo) {
        Corredor corredor = buscarUnCorredorDni(dni);
        corredor.setDni(dniNuevo);
        corredores.replace(dni, corredor);
        return corredor;
    }

    public Corredor modificarDireccion(String dni, String nombre) {
        Corredor corredor = buscarUnCorredorDni(dni);
        corredor.setDireccion(nombre);
        corredores.replace(dni, corredor);
        return corredor;
    }

    public List DevolverListaOrdenada() {
        List<Corredor> listaCorredoresOrdenados = devolverColeccion();
        Collections.sort(listaCorredoresOrdenados, new Comparator<Corredor>() {
            @Override
            public int compare(Corredor runner, Corredor runner2) {
                return runner.getFechaNacimiento().compareTo(runner2.getFechaNacimiento());
            }
        });
        return listaCorredoresOrdenados;
    }

    public List<Corredor> devolverColeccion() {
        return new ArrayList(corredores.values());
    }
}

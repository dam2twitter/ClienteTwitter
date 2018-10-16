/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import modelo.Carrera;
import modelo.Corredor;

/**
 *
 * @author Daniel Regueiro
 */
public class GestorPrincipal {

    private static GestorPrincipal gestor = null;
    private Map<String, Corredor> corredores;
    private Map<String, Carrera> carreras;
    private GestorCsv gestorFicheroCorredores;

    private GestorPrincipal() {
        corredores = new TreeMap();
        carreras = new TreeMap();
        File archivoCorredores = new File("corredores.csv");
        if (!archivoCorredores.exists()) {
            try {
                archivoCorredores.createNewFile();
            } catch (IOException ex) {
                System.out.println("no se puede crear el csv");
            }
        }
        gestorFicheroCorredores = new GestorCsv(archivoCorredores);
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

    public void volcarCsvCorredoresAColeccion() {
        gestorFicheroCorredores.abrirLector();
        List<String> datosCorredoresCsv = gestorFicheroCorredores.guardarLineasCSVEnColeccion();
        gestorFicheroCorredores.cerrarLector();
        Corredor runner = null;
        for (String linea : datosCorredoresCsv) {
            try {
                runner = tokenizarCorredores(linea);
            } catch (ParseException ex) {
                Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            corredores.put(runner.getDni(), runner);
        }
    }

    /**
     * graba una carrera en un dat ya existente
     *
     * @param carrera
     * @param archivo
     */
    public void grabar(Carrera carrera, File archivo) {

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(archivo, true);
            AddObject escritor = new AddObject(fos);
            escritor.writeObject(carrera);

        } catch (FileNotFoundException ex) {
            System.out.println("no se encuentra el archivo");
        } catch (IOException ex) {
            System.out.println("no se pudo grabar el archivo");
        }

    }

    /**
     * crea el archivo y graba la primera carrera
     *
     * @param carrera
     */
    public void crearArchivoYGrabarObjetos(File file, Carrera carrera) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream escritor = new ObjectOutputStream(fos);

            escritor.writeObject(carrera);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo, fallo en la clase crearArchivosYGrabarObjetos");
        } catch (IOException ex) {
            System.out.println("No se puede escribir el archivo, fallo en la clase crearArchivosYGrabarObjetos");
        }

    }

    /**
     * Metodo que recorre la coleccion pasando cada corredor a una linea y lo
     * graba en un csv
     */
    public void grabarColeccionCorredoresAcsv() {
        gestorFicheroCorredores.abrirEscritor();
        for (Corredor corredores : corredores.values()) {
            gestorFicheroCorredores.println(destokenizarCorredores(corredores));
        }
        gestorFicheroCorredores.cerrarEscritor();
    }

    public Corredor tokenizarCorredores(String linea) throws ParseException {
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
    public String destokenizarCorredores(Corredor c) {
        String linea = null;
        linea = c.getNombre() + ";" + c.getApellidos() + ";"
                + (new SimpleDateFormat("dd/mm/yyyy")).format(c.getFechaNacimiento())
                + ";" + c.getDni() + ";" + c.getDireccion();
        return linea;
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

    public void anadirCarrera(String nombre, String lugar, String identificador) {
        Carrera c = new Carrera(nombre, lugar, identificador);
        carreras.put(c.getIdentificador(), c);
    }

    public Corredor buscarUnCorredorDni(String dni) {
        return corredores.get(dni);
    }

    public Corredor eleminarUnCorredor(String dni) {
        return corredores.remove(dni);
    }

    public List DevolverListaOrdenada() {
        List<Corredor> listaCorredoresOrdenados = devolverColeccionCorredores();
        Collections.sort(listaCorredoresOrdenados, new Comparator<Corredor>() {
            @Override
            public int compare(Corredor runner, Corredor runner2) {
                return runner.getFechaNacimiento().compareTo(runner2.getFechaNacimiento());
            }
        });
        return listaCorredoresOrdenados;
    }

    public List<Corredor> devolverColeccionCorredores() {
        return new ArrayList(corredores.values());
    }
}

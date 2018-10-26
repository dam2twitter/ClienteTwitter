/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Carrera;
import modelo.Corredor;
import org.openide.util.Exceptions;

/**
 *
 * @author Daniel Regueiro
 */
public class GestorPrincipal {

    private static GestorPrincipal gestor = null;
    private Map<String, Corredor> corredores;
    private Map<String, Carrera> carreras;
    private GestorCsv gestorFicheroCorredores;
    private File fileCarreras;
    private FileInputStream lector;
    private ObjectInputStream obInputS;

    private GestorPrincipal() {
        corredores = new TreeMap();

        File archivoCorredores = new File("corredores.csv");
        fileCarreras = new File("carreras.dat");
        if (!fileCarreras.exists()) {
            carreras = new TreeMap();
        } else {
            try {
                lector = new FileInputStream(fileCarreras);
                obInputS = new ObjectInputStream(lector);
                carreras = (Map) obInputS.readObject();

            } catch (FileNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } catch (ClassNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

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
      {
            corredores.put(c.getDni(), c);
        
        }
    }

    public void anadirCarrera(String nombre, String lugar, String identificador, Date fecha) {
        Carrera c = new Carrera(nombre, lugar, identificador, fecha);
        carreras.put(c.getIdentificador(), c);
    }

    public List<Carrera> devolverColeccionCarreras() {
        return new ArrayList(carreras.values());
    }

    public void grabarArchivoCarreras() {
        CrearArchivoDatygrabarObjetos grabar = new CrearArchivoDatygrabarObjetos();
        grabar.crearArchivoYGrabarObjetos(fileCarreras, carreras);

    }

    public Corredor buscarUnCorredorDni(String dni) {
        return corredores.get(dni);
    }

    public Corredor eleminarUnCorredor(String dni) {
        return corredores.remove(dni);
    }

    public Carrera eleminarCarrera(String id) {
        return carreras.remove(id);
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

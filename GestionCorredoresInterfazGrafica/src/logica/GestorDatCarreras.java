/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.Carrera;
import org.openide.util.Exceptions;

/**
 *
 * @author daniel
 */
public class GestorDatCarreras {

    private File fileCarreras = new File("carreras.dat");
    private FileInputStream lector;
    private ObjectInputStream pantalla;
    private Map<String, Carrera> carreras;

    public GestorDatCarreras() {

        if (!fileCarreras.exists()) {
            try {
                fileCarreras.createNewFile();
                lector = new FileInputStream(fileCarreras);
                pantalla = new ObjectInputStream(lector);
            } catch (IOException ex) {
                System.out.println("no se puede crear el archivo de carreras");
            }

        }

    }

    public Map leerArchivoCarreras() {
            try {
                carreras = (Map) pantalla.readObject();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } catch (ClassNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
        return carreras;
    }

    public List<Carrera> devolverColeccionCarreras() {
        return new ArrayList(carreras.values());
    }

    public void grabarArchivoCarreras(Map mapCarreras) {
        CrearArchivoDatygrabarObjetos grabar = new CrearArchivoDatygrabarObjetos();
        grabar.crearArchivoYGrabarObjetos(fileCarreras, mapCarreras);

    }

}
